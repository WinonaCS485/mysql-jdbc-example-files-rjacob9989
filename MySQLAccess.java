import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class MySQLAccess {
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public void readDataBase() throws Exception {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the test DataBase - EVERYONE HAS ACCESS, PLEASE BE CAREFUL!!
            // Obviously, if you were distributing this file, you would not include the username and password. There are other ways...
            connect = DriverManager.getConnection("jdbc:mysql://mrbartucz.com/av6352tk?user=av6352tk&password=Buddha414!");

            // if you want to connect to your local machine:
            // connect = DriverManager.getConnection("jdbc:mysql://localhost/MyDatabaseName?user=MyUserName&password=MyPassword");

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("select * from  av6352tk_University.Students");
            writeResultSet(resultSet);

        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }

    }

    private void writeResultSet(ResultSet resultSet) throws SQLException {
        // ResultSet is initially before the first data set
        while (resultSet.next()) {
            // It is possible to get the columns via name
            // also possible to get the columns via the column number
            // which starts at 1
            // e.g. resultSet.getSTring(2);
            String studentID = resultSet.getString("StudentID");
            String firstName = resultSet.getString("Name");
            String phone = resultSet.getString("Phone Number");
            //String lastName = resultSet.getString("LastName");
            //String age = resultSet.getString("Age");
            System.out.println("StudentID: " + studentID);
            System.out.println("Name: " + firstName);
            System.out.println("Phone Number: " + phone);
            //System.out.println("Last Name: " + lastName);
            //System.out.println("Age: " + age);
        }
    }

    // You need to close the resultSet
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }

}
