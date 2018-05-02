import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


//重复内容过多了
public class InsertDataToODTables {

    public static void main(String[] args) throws FileNotFoundException {
        String path = "C:\\Users\\PYF\\Desktop\\test\\oddata\\201709";
        List<CSVReader> readers = new ArrayList<>();
        for(int i = 0; i < 30; i++){
            readers.add(new CSVReader(new BufferedReader(new FileReader(
                 path + CsvFileInit.getNumbers(i + 1) + ".csv"
            ))));
        }

        Connection connection;
        String driver = "com.mysql.jdbc.Driver";
        String url =
                "jdbc:mysql://localhost:3306/graduationproject?" +
                        "useUnicode=true&characterEncoding=utf-8&useSSL=false" +
                        "&rewriteBatchedStatements=true&useServerPrepStmts=true";
        String user = "root";
        String pass = "123456";
        CSVReader csvReader = null;
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, pass);
            connection.setAutoCommit(false);

            System.out.println("test");
            for(int j = 0; j < 30; j++){
                String sql = "insert into graduationproject.`od201709" +
                        CsvFileInit.getNumbers(j+1) + "` values(?,?,?,?,?,?,?,?)";
                csvReader = readers.get(j);
                String[] nextLine = null;
                PreparedStatement cmd = connection.prepareStatement(
                        sql);
                int i = 0;
                int count = 0;
                long startTime = System.currentTimeMillis();
                long endTime;
                while ((nextLine = csvReader.readNext()) != null){
                    i++;
                    cmd.setString(1, nextLine[0]);
                    cmd.setString(2, nextLine[1]);
                    cmd.setString(3, nextLine[2]);
                    cmd.setString(4, nextLine[3]);
                    cmd.setString(5, nextLine[4]);
                    cmd.setString(6, nextLine[5]);
                    cmd.setString(7, nextLine[6]);
                    cmd.setString(8, nextLine[7]);
                    cmd.addBatch();
                    if(i % 10000 == 0){
                        i = 0;
                        count++;
                        cmd.executeBatch();
                        connection.commit();
                        cmd.clearBatch();
                        endTime = System.currentTimeMillis();
                        System.out.println((endTime - startTime) + "   " + count);
                        startTime = endTime;
                    }
                }
                cmd.executeBatch();
                connection.commit();
                cmd.close();
            }
            connection.close();
        }catch (ClassNotFoundException|SQLException |IOException e){
            e.printStackTrace();
        }
    }
}
