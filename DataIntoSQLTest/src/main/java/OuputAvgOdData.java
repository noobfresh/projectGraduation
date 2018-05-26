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

            for(int i = 0; i < 5; i++){
                String tableName = "avgtime201709" + CsvFileInit.getNumbers(i * 7 + 1);
                String sql = "SELECT START_STATION, END_STATION, DURATION FROM " + tableName;
                String path = "F:\\github\\test\\predict\\" + tableName + ".csv";
                Writer writer = new BufferedWriter(new FileWriter(new File(path)));
                CSVWriter csvWriter = new CSVWriter(writer);
                String[] lines = new String[3];
                lines[0] = "start_station";
                lines[1] = "end_station";
                lines[2] = "duration";
                csvWriter.writeNext(lines);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()){
                    lines[0] = resultSet.getString(1);
                    lines[1] = resultSet.getString(2);
                    lines[2] = String.valueOf(resultSet.getInt(3));
                    csvWriter.writeNext(lines);
                }
            }


            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
