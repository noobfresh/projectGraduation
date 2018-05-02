import com.opencsv.CSVReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteWrongODData2 {

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
                        "  " + tableName + " " +
                        "WHERE " +
                        " (START_ID, END_ID) IN ( " +
                        "  SELECT " +
                        "   M.START_ID, " +
                        "   M.END_ID " +
                        "  FROM " +
                        "   ( " +
                        "    SELECT " +
                        "     G.START_ID, " +
                        "     G.END_ID " +
                        "    FROM " +
                        "      " + tableName + " AS G " +
                        "    WHERE " +
                        "     (G.TICKET_ID, G.END_TIME) IN ( " +
                        "      SELECT " +
                        "       B.TICKET_ID, " +
                        "       B.END_TIME " +
                        "      FROM " +
                        "        " + tableName + " AS B " +
                        "      GROUP BY " +
                        "       B.TICKET_ID, " +
                        "       B.END_TIME " +
                        "      HAVING " +
                        "       COUNT(B.TICKET_ID) > 1 " +
                        "     ) " +
                        "    AND (G.START_ID, G.END_ID) NOT IN ( " +
                        "     SELECT " +
                        "      C.START_ID, " +
                        "      C.END_ID " +
                        "     FROM " +
                        "      ( " +
                        "       SELECT " +
                        "        A.TICKET_ID, " +
                        "        A.START_ID, " +
                        "        A.END_ID, " +
                        "        (A.END_ID - A.START_ID) AS MINUS " +
                        "       FROM " +
                        "         " + tableName + " AS A " +
                        "       WHERE " +
                        "        (A.TICKET_ID, A.END_TIME) IN ( " +
                        "         SELECT " +
                        "          B.TICKET_ID, " +
                        "          B.END_TIME " +
                        "         FROM " +
                        "           " + tableName + " AS B " +
                        "         GROUP BY " +
                        "          B.TICKET_ID, " +
                        "          B.END_TIME " +
                        "         HAVING " +
                        "          COUNT(B.TICKET_ID) > 1 " +
                        "        ) " +
                        "      ) AS C " +
                        "     WHERE " +
                        "      C.MINUS = ( " +
                        "       SELECT " +
                        "        MIN(D.MINUS) " +
                        "       FROM " +
                        "        ( " +
                        "         SELECT " +
                        "          AA.TICKET_ID, " +
                        "          AA.START_ID, " +
                        "          AA.END_ID, " +
                        "          (AA.END_ID - AA.START_ID) AS MINUS " +
                        "         FROM " +
                        "           " + tableName + " AS AA " +
                        "         WHERE " +
                        "          (AA.TICKET_ID, AA.END_TIME) IN ( " +
                        "           SELECT " +
                        "            BB.TICKET_ID, " +
                        "            BB.END_TIME " +
                        "           FROM " +
                        "             " + tableName + " AS BB " +
                        "           GROUP BY " +
                        "            BB.TICKET_ID, " +
                        "            BB.END_TIME " +
                        "           HAVING " +
                        "            COUNT(BB.TICKET_ID) > 1 " +
                        "          ) " +
                        "        ) AS D " +
                        "       WHERE " +
                        "        D.TICKET_ID = C.TICKET_ID " +
                        "      ) " +
                        "    ) " +
                        "   ) AS M " +
                        " )";
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
