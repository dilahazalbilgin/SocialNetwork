package minisocialnetwork;
public class MiniSocialNetwork {
    public static void main(String[] args) {
        Network network = new Network();

        network.addUser(1, "Alice");
        network.addUser(2, "Bob");
        network.addUser(3, "Charlie");

        network.addFriend(1, 2);
        network.addFriend(1, 3);

        network.postMessage(1, "Hello, world!");
        network.postMessage(2, "Good morning!");

        network.viewFeed(1);
        network.viewFeed(2);
        network.searchUser("Charlie");
        
    }
    
}
