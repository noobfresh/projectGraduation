import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InsertDataToAvgTimeTables {

    public static void main(String[] args){
        Connection connection;
        String driver = "com.mysql.jdbc.Driver";
        String url =
                "jdbc:mysql://localhost:3306/graduationproject?" +
                        "useUnicode=true&characterEncoding=utf-8&useSSL=false" +
                        "&rewriteBatchedStatements=true&useServerPrepStmts=true";
        String user = "root";
        String pass = "123456";
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
        System.out.println(idList.size());
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, pass);
            connection.setAutoCommit(false);

            //之后做一个日期计算，这样就不用重复直接拿个循环来做
//            addTableName("20170901", connection, idList);
//            addTableName("20170908", connection, idList);
//            addTableName("20170915", connection, idList);
//            addTableName("20170922", connection, idList);
//            addTableName("20170929", connection, idList);
            addTableName("201709", connection, idList);
            connection.close();

        }catch (ClassNotFoundException|SQLException e){
            e.printStackTrace();
        }
    }


    public static void addTableName(String date, Connection connection, List<String> idList) throws SQLException {
//未特殊处理机场延长线，目前是手动改一条数据的！！切记
        String tableName = "avgTime" + date;
        String sql = "INSERT INTO "+ tableName + " VALUES(?,?,?,?,?,?,?)";
        System.out.println(sql);
        PreparedStatement statement = connection.prepareStatement(sql);
        long startTime = System.currentTimeMillis();
        for(int i = 0; i < idList.size(); i++){
            for(int j = 0; j < idList.size(); j++){
                if(j == i){
                    continue;
                }
                int startStation = Integer.valueOf(idList.get(i));
                int endStation = Integer.valueOf(idList.get(j));

                //起点，终点，正向
                statement.setString(1, idList.get(i));
                statement.setString(2, idList.get(j));
                //方向
                statement.setString(3, String.valueOf((endStation - startStation) > 0 ? 1 : -1));
                //旅程时间
                statement.setInt(4, 0);
                //距离
                statement.setFloat(5, 0.0f);
                //时间段
                statement.setString(6, "20170901");
                //是否公交
                statement.setString(7, "0");
                statement.addBatch();
            }



//                if(Math.abs(startStation - endStation) > 50){
//                    continue;
//                }
            statement.executeBatch();
            connection.commit();
            long endTime = System.currentTimeMillis();
            System.out.println(endTime - startTime);
        }
        statement.close();
    }
}
