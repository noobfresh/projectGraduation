import java.sql.*;

public class DealTransStationPro {

    //处理因为是转乘点导致的数据为0的问题

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

//            updateZeroRecords("20170908", connection);
//            updateZeroRecords("20170915", connection);
//            updateZeroRecords("20170922", connection);
//            updateZeroRecords("20170929", connection);
//            updateZeroRecords("201709", connection);

            for(int i = 11; i < 30; i++){
                if(i == 15 || i == 22 || i == 29){
                    continue;
                }
                updateZeroRecords("201709" + i, connection);
            }
            updateZeroRecords("20170902", connection);
            updateZeroRecords("20170903", connection);

            connection.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void updateZeroRecords(String date, Connection connection) throws SQLException {
        //第一个 0105 两路口 对应 0318
        updateTable("0105", "0318", connection, date);
        //第二个 0225 鱼洞 对应 0301
        updateTable("0225", "0301", connection, date);
        //第三个 0612 红旗河沟 对应 0322
        updateTable("0612", "0322", connection, date);
        //第四个 5601 礼嘉 对应 0620
        updateTable("5601", "0620", connection, date);
        //第五个 0206 = 0319（牛角沱）少部分
        updateTable("0206", "0319", connection, date);
        // 0102 （小十字） 0606
        updateTable("0102", "0606", connection, date);
    }


    public static void updateTable(String orginTrans1, String newTrans2, Connection connection, String date) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = null;
        String tableName = "avgTime" + date;


        String sql = "SELECT " +
                " START_STATION, " +
                " END_STATION, " +
                " DURATION " +
                "FROM " +
                " "+ tableName + " " +
                "WHERE " +
                " DURATION = 0 " +
                "AND (START_STATION = '" + orginTrans1 +"'  " +
                "OR END_STATION = '" + orginTrans1 + "')";
        resultSet = statement.executeQuery(sql);
        String updateSql = "UPDATE "+ tableName + " SET DURATION = ? WHERE START_STATION = ? AND END_STATION = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
        //不断更新
        while (resultSet.next()){
            String startStation = resultSet.getString(1);
            String endStation = resultSet.getString(2);
            int duration = resultSet.getInt(3);

            String newStartStation = null;
            String newEndStation = null;

            if(startStation.equals(orginTrans1)){
                newStartStation = newTrans2;
                newEndStation = endStation;
            } else {
                newStartStation = startStation;
                newEndStation = newTrans2;
            }
            preparedStatement.setString(2, startStation);
            preparedStatement.setString(3, endStation);
            String tempSql = "SELECT DURATION FROM " + tableName + " WHERE START_STATION = '" +
                    newStartStation + "' AND END_STATION = '" + newEndStation + "'";
//            System.out.println(tempSql);
            Statement anotherStatement = connection.createStatement();
            ResultSet temp = anotherStatement.executeQuery(tempSql);
            System.out.println(temp == resultSet);
            if(!temp.next()){
                preparedStatement.setInt(1, duration);
            }else {
                preparedStatement.setInt(1, temp.getInt(1));
            }
            preparedStatement.addBatch();
            temp.close();
        }
        preparedStatement.executeBatch();
        connection.commit();
        preparedStatement.close();
        resultSet.close();
        statement.close();
    }
}
