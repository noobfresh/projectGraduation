import com.opencsv.CSVReader;

import java.sql.*;

public class DeleteWrongODData3 {
    //删除不太靠谱的数据，过滤数据，删除前百分之1的数据和后百分之一的数据
    public static void main(String[] args){
        Connection connection;
        String driver = "com.mysql.jdbc.Driver";
        String url =
                "jdbc:mysql://localhost:3306/graduationproject?" +
                        "useUnicode=true&characterEncoding=utf-8&useSSL=false" +
                        "&rewriteBatchedStatements=true&useServerPrepStmts=true";
        String user = "root";
        String pass = "123456";
        CSVReader csvReader = null;
        int all = 0;
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, pass);
            connection.setAutoCommit(false);

            System.out.println("test");
            for(int j = 0; j < 30; j++){
                String tableName = "od201709" + CsvFileInit.getNumbers(j+1);
                Statement statement = connection.createStatement();
                String sql = "SELECT " +
                        " COUNT(TICKET_ID) " +
                        "FROM " +
                        " " + tableName +" " +
                        "WHERE " +
                        " ABS( " +
                        "  CAST( " +
                        "   START_STATION AS SIGNED INTEGER " +
                        "  ) - CAST(END_STATION AS SIGNED INTEGER) " +
                        " ) = 1";
                ResultSet resultSet = statement.executeQuery(sql);
                //初始一个默认值
                int onePercent = 700;
                if(resultSet.next()){
                    
                    onePercent = (int)(resultSet.getInt(1)*0.01);
                }
                sql = "DELETE " +
                        "FROM " +
                        " "+ tableName +" " +
                        "WHERE " +
                        " (START_ID, END_ID) IN ( " +
                        "  SELECT " +
                        "   A.START_ID, " +
                        "   A.END_ID " +
                        "  FROM " +
                        "   ( " +
                        "    SELECT " +
                        "     START_ID, " +
                        "     END_ID " +
                        "    FROM " +
                        "     " + tableName + " " +
                        "    WHERE " +
                        "     ABS( " +
                        "      CAST( " +
                        "       START_STATION AS SIGNED INTEGER " +
                        "      ) - CAST(END_STATION AS SIGNED INTEGER) " +
                        "     ) = 1 " +
                        "    ORDER BY " +
                        "     ( " +
                        "      ( " +
                        "       (SUBSTR(END_TIME FROM 1 FOR 2)) * 3600 + SUBSTR(END_TIME FROM 3 FOR 2) * 60 + SUBSTR(END_TIME FROM 5 FOR 2) " +
                        "      ) - ( " +
                        "       ( " +
                        "        SUBSTR(START_TIME FROM 1 FOR 2) " +
                        "       ) * 3600 + SUBSTR(START_TIME FROM 3 FOR 2) * 60 + SUBSTR(START_TIME FROM 5 FOR 2) " +
                        "      ) " +
                        "     ) ASC " +
                        "    LIMIT "+ onePercent +" " +
                        "   ) AS A " +
                        " ) " +
                        "OR (START_ID, END_ID) IN ( " +
                        " SELECT " +
                        "  B.START_ID, " +
                        "  B.END_ID " +
                        " FROM " +
                        "  ( " +
                        "   SELECT " +
                        "    START_ID, " +
                        "    END_ID " +
                        "   FROM " +
                        "    "+ tableName +" " +
                        "   WHERE " +
                        "    ABS( " +
                        "     CAST( " +
                        "      START_STATION AS SIGNED INTEGER " +
                        "     ) - CAST(END_STATION AS SIGNED INTEGER) " +
                        "    ) = 1 " +
                        "   ORDER BY " +
                        "    ( " +
                        "     ( " +
                        "      (SUBSTR(END_TIME FROM 1 FOR 2)) * 3600 + SUBSTR(END_TIME FROM 3 FOR 2) * 60 + SUBSTR(END_TIME FROM 5 FOR 2) " +
                        "     ) - ( " +
                        "      ( " +
                        "       SUBSTR(START_TIME FROM 1 FOR 2) " +
                        "      ) * 3600 + SUBSTR(START_TIME FROM 3 FOR 2) * 60 + SUBSTR(START_TIME FROM 5 FOR 2) " +
                        "     ) " +
                        "    ) DESC " +
                        "   LIMIT " + onePercent +" " +
                        "  ) AS B " +
                        ")";
                System.out.println(sql);
                long startTime = System.currentTimeMillis();
                statement.execute(sql);
                System.out.println("初始百分之1 为 = " + onePercent + "      " + (System.currentTimeMillis() - startTime) + "   " + j);

                statement.close();
            }
            connection.close();
        }catch (ClassNotFoundException|SQLException e){
            e.printStackTrace();
        }
    }
}
