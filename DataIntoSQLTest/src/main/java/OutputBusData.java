import com.opencsv.CSVWriter;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class OutputBusData {

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

            for(int i = 4; i < 31; i+=7){
                String tableName = "busdata05" + CsvFileInit.getNumbers(i + 1);
                //线路号特殊处理
                String sql = "";
                String path = "F:\\github\\test\\predict_day\\" + tableName + ".csv";
                DataOutputStream out = new DataOutputStream(new FileOutputStream(new File(path)));
                Writer writer = new BufferedWriter(new OutputStreamWriter(out, "gbk"));
                CSVWriter csvWriter = new CSVWriter(writer);
                String[] lines = new String[5];
//                lines[0] = "start_station";
//                lines[1] = "end_station";
//                lines[2] = "duration";
//                csvWriter.writeNext(lines);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()){
                    lines[0] = resultSet.getString(1);
                    lines[1] = resultSet.getString(2);
                    lines[2] = resultSet.getString(3);
                    lines[3] = String.valueOf(resultSet.getInt(4));
                    lines[4] = resultSet.getString(5);
                    csvWriter.writeNext(lines);
                }
                csvWriter.flush();
            }


            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
