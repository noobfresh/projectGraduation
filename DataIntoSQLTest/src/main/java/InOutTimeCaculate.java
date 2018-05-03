import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;

public class InOutTimeCaculate {

    public static void main(String[] args){

        Connection connection;
        String driver = "com.mysql.jdbc.Driver";
        String url =
                "jdbc:mysql://localhost:3306/graduationproject?" +
                        "useUnicode=true&characterEncoding=utf-8&useSSL=false" +
                        "&rewriteBatchedStatements=true&useServerPrepStmts=true";
        String user = "root";
        String pass = "123456";

        try {

            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, pass);
            connection.setAutoCommit(false);
            String tableName = "stationinouttime";
//            String psql = "INSERT INTO " + tableName + " VALUES(?,?,?)";
//            CSVReader reader = new CSVReader(new BufferedReader(
//                    new FileReader(
//                            new File("C:\\Users\\PYF\\Desktop\\test\\STATION_ID.csv"))));
//            String[] nextLine = reader.readNext();
//            PreparedStatement preparedStatement = connection.prepareStatement(psql);
//            while ((nextLine = reader.readNext()) != null){
//                preparedStatement.setString(1, nextLine[0]);
//                preparedStatement.setInt(2, 0);
//                preparedStatement.setString(3, "201709");
//                preparedStatement.addBatch();
//            }
//            preparedStatement.executeBatch();
//            connection.commit();



            connection.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void caculate(Connection connection, String tableName){

    }
}
