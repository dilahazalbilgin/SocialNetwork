package minisocialnetwork;

import java.util.*;

public class UserManagement {

    protected int ID;
    protected String name;
    protected List<UserManagement> friends;
    protected LinkedList<Post> posts;
    protected PriorityQueue<String> activityFeed;

    public UserManagement(int ID, String name) {
        this.name = name;
        this.ID = ID;
        this.friends = new ArrayList<>();
        this.posts = new LinkedList<>();
        this.activityFeed = new PriorityQueue<>(Comparator.reverseOrder());
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public List<UserManagement> getFriends() {
        if (friends == null) {
            return new ArrayList<>();
        }
        return friends;
    }

    public Queue<Post> getPosts() {
        return posts;
    }

    public PriorityQueue<String> getActivityFeed() {
        return activityFeed;
    }

    public void addFriend(UserManagement friend) {
        if (!friends.contains(friend)) {
            friends.add(friend);
        }
    }

    public void removeFriend(UserManagement friend) {
        friends.remove(friend);
    }

    public void addPost(Post post) {
        posts.addFirst(post);
        activityFeed.add("New Post: " + post.getContent() + " at " + post.getTimestamp());
    }

    public void addActivity(String activity) {
        activityFeed.add(activity);
    }
}
