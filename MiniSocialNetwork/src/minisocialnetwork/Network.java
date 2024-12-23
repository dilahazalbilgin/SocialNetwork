package minisocialnetwork;

import java.util.*;


public class Network {
    private Map<Integer, UserManagement> users;
    
    public Network(){
    this.users = new HashMap<>();
    }
    
    public void addUser(int id, String name) {
        if (users.containsKey(id)) {
            System.out.println("User with ID " + id + " already exists.");
        } else {
            users.put(id, new UserManagement(id, name));
            System.out.println("User added: " + name);
        }
    }

    public void addFriend(int id1, int id2) {
        UserManagement user1 = users.get(id1);
        UserManagement user2 = users.get(id2);

        if (user1 == null || user2 == null) {
            System.out.println("One or both users not found.");
            return;
        }

        user1.addFriend(user2);
        user2.addFriend(user1);

        user1.addActivity("Added friend: " + user2.getName());
        user2.addActivity("Added friend: " + user1.getName());

        System.out.println("Friendship added between " + user1.getName() + " and " + user2.getName());
    }

    public void postMessage(int userId, String content) {
        UserManagement user = users.get(userId);

        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        Post post = new Post(content, userId);
        user.addPost(post);

        System.out.println("Post added by " + user.getName() + ": " + content);
    }

    public void viewFeed(int userId) {
        UserManagement user = users.get(userId);

        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        PriorityQueue<Post> newsFeed = new PriorityQueue<>(Comparator.comparingLong(Post::getTimestap).reversed());

        for (UserManagement friend : user.getFriends()) {
            newsFeed.addAll(friend.getPosts());
        }

        System.out.println("News Feed for " + user.getName() + ":");
        while (!newsFeed.isEmpty()) {
            Post post = newsFeed.poll();
            System.out.println(post.getContent() + " (by User " + post.getUserID() + ")");
        }
    }

    public void searchUser(String name) {
        for (UserManagement user : users.values()) {
            if (user.getName().equalsIgnoreCase(name)) {
                System.out.println("Found user: " + user.getName() + " (ID: " + user.getID() + ")");
                return;
            }
        }
        System.out.println("User not found.");
    }
}
    

