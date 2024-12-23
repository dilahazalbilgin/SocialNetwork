package minisocialnetwork;

import java.util.Date;


public class ActivityFeed{
    private String description;
    private long timestamp;
    
    public ActivityFeed(String description) {
    this.description = description;
    this.timestamp = new Date().getTime();
}
    
    public String getDescription() {
        return description;
    }

    public long getTimestamp() {
        return timestamp;
    }
    
    @Override
    public String toString(){
        return description+ new Date(timestamp);
    }
    
}
