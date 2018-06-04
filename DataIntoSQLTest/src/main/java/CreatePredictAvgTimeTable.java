import java.sql.*;

public class CreatePredictAvgTimeTable {

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


            Statement statement = connection.createStatement();



            for(int i = 0; i < 30; i++){
                String tableName = "predict_avgtime_201709" + CsvFileInit.getNumbers(i);
                String sql = "CREATE TABLE `" + tableName +"` ( " +
                        "  `START_STATION` varchar(20) NOT NULL, " +
                        "  `END_STATION` varchar(20) NOT NULL, " +
                        "  `DURATION` int(11) DEFAULT NULL, " +
                        "  `TIME` varchar(20) DEFAULT NULL, " +
                        "  `PREDICT_DURATION` int(11) DEFAULT NULL, " +
                        "  `DATE` varchar(20) DEFAULT NULL, " +
                        "  PRIMARY KEY (`START_STATION`,`END_STATION`) " +
                        ") ENGINE=InnoDB DEFAULT CHARSET=utf8";
                statement.execute(sql);
            }
            String startTime = "0600";
            while (!startTime.equals("0000")){
                String endTime = CreateTablesTenMinsAvgTime.timeIncrement(startTime);
                String tableName = "predict_avgtime_" + startTime + endTime;
                String sql = "CREATE TABLE `" + tableName +"` ( " +
                        "  `START_STATION` varchar(20) NOT NULL, " +
                        "  `END_STATION` varchar(20) NOT NULL, " +
                        "  `DURATION` int(11) DEFAULT NULL, " +
                        "  `TIME` varchar(20) DEFAULT NULL, " +
                        "  `PREDICT_DURATION` int(11) DEFAULT NULL, " +
                        "  `DATE` varchar(20) DEFAULT NULL, " +
                        "  PRIMARY KEY (`START_STATION`,`END_STATION`) " +
                        ") ENGINE=InnoDB DEFAULT CHARSET=utf8";
                statement.execute(sql);
                startTime = endTime;
            }
            connection.commit();
            connection.close();
        }catch (ClassNotFoundException|SQLException e){
            e.printStackTrace();
        }
    }
}
