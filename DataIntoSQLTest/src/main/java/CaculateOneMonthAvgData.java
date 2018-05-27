import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class CaculateOneMonthAvgData {

    private static Map<AvgTimeData.PrimaryKey, AvgTimeData> avgTimeDatas = new HashMap<>();

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
            String tableName = null;

            String sqlQueryAvgTable = "SELECT START_STATION, " +
                    "END_STATION, DURATION FROM avgtime201709";
            Statement statement = connection.createStatement();
            ResultSet avgTime = statement.executeQuery(sqlQueryAvgTable);

            while (avgTime.next()) {
                AvgTimeData temp = new AvgTimeData();
                temp.setStartStation(avgTime.getString(1));
                temp.setEndStation(avgTime.getString(2));
                temp.setDuration(avgTime.getInt(3));
                AvgTimeData.PrimaryKey key = new AvgTimeData.PrimaryKey
                        (avgTime.getString(1), avgTime.getString(2));
                avgTimeDatas.put(key, temp);
            }
            avgTime.close();
            System.out.println("完成读取平均旅程时间表");

            for(int i = 0; i < 30; i++){
                tableName = "od201709" + CsvFileInit.getNumbers(i+1);
                insertData(connection, tableName);
            }


            //写入avgTime表
            String sqlUpdate = "UPDATE " + "avgtime201709" +
                    " SET DURATION = ? WHERE START_STATION = ? AND END_STATION = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate);
            for(AvgTimeData temp : avgTimeDatas.values()){
                preparedStatement.setInt(1, (int)temp.getDuration());
                preparedStatement.setString(2, temp.getStartStation());
                preparedStatement.setString(3, temp.getEndStation());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();

            connection.close();
        } catch (ClassNotFoundException |
                SQLException e) {
            e.printStackTrace();
        }

    }

    public static void insertData(Connection connection, String tableName) throws SQLException {
        String sql = "SELECT TICKET_ID, START_TIME, END_TIME, START_STATION, END_STATION FROM " + tableName;

        PreparedStatement preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setFetchSize(Integer.MIN_VALUE);
        ResultSet resultSet = preparedStatement.executeQuery();
        int count = 0;
        int wan = 0;
        long startMillis = System.currentTimeMillis();
        while (resultSet.next()){

            String startTime = resultSet.getString(2);
            String endTime = resultSet.getString(3);
            String startStation = resultSet.getString(4);
            String endStation = resultSet.getString(5);
            count++;
            double duration = CaculateAvgTime.date2Second(endTime) - CaculateAvgTime.date2Second(startTime);
            AvgTimeData.PrimaryKey key = new AvgTimeData.PrimaryKey(startStation, endStation);
            AvgTimeData data = avgTimeDatas.get(key);
            data.setDuration(CaculateAvgTime.avgTime(data.getDuration(), duration));
            if(count == 10000){

                count = 0;
                long endMillis = System.currentTimeMillis();
                wan++;
                System.out.println((endMillis - startMillis) + "      " + wan);
                startMillis = endMillis;
            }
        }
        System.out.println("结束了漫长的计算");
        preparedStatement.close();
        resultSet.close();

    }
}
