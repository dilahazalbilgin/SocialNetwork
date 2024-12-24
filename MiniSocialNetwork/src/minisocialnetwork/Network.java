package minisocialnetwork;

import java.util.*;

public class Network {

    private Map<Integer, UserManagement> users;

    public Network() {
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

    public void removeUser(int id, String name) {
        if (users.containsKey(id)) {
            users.remove(id);
            System.out.println("User " + name + " removed");
        } else {
            System.out.println("User doesn't exist!");
        }
    }

    public UserManagement getUserById(int userId) {
        return users.get(userId);
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

    public void removeFriend(int id1, int id2) {
        UserManagement user1 = users.get(id1);
        UserManagement user2 = users.get(id2);

        if (user1 == null || user2 == null) {
            System.out.println("One or both users not found.");
            return;
        }

        user1.removeFriend(user2);
        user2.removeFriend(user1);

        user1.addActivity("Removed friend: " + user2.getName());
        user2.addActivity("Removed friend: " + user1.getName());

        System.out.println("Friendship ended between " + user1.getName() + " and " + user2.getName());
    }

    public List<UserManagement> getMutualFriends(UserManagement user1, UserManagement user2) {
        List<UserManagement> mutualFriends = new ArrayList<>(user1.getFriends());
        mutualFriends.retainAll(user2.getFriends());
        return mutualFriends;
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

    public void viewNewsFeed(int userId) {
        UserManagement user = users.get(userId);

        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        PriorityQueue<Post> newsFeed = new PriorityQueue<>(Comparator.comparingLong(Post::getTimestamp).reversed());

        for (UserManagement friend : user.getFriends()) {
            newsFeed.addAll(friend.getPosts());
        }

        System.out.println("News Feed for " + user.getName() + ":");
        while (!newsFeed.isEmpty()) {
            Post post = newsFeed.poll();
            System.out.println(post.getContent() + " (by User " + post.getUserID() + ")");
        }
    }

    public void viewActivityFeed(int userId) {
        UserManagement user = users.get(userId);

        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.println("Activity Feed for " + user.getName() + ":");
        PriorityQueue<String> activityfeed = user.getActivityFeed();
        while (!activityfeed.isEmpty()) {
            System.out.println(activityfeed.poll());
        }
    }
    
    public void searchUser(String query) {
        try {
            int id = Integer.parseInt(query);
            UserManagement user = users.get(id);
            if (user != null) {
                System.out.println("Found user: " + user.getName() + " (ID: " + id + ")");
                return;
            }
        } catch (NumberFormatException e) {
        }

        for (UserManagement user : users.values()) {
            if (user.getName().equalsIgnoreCase(query)) {
                System.out.println("Found user: " + user.getName() + " (ID: " + user.getID() + ")");
                return;
            }
        }

        System.out.println("User not found.");
    }
}
