import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountEveryODRecords {

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

            List<AvgTimeData.PrimaryKey> keyList = new ArrayList<>();
            String sql = "SELECT START_STATION, END_STATION FROM avgTime20170901";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                keyList.add(new AvgTimeData.PrimaryKey(
                        resultSet.getString(1), resultSet.getString(2)));
            }

            for(AvgTimeData.PrimaryKey key : keyList){
                String sqlQuery = "SELECT COUNT(TICKET_ID) FROM od20170901 WHERE START_STATION = '"
                        + key.getStartStation() + "' AND END_STATION = '" + key.getEndStation() + "'";
                ResultSet temp = statement.executeQuery(sqlQuery);
                if(temp.next()){
                    System.out.println("起点站：" + key.getStartStation()
                            + "  终点站: " + key.getEndStation()
                            +"  总共条目数：" + temp.getInt(1));
                }
            }
            connection.close();
        }catch (ClassNotFoundException|SQLException e){
            e.printStackTrace();
        }
    }
}
