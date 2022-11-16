package ds.project4task2.models;

import lombok.Builder;

@Builder
public class Log {
    String deviceType;
    String searchTerm;
    long responseTime;
    String title;
    String description;
    String img;
}
