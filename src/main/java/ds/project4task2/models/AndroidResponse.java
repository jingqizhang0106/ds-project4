package ds.project4task2.models;

public class AndroidResponse {
    private String status;
    private SearchResult searchResult;

    public AndroidResponse() {
        status = "fail";
        this.searchResult = null;
    }

    public AndroidResponse(SearchResult searchResult) {
        status = "success";
        this.searchResult = searchResult;
    }

    public String getStatus() {
        return status;
    }

    public SearchResult getSearchResult() {
        return searchResult;
    }
}
