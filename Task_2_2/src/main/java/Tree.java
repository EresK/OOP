import java.util.*;

public class Tree<T> implements Iterable<T>{
    private Node<T> root;
    private int count; // Number of all possible nodes
    private int curr; // Number of existing(not-null) nodes

    Tree(T value, int branches) {
        root = new Node<>(value, branches);
        count = 1 + branches;
        curr = 1;
    }

    /**
     * @return - number of all possible nodes in the tree
     */
    public int getCount() {
        return count;
    }

    /**
     * @return - number of existing(not-null) nodes in the tree
     */
    public int getCurr() {
        return curr;
    }

    /**
     * @return - true if the tree has free(null) branch, false otherwise
     */
    public boolean hasSpace() {
        return curr < count;
    }

    /**
     * @param value - value of T
     * @param branches - number of branches of a node
     * @return - true if node was added, false otherwise
     */
    public boolean add(T value, int branches) {
        if (branches < 0)
            return false;

        boolean isAdded = false;

        if (root == null) {
            root = new Node<>(value, branches);
            count = 1 + branches;
            curr = 1;

            isAdded = true;
        }
        else {
            Node<T> node = root;
            Queue<Node<T>> queue = new LinkedList<>();

            while (node != null) {
                if (node.hasSpace() && node.add(value, branches)) {
                    count += branches;
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
        }

        return isAdded;
    }

    /**
     * @param c - collection of T values
     * @param branches - number of branches of each node
     * @return - number of added elements
     */
    public int addAll(Collection<T> c, int branches) {
        if (c.size() > count - curr)
            return 0;

        int added = 0;

        for (T value : c) {
            if (add(value, branches))
                added += 1;
        }

        return added;
    }

    /**
     * @param index - index of node to remove
     * @return - true if element was removed, false otherwise
     */
    public boolean remove(int index) {
        if (index < 0 || index >= count)
            return false;

        boolean isRemoved = false;

        Node<T> node = root;
        Node<T> prev = null;
        int id = 0;
        Queue<Object> queue = new LinkedList<>();

        while (node != null) {
            if (id == index) {
                int[] freeSize = node.removeAll();
                count -= freeSize[0];
                curr -= freeSize[1] + 1;

                if (prev != null) {
                    for (int i = 0; i < prev.nodes.length; i++) {
                        if (prev.nodes[i] == node) {
                            prev.nodes[i] = null;
                            break;
                        }
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
            id += 1;
        }

        return isRemoved;
    }

    /**
     * Removing all nodes in the tree
     */
    public void removeAll() {
        Node<T> node = root;
        Queue<Object> queue = new LinkedList<>();

        while (node != null) {
            for (int i = 0; i < node.nodes.length; i++) {
                if (node.nodes[i] != null)
                    queue.add(node.nodes[i]);
                node.nodes[i] = null;
            }
            node = (Node<T>) queue.poll();
        }

        count = 0;
        curr = 0;
        root = null;
    }

    /**
     * @return - array of T elements placed by left to right BFS
     */
    public Object[] toArray() {
        if (count < 1)
            return new Object[0];

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

    @Override
    public Iterator<T> iterator() {
        Iterator<T> itr = new Iterator<T>() {
            private Node<T> node;
            private Queue<Object> queue;

            @Override
            public boolean hasNext() {
                if (node == null)
                    init();

                return queue.peek() != null;
            }

            @Override
            public T next() {
                if (node == null)
                    init();

                node = (Node<T>) queue.poll();

                if (node != null) {
                    for (int i = 0; i < node.nodes.length; i++) {
                        if (node.nodes[i] != null)
                            queue.add(node.nodes[i]);
                    }
                }
                else
                    throw new NoSuchElementException("There is no next element in tree");

                return node.value;
            }

            private void init() {
                queue = new LinkedList<>();
                queue.add(root);
            }
        };

        return itr;
    }
}
