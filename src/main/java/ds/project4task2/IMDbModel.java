package ds.project4task2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ds.project4task2.models.AndroidResponse;
import ds.project4task2.models.SearchData;
import ds.project4task2.models.SearchResult;
import jakarta.servlet.http.HttpServletRequest;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Date;

public class IMDbModel {
    String url = "https://imdb-api.com/en/API/SearchMovie/k_46acfnj9/";

    public String fetchInfo(HttpServletRequest appRequest) {
        Date timeStamp = new Date();
        // Get the search term and encode it
        String searchTerm = appRequest.getParameter("movie");
        String encoded_movie = searchTerm.replaceAll(" ", "%20");

        // Create the request with the API end point and parameters
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + encoded_movie))
                .header("content-type", "application/json")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;

        long apiResponseTime = 0L;
        // Use the client to send the request; record the API response time
        try {
            long startTime = System.currentTimeMillis();
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Searching ... ");
            long endTime = System.currentTimeMillis();
            apiResponseTime = endTime - startTime;
        } catch (Exception e) {
            e.printStackTrace();
        }

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .serializeNulls()
                .create();
        AndroidResponse androidResponse = new AndroidResponse();
        // success
        if (response != null) {
            // Extract data needed by Android App from the IMDb API
            String info = response.body();

            SearchData searchData = gson.fromJson(info, SearchData.class);
            SearchResult searchResult = null;
            if (searchData.results.size() != 0) {   // movies found
                searchResult = searchData.results.get(0);
            }
            androidResponse = new AndroidResponse(searchResult);
        }

        // Write log to MongoDB
        MongoService mongoService = new MongoService();
        mongoService.writeLog(timeStamp, appRequest, apiResponseTime, androidResponse);

        String jsonResponse = gson.toJson(androidResponse);
        return jsonResponse;
    }

}
