import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

public class TestTree {

    @Test
    public void TestingNode() {
        Node<Integer> node = new Node<>(5, 2);

        // Adding nodes
        Assertions.assertEquals(2, node.size);
        Assertions.assertEquals(0, node.curr);

        Assertions.assertTrue(node.hasSpace());
        node.add(1, 2);
        Assertions.assertEquals(1, node.curr);

        Assertions.assertTrue(node.hasSpace());
        node.add(2, 0);
        Assertions.assertEquals(2, node.curr);

        // Try to add extra node
        Assertions.assertFalse(node.hasSpace());
        Assertions.assertFalse(node.add(10, 5));

        Assertions.assertEquals(1, ((Node<Integer>) node.nodes[0]).value);
        Assertions.assertEquals(2, ((Node<Integer>) node.nodes[1]).value);

        // Removing all subtrees
        node.removeAll();

        Assertions.assertTrue(node.hasSpace());
        Assertions.assertEquals(2, node.size);
        Assertions.assertEquals(0, node.curr);

        Assertions.assertNull(node.nodes[0]);
        Assertions.assertNull(node.nodes[1]);
    }

    @Test
    public void TestingTree() {
        Tree<String> tree = new Tree<>("string", 3);

        Assertions.assertTrue(tree.hasSpace());
        tree.add("another string", 1);
        tree.add("second branch", 0);
        tree.add("third branch", 0);

        Assertions.assertTrue(tree.hasSpace());
        Assertions.assertEquals(5, tree.getCount());
        Assertions.assertEquals(4, tree.getCurr());

        // This node will be placed in third level of tree
        tree.add("third level", 0);
        Assertions.assertFalse(tree.hasSpace());

        Assertions.assertArrayEquals(
                new Object[] {"string", "another string", "second branch", "third branch", "third level"},
                tree.toArray());

        // Removing all subtree
        tree.remove(1);
        Assertions.assertTrue(tree.hasSpace());

        Assertions.assertArrayEquals(
                new Object[] {"string", "second branch", "third branch"},
                tree.toArray());

        // New node will be placed in a first free(null) branch
        tree.add("instead of another string", 5);
        Assertions.assertTrue(tree.hasSpace());

        Assertions.assertArrayEquals(
                new Object[] {"string", "instead of another string", "second branch", "third branch"},
                tree.toArray());

        // Removing all tree
        tree.removeAll();
        Assertions.assertEquals(0, tree.getCount());
        Assertions.assertEquals(0, tree.getCurr());

        Assertions.assertArrayEquals(new Object[0], tree.toArray());

        // Adding new element after removeAll operation
        tree.add("the root", 10);
        Assertions.assertEquals(11, tree.getCount());
        Assertions.assertEquals(1, tree.getCurr());

        Assertions.assertArrayEquals(new Object[] {"the root"}, tree.toArray());

        // Adding collection of elements
        Collection<String> c = new ArrayList<>();
        c.add("Second");
        c.add("Third");
        c.add("Fourth");

        Assertions.assertEquals(3, tree.addAll(c, 2));
        Assertions.assertEquals(17, tree.getCount());
        Assertions.assertEquals(4, tree.getCurr());

        Assertions.assertArrayEquals(new Object[] {"the root", "Second", "Third", "Fourth"}, tree.toArray());
    }
}
