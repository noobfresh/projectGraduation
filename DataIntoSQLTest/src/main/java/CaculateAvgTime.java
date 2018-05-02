import com.opencsv.CSVReader;
import org.omg.CORBA.INTERNAL;

import java.sql.*;

public class CaculateAvgTime {

    public static void main(String[] args){
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

            Connection conn = DriverManager.getConnection(url, user, pass);

            //先取出数据，then计算花费时间，then写入db
            String sqlQuery = "SELECT " +
                    " TICKET_ID, " +
                    " START_TIME, " +
                    " END_TIME, " +
                    " START_STATION, " +
                    " END_STATION " +
                    "FROM " +
                    " od20170901 " +
                    "WHERE " +
                    " ABS( " +
                    "  CAST( " +
                    "   START_STATION AS SIGNED INTEGER " +
                    "  ) - CAST(END_STATION AS SIGNED INTEGER) " +
                    " ) = 1";
//            PreparedStatement statement = connection.prepareStatement(sqlQuery, ResultSet.TYPE_FORWARD_ONLY,
//                    ResultSet.CONCUR_READ_ONLY);
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
//            statement.setFetchSize(Integer.MIN_VALUE);
//            statement.setFetchDirection(ResultSet.FETCH_REVERSE);
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            String[] data = new String[5];
            int count = 0;
            while (resultSet.next()){
                System.out.println(count);
                count++;
                String startStation = resultSet.getString(4);
                String endStation = resultSet.getString(5);
                String startTime = resultSet.getString(2);
                String endTime = resultSet.getString(3);

                String sqlQuery2 = "SELECT DURATION FROM avgTime20170901 " +
                        "WHERE START_STATION = '" + startStation + "' AND END_STATION = '"+ endStation + "'" ;
                Statement statementAVG = conn.createStatement();
                ResultSet resultSetAVG = statementAVG.executeQuery(sqlQuery2);
                if(!resultSetAVG.next()){
                    continue;
                }
                double avgTime = avgTime(resultSetAVG.getInt(1), date2Second(endTime) - date2Second(startTime));
                String sqlUpdate = "UPDATE avgTime20170901 SET DURATION = " + (int)avgTime + " " +
                        "WHERE START_STATION = '" + startStation + "' AND END_STATION = '"+ endStation + "'";
                statementAVG.execute(sqlUpdate);
                resultSetAVG.close();
                statementAVG.close();
            }
            resultSet.close();
            statement.close();
            connection.close();
            conn.close();
        }catch (ClassNotFoundException|SQLException e){
            e.printStackTrace();
        }
    }


    /**
     *  日期转换成秒数，便于计算
     * @param dbDate
     * @return
     */
    public static double date2Second(String dbDate){

        String hour = dbDate.substring(0, 2);
        String min = dbDate.substring(2, 4);
        String sec = dbDate.substring(4);

        return Integer.valueOf(sec) + Integer.valueOf(min) * 60 + Integer.valueOf(hour) * 3600;
    }


    /**
     *  算平均
     * @param origin
     * @param delta
     * @return
     */
    public static double avgTime(double origin, double delta){
        if(origin <= 0){
            return delta;
        }else {
            return (origin + delta)/2;
        }
    }
}
