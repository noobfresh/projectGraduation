import com.opencsv.CSVWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.sql.*;

public class OuputAvgOdData {

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

            int count = 0;
            String startTime = "0600";
            while (!startTime.equals("0000")) {
                count++;
                String endTime = CreateTablesTenMinsAvgTime.timeIncrement(startTime);

                for(int i = 0; i < 30; i+=7){
                    String tableName = "avgtime201709" + CsvFileInit.getNumbers(i + 1) + startTime + endTime;
                    String sql = "SELECT START_STATION, END_STATION, DURATION FROM " + tableName;
                    String path = "F:\\github\\test\\predict_day\\" + CsvFileInit.getNumbers(count)
                            + "\\" + tableName + ".csv";
                    Writer writer = new BufferedWriter(new FileWriter(new File(path)));
                    CSVWriter csvWriter = new CSVWriter(writer);
                    String[] lines = new String[3];
                    lines[0] = "start_station";
                    lines[1] = "end_station";
                    lines[2] = "duration";
                    csvWriter.writeNext(lines);
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(sql);
                    while (resultSet.next()) {
                        lines[0] = resultSet.getString(1);
                        lines[1] = resultSet.getString(2);
                        lines[2] = String.valueOf(resultSet.getInt(3));
                        csvWriter.writeNext(lines);
                    }
                    csvWriter.flush();
                }

                startTime = endTime;
            }


//            for(int i = 0; i < 108; i++){
//                File file = new File("F:\\github\\test\\predict_day\\" + CsvFileInit.getNumbers(i + 1));
//                if(!file.exists()){
//                    file.mkdir();
//                }
//            }


            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
