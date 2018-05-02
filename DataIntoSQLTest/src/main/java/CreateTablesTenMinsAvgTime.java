import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTablesTenMinsAvgTime {

//    public static void main(String[] args){
//        Connection connection;
//        String driver = "com.mysql.jdbc.Driver";
//        String url =
//                "jdbc:mysql://localhost:3306/graduationproject?" +
//                        "useUnicode=true&characterEncoding=utf-8&useSSL=false" +
//                        "&rewriteBatchedStatements=true&useServerPrepStmts=true";
//        String user = "root";
//        String pass = "123456";
//
//        try{
//            Class.forName(driver);
//            connection = DriverManager.getConnection(url, user, pass);
//            connection.setAutoCommit(false);
//
//            String tableName = "";
//
//            Statement statement = connection.createStatement();
//            String startTime = "0600";
//            while (!startTime.equals("0000")){
//                String endTime = timeIncrement(startTime);
//                tableName = "avgTime" + startTime + endTime;
//                String createTableSQL = "CREATE TABLE `"+ tableName +"` ( " +
//                        "  `START_STATION` varchar(20) NOT NULL, " +
//                        "  `END_STATION` varchar(20) NOT NULL, " +
//                        "  `DIRECTION` varchar(10) DEFAULT NULL, " +
//                        "  `DURATION` int(11) DEFAULT NULL, " +
//                        "  `DISTANCE` float DEFAULT NULL, " +
//                        "  `TIME` varchar(20) DEFAULT NULL, " +
//                        "  `ISBUS` varchar(10) DEFAULT NULL, " +
//                        "  PRIMARY KEY (`START_STATION`,`END_STATION`) " +
//                        ") ENGINE=InnoDB DEFAULT CHARSET=utf8";
//                statement.execute(createTableSQL);
//                System.out.println(startTime);
//                startTime = endTime;
//            }
//
//            connection.close();
//        }catch (ClassNotFoundException|SQLException e){
//            e.printStackTrace();
//        }
//    }

    /**
     *
     * @param time "0630", 以10为单位
     * @return
     */
    public static String timeIncrement(String time){

        int hour = Integer.valueOf(time.substring(0, 2));
        int min = Integer.valueOf(time.substring(2));

        if(min == 50){
            min = 0;
            if(hour == 23){
                hour = 0;
            } else {
                hour++;
            }
        } else {
            min += 10;
        }
        return convertLess10Num(hour) + convertLess10Num(min);
    }

    public static String convertLess10Num(int num){
        if(num < 10){
            return "0" + num;
        }
        return String.valueOf(num);
    }
}
