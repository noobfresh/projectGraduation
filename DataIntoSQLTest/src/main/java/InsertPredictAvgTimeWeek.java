import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.sql.*;

public class InsertPredictAvgTimeWeek {

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


            for(int i = 22; i < 29; i++){
                String path = "F:\\github\\test\\predict_week\\avgtime201709" + i + ".csv";
                String sql  = "INSERT INTO predict_avgtime_week VALUES(?,?,?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                CSVReader csvReader = new CSVReader(new InputStreamReader(new FileInputStream(path),
                        "gbk"));
                String[] data = csvReader.readNext();
                while ((data = csvReader.readNext()) != null) {
                    System.out.println(data[0] + " " + data[1] + " " + data[2] + " " + data[3] + " ");
                    preparedStatement.setString(1, data[0]);
                    preparedStatement.setString(2, data[1]);
                    preparedStatement.setInt(3, Integer.valueOf(data[2]));
                    if(data[3].equals("")){
                        break;
                    }
                    float predict = Float.valueOf(data[3]);
                    preparedStatement.setInt(4, (int) predict);
                    preparedStatement.setString(5, "201709" + i);
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
                connection.commit();
            }


            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
