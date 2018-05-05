import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

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
            doc.limit(1);
            for(Document temp : doc){
                System.out.println(temp.toJson());
            }

        }catch(Exception e){
//            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            e.printStackTrace();
        }
    }
}
