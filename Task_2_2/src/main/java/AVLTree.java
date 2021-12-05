public class AVLTree<T extends Comparable<T>> {
    private Node<T> root;

    public void add(T value) { root = insert(root, value); }

    public void remove(T value) {
        root = delete(root, value);
    }

    /*  Small left rotate          *   Small right rotate
     *    a                 b      *         b              a
     *  /   \             /   \    *       /   \          /   \
     * e     b     =>    a     d   *      a     d   =>   e     b
     *     /   \       /   \       *    /   \                /   \
     *    c     d     e     c      *   e     c              c     d
     */
    private Node<T> rotateLeft(Node<T> node) {
        Node<T> nodeR = node.right;
        Node<T> nodeRL = nodeR.left;

        nodeR.left = node;
        node.right = nodeRL;

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        nodeR.height = Math.max(height(nodeR.left), height(nodeR.right)) + 1;

        return nodeR;
    }

    private Node<T> rotateRight(Node<T> node) {
        Node<T> nodeL = node.left;
        Node<T> nodeLR = nodeL.right;

        nodeL.right = node;
        node.left = nodeLR;

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        nodeL.height = Math.max(height(nodeL.left), height(nodeL.right)) + 1;

        return nodeL;
    }

    private int difference(Node<T> node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    private int height(Node<T> node) {
        if (node == null)
            return 0;
        return node.height;
    }

    private Node<T> insert(Node<T> node, T value) {
        if (node == null)
            return new Node<>(value);

        // value < node.value
        if (value.compareTo(node.value) < 0)
            node.left = insert(node.left, value);
        // value > node.value
        else if (value.compareTo(node.value) > 0)
            node.right = insert(node.right, value);
        // value == node.value
        else
            return node;

        /* Checks that after insertion tree saves invariant */
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        int diff = difference(node);

        /* Big right rotate or small right rotate */
        if (diff > 1) {
            if (value.compareTo(node.left.value) < 0) {
                return rotateRight(node);
            }
            else if (value.compareTo(node.left.value) > 0) {
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
        }

        /* Big left rotate or small left rotate */
        if (diff < -1) {
            if (value.compareTo(node.right.value) > 0) {
                return rotateLeft(node);
            }
            else if (value.compareTo(node.right.value) < 0) {
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }
        }

        return node;
    }

    private Node<T> minimumValue(Node<T> node) {
        Node<T> current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    private Node<T> delete(Node<T> node, T value) {
        if (node == null)
            return node;

        // value < node.value
        if (value.compareTo(node.value) < 0)
            node.left = delete(node.left, value);
        // value > node.value
        else if (value.compareTo(node.value) > 0)
            node.right = delete(node.right, value);
        // value == node.value
        else {
            if (node.left == null || node.right == null) {
                if (node.left == null)
                    node = node.right;
                else
                    node = node.left;
            }
            else {
                Node<T> tmp = minimumValue(node.right);
                node.value = tmp.value;
                node.right = delete(node.right, tmp.value);
            }
        }

        if (node == null)
            return node;

        /* Checks that after insertion tree saves invariant */
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        int diff = difference(node);

        /* Big right rotate or small right rotate */
        if (diff > 1) {
            if (difference(node.left) >= 0) {
                return rotateRight(node);
            }
            else {
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
        }

        /* Big left rotate or small left rotate */
        if (diff < -1) {
            if (difference(node.right) <= 0) {
                return rotateLeft(node);
            }
            else {
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }
        }

        return node;
    }

    /*
    public void printTree() {
        print(root, "", true);
    }

    private void print(Node<T> currPtr, String indent, boolean last) {
        if (currPtr != null) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "   ";
            } else {
                System.out.print("L----");
                indent += "|  ";
            }
            System.out.println(currPtr.value);
            print(currPtr.left, indent, false);
            print(currPtr.right, indent, true);
        }
    }
    */
}
