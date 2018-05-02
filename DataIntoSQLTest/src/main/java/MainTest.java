import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;

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


/**
 * 代码包括了 jdbc批量插入以及 csv分天处理
 */
public class MainTest {
    public static void main(String[] args) throws IOException {
        System.out.println("this is a test");

//        List<OData> beans = new CsvToBeanBuilder<OData>(new BufferedReader(new FileReader(
//                "C:\\Users\\PYF\\Desktop\\英语论文作业\\jieshang201709.csv")))
//                .withType(OData.class).build().parse();
//        System.out.println(beans.size());

        CSVReader csvReader = null;
        List<CSVReader> readers = new ArrayList<>();
        try {
            String path = "C:\\Users\\PYF\\Desktop\\test\\20170930.csv";
            for(int i = 0; i < 30; i++){
                readers.add(new CSVReader(new BufferedReader(new FileReader(
                        "C:\\Users\\PYF\\Desktop\\test\\201709" + CsvFileInit.getNumbers(i+1) + ".csv"))));
            }
//            csvReader = new CSVReader(new BufferedReader(new FileReader(
//                    "C:\\Users\\PYF\\Desktop\\test\\20170930.csv")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        CsvFileInit.initFile();

//        int i = 0;
//        int count = 0;
//        long startTime = System.currentTimeMillis();
//        long endTime;
//        while ((nextLine = csvReader.readNext()) != null){
//            i++;
//            CsvFileInit.getWriter(CsvFileInit.date2Num(nextLine[1])).writeNext(nextLine);
//            if(i == 10000){
//                i = 0;
//                count++;
//                endTime = System.currentTimeMillis();
//                System.out.println((endTime - startTime) + "    " + count);
//                startTime = endTime;
//            }
//        }
//        CsvFileInit.closeFile();



//        while ((nextLine = csvReader.readNext()) != null){
//            System.out.println(nextLine[0]);
//        }

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


            for(int j = 29; j < 30; j++){
                String sql = "insert into graduationproject.`201709" + CsvFileInit.getNumbers(j+1) +
                        "` (TICKET_ID, TXN_DATE, TXN_TIME, TXN_STATION_ID, TICKET_TYPE, TRANS_CODE, TXN_AMT, `SUBSTR(RSV_BUF,17,2)`)" +
                        " values (?,?,?,?,?,?,?,?)";
                System.out.println(sql);
                csvReader = readers.get(j);
                String[] nextLine = csvReader.readNext();
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
                        System.out.println((endTime - startTime) + "    " + count);
                        startTime = endTime;
                    }
                }
                cmd.executeBatch();
                connection.commit();
                cmd.close();
            }
            connection.close();
        }catch (ClassNotFoundException|SQLException|IOException e){
            e.printStackTrace();
        }


    }


}
