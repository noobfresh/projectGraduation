import java.sql.*;

public class StatisticEveryDayMissingNum {

    public static void main(String[] args) {
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
            Statement statement = connection.createStatement();
            String updatesql = "INSERT INTO statistic VALUES(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(updatesql);
            for(int i = 0; i < 30; i+=7){
                String tableName = "avgtime201709" + CsvFileInit.getNumbers(i+1);
                String sql1 = "SELECT COUNT(*) FROM " + tableName +" WHERE DURATION <> 0";
                String sql2 = "SELECT COUNT(*) FROM " + tableName +" WHERE DURATION = 0";

                ResultSet resultSet1 = statement.executeQuery(sql1);
                int countVaild = 0;
                if(resultSet1.next()){
                    countVaild = resultSet1.getInt(1);
                }
                resultSet1.close();
                ResultSet resultSet2 = statement.executeQuery(sql2);
                int countInvalid = 0;
                if(resultSet2.next()){
                    countInvalid = resultSet2.getInt(1);
                }
                resultSet2.close();

                preparedStatement.setString(1, tableName);
                preparedStatement.setInt(2, countInvalid);
                preparedStatement.setInt(3, countVaild);
                preparedStatement.addBatch();
            }

//            String startTime = "0600";
//            while (!startTime.equals("0000")){
//                String endTime = CreateTablesTenMinsAvgTime.timeIncrement(startTime);
//                String tableName = "avgtime20170902" + startTime + endTime;
//
//                String sql1 = "SELECT COUNT(*) FROM " + tableName +" WHERE DURATION <> 0";
//                String sql2 = "SELECT COUNT(*) FROM " + tableName +" WHERE DURATION = 0";
//
//                ResultSet resultSet1 = statement.executeQuery(sql1);
//                int countVaild = 0;
//                if(resultSet1.next()){
//                    countVaild = resultSet1.getInt(1);
//                }
//                resultSet1.close();
//                ResultSet resultSet2 = statement.executeQuery(sql2);
//                int countInvalid = 0;
//                if(resultSet2.next()){
//                    countInvalid = resultSet2.getInt(1);
//                }
//                resultSet2.close();
//
//                preparedStatement.setString(1, tableName);
//                preparedStatement.setInt(2, countInvalid);
//                preparedStatement.setInt(3, countVaild);
//                preparedStatement.addBatch();
//                startTime = endTime;
//            }
            preparedStatement.executeBatch();
            connection.commit();
            preparedStatement.close();

            connection.close();
        } catch (ClassNotFoundException |
                SQLException e) {
            e.printStackTrace();
        }

    }
}
