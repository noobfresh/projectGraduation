import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FastenCaculateAvgTime2 {

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

        //取出站点表
        List<String> idList = new ArrayList<>();
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new BufferedReader(new FileReader(
                    "C:\\Users\\PYF\\Desktop\\test\\STATION_ID.csv")));
            String[] nextLine = csvReader.readNext();
            while ((nextLine = csvReader.readNext()) != null){
                idList.add(nextLine[0]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                csvReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            csvReader = null;
        }

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
                avgTimeDatas.put(key, temp);
            }
            avgTime.close();
            System.out.println("完成读取平均旅程时间表");

            Map<AvgTimeData.PrimaryKey, AvgTimeData> up2TimeDatas = null;


            //注意三号线延长线问题
            for(int i = 0; i < 38; i++){

                //根据当前窗口大小，来给up2TimeDatas 构建中间表
                up2TimeDatas = new HashMap<>();
                for(int j = 0; j < idList.size() - (i+1); j++){
                    AvgTimeData.PrimaryKey key = new AvgTimeData.PrimaryKey(idList.get(j), idList.get(j + (i+1)));
                    up2TimeDatas.put(key, new AvgTimeData(key.getStartStation(), key.getEndStation(), 0));
                    AvgTimeData.PrimaryKey key2 = new AvgTimeData.PrimaryKey(idList.get(j + (i + 1)), idList.get(j));
                    up2TimeDatas.put(key2, new AvgTimeData(key2.getStartStation(), key2.getEndStation(), 0));
                }

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
                    if(FastenCaculateAvgTime.isTransIn3(startStation, endStation)){
                        continue;
                    }

                    double duration = CaculateAvgTime.date2Second(endTime) - CaculateAvgTime.date2Second(startTime);
                    AvgTimeData.PrimaryKey keyTemp = new AvgTimeData.PrimaryKey(startStation, endStation);
                    AvgTimeData data = up2TimeDatas.get(keyTemp);
                    data.setDuration(CaculateAvgTime.avgTime(data.getDuration(), duration));


//                    int delta = FastenCaculateAvgTime.direction(startStation, endStation)? 1 : -1;
//                    for(int j = 0; j < i + 1; j++){
//                        String resumeStartionStation = FastenCaculateAvgTime.stationIncrement(startStation, j * delta);
//                        //如果是反方向，那么 delta应该是-1
//                        String resumeEndStation = FastenCaculateAvgTime.stationIncrement(resumeStartionStation, delta);
//
//                        //可能存在负数
//                        double calDuration = duration - FastenCaculateAvgTime.subAvgTime(resumeStartionStation, resumeEndStation, startStation, endStation);
//                        AvgTimeData.PrimaryKey key = new AvgTimeData.PrimaryKey(resumeStartionStation, resumeEndStation);
//                        AvgTimeData data = avgTimeDatas.get(key);
//                        data.setDuration(CaculateAvgTime.avgTime(data.getDuration(), calDuration));
//                    }
                }
                queryResult.close();

                //计算完之后 再写入 平均时间表结构

                long endMills = System.currentTimeMillis();
                System.out.println((endMills - startMills) + "     " + (i + 1));
            }
            statement.close();
            connection.close();
        }catch (ClassNotFoundException|SQLException e){
            e.printStackTrace();
        }
    }
}
