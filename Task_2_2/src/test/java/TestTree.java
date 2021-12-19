import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestTree {

    @Test
    public void TestingNode() {
        Node<Integer> node = new Node<>(5, 2);

        Assertions.assertTrue(node.hasSpace());
        node.add(1, 0);

        Assertions.assertTrue(node.hasSpace());
        node.add(2, 0);

        Assertions.assertFalse(node.hasSpace());
        Assertions.assertFalse(node.add(10, 5));

        Assertions.assertEquals(1, ((Node<Integer>) node.nodes[0]).value);
        Assertions.assertEquals(2, ((Node<Integer>) node.nodes[1]).value);

        node.removeAll();
        Assertions.assertTrue(node.hasSpace());

        Assertions.assertNull(node.nodes[0]);
        Assertions.assertNull(node.nodes[1]);
    }

    @Test
    public void TestingTree() {

    }
}
