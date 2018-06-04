import java.sql.*;

public class FillDayAvgTimeStrategy {

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

            //注意循环条件
            for(int i = 1; i < 31; i ++){
//                if(i == 15 || i == 22 || i == 29){
//                    continue;
//                }
                String tableName = "avgtime201709" + CsvFileInit.getNumbers(i);
                String rawDataSQL = "SELECT " +
                        " START_STATION, END_STATION, DURATION " +
                        "FROM " +
                        " `" + tableName + "` " +
                        "WHERE " +
                        " DURATION = 0";

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(rawDataSQL);

                while (resultSet.next()){

                    String rawStartStation = resultSet.getString(1);
                    String rawEndStation = resultSet.getString(2);
                    int duration = 0;

                    //取出前一站的时间，和后一站的时间，平均


                    String queryAvgTimeDataSQL = "SELECT " +
                            " DURATION " +
                            "FROM  " +
                            " `avgtime201709` " +
                            "WHERE START_STATION = ?  " +
                            "AND END_STATION = ?;";
                    //
                    PreparedStatement tempStatement = connection.prepareStatement(queryAvgTimeDataSQL);
                    tempStatement.setString(1, rawStartStation);
                    tempStatement.setString(2, rawEndStation);
                    ResultSet tempDurationResultSet = tempStatement.executeQuery();
                    if(tempDurationResultSet.next()){
                        duration = tempDurationResultSet.getInt(1);
                    }

                    //更新
                    String updateAvgTimeSQL = "UPDATE `" + tableName +"` " +
                            "SET DURATION = ? WHERE START_STATION = ? AND END_STATION = ?";
                    tempStatement = connection.prepareStatement(updateAvgTimeSQL);
                    tempStatement.setInt(1, duration);
                    tempStatement.setString(2, rawStartStation);
                    tempStatement.setString(3, rawEndStation);
                    tempStatement.executeUpdate();
                    connection.commit();
                    tempStatement.close();
                    //
                    System.out.println("更新了一条 起点站：" + rawStartStation + "  终点站：" + rawEndStation +
                            "  计算出来的duration：" + duration);
                }
            }

            connection.close();
        }catch (ClassNotFoundException|SQLException e){
            e.printStackTrace();
        }
    }
}
