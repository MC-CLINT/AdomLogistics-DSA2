package vehicle;

public class VehicleTree {
  private class Node {
    Vehicle vehicle;
    Node left, right;

    Node(Vehicle vehicle) {
      this.vehicle = vehicle;
    }  
  }

  private Node root;

public void addVehicle(Vehicle vehicle) {
    root = insert(root, vehicle);
  }
  private Node insert(Node node, Vehicle vehicle) {
    if (node == null) {
      return new Node(vehicle);
    }
     int cmp = vehicle.getRegNumber().compareTo(node.vehicle.getRegNumber());
        if (cmp < 0) {
            node.left = insert(node.left, vehicle);
        } else if (cmp > 0) {
            node.right = insert(node.right, vehicle);
        } else {
            // Equal keys: replace existing vehicle
            node.vehicle = vehicle;
        }
        return node;
    }
    public Vehicle searchVehicle(String regNumber) {
        Node current = root;
        while (current != null) {
            int cmp = regNumber.compareTo(current.vehicle.getRegNumber());
            if (cmp == 0) {
                return current.vehicle;
            } else if (cmp < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return null;
    }
    public boolean removeVehicle(String regNumber) {
        if (searchVehicle(regNumber) == null) {
            return false;
        }
        root = delete(root, regNumber);
        return true;
    }

    private Node delete(Node node, String regNumber) {
        if (node == null) {
            return null;
        }
        int cmp = regNumber.compareTo(node.vehicle.getRegNumber());
        if (cmp < 0) {
            node.left = delete(node.left, regNumber);
        } else if (cmp > 0) {
            node.right = delete(node.right, regNumber);
        } else {
            // Node to delete found
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                // Two children: get inorder successor (smallest in right subtree)
                Node successor = min(node.right);
                node.vehicle = successor.vehicle;
                node.right = delete(node.right, successor.vehicle.getRegNumber());
            }
        }
        return node;
    }

    private Node min(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    public void displayVehicles() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.println(node.vehicle);
            inOrderTraversal(node.right);
        }
    }
    public int size() {
        return countNodes(root);
    }

    private int countNodes(Node node) {
        if (node == null) return 0;
        return 1 + countNodes(node.left) + countNodes(node.right);
    }
}

