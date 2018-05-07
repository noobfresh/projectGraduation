import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateBusDataTables {

    public static void main(String[] atrgs){

        Connection connection;
        String driver = "com.mysql.jdbc.Driver";
        String url =
                "jdbc:mysql://localhost:3306/graduationproject?" +
                        "useUnicode=true&characterEncoding=utf-8&useSSL=false" +
                        "&rewriteBatchedStatements=true&useServerPrepStmts=true";
        String user = "root";
        String pass = "123456";

        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, pass);
            connection.setAutoCommit(false);

            Statement statement = connection.createStatement();
            for(int i = 4; i < 31; i++){
                String tableName = "busdata05" + CsvFileInit.getNumbers(i+1);
                String createSQL = "CREATE TABLE `" + tableName +"` ( " +
                        "  `line_no` varchar(10) DEFAULT NULL, " +
                        "  `date` varchar(10) DEFAULT NULL, " +
                        "  `start_station` varchar(20) DEFAULT NULL, " +
                        "  `end_station` varchar(20) DEFAULT NULL, " +
                        "  `direction` varchar(5) DEFAULT NULL, " +
                        "  `duration` int(11) DEFAULT NULL, " +
                        "  `period` varchar(20) DEFAULT NULL " +
                        ") ENGINE=InnoDB DEFAULT CHARSET=utf8";
                System.out.println(createSQL);
                statement.execute(createSQL);

            }
            statement.close();
            connection.close();
        }catch (ClassNotFoundException|SQLException e){
            e.printStackTrace();
        }
    }
}
