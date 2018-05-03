import java.sql.*;

public class SelectCompareEvery10MinsCounts {

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
            String tableName = null;
            String startTime = "0600";
            while (!startTime.equals("0000")) {
                String endTime = CreateTables30MinsAvgTime.timeIncrement(startTime);
                tableName = "avgTime" + startTime + endTime;

                String sql = "SELECT COUNT(*) FROM " + tableName +" WHERE DURATION <> 0;";
                String sql2 = "SELECT COUNT(*) FROM " + tableName +" WHERE DURATION = 0;";

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                int countVaild = 0;
                if(resultSet.next()){
                    countVaild = resultSet.getInt(1);
                }
                ResultSet resultSet2 = statement.executeQuery(sql2);
                int countInvalid = 0;
                if(resultSet2.next()){
                    countInvalid = resultSet2.getInt(1);
                }
                System.out.println("当前表为： " + tableName + "  有效个数："
                        + countVaild + "  无效个数：" + countInvalid);
                startTime = endTime;
            }
            connection.close();
        } catch (ClassNotFoundException |
                SQLException e) {
            e.printStackTrace();
        }

    }
}

