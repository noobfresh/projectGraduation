import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import javafx.print.Printer;
import org.bson.Document;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;

public class MongoDBAccess {

    public static void main(String[] args){
        try{
            // 连接到 mongodb 服务
            MongoClient mongoClient = new MongoClient( "111.230.96.139" , 27017 );

            // 连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase("bysj");
            System.out.println("Connect to database successfully");

            MongoCollection<Document> collection = mongoDatabase.getCollection("travel_time");

            FindIterable<Document> doc = collection.find();
            System.out.println("==============================================================");
//            doc.limit(7);
            int countId = 0;
            for(Document temp : doc){
                countId++;
                String json = temp.toJson();
                String path = "C:\\Users\\PYF\\Desktop\\test\\busdata" + "\\" + countId + ".txt";
                File file = new File(path);
                if(!file.exists()){
                    file.createNewFile();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                PrintWriter printWriter = new PrintWriter(fileOutputStream);
                printWriter.write(json);
                printWriter.flush();
                printWriter.close();
                fileOutputStream.close();
            }

        }catch(Exception e){
//            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            e.printStackTrace();
        }
    }
}
