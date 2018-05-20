import com.opencsv.CSVReader;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

            String insertSQL = "INSERT INTO bussatation VALUES(?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            int id = 0;

            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
