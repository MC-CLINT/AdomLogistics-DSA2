package maintenance;

public class MaintenanceScheduler {
    private MaintenanceRecord[] heap;
    private int size;

    public MaintenanceScheduler(int capacity) {
        this.heap = new MaintenanceRecord[capacity];
        this.size = 0;
    }

    public void schedule(MaintenanceRecord record) {
        if (size >= heap.length) resizeHeap();
        heap[size] = record;
        heapifyUp(size++);
    }

    private void heapifyUp(int index) {
        int parent = (index - 1) / 2;
        while (index > 0 &&
                heap[parent].serviceDate.compareTo(heap[index].serviceDate) > 0) {
            swap(parent, index);
            index = parent;
            parent = (index - 1) / 2;
        }
    }

    private void swap(int i, int j) {
        MaintenanceRecord temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private void resizeHeap() {
        MaintenanceRecord[] newHeap = new MaintenanceRecord[heap.length * 2];
        System.arraycopy(heap, 0, newHeap, 0, size);
        heap = newHeap;
    }

    public MaintenanceRecord getNextDue() {
        if (size == 0) return null;
        MaintenanceRecord next = heap[0];
        heap[0] = heap[--size];
        heapifyDown(0);
        return next;
    }

    private void heapifyDown(int index) {
        int smallest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if (left < size &&
                heap[left].serviceDate.compareTo(heap[smallest].serviceDate) < 0) {
            smallest = left;
        }
        if (right < size &&
                heap[right].serviceDate.compareTo(heap[smallest].serviceDate) < 0) {
            smallest = right;
        }
        if (smallest != index) {
            swap(index, smallest);
            heapifyDown(smallest);
        }
    }
}
