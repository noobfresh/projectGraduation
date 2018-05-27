import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InsertTenMinsTablesData {

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

            List<AvgTimeData.PrimaryKey> keyList = new ArrayList<>();
            String sql = "SELECT START_STATION, END_STATION FROM avgTime20170901";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                keyList.add(new AvgTimeData.PrimaryKey(
                        resultSet.getString(1), resultSet.getString(2)));
            }

            String tableName = "";


            String startTime = "0600";
            while (!startTime.equals("0000")){
                //
                String endTime = CreateTablesTenMinsAvgTime.timeIncrement(startTime);
                tableName = "avgTime20170902" + startTime + endTime;

                InsertRecord(tableName, connection, keyList);

                System.out.println(startTime);
                startTime = endTime;
            }

            connection.close();
        }catch (ClassNotFoundException|SQLException e){
            e.printStackTrace();
        }
    }

    public static void InsertRecord(String tableName, Connection connection, List<AvgTimeData.PrimaryKey> keyList) throws SQLException {
        long startMillis = System.currentTimeMillis();
        String insertSQL = "INSERT INTO " + tableName + " VALUES(?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
        for(AvgTimeData.PrimaryKey key : keyList){
            preparedStatement.setString(1, key.getStartStation());
            preparedStatement.setString(2, key.getEndStation());
            preparedStatement.setInt(3,
                    Integer.valueOf(key.getEndStation()) - Integer.valueOf(key.getStartStation()));
            //旅程时间
            preparedStatement.setInt(4, 0);
            //距离
            preparedStatement.setFloat(5, 0.0f);
            //时间段
            preparedStatement.setString(6, "20170902");
            //是否公交
            preparedStatement.setString(7, "0");
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        connection.commit();
        preparedStatement.close();
        long endMillis = System.currentTimeMillis();
        System.out.println(tableName + "  " + (endMillis - startMillis));
    }
}
