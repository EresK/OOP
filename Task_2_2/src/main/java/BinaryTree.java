import java.util.Collection;
import java.util.List;

public class BinaryTree<T extends Comparable<T>> {
    private int sizeTree;
    private Node<T> root;

    BinaryTree() {
        sizeTree = 0;
        root = null;
    }

    public boolean add(T value) {
        if (root == null)
            root = new Node<>(value);
        else {
            Node<T> node = root;
            while (true) {
                if (value.compareTo(node.value) < 0) {
                    if (node.left != null) {
                        node = node.left;
                    }
                    else {
                        node.left = new Node<>(value);
                        sizeTree += 1;
                        break;
                    }
                }
                else if (value.compareTo(node.value) > 0) {
                    if (node.right != null) {
                        node = node.right;
                    }
                    else {
                        node.right = new Node<>(value);
                        sizeTree += 1;
                        break;
                    }
                }
                else
                    return false;
            }
        }
        return true;
    }

    public boolean addAll(Collection<T> c) {
        boolean isAddAll = true;
        for (T value : c) {
            if (!add(value))
                isAddAll = false;
        }
        return isAddAll;
    }

    public void clear() {
        root = null;
    }

    public boolean contains(T value) {
        boolean isContain = false;
        if (root == null)
            isContain = true;
        else {
            Node<T> node = root;
            while (true) {
                if (value.compareTo(node.value) < 0) {
                    if (node.left != null)
                        node = node.left;
                    else break;
                }
                else if (value.compareTo(node.value) > 0) {
                    if (node.right != null)
                        node = node.right;
                    else break;
                }
                else isContain = true;
            }
        }
        return isContain;
    }

    public boolean containsAll(Collection<T> c) {
        for (T value : c) {
            if (!contains(value))
                return false;
        }
        return true;
    }

    public boolean isEmpty() {
        return root == null;
    }

    /* Removal (the same for right nodes)
     *         previous               previous
     *        /        \             /        \
     *      node        a    =>     b          a
     *    /     \
     *  null     b
     *
     *         previous               previous
     *        /        \             /        \
     *      node        a    =>     d          a
     *    /     \                 /  \
     *   b       c               b    c
     *          / \                    \
     *         d   e                    e
     */
    public boolean remove(T value) {
        if (root != null) {
            Node<T> node = root;
            Node<T> previous = null;

            while (node != null) {
                // value < node.value
                if (value.compareTo(node.value) < 0) {
                    previous = node;
                    node = node.left;
                }
                // value > node.value
                else if (value.compareTo(node.value) > 0) {
                    previous = node;
                    node = node.right;
                }
                // value == node.value
                else {
                    if (node.left == null) {
                        if (previous == null)
                            root = node.right;
                        else {
                            if (previous.left == node)
                                previous.left = node.right;
                            else
                                previous.right = node.right;
                        }
                    }
                    else if (node.right == null) {
                        if (previous == null)
                            root = node.left;
                        else {
                            if (previous.left == node)
                                previous.left = node.left;
                            else
                                previous.right = node.left;
                        }
                    }
                    else {
                        previous = node;
                        Node<T> pos = node.right;

                        while (pos.left != null) {
                            previous = pos;
                            pos = pos.left;
                        }
                        node.value = pos.value;

                        if (pos.right != null)
                            previous.right = pos.right;
                        else
                            previous.left = null;
                    }

                    sizeTree -= 1;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean removeAll(Collection<T> c) {
        boolean isRemoveAll = true;
        for (T value : c) {
            if (!remove(value))
                isRemoveAll = false;
        }
        return isRemoveAll;
    }

    public int size() {
        return sizeTree;
    }

    public Object[] toArray() {
        Object[] values = new Object[sizeTree];
        int valCnt = 0;

        Object[] buff = new Object[sizeTree];
        buff[0] = root;
        int buffBeg = 0;
        int buffEnd = 1;

        Node<T> node;
        while (buff[buffBeg] != null) {
            node = (Node<T>) buff[buffBeg++];

            values[valCnt++] = node.value;

            if (node.left != null)
                buff[buffEnd++] = node.left;
            if (node.right != null)
                buff[buffEnd++] = node.right;
        }

        return values;
    }
}
