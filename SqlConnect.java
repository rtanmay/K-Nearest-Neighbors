import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SqlConnect {
    public static void main(String[] args) {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection conn = null;
            conn =
               DriverManager.getConnection("jdbc:mysql://localhost/database1?" +
                                           "user=root&password=root");
            
            PreparedStatement Statement = conn.prepareStatement("Select * from table1");
            ResultSet result = Statement.executeQuery();
            while(result.next()) {
                // Data product = new Data();
                // result.getDouble("att1");
                System.out.println(result.getDouble("att2"));
            }

        }catch (Exception ex) {
            // handle any errors
            System.out.println("SQLState: " );
        }
    }
}

