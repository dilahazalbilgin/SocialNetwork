
import java.util.Scanner;
import minisocialnetwork.Network;


public class MiniSocialNetwork {

    public static void main(String[] args) {
        Network network = new Network();
        boolean isUsersAdded = false; 
        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome!!!");

        while (true) {
            System.out.println("\nPlease enter your step:");
            System.out.println("1 - Add User");
            System.out.println("2 - Remove User");
            System.out.println("3 - Send Friend Request");
            System.out.println("4 - Accept Friend Request");
            System.out.println("5 - Reject Friend Request");
            System.out.println("6 - Remove Friend");
            System.out.println("7 - Find Mutual Friend");
            System.out.println("8 - Post Message");
            System.out.println("9 - View Feed");
            System.out.println("10 - Search User");
            System.out.println("11 - Recommend Friends");
            System.out.println("12 - Exit");

            int ans = scan.nextInt();

            if (!isUsersAdded && ans != 1 && ans != 12) {
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

                case 3:
                    System.out.print("Enter Your User ID: ");
                    int senderId = scan.nextInt();
                    System.out.print("Enter Friend's User ID: ");
                    int receiverId = scan.nextInt();
                    network.sendFriendRequest(senderId, receiverId);
                    break;

                case 4:
                    System.out.print("Enter Your User ID: ");
                    int acceptSenderId = scan.nextInt();
                    System.out.print("Enter Friend's User ID to accept: ");
                    int acceptReceiverId = scan.nextInt();
                    network.acceptFriendRequest(acceptSenderId, acceptReceiverId);
                    break;

                case 5:
                    System.out.print("Enter Your User ID: ");
                    int rejectSenderId = scan.nextInt();
                    System.out.print("Enter Friend's User ID to reject: ");
                    int rejectReceiverId = scan.nextInt();
                    network.rejectFriendRequest(rejectSenderId, rejectReceiverId);
                    break;

                // Other cases remain unchanged

                case 12:
                    System.out.println("Exiting... Goodbye!");
                    scan.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
