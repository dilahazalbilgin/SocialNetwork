package datastructuresproject;
public class Post {
    
    private String content;
    private long timestamp;
    private int userId;
    
    public Post(String content,long timestamp,int userId){
    this.content=content;
    this.timestamp=timestamp;
    this.userId=userId;
    }

    public String getContent() {
        return content;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getUserId() {
        return userId;
    }
}
