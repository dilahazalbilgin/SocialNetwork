package minisocialnetwork;

import java.util.*;

public class ActivityFeed {

    private String description;
    private long timestamp;

    public ActivityFeed(String description) {
        this.description = description;
        this.timestamp = new Date().getTime();
    }

    public String getDescription() {
        return description;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public List<Post> getUserActivityFeed(UserManagement user) {
        PriorityQueue<Post> queue = new PriorityQueue<>(
                (a, b) -> Long.compare(b.getTimestamp(), a.getTimestamp())
        );

        queue.addAll(user.getPosts());

        List<Post> activityFeed = new ArrayList<>();
        while (!queue.isEmpty()) {
            activityFeed.add(queue.poll());
        }

        return activityFeed;
    }

}
