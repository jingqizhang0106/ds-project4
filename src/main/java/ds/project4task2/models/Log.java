package ds.project4task2.models;

import lombok.Builder;

import java.util.Date;

@Builder
public class Log {
    Date timeStamp;
    String appId;
    String deviceType;
    String searchTerm;
    long apiResponseTime;
    SearchResult result;

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
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
