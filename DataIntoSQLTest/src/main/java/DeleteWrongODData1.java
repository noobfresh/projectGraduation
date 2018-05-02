import com.opencsv.CSVReader;

import java.io.IOException;
import java.sql.*;

public class DeleteWrongODData1 {

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
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, pass);
            connection.setAutoCommit(false);

            System.out.println("test");
            for(int j = 0; j < 30; j++){
                String tableName = "od201709" + CsvFileInit.getNumbers(j+1);
                String sql = "DELETE " +
                        "FROM " +
                        tableName + " " +
                        "WHERE " +
                        " (TICKET_ID, START_TIME) IN ( " +
                        "  SELECT " +
                        "   A.TICKET_ID, " +
                        "   A.START_TIME " +
                        "  FROM " +
                        "   ( " +
                        "    SELECT " +
                        "     B.TICKET_ID, " +
                        "     B.START_TIME " +
                        "    FROM " +
                        "     "+ tableName + " AS B " +
                        "    GROUP BY " +
                        "     B.TICKET_ID, " +
                        "     B.START_TIME " +
                        "    HAVING " +
                        "     count(B.TICKET_ID) > 1 " +
                        "   ) AS A " +
                        " ) " +
                        "AND ( " +
                        " TICKET_ID, " +
                        " START_TIME, " +
                        " END_TIME " +
                        ") NOT IN ( " +
                        " SELECT " +
                        "  CC.TICKET_ID, " +
                        "  CC.START_TIME, " +
                        "  CC.END_TIME " +
                        " FROM " +
                        "  ( " +
                        "   SELECT " +
                        "    BB.TICKET_ID, " +
                        "    BB.START_TIME, " +
                        "    BB.END_TIME " +
                        "   FROM " +
                        "    " + tableName +" AS BB " +
                        "   WHERE " +
                        "    ( " +
                        "     BB.TICKET_ID, " +
                        "     BB.START_TIME, " +
                        "     BB.END_TIME " +
                        "    ) IN ( " +
                        "     SELECT " +
                        "      AA.TICKET_ID, " +
                        "      AA.START_TIME, " +
                        "      AA.END_TIME " +
                        "     FROM " +
                        "      ( " +
                        "       SELECT " +
                        "        C.TICKET_ID, " +
                        "        C.START_TIME, " +
                        "        MIN(C.END_TIME) AS END_TIME " +
                        "       FROM " +
                        "        "+ tableName + " AS C " +
                        "       GROUP BY " +
                        "        C.TICKET_ID, " +
                        "        C.START_TIME " +
                        "       HAVING " +
                        "        COUNT(*) > 1 " +
                        "      ) AS AA " +
                        "    ) " +
                        "  ) AS CC " +
                        ")";
                System.out.println(sql);
                Statement statement = connection.createStatement();
                long startTime = System.currentTimeMillis();
                statement.execute(sql);
                System.out.println((System.currentTimeMillis() - startTime) + "   " + j);
                statement.close();
            }
            connection.close();
        }catch (ClassNotFoundException|SQLException e){
            e.printStackTrace();
        }
    }
}
