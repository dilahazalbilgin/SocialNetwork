package datastructuresproject;

import java.util.List;

public class User {
    private int userId;
    private String name;
    private List<String>friends;
    public User(int userId,String name){
    this.userId=userId;
    this.name=name;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
    
}
