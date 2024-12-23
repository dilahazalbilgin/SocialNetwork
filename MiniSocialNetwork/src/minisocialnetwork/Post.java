package minisocialnetwork;

import java.time.LocalDateTime;
import java.util.Date;

public class Post {
   private String content;
   private long timestap;
   private int userID;
   
   public Post(String content,int userID){
   this.content=content;
   this.timestap=new Date().getTime();
   this.userID=userID;
   }

    public String getContent() {
        return content;
    }

    public long getTimestap() {
        return timestap;
    }

    public int getUserID() {
        return userID;
    }
   
    
    
    
}
