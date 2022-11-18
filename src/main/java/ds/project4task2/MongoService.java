package ds.project4task2;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ds.project4task2.models.AndroidResponse;
import ds.project4task2.models.Log;
import jakarta.servlet.http.HttpServletRequest;
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


    public MongoService() {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        // Connect to MongoDB
        ConnectionString connectionString = new ConnectionString("mongodb+srv://jingqiz:ds2022F@cluster0.5sahucz.mongodb.net/?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .serverApi(ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build())
                .build();
        // Get MongoDB client, database, collection
        mongoClient = MongoClients.create(settings);
        database = mongoClient.getDatabase("IMDb").withCodecRegistry(pojoCodecRegistry);
        collection = database.getCollection("movies", Log.class);
    }


    public void writeLog(Date timeStamp, HttpServletRequest request, long apiResponseTime, AndroidResponse androidResponse) {
        // Create a new Log and write into MongoDB
        Log log = new Log(timeStamp, request.getRemoteAddr(), request.getHeader("User-Agent"),
                request.getParameter("movie"), apiResponseTime, androidResponse);
        collection.insertOne(log);
    }

    public List<Log> getFullLogs() {
        // Retrieve full logs from MongoDB
        System.out.println("Retrieve all logs in MongoDB:");
        List<Log> logs = new LinkedList<>();
        collection.find().forEach(log -> logs.add(log));
        return logs;
    }

}
