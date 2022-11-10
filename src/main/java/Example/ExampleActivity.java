package Example;

import Util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
/**
 * This is an example activity, a simple version of the lab activities we will have as part of PEP
 */
public class ExampleActivity {
    /**
     * Consider the following "employees" table which contains several employees and their salaries.
     *   |  id  |   first_name   |   last_name   |  salary  |
     *   --------------------------------------------------
     *   |1     |'Steve'         |'Garcia'       |67400.00  |
     *   |2     |'Alexa'         |'Smith'        |42500.00  |
     *   |3     |'Steve'         |'Jones'        |99890.99  |
     *   |4     |'Brandon'       |'Smith'        |120000    |
     *   |5     |'Adam'          |'Jones'        |55050.50  |
     */


    public Set<ExampleEntity> exampleActivityProblem1(){
        /**
         * Select everything from this table
         */
        //Write your statement below:
        String sql = "SELECT * FROM employees";


        //The following code will execute your statement on the database
        Set<ExampleEntity> results = new HashSet<>();
        try {
            Connection conn = ConnectionUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                results.add(new ExampleEntity(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4)));
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return results;

    }
}
