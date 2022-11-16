package ds.project4task2;

import com.google.gson.Gson;
import ds.project4task2.models.SearchData;
import ds.project4task2.models.SearchResult;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class IMDbModel {
    String url = "https://imdb-api.com/en/API/SearchMovie/k_46acfnj9/";

    public String fetchInfo(String movieName) {

        String encoded_movie = movieName.replaceAll(" ", "%20");

        // create the request with the API end point and parameters
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + encoded_movie))
                .header("content-type", "application/json")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;

        // use the client to send the request
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Searching ... ");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // extract data needed by Android App from the IMDb API
        String info = response.body();
        Gson gson = new Gson();
        SearchData searchData = gson.fromJson(info, SearchData.class);
        SearchResult searchResult=searchData.results.get(0);

        return gson.toJson(searchResult);
    }
}
