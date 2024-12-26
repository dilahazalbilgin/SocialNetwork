package minisocialnetwork;

import java.util.*;

public class UserManagement {

    protected int ID;
    protected String name;
    protected List<UserManagement> friends;
    protected LinkedList<Post> posts;
    protected PriorityQueue<ActivityFeed> activityFeed;

    public UserManagement(int ID, String name) {
        this.name = name;
        this.ID = ID;
        this.friends = new ArrayList<>();
        this.posts = new LinkedList<>();
        this.activityFeed = new PriorityQueue<>((a, b) -> Long.compare(b.getTimestamp(), a.getTimestamp()));
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

    public PriorityQueue<ActivityFeed> getActivityFeed() {
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
        activityFeed.add(new ActivityFeed("New Post: " + post.getContent()));
    }

    public void addActivity(String activity) {
        activityFeed.add(new ActivityFeed(activity));
    }
}
