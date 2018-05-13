import java.sql.*;

public class FillMonthAvgTimeStrategy {

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

            String rawDataSQL = "SELECT " +
                    " START_STATION, END_STATION, DURATION " +
                    "FROM " +
                    " `avgtime201709` " +
                    "WHERE " +
                    " DURATION = 0";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(rawDataSQL);
            int i = 0;
            while (resultSet.next()){
                i++;
                String rawStartStation = resultSet.getString(1);
                String rawEndStation = resultSet.getString(2);
                int duration = 0;

                //取出前一站的时间，和后一站的时间，平均
                //前一站
                String beforeRawEndStation = rawEndStation.substring(0, 2)
                        + String.valueOf(CsvFileInit.getNumbers(Integer.valueOf(rawEndStation.substring(2)) - 1));

                //后一站
                String afterRawEndStation = rawEndStation.substring(0, 2)
                        + String.valueOf(CsvFileInit.getNumbers(Integer.valueOf(rawEndStation.substring(2)) + 1));

                String queryAvgTimeDataSQL = "SELECT " +
                        " DURATION " +
                        "FROM  " +
                        " `avgtime201709` " +
                        "WHERE START_STATION = ?  " +
                        "AND END_STATION = ?;";
                //
                PreparedStatement tempStatement = connection.prepareStatement(queryAvgTimeDataSQL);
                tempStatement.setString(1, rawStartStation);
                tempStatement.setString(2, beforeRawEndStation);
                ResultSet tempDurationResultSet = tempStatement.executeQuery();
                int durationBefore = 0;
                if(tempDurationResultSet.next()){
                    durationBefore = tempDurationResultSet.getInt(1);
                }

                //
                tempStatement.setString(1, rawStartStation);
                tempStatement.setString(2, afterRawEndStation);
                int durationAfter = 0;
                tempDurationResultSet = tempStatement.executeQuery();
                if(tempDurationResultSet.next()){
                    durationAfter = tempDurationResultSet.getInt(1);
                }

                //犯了个致命错误，忘记加和减一站的时间了
                //后一站的单站时间
                int oneStationDurationAfter = 0;
                tempStatement.setString(1, rawEndStation);
                tempStatement.setString(2, afterRawEndStation);
                tempDurationResultSet = tempStatement.executeQuery();
                if(tempDurationResultSet.next()){
                    oneStationDurationAfter = tempDurationResultSet.getInt(1);
                }

                //前一站单站时间
                int oneStationDurationBefore = 0;
                tempStatement.setString(1, beforeRawEndStation);
                tempStatement.setString(2, rawEndStation);
                tempDurationResultSet = tempStatement.executeQuery();
                if(tempDurationResultSet.next()){
                    oneStationDurationBefore = tempDurationResultSet.getInt(1);
                }

                //如果其中一个为0的话，就不能随意除0了
                int avgDuration = 0;
                if(durationAfter == 0){
                    avgDuration = durationBefore + oneStationDurationBefore;
                }else if(durationBefore == 0){
                    avgDuration = durationAfter - oneStationDurationAfter;
                }else {
                    avgDuration = (durationAfter - oneStationDurationAfter +
                            durationBefore + oneStationDurationBefore) / 2;
                }



                //close
                tempDurationResultSet.close();
                tempStatement.close();

                //更新
                String updateAvgTimeSQL = "UPDATE graduationproject.`avgtime201709` " +
                        "SET DURATION = ? WHERE START_STATION = ? AND END_STATION = ?";
                tempStatement = connection.prepareStatement(updateAvgTimeSQL);
                tempStatement.setInt(1, avgDuration);
                tempStatement.setString(2, rawStartStation);
                tempStatement.setString(3, rawEndStation);
                tempStatement.execute();
                connection.commit();
                tempStatement.close();
                //
                System.out.println("更新了一条 起点站：" + rawStartStation + "  终点站：" + rawEndStation +
                "  计算出来的duration：" + avgDuration);
            }

            connection.close();
        }catch (ClassNotFoundException|SQLException e){
            e.printStackTrace();
        }
    }
}
