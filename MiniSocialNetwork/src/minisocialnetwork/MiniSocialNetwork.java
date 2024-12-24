package minisocialnetwork;

import java.util.*;

public class MiniSocialNetwork {

    public static void main(String[] args) {
        Network network = new Network();
        boolean isUsersAdded = false; // Kullanıcılar sadece bir kez eklenecek
        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome!!!");

        while (true) {
            System.out.println("\nPlease enter your step:");
            System.out.println("1 - Add User");
            System.out.println("2 - Remove User");
            System.out.println("3 - Add Friend");
            System.out.println("4 - Remove Friend");
            System.out.println("5 - Find Mutual Friend");
            System.out.println("6 - Post Message");
            System.out.println("7 - View Feed");
            System.out.println("8 - Search User");
            System.out.println("9 - Exit");

            int ans = scan.nextInt();

            if (!isUsersAdded && ans != 1 && ans != 9) {
                System.out.println("\nUsers must be added first! Adding default users...");
                network.addUser(1, "Dila");
                network.addUser(2, "Ceren");
                network.addUser(3, "Nisa");
                network.addUser(4, "Sevde");
                isUsersAdded = true;
                System.out.println("Default users added successfully.\n");
            }

            switch (ans) {
                case 1:
                    if (!isUsersAdded) {
                        network.addUser(1, "Dila");
                        network.addUser(2, "Ceren");
                        network.addUser(3, "Nisa");
                        network.addUser(4, "Sevde");
                        isUsersAdded = true;
                        System.out.println("Default users added.");
                    } else {
                        System.out.println("Users have already been added!");
                    }
                    break;

                case 2:
                    System.out.print("Enter User ID to remove: ");
                    int removeUserId = scan.nextInt();
                    network.removeUser(removeUserId, null);
                    break;

                case 3:
                    System.out.print("Enter Your User ID: ");
                    int userId1 = scan.nextInt();
                    System.out.print("Enter Friend's User ID: ");
                    int userId2 = scan.nextInt();
                    network.addFriend(userId1, userId2);
                    System.out.println("Friend added successfully.");
                    break;

                case 4:
                    System.out.print("Enter Your User ID: ");
                    int removeFriendId1 = scan.nextInt();
                    System.out.print("Enter Friend's User ID to remove: ");
                    int removeFriendId2 = scan.nextInt();
                    network.removeFriend(removeFriendId1, removeFriendId2);
                    System.out.println("Friend removed successfully.");
                    break;

                case 5:
                    System.out.print("Enter Your User ID: ");
                    int mutualUser1 = scan.nextInt();
                    System.out.print("Enter Another User's ID: ");
                    int mutualUser2 = scan.nextInt();

                    UserManagement user1 = network.getUserById(mutualUser1);
                    UserManagement user2 = network.getUserById(mutualUser2);

                    if (user1 == null || user2 == null) {
                        System.out.println("One or both users not found.");
                        break;
                    }

                    List<UserManagement> mutualFriends = network.getMutualFriends(user1, user2);

                    if (mutualFriends == null || mutualFriends.isEmpty()) {
                        System.out.println("No mutual friends found.");
                    } else {
                        System.out.println("Mutual Friends: ");
                        for (UserManagement mutualFriend : mutualFriends) {
                            System.out.println(mutualFriend.getName());
                        }
                    }
                    break;

                case 6:
                    System.out.print("Enter Your User ID: ");
                    int posterId = scan.nextInt();
                    scan.nextLine(); // Yeni satırı temizlemek için
                    System.out.print("Enter Your Message: ");
                    String message = scan.nextLine();
                    network.postMessage(posterId, message);
                    break;

                case 7:
                    System.out.print("Enter Your User ID to view feed: ");
                    int feedUserId = scan.nextInt();
                    network.viewNewsFeed(feedUserId);
                    break;

                case 8:
                    System.out.print("Enter User Name or ID to search: ");
                    String query = scan.next();
                    network.searchUser(query);
                    break;

                case 9:
                    System.out.println("Exiting... Goodbye!");
                    scan.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
