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
                
                int avgDuration = (durationAfter + durationBefore) / 2;

                //close
                tempDurationResultSet.close();
                tempStatement.close();

                //更新
                String updateAvgTimeSQL = "UPDATE `avgtime201709` " +
                        "SET DURATION = ?";
                tempStatement = connection.prepareStatement(updateAvgTimeSQL);
                tempStatement.setInt(1, avgDuration);
                tempStatement.executeUpdate();
                //
                System.out.println("更新了一条 " + i);
            }

            connection.close();
        }catch (ClassNotFoundException|SQLException e){
            e.printStackTrace();
        }
    }
}
