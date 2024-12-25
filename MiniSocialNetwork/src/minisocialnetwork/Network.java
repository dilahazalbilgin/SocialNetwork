package minisocialnetwork;

import java.util.*;

public class Network {

    private Map<Integer, UserManagement> users;
    private List<FriendRequest> friendRequests;

    public Network() {
        this.users = new HashMap<>();
        this.friendRequests=new ArrayList<>();
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
    
   public void sendFriendRequest(int senderId, int receiverId) {
    UserManagement sender = users.get(senderId);
    UserManagement receiver = users.get(receiverId);

    if (sender == null || receiver == null) {
        System.out.println("One or both users not found.");
        return;
    }

    for (FriendRequest request : friendRequests) {
        if ((request.getSenderId() == senderId && request.getReceiverId() == receiverId) ||
            (request.getSenderId() == receiverId && request.getReceiverId() == senderId)) {
            System.out.println("Friend request already exists.");
            return;
        }
    }

    friendRequests.add(new FriendRequest(senderId, receiverId));
    System.out.println("Friend request sent from " + sender.getName() + " to " + receiver.getName());
}

public void acceptFriendRequest(int receiverId, int senderId) {
    for (FriendRequest request : friendRequests) {
        if (request.getSenderId() == senderId && request.getReceiverId() == receiverId &&
            request.getStatus().equals("PENDING")) {
            request.accept();
            addFriend(senderId, receiverId);  
            System.out.println("Friend request accepted.");
            return;
        }
    }
    System.out.println("No pending friend request found.");
}

public void rejectFriendRequest(int receiverId, int senderId) {
    for (FriendRequest request : friendRequests) {
        if (request.getSenderId() == senderId && request.getReceiverId() == receiverId &&
            request.getStatus().equals("PENDING")) {
            request.reject();  
            System.out.println("Friend request rejected.");
            return;
        }
    }
    System.out.println("No pending friend request found.");
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
    
    public List<UserManagement> recommendFriends(int userId) {
    UserManagement user = users.get(userId);

    if (user == null) {
        System.out.println("Kullanıcı bulunamadı.");
        return Collections.emptyList();
    }

    Set<UserManagement> recommendedFriends = new HashSet<>();
    Map<UserManagement, Integer> mutualFriendCount = new HashMap<>();

    for (UserManagement friend : user.getFriends()) {
        for (UserManagement friendOfFriend : friend.getFriends()) {
            if (friendOfFriend != user && !user.getFriends().contains(friendOfFriend)) {
                recommendedFriends.add(friendOfFriend);

                mutualFriendCount.put(friendOfFriend, mutualFriendCount.getOrDefault(friendOfFriend, 0) + 1);
            }
        }
    }

    List<UserManagement> sortedRecommendations = new ArrayList<>(recommendedFriends);
    sortedRecommendations.sort((u1, u2) -> mutualFriendCount.get(u2).compareTo(mutualFriendCount.get(u1)));

    System.out.println("Recommend Friends:");
    for (UserManagement recommendedUser : sortedRecommendations) {
        System.out.println("User: " + recommendedUser.getName() + " (Recommend Friends: " + mutualFriendCount.get(recommendedUser) + ")");
    }

    return sortedRecommendations;
}

    
}
