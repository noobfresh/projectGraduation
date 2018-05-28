import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FastenCaculateAvgTime {

    private static Map<AvgTimeData.PrimaryKey, AvgTimeData> avgTimeDatas = new HashMap<>();

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

            //取出平均时间表结构
            String sqlQueryAvgTable = "SELECT START_STATION, END_STATION, DURATION FROM avgTime20170901";
            Statement statement = connection.createStatement();
            ResultSet avgTime = statement.executeQuery(sqlQueryAvgTable);

            while (avgTime.next()) {
                AvgTimeData temp = new AvgTimeData();
                temp.setStartStation(avgTime.getString(1));
                temp.setEndStation(avgTime.getString(2));
                temp.setDuration(avgTime.getInt(3));
                AvgTimeData.PrimaryKey key = new AvgTimeData.PrimaryKey
                        (avgTime.getString(1), avgTime.getString(2));
//                System.out.println(key.toString());
                avgTimeDatas.put(key, temp);
            }
            avgTime.close();
            System.out.println("完成读取平均旅程时间表");


            //注意三号线延长线问题
            for(int i = 0; i < 38; i++){
                long startMills = System.currentTimeMillis();
                String sql = "SELECT " +
                        " TICKET_ID, " +
                        " START_TIME, " +
                        " END_TIME, " +
                        " START_STATION, " +
                        " END_STATION " +
                        "FROM " +
                        " od20170901 " +
                        "WHERE " +
                        " ABS( " +
                        "  CAST( " +
                        "   START_STATION AS SIGNED INTEGER " +
                        "  ) - CAST(END_STATION AS SIGNED INTEGER) " +
                        " ) = " + (i+1);

                ResultSet queryResult = statement.executeQuery(sql);
                while (queryResult.next()){
                    String startStation = queryResult.getString(4);
                    String endStation = queryResult.getString(5);
                    String startTime = queryResult.getString(2);
                    String endTime = queryResult.getString(3);

                    //排除换乘三号线延长线
                    if(isTransIn3(startStation, endStation)){
                        continue;
                    }

                    double duration = CaculateAvgTime.date2Second(endTime) - CaculateAvgTime.date2Second(startTime);
                    int delta = direction(startStation, endStation)? 1 : -1;
                    for(int j = 0; j < i + 1; j++){
                        String resumeStartionStation = stationIncrement(startStation, j * delta);
                        //如果是反方向，那么 delta应该是-1
                        String resumeEndStation = stationIncrement(resumeStartionStation, delta);

                        //可能存在负数
                        double calDuration = duration - subAvgTime(resumeStartionStation, resumeEndStation, startStation, endStation);
                        AvgTimeData.PrimaryKey key = new AvgTimeData.PrimaryKey(resumeStartionStation, resumeEndStation);
                        AvgTimeData data = avgTimeDatas.get(key);
                        data.setDuration(CaculateAvgTime.avgTime(data.getDuration(), calDuration));
                    }


                }
                queryResult.close();


//                for(AvgTimeData temp : avgTimeDatas.values()){
//                    System.out.println(temp.toString());
//                }
                //更新一波
                String updateSql = "UPDATE avgTime20170901 SET DURATION = ? " +
                        "WHERE START_STATION = ? AND END_STATION = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
                for(AvgTimeData temp : avgTimeDatas.values()){
                    preparedStatement.setInt(1, (int)temp.getDuration());
                    preparedStatement.setString(2, temp.getStartStation());
                    preparedStatement.setString(3, temp.getEndStation());
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
                connection.commit();
                preparedStatement.close();
                long endMills = System.currentTimeMillis();
                System.out.println((endMills - startMills) + "     " + (i + 1));
            }
            statement.close();
            connection.close();
        }catch (ClassNotFoundException|SQLException e){
            e.printStackTrace();
        }
    }


    /**
     * 判断是否涉及转乘3号线延长线
     * @param startStation
     * @param endStation
     * @return
     */
    public static boolean isTransIn3(String startStation, String endStation){

        int start = Integer.valueOf(startStation);
        int end = Integer.valueOf(endStation);

        //非三号线排除
        if(start < 301 || start > 345){
            return false;
        }
        //非三号线排除
        if (end < 301 || end > 345){
            return false;
        }
        //三号线主线排除
        if(start < 340 && end < 340){
            return false;
        }
        //三号线延长线排除
        if(start > end && start >= 338 && start != 339 && end > 339){
            return false;
        }
        //三号线延长线排除
        if(start < end && end >= 338 && end != 339 && start > 339){
            return false;
        }
        return true;
    }

    public static String stationIncrement(String station, int delta){
        int time = Integer.valueOf(station) + delta;
        if(time < 1000){
            return "0" + time;
        }
        return String.valueOf(time);
    }


    /**
     * 判断列车行驶方向
     * @param startStation
     * @param endStation
     * @return
     */
    public static boolean direction(String startStation, String endStation){
        return Integer.valueOf(startStation) - Integer.valueOf(endStation) < 0;
    }

    /**+
     * 当OD起始大于等于2时，需要减去其中的部分，用另一部分计算平均值
     * @param resumeStartStation
     * @param resumeEndStation
     * @param startStation
     * @param endStation
     * @return
     */
    public static double subAvgTime(String resumeStartStation, String resumeEndStation,
                                    String startStation, String endStation){
        double sumAvgTime = 0.0;

        int start = Integer.valueOf(startStation);
        int end = Integer.valueOf(endStation);

        //暴露问题，如果end > start 那么delta 应该是-1

        int delta = direction(startStation, endStation)? 1 : -1;
        for(int i = 0; i < Math.abs(end - start); i++){
            String calStart = stationIncrement(startStation, i * delta);
            String calEnd = stationIncrement(startStation, i*delta + delta);
            if(calStart.equals(resumeStartStation)
                    && calEnd.equals(resumeEndStation)){
                continue;
            }
            AvgTimeData.PrimaryKey key = new AvgTimeData.PrimaryKey(calStart, calEnd);
            sumAvgTime += avgTimeDatas.get(key).getDuration();
        }
//        System.out.println(sumAvgTime);
        return sumAvgTime;
    }
}
