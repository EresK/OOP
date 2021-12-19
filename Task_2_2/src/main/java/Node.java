import java.util.LinkedList;
import java.util.Queue;

class Node<T> {
    public T value;
    public final Object[] nodes;
    public int size; // size of nodes array
    public int curr; // number of existing(not-null) elements in nodes array

    Node(T value, int branches) {
        this.value = value;
        nodes = new Object[branches];
        size = branches;
        curr = 0;
    }

    /**
     * @param value - value of T
     * @param branches - number of branches of the node
     * @return - true if node was added, false otherwise
     */
    public boolean add(T value, int branches) {
        if (curr >= size)
            return false;

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] == null) {
                nodes[i] = new Node<>(value, branches);
                curr++;
                break;
            }
        }

        return true;
    }

    /**
     * @return - array with length 2, where [0] is removed size, [1] is removed curr
     */
    public int[] removeAll() {
        Queue<Object> queue = new LinkedList<>();

        //[0] - size, [1] - curr
        int[] freeSize = new int[] {0, 0};

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null) {
                queue.add(nodes[i]);
                freeSize[1] += 1;
            }
            nodes[i] = null;
        }
        freeSize[0] += nodes.length;

        Node<T> node = (Node<T>) queue.poll();

        while (node != null) {
            for (int i = 0; i < node.nodes.length; i++) {
                if (node.nodes[i] != null) {
                    queue.add(node.nodes[i]);
                    freeSize[1] += 1;
                }
                node.nodes[i] = null;
            }
            freeSize[0] += node.nodes.length;

            node = (Node<T>) queue.poll();
        }

        curr = 0;
        return freeSize;
    }

    /**
     * @return - true if node has free(null) branch, false otherwise
     */
    public boolean hasSpace() {
        return curr < size;
    }
}
