import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class CreateODTables {

    public static void main(String[] args){
        String tableName = "OD201709";

        Connection connection;
        String driver = "com.mysql.jdbc.Driver";
        String url =
                "jdbc:mysql://localhost:3306/graduationproject?" +
                        "useUnicode=true&characterEncoding=utf-8&useSSL=false" +
                        "&rewriteBatchedStatements=true&useServerPrepStmts=true" +
                        "&useCursorFetch=true";
        String user = "root";
        String pass = "123456";

        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, pass);
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            for(int i = 0; i < 30; i++){
                System.out.println("create a table = " + i);
                String sql = "CREATE TABLE " + "`" + tableName + CsvFileInit.getNumbers(i+1) + "`" + "(" +
                        "  `TICKET_ID` varchar(100) DEFAULT NULL ," +
                        "  `TXN_DATE` varchar(100) default null ," +
                        "  `START_TIME` varchar(100) DEFAULT NULL," +
                        "  `END_TIME` varchar(100) DEFAULT NULL ," +
                        "  `START_STATION` varchar(100) DEFAULT NULL ," +
                        "  `END_STATION` varchar(100) DEFAULT NULL ," +
                        "  `START_ID` INT DEFAULT NULL ," +
                        "  `END_ID` INT  DEFAULT NULL " +
                        ") ENGINE=MyISAM CHARSET=utf8;";
                System.out.println(sql);
                statement.execute(sql);
            }
//            statement.executeBatch();
            connection.commit();
            statement.clearBatch();

            statement.close();
            connection.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
