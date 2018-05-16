import java.sql.*;

public class FillTenMinAvgTimeStrategy {

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

            String startTime = "1300";
            while (!startTime.equals("0000")){
                long startMillis = System.currentTimeMillis();
                String endTime = CreateTablesTenMinsAvgTime.timeIncrement(startTime);
                String tableName = "avgtime" + startTime + endTime;
                System.out.println(tableName);
                String queryRawDataSQL = "SELECT " +
                        " START_STATION, END_STATION " +
                        "FROM " +
                        " `" + tableName +"` " +
                        "WHERE " +
                        " DURATION = 0";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(queryRawDataSQL);

                String updateSQL = "UPDATE `" + tableName + "` " +
                        "SET DURATION = ? WHERE START_STATION = ? AND END_STATION = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);

                //
                int count = 0;
                while (resultSet.next()){
                    count++;
                    String rawStartStation = resultSet.getString(1);
                    String rawEndStation = resultSet.getString(2);

                    //前三张表
                    String tempStartTime = startTime;
                    int avgDuration = 0;
                    for(int i = 0; i < 3; i++){
                        String tempEndTime = CreateTablesTenMinsAvgTime.timeDecrement(tempStartTime);
                        //边界判断
                        if(tempEndTime.equals("0050")){
                            break;
                        }
                        //
                        String tempTableName = "avgtime" + tempEndTime + tempStartTime;
//                        System.out.println(tempTableName);
                        String tempQuerySQL = "SELECT " +
                                " DURATION " +
                                "FROM  " +
                                " `" + tempTableName + "` " +
                                "WHERE START_STATION = ?  " +
                                "AND END_STATION = ?";
                        PreparedStatement tempStatement =
                                connection.prepareStatement(tempQuerySQL);
                        tempStatement.setString(1, rawStartStation);
                        tempStatement.setString(2, rawEndStation);
                        ResultSet tempResultSet = tempStatement.executeQuery();
                        if(tempResultSet.next()){
                            avgDuration = avg(avgDuration, tempResultSet.getInt(1));
                        }

                        tempStartTime = tempEndTime;
                    }

                    //后三张表
                    tempStartTime = startTime;
                    for(int i = 0; i < 3; i++){
                        String tempEndTime = CreateTablesTenMinsAvgTime.timeIncrement(tempStartTime);
                        //边界判定
                        if(tempEndTime.equals("0010")){
                            break;
                        }
                        String tempTableName = "avgtime" + tempStartTime + tempEndTime;
//                        System.out.println(tempTableName);
                        String tempQuerySQL = "SELECT " +
                                " DURATION " +
                                "FROM  " +
                                " `" + tempTableName + "` " +
                                "WHERE START_STATION = ?  " +
                                "AND END_STATION = ?";
                        PreparedStatement tempStatement =
                                connection.prepareStatement(tempQuerySQL);
                        tempStatement.setString(1, rawStartStation);
                        tempStatement.setString(2, rawEndStation);
                        ResultSet tempResultSet = tempStatement.executeQuery();
                        if(tempResultSet.next()){
                            avgDuration = avg(avgDuration, tempResultSet.getInt(1));
                        }
                        tempStartTime = tempEndTime;
                    }

                    preparedStatement.setInt(1, avgDuration);
                    preparedStatement.setString(2, rawStartStation);
                    preparedStatement.setString(3, rawEndStation);
                    preparedStatement.addBatch();
//                    System.out.println(avgDuration + " " + rawStartStation + " " + rawEndStation + " " + count);
                }

                preparedStatement.executeBatch();
                connection.commit();
                preparedStatement.close();
                //手动释放
                preparedStatement = null;

                System.out.println(startTime + "  front   time = " + (System.currentTimeMillis() - startMillis));

                startTime = CreateTablesTenMinsAvgTime.timeIncrement(startTime);
            }

            startTime = "0600";
            while (!startTime.equals("0000")){
                long startMillis = System.currentTimeMillis();
                String endTime = CreateTablesTenMinsAvgTime.timeIncrement(startTime);
                String tableName = "avgtime" + startTime + endTime;

                String queryRawDataSQL = "SELECT " +
                        " START_STATION, END_STATION " +
                        "FROM " +
                        " `" + tableName +"` " +
                        "WHERE " +
                        " DURATION = 0";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(queryRawDataSQL);

                String updateSQL = "UPDATE `" + tableName + "` " +
                        "SET DURATION = ? WHERE START_STATION = ? AND END_STATION = ?";
                PreparedStatement tempStatement = connection.prepareStatement(updateSQL);

                //
                while (resultSet.next()){

                    String rawStartStation = resultSet.getString(1);
                    String rawEndStation = resultSet.getString(2);

                    String queryFromDaySQL =  "SELECT " +
                            " DURATION " +
                            "FROM  " +
                            " `avgtime20170901` " +
                            "WHERE START_STATION = ?  " +
                            "AND END_STATION = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(queryFromDaySQL);
                    preparedStatement.setString(1, rawStartStation);
                    preparedStatement.setString(2, rawEndStation);
                    ResultSet tempResultSet = preparedStatement.executeQuery();

                    int duration = 0;
                    if(tempResultSet.next()){
                        duration = tempResultSet.getInt(1);
                    }
                    //考虑是否要把这一句变成批量更新

                    tempStatement.setInt(1, duration);
                    tempStatement.setString(2, rawStartStation);
                    tempStatement.setString(3, rawEndStation);
                    tempStatement.addBatch();
                }
                tempStatement.executeBatch();
                connection.commit();
                tempStatement.close();
                System.out.println(startTime + " behind  time = " + (System.currentTimeMillis() - startMillis));
                startTime = CreateTablesTenMinsAvgTime.timeIncrement(startTime);
            }
            connection.close();
        }catch (ClassNotFoundException|SQLException e){
            e.printStackTrace();
        }
    }

    public static int avg(int origin, int delta){
        if(origin == 0){
            return delta;
        }
        return (origin + delta) / 2;
    }
}
