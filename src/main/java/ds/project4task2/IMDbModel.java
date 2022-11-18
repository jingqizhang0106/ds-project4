package ds.project4task2;

import com.google.gson.Gson;
import ds.project4task2.models.SearchData;
import ds.project4task2.models.SearchResult;
import jakarta.servlet.http.HttpServletRequest;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Date;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

public class IMDbModel {
    String url = "https://imdb-api.com/en/API/SearchMovie/k_46acfnj9/";

    public String fetchInfo( HttpServletRequest appRequest) {
        Date timeStamp=new Date();
        // fetch movie info in Json format
        String movieName = appRequest.getParameter("movie");
        String encoded_movie = movieName.replaceAll(" ", "%20");

        // create the request with the API end point and parameters
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + encoded_movie))
                .header("content-type", "application/json")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;

        long apiResponseTime=0L;
        // use the client to send the request
        try {
            long startTime = System.currentTimeMillis();
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Searching ... ");
            long endTime = System.currentTimeMillis();
            apiResponseTime=endTime-startTime;
        } catch (Exception e) {
            e.printStackTrace();
        }



        // extract data needed by Android App from the IMDb API
        String info = response.body();
        Gson gson = new Gson();
        SearchData searchData = gson.fromJson(info, SearchData.class);
        SearchResult searchResult=searchData.results.get(0);
        String jsonResponse=gson.toJson(searchResult);

        //  request.getHeader("User-Agent")
        MongoService mongoService=new MongoService();
        mongoService.writeLog(timeStamp, appRequest, apiResponseTime,searchResult);

        return jsonResponse;
    }


}
