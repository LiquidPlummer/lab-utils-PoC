package Example;

import Util.ConnectionUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ExampleTest {
    private final ExampleActivity exampleActivity = new ExampleActivity();
    private Connection conn;

    @Before
    public void setup() {
        try {
            conn = ConnectionUtil.getConnection();

            String createTable = "CREATE TABLE employees (" +
                    "id SERIAL PRIMARY KEY," +
                    "first_name VARCHAR(255)," +
                    "last_name VARCHAR(255)," +
                    "salary DOUBLE PRECISION" +
                    ");";
            PreparedStatement createTableStatement = conn.prepareStatement(createTable);
            createTableStatement.executeUpdate();

            String insertData = "INSERT INTO employees (first_name, last_name, salary) VALUES" +
                    "('Steve', 'Garcia', 67400.00)," +
                    "('Alexa', 'Smith', 42500.00)," +
                    "('Steve', 'Jones', 99890.99)," +
                    "('Brandon', 'Smith', 120000)," +
                    "('Adam', 'Jones', 55050.50);";
            PreparedStatement insertDataStatement = conn.prepareStatement(insertData);
            insertDataStatement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @After
    public void cleanup() {
        try {
            conn = ConnectionUtil.getConnection();

            String dropTable = "DROP TABLE IF EXISTS employees";
            PreparedStatement createTableStatement = conn.prepareStatement(dropTable);
            createTableStatement.executeUpdate();

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testActivityProblem1() {
        Set<ExampleEntity> expected = new HashSet<>();
        expected.add(new ExampleEntity(1, "Steve", "Garcia", 67400.00));
        expected.add(new ExampleEntity(2, "Alexa", "Smith", 42500.00));
        expected.add(new ExampleEntity(3, "Steve", "Jones", 99890.99));
        expected.add(new ExampleEntity(4, "Brandon", "Smith", 120000.00));
        expected.add(new ExampleEntity(5, "Adam", "Jones", 55050.50));
        Set<ExampleEntity> results = exampleActivity.exampleActivityProblem1();

        Assert.assertEquals(expected, results);
    }
}
