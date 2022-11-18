package ds.project4task2;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ds.project4task2.models.Log;
import ds.project4task2.models.SearchResult;
import jakarta.servlet.http.HttpServletRequest;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoService {
    MongoClient mongoClient;
    MongoDatabase database;
    MongoCollection<Log> collection;


    public  MongoService(){

        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        ConnectionString connectionString = new ConnectionString("mongodb+srv://jingqiz:ds2022F@cluster0.5sahucz.mongodb.net/?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .serverApi(ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build())
                .build();
        mongoClient = MongoClients.create(settings);
         database = mongoClient.getDatabase("IMDb").withCodecRegistry(pojoCodecRegistry);
  collection = database.getCollection("movies", Log.class);



//        // Write the string as part of a document to the mongoDB database
//        Document doc = new Document()
//                .append("string", "user_input")
//                .append("timestamp", new Date(System.currentTimeMillis()));
//        collection.insertOne(doc);
//
//        // Read all documents currently stored in the database and print all strings contained in the documents to the console
//        System.out.println("Retrieve all logs in MongoDB:");
//        collection.find().forEach(log -> System.out.println(log.get("string")));



//        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
//        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(PojoCodecProvider.builder().automatic(true).build()));
//
//        ConnectionString connectionString = new ConnectionString("mongodb+srv://jingqiz:ds2022F@cluster0.5sahucz.mongodb.net/?retryWrites=true&w=majority");
//        MongoClientSettings settings = MongoClientSettings.builder()
//                .applyConnectionString(connectionString)
//                .serverApi(ServerApi.builder()
//                        .version(ServerApiVersion.V1)
//                        .build())
//                .build();
//         mongoClient = MongoClients.create(settings);
//         database = mongoClient.getDatabase("IMDb").withCodecRegistry(pojoCodecRegistry);
//         collection = database.getCollection("log", Log.class);


     //   getAllLogs();



//        Bson filter = Filters.eq("string", "hello");
//        collection.deleteMany(filter);
    }


    public void writeLog(Date timeStamp, HttpServletRequest request, long apiResponseTime, SearchResult result) {
        Log log=new Log(timeStamp,request.getRemoteAddr(),request.getHeader("User-Agent"),request.getParameter("movie"),
                apiResponseTime,result);

        collection.insertOne(log);
    }

    public List<Log> getAllLogs(){
        // Read all documents currently stored in the database and print all strings contained in the documents to the console
        System.out.println("Retrieve all logs in MongoDB:");
        List<Log> logs=new LinkedList<>();
        collection.find().forEach(log -> logs.add(log));

        System.out.println(logs.get(0).getTimeStamp());
        return logs;
    }

}
