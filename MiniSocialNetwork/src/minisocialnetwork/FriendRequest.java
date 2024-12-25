package minisocialnetwork; 

public class FriendRequest {
    private int senderId;
    private int receiverId;
    private String status;

    public FriendRequest(int senderId, int receiverId) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.status = "PENDING";
    }

    public int getSenderId() {
        return senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public String getStatus() {
        return status;
    }

    public void accept() {
        this.status = "ACCEPTED";
    }

    public void reject() {
        this.status = "REJECTED";
    }
}
