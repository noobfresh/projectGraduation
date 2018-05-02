import com.opencsv.CSVReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteWrongOD3 {

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

            System.out.println("test");
            for(int j = 0; j < 30; j++){
                String tableName = "od201709" + CsvFileInit.getNumbers(j+1);
                String sql = 
                        "DELETE " +
                        "FROM " +
                        " " + tableName +" " +
                        "WHERE " +
                        " (START_STATION = '0102' AND END_STATION = '0606') " +
                        "OR (START_STATION = '0606' AND END_STATION = '0102') " +
                        "OR (START_STATION = '5601' AND END_STATION = '0620') " +
                        "OR (START_STATION = '0620' AND END_STATION = '5601') " +
                        "OR (START_STATION = '0612' AND END_STATION = '0322') " +
                        "OR (START_STATION = '0322' AND END_STATION = '0612') " +
                        "OR (START_STATION = '0103' AND END_STATION = '0201') " +
                        "OR (START_STATION = '0201' AND END_STATION = '0103') " +
                        "OR (START_STATION = '0105' AND END_STATION = '0318') " +
                        "OR (START_STATION = '0318' AND END_STATION = '0105') " +
                        "OR (START_STATION = '0107' AND END_STATION = '0209') " +
                        "OR (START_STATION = '0209' AND END_STATION = '0107') " +
                        "OR (START_STATION = '0301' AND END_STATION = '0225') " +
                        "OR (START_STATION = '0225' AND END_STATION = '0301') " +
                        "OR (START_STATION = '0319' AND END_STATION = '0206') " +
                        "OR (START_STATION = '0206' AND END_STATION = '0319')";
                System.out.println(sql);
                Statement statement = connection.createStatement();
                long startTime = System.currentTimeMillis();
                statement.execute(sql);
                System.out.println((System.currentTimeMillis() - startTime) + "   " + j);
                statement.close();
            }
            connection.close();
        }catch (ClassNotFoundException|SQLException e){
            e.printStackTrace();
        }
    }
}
