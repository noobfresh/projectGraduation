import org.omg.CORBA.INTERNAL;
import sun.dc.pr.PRError;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class TravelRecordToInsertData {

    static Map<AvgTimeData.PrimaryKey, AvgTimeData> data = new HashMap<>();

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

            String tableName = null;
            String startTime = "0600";
            while (!startTime.equals("0000")){
                String endTime = CreateTablesTenMinsAvgTime.timeIncrement(startTime);
                tableName = "avgTime" + startTime + endTime;
                int intStartTime = Integer.valueOf(startTime) * 100;
                int intEndTime = Integer.valueOf(endTime) * 100;
                //特殊处理下
                if(intEndTime == 0){
                    intEndTime = 240000;
                }
                String sql = "SELECT " +
                        " TICKET_ID, " +
                        " START_TIME, " +
                        " END_TIME, " +
                        " START_STATION, " +
                        " END_STATION " +
                        "FROM " +
                        " od20170901 " +
                        "WHERE " +
                        " CAST(START_TIME AS SIGNED INTEGER)  >= "+ intStartTime +" " +
                        "AND  " +
                        " CAST(START_TIME AS SIGNED INTEGER) < " + intEndTime;

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                int count = 0;
                //将该时间段内的所有OD取出
                while (resultSet.next()){
                    count++;
                    String resultStartTime = resultSet.getString(2);
                    String resultEndTime = resultSet.getString(3);
                    String resultStartStation = resultSet.getString(4);
                    String resultEndStation = resultSet.getString(5);

                    double duration = CaculateAvgTime.date2Second(resultEndTime) - CaculateAvgTime.date2Second(resultStartTime);
                    AvgTimeData.PrimaryKey key = new AvgTimeData.PrimaryKey(resultStartStation, resultEndStation);
                    //是否包含该key
                    if(!data.containsKey(key)){
                        data.put(key,
                                new AvgTimeData(resultStartStation, resultEndStation, duration));

                    }else {
                        AvgTimeData temp = data.get(key);
                        temp.setDuration(CaculateAvgTime.avgTime(temp.getDuration(), duration));
                    }
                }
                resultSet.close();
                statement.close();

                //计算完之后
                String updateSQL = "UPDATE " + tableName
                        + " SET DURATION = ? WHERE START_STATION = ? AND END_STATION = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
                for(AvgTimeData avgTimeData : data.values()){
                    preparedStatement.setInt(1, (int) avgTimeData.getDuration());
                    preparedStatement.setString(2, avgTimeData.getStartStation());
                    preparedStatement.setString(3, avgTimeData.getEndStation());
                    preparedStatement.addBatch();
                }

                preparedStatement.executeBatch();
                connection.commit();
                preparedStatement.close();

                //更新完之后
                data.clear();
                System.out.println(startTime + "~" + endTime + "   总共： " + count);
                startTime = endTime;
            }

            //大错误！！ 更新时候还是必须取出原有数据进行一个求均，不对，不用，每一次循环都是插入一张新的表

            connection.close();
        }catch (ClassNotFoundException|SQLException e){
            e.printStackTrace();
        }
    }
}
