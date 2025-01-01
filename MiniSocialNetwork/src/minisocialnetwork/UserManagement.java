package minisocialnetwork;

import java.util.*;

public class UserManagement {

    protected int ID;
    protected String name;
    protected List<UserManagement> friends;
    protected Queue<Post> posts;
    protected PriorityQueue<ActivityFeed> activityFeed;

    public UserManagement(int ID, String name) {
        this.name = name;
        this.ID = ID;
        this.friends = new ArrayList<>();
        this.posts =  new LinkedList<>();
        this.activityFeed = new PriorityQueue<>(Comparator.comparingLong(ActivityFeed::getTimestamp).reversed());
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
        posts.add(post);
        activityFeed.add(new ActivityFeed("New Post: " + post.getContent()));
    }

    public void addActivity(String activity) {
        activityFeed.add(new ActivityFeed(activity));
    }
    public void displayActivityFeed() {
    System.out.println("Activity Feed for " + name + ":");
    PriorityQueue<ActivityFeed> tempFeed = new PriorityQueue<>(activityFeed);
    while (!tempFeed.isEmpty()) {
        ActivityFeed activity = tempFeed.poll();
        System.out.println(activity.getDescription() + " at " + new Date(activity.getTimestamp()));
    }
}

}
