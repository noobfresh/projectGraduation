import com.opencsv.CSVReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteNonUseAvgtime {

    public static void main(String[] args){
        Connection connection;
        String driver = "com.mysql.jdbc.Driver";
        String url =
                "jdbc:mysql://localhost:3306/graduationproject?" +
                        "useUnicode=true&characterEncoding=utf-8&useSSL=false" +
                        "&rewriteBatchedStatements=true&useServerPrepStmts=true";
        String user = "root";
        String pass = "123456";
        CSVReader csvReader = null;
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, pass);
            connection.setAutoCommit(false);

            //
            Statement statement = connection.createStatement();
            for(int i = 11; i < 30; i++){
                if(i == 15 || i == 22 || i == 29){
                    continue;
                }
                String tableName = "avgtime201709" + i;
                System.out.println(tableName);
                String sql = "DELETE " +
                        "FROM " +
                        " "+ tableName +" " +
                        "WHERE " +
                        " (START_STATION, END_STATION) NOT IN ( " +
                        "  SELECT " +
                        "   START_STATION, " +
                        "   END_STATION " +
                        "  FROM " +
                        "   avgtime06000610 " +
                        " );";
                statement.execute(sql);
                connection.commit();
            }
            connection.close();
        }catch (ClassNotFoundException|SQLException e){
            e.printStackTrace();
        }
    }
}
