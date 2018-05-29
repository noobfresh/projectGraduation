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

            for(int index = 8; index < 30; index += 7){
                String startTime = "0600" +
                        "";
                // 有一个致命的问题没有解决：当前思路是每次先扫描一张表中时间空缺的记录，
                // 然后选取该表前三张和后三张表同一位置的记录，然后做平均，然后更新进表。
                // 问题在于：前一张表通过上述思路更新的记录会影响后面的表在相同位置但是时间空缺的记录。（有必要论证一波）
                // 解决思路：暂时没想到，没想到好的（有感觉其实就算出现上面的情况也无所谓）
                while (!startTime.equals("0000")){
                    long startMillis = System.currentTimeMillis();
                    String endTime = CreateTablesTenMinsAvgTime.timeIncrement(startTime);
                    //
                    String tableName = "avgtime201709" + CsvFileInit.getNumbers(index) + startTime + endTime;
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
                        Statement tempStatement = connection.createStatement();
                        for(int i = 0; i < 3; i++){
                            String tempEndTime = CreateTablesTenMinsAvgTime.timeDecrement(tempStartTime);
                            //边界判断
                            if(tempEndTime.equals("0050")){
                                break;
                            }
                            if(tempEndTime.equals("0610")){
                                break;
                            }
                            //
                            String tempTableName = "avgtime201709" + CsvFileInit.getNumbers(index) + tempEndTime + tempStartTime;
                            String tempQuerySQL = "SELECT " +
                                    " DURATION " +
                                    "FROM  " +
                                    " `" + tempTableName + "` " +
                                    "WHERE START_STATION = '" + rawStartStation + "'  " +
                                    "AND END_STATION = '" +  rawEndStation + "'";

                            ResultSet tempResultSet = tempStatement.executeQuery(tempQuerySQL);
                            if(tempResultSet.next()){
                                avgDuration = avg(avgDuration, tempResultSet.getInt(1));
                            }
                            tempResultSet.close();
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
                            String tempTableName = "avgtime201709" + CsvFileInit.getNumbers(index) + tempStartTime + tempEndTime;
//                        System.out.println(tempTableName);
                            String tempQuerySQL = "SELECT " +
                                    " DURATION " +
                                    "FROM  " +
                                    " `" + tempTableName + "` " +
                                    "WHERE START_STATION = '" + rawStartStation + "'  " +
                                    "AND END_STATION = '" +  rawEndStation + "'";
                            ResultSet tempResultSet = tempStatement.executeQuery(tempQuerySQL);
                            if(tempResultSet.next()){
                                avgDuration = avg(avgDuration, tempResultSet.getInt(1));
                            }
                            tempResultSet.close();
                            tempStartTime = tempEndTime;
                        }
                        tempStatement.close();
                        if(avgDuration > 0 && avgDuration < 10){
                            System.out.println(avgDuration);
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
                    //注意
                    String tableName = "avgtime201709" + CsvFileInit.getNumbers(index) + startTime + endTime;

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
                    Statement statement1 = connection.createStatement();
                    while (resultSet.next()){

                        String rawStartStation = resultSet.getString(1);
                        String rawEndStation = resultSet.getString(2);

                        //
                        String queryFromDaySQL =  "SELECT " +
                                " DURATION " +
                                "FROM  " +
                                " `avgtime201709" + CsvFileInit.getNumbers(index) +"` " +
                                "WHERE START_STATION = '" + rawStartStation +"'  " +
                                "AND END_STATION = '" + rawEndStation + "'";
                        ResultSet tempResultSet = statement1.executeQuery(queryFromDaySQL);

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
        //防止delta为0
        if(delta == 0){
            return origin;
        }
        return (origin + delta) / 2;
    }
}
