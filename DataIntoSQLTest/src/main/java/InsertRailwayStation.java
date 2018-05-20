import com.opencsv.CSVReader;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InsertRailwayStation {

    public static void main(String[] args) {
        String path = "C:\\Users\\PYF\\Desktop\\test\\railwaystation.csv";


        Connection connection;
        String driver = "com.mysql.jdbc.Driver";
        String url =
                "jdbc:mysql://localhost:3306/graduationproject?" +
                        "useUnicode=true&characterEncoding=utf-8&useSSL=false" +
                        "&rewriteBatchedStatements=true&useServerPrepStmts=true";
        String user = "root";
        String pass = "123456";

        try{
            DataInputStream in = new DataInputStream(new FileInputStream(new File(path)));
            //解析中文防乱码
            CSVReader csvReader = new CSVReader(new InputStreamReader(in, "gbk"));
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, pass);
            connection.setAutoCommit(false);
            String[] read = csvReader.readNext();
            String sql = "INSERT INTO railwaystation VALUES(?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            while ((read = csvReader.readNext()) != null){
                String name = read[0];
                String id = read[1];
                System.out.println(id + name);
                preparedStatement.setString(1, id);
                preparedStatement.setString(2, name);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
            preparedStatement.close();
            connection.close();
        }catch (ClassNotFoundException|SQLException |IOException e){
            e.printStackTrace();
        }
    }
}
