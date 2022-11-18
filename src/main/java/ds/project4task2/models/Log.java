package ds.project4task2.models;

import java.util.Date;


public class Log {
    Date timeStamp;
    String status;
    String requestIP;
    String deviceType;
    String searchTerm;
    long apiResponseTime;
    SearchResult result;

    public Log() {
    }

    public Log(Date timeStamp, String requestIP, String deviceType, String searchTerm, long apiResponseTime, AndroidResponse androidResponse) {
        this.timeStamp = timeStamp;
        this.status = androidResponse.getStatus();
        this.requestIP = requestIP;
        this.deviceType = deviceType;
        this.searchTerm = searchTerm;
        this.apiResponseTime = apiResponseTime;
        this.result = androidResponse.getSearchResult();
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequestIP() {
        return requestIP;
    }

    public void setRequestIP(String requestIP) {
        this.requestIP = requestIP;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public long getApiResponseTime() {
        return apiResponseTime;
    }

    public void setApiResponseTime(long apiResponseTime) {
        this.apiResponseTime = apiResponseTime;
    }

    public SearchResult getResult() {
        return result;
    }

    public void setResult(SearchResult result) {
        this.result = result;
    }
}
