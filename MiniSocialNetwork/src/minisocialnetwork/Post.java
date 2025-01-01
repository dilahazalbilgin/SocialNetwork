package minisocialnetwork;

public class Post {

    private String content;
    private long timestamp;
    private int userID;

    public Post(String content, int userID) {
        this.content = content;
        this.timestamp = System.currentTimeMillis();
        this.userID = userID;
    }

    public String getContent() {
        return content;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getUserID() {
        return userID;
    }

}
