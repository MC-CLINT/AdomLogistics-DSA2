package delivery;

public class Node {
    PackageDelivery data;
    Node next;

    public Node(PackageDelivery data) {
        this.data = data;
        this.next = null;
    }
}
