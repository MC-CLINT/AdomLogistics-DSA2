package driver;

public class DriverQueue {
    private static class Node {
        Driver driver;
        Node next;
        Node(Driver d) { this.driver = d; }
    }

    private Node head;
    private Node tail;

    public void enqueue(Driver d) {
        Node node = new Node(d);
        if (tail != null) {
            tail.next = node;
        }
        tail = node;
        if (head == null) {
            head = node;
        }
    }

    public Driver dequeue() {
        if (head == null) return null;
        Driver d = head.driver;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return d;
    }

    public Driver peek() {
        return head != null ? head.driver : null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public boolean removeDriver(String id) {
        Node current = head;
        Node prev = null;
        while (current != null) {
            if (current.driver.getDriverID().equals(id)) {
                if (prev == null) { // Removing head
                    head = current.next;
                    if (head == null) tail = null;
                } else {
                    prev.next = current.next;
                    if (current == tail) tail = prev;
                }
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }

    public boolean replaceDriver(String id, Driver newDriver) {
        Node current = head;
        while (current != null) {
            if (current.driver.getDriverID().equals(id)) {
                current.driver = newDriver;
                return true;
            }
            current = current.next;
        }
        return false;
    }
}
