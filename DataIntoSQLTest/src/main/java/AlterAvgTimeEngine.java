import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AlterAvgTimeEngine {

    public static void main(String[] args){
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

            String tableName = "";

            Statement statement = connection.createStatement();
            String startTime = "0600";
            while (!startTime.equals("0000")){
                String endTime = CreateTablesTenMinsAvgTime.timeIncrement(startTime);
                tableName = "avgTime" + startTime + endTime;
                String sql = "ALTER TABLE " + tableName +" ENGINE = MyISAM";
                statement.execute(sql);
                System.out.println(startTime);
                startTime = endTime;
            }

            connection.close();
        }catch (ClassNotFoundException|SQLException e){
            e.printStackTrace();
        }
    }
}
