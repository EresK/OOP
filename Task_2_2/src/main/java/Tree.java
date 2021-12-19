import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Tree<T> {
    private Node<T> root;
    private int size;
    private int curr;

    Tree(T value, int branches) {
        root = new Node<>(value, branches);
        size = 1 + branches;
        curr = 1;
    }

    public int getSize() {
        return size;
    }

    public int getCurr() {
        return curr;
    }

    public boolean hasSpace() {
        return curr < size;
    }

    public boolean add(T value, int branches) {
        if (branches < 0)
            return false;

        Node<T> node = root;
        boolean isAdded = false;
        Queue<Node<T>> queue = new LinkedList<>();

        while (node != null) {
            if (node.hasSpace() && node.add(value, branches)) {
                size += branches;
                curr += 1;
                isAdded = true;

                break;
            }
            else {
                for (int i = 0; i < node.nodes.length; i++) {
                    if (node.nodes[i] != null)
                        queue.add((Node<T>) node.nodes[i]);
                }
                node = queue.poll();
            }
        }

        return isAdded;
    }

    public boolean remove(int index) {
        if (index < 0 || index >= size)
            return false;

        boolean isRemoved = false;

        Node<T> node = root;
        Node<T> prev = null;
        int id = 0;
        Queue<Object> queue = new LinkedList<>();

        while (node != null) {
            if (id == index) {
                int[] freeSize = node.removeAll();
                size -= freeSize[0];
                curr -= freeSize[1] + 1;

                if (prev != null) {
                    for (int i = 0; i < prev.nodes.length; i++) {
                        if (prev.nodes[i] == node)
                            prev.nodes[i] = null;
                    }
                    prev.curr -= 1;
                }
                else root = null;

                isRemoved = true;
                break;
            }
            else {
                for (int i = 0; i < node.nodes.length; i++) {
                    if (node.nodes[i] != null)
                        queue.add(node.nodes[i]);
                }
                prev = node;
                node = (Node<T>) queue.poll();
            }
        }

        return isRemoved;
    }

    public Object[] toArray() {
        Node<T> node = root;
        Queue<Object> queue = new LinkedList<>();

        Object[] array = new Object[curr];
        array[0] = root.value;
        int id = 1;

        while (node != null) {
            for (int i = 0; i < node.nodes.length; i++) {
                if (node.nodes[i] != null) {
                    queue.add(node.nodes[i]);
                    array[id++] = ((Node<T>) node.nodes[i]).value;
                }
            }
            node = (Node<T>) queue.poll();
        }

        return array;
    }
}
