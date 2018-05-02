import com.opencsv.CSVWriter;

import java.io.IOException;
import java.sql.*;
import java.util.Arrays;

public class ODFilter {

    public static void main(String[] args) throws IOException {

        CsvFileInit.initFile();

        Connection connection;
        String driver = "com.mysql.jdbc.Driver";
        String url =
                "jdbc:mysql://localhost:3306/graduationproject?" +
                        "useUnicode=true&characterEncoding=utf-8&useSSL=false" +
                        "&rewriteBatchedStatements=true&useServerPrepStmts=true" +
                        "&useCursorFetch=true";
        String user = "root";
        String pass = "123456";
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, pass);
            connection.setAutoCommit(false);
            for(int j = 0; j < 30; j++){
                CSVWriter writer = CsvFileInit.getWriter(j+1);
                String tableName = "graduationproject.`201709" + CsvFileInit.getNumbers(j+1) + "`";
                //
                String sql = "SELECT " +
                        "tableA.TICKET_ID AS ticketId," +
                        "tableA.TXN_DATE AS txnDate," +
                        "tableA.TXN_TIME AS startTime," +
                        "tableB.TXN_TIME AS endTime," +
                        "tableA.TXN_STATION_ID AS startStation," +
                        "tableB.TXN_STATION_ID AS endStation," +
                        "tableA.ID AS START_ID," +
                        "tableB.ID AS END_ID " +
                        "FROM " + tableName +
                        "AS tableA " +
                        "INNER JOIN " + tableName + " AS tableB ON tableA.TICKET_ID = tableB.TICKET_ID " +
                        "AND (" +
                        "cast(" +
                        "tableB.TXN_TIME AS signed INTEGER" +
                        ") - cast(" +
                        "tableA.TXN_TIME AS signed INTEGER" +
                        ")" +
                        ") BETWEEN 0 AND 30000 " +
                        "AND tableA.TRANS_CODE = '21'" +
                        "AND tableB.TRANS_CODE = '22'" +
                        "AND tableA.TXN_STATION_ID <> tableB.TXN_STATION_ID";

                PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_READ_ONLY);
                statement.setFetchSize(Integer.MIN_VALUE);
                statement.setFetchDirection(ResultSet.FETCH_REVERSE);
                //测试
                System.out.println("111111111111111111111111");
                long startTime = System.currentTimeMillis();
                ResultSet resultSet = statement.executeQuery(sql);
                long endTime = System.currentTimeMillis();
                System.out.println(endTime - startTime);
                int i = 0;
                int count = 0;
                startTime = System.currentTimeMillis();
                String[] data = new String[8];
                while (resultSet.next()){
                    data[0] = resultSet.getString(1);
                    data[1] = resultSet.getString(2);
                    data[2] = resultSet.getString(3);
                    data[3] = resultSet.getString(4);
                    data[4] = resultSet.getString(5);
                    data[5] = resultSet.getString(6);
                    data[6] = resultSet.getString(7);
                    data[7] = resultSet.getString(8);
                    writer.writeNext(data);
                    i++;
                    if(i == 10000){
                        count++;
                        endTime = System.currentTimeMillis();
                        System.out.println((endTime - startTime) + "       " + count);
                        startTime = endTime;
                        i = 0;
                    }
                }
                resultSet.close();
                statement.close();
            }
            CsvFileInit.closeFile();
            connection.close();
        }catch (ClassNotFoundException|SQLException e){
            e.printStackTrace();
        }
    }

    //突然想到可以开线程做，只不过如果希望保留顺序的话，
}
