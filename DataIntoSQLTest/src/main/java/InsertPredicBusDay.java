import com.opencsv.CSVReader;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class InsertPredicBusDay {

    public static void main(String arg[]){
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


            String date = "20170922";
            String basePath = "F:\\github\\predict_data\\轨交1天\\avgtime.csv";
            CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(basePath),
                    "gbk"));
            List<String> startStations = new ArrayList<>();
            List<String> endStations = new ArrayList<>();
            String[] data = reader.readNext();
            while ((data = reader.readNext()) != null){
                startStations.add(data[0]);
                endStations.add(data[1]);
            }


            String startTime = "0600";
            int count = 0;
            while (!startTime.equals("0000")){
                count++;
                String endTime = CreateTablesTenMinsAvgTime.timeIncrement(startTime);
                String tableName = "predict_avgtime_" + startTime + endTime;
                String sql = "INSERT INTO " + tableName + " VALUES(?,?,?,?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);


                String path = "F:\\github\\predict_data\\轨交1天\\轨交" + count + "号.csv";
                CSVReader csvReader = new CSVReader(new InputStreamReader(new FileInputStream(path),
                        "gbk"));
                String[] durations = csvReader.readNext();
                int i = 0;
                while ((durations = csvReader.readNext()) != null) {
//                    for(String temp : durations){
//                        System.out.println(temp);
//                    }
                    preparedStatement.setString(1, startStations.get(i));
                    preparedStatement.setString(2, endStations.get(i));
                    float temp2 = Float.valueOf(durations[3]);
                    preparedStatement.setInt(3, (int)temp2);
                    preparedStatement.setString(4, startTime + endTime);
                    float temp = Float.valueOf(durations[4]);
                    preparedStatement.setInt(5, (int) temp);
                    preparedStatement.setString(6, date);
                    preparedStatement.addBatch();
                    i++;
                }

                preparedStatement.executeBatch();
                connection.commit();
                preparedStatement.close();
                System.out.println(count);
                startTime = endTime;
            }

            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
