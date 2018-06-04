import com.opencsv.CSVReader;

import java.io.*;
import java.sql.*;

public class InsertBusStation {

    public static void main(String[] args) {
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

            String path = "F:\\github\\test\\bus.csv";
            DataInputStream in = new DataInputStream(new FileInputStream(new File(path)));
            //解析中文防乱码
            CSVReader csvReader = new CSVReader(new InputStreamReader(in, "gbk"));
            String[] read = csvReader.readNext();

            String insertSQL = "INSERT INTO busstation VALUES(?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            while ((read = csvReader.readNext()) != null){
                preparedStatement.setString(1, read[0]);
                preparedStatement.setString(2, read[1]);
                preparedStatement.setString(3, read[2]);
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();
            connection.commit();
            preparedStatement.close();


            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
