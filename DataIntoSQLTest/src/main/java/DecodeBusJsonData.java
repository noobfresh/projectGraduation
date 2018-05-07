
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Map;

public class DecodeBusJsonData {

    //早高峰时间 7 - 9
    public static final String BUS_PERIOD_MORNING = "morning";

    //晚高峰时间 17 - 19
    public static final String BUS_PERIOD_EVENING = "evening";

    //平峰时间
    public static final String BUS_PERIOD_OTHER = "other";

    public static void main(String[] args){
        String path = "C:\\Users\\PYF\\Desktop\\test\\busdata\\";

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

            //从json中读取数据
            for(int i = 0; i < 27; i++){
                //构建插入的sql
                String date = "05" + CsvFileInit.getNumbers(i+5);
                String sql = "INSERT INTO busdata" + date
                        + " VALUES(?,?,?,?,?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                File file = new File(path + (i + 1) + ".txt");
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream(file)));
                StringBuilder buff = new StringBuilder("");
                String temp = reader.readLine();
                while (temp != null){
                    buff.append(temp);
                    temp = reader.readLine();
                }
                String busData = buff.toString();
                JSONObject jsonObject = JSON.parseObject(busData);
                //每一份文件的所有文件的数据
                for(Map.Entry<String, Object> entry : jsonObject.entrySet()){
                    String key = entry.getKey();
                    if(key.equals("_id")){
                        continue;
                    }
                    if(key.equals("date")){
                        //更新表内日期
                        continue;
                    }
                    JSONObject busDetail = (JSONObject) entry.getValue();
                    String busLineNo = "";
                    //每辆车一天的所有数据
                    for(Map.Entry<String, Object> entryTemp : busDetail.entrySet()){
                        String keyTemp = entryTemp.getKey();
                        if(keyTemp.equals("LINE_NO")){
                            busLineNo = String.valueOf(entryTemp.getValue());
                            continue;
                        }
                        String period = period2Flag(keyTemp);
                        JSONObject stationDetail = (JSONObject) entryTemp.getValue();
                        //busDetail内的所有数据
                        for(Map.Entry<String, Object> entryStation : stationDetail.entrySet()){
                            if(entryStation.getKey().equals("num")){
                                continue;
                            }
                            //真实的每个站点数据
                            String trueData = String.valueOf(entryStation.getValue());
                            //解构数据
                            String[] stationsAndOther = trueData.split(" ");
                            String[] stations = stationsAndOther[0].split("-");
                            String startStation = stations[0];
                            String endStation = stations[1];
                            String direction = stations[2];
                            int duration = Integer.valueOf(stationsAndOther[1]);
                            //填数据
                            preparedStatement.setString(1, busLineNo);
                            preparedStatement.setString(2, date);
                            preparedStatement.setString(3, startStation);
                            preparedStatement.setString(4, endStation);
                            preparedStatement.setString(5, direction);
                            preparedStatement.setInt(6, duration);
                            preparedStatement.setString(7, period);

                            preparedStatement.addBatch();
                        }
                        //每一条线，提交一次
                        preparedStatement.executeBatch();
                        connection.commit();
//                        System.out.println(busLineNo);
                    }
                }
                preparedStatement.close();
            }
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String period2Flag(String period){
        //可以换成switch
        String flag = "";
        switch (period){
            case "mor" :
                flag = BUS_PERIOD_MORNING;
                break;
            case "eve" :
                flag = BUS_PERIOD_EVENING;
                break;
            case "other" :
                flag = BUS_PERIOD_OTHER;
        }
        return flag;
    }
}
