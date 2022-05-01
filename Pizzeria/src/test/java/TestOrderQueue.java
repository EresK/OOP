import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestOrderQueue {

    @Test
    public void OrderQueueTesting() {
        OrderQueue<Order> queue = new OrderQueue<>(3);

        Order o1 = new Order("1", 1);
        Order o2 = new Order("2", 2);
        Order o3 = new Order("3", 3);

        Assertions.assertEquals(3, queue.getMaxSize());
        Assertions.assertEquals(0, queue.getSize());

        Assertions.assertTrue(queue.push(o1));
        Assertions.assertEquals(1, queue.getSize());
        Assertions.assertTrue(queue.push(o2));
        Assertions.assertEquals(2, queue.getSize());
        Assertions.assertTrue(queue.push(o3));
        Assertions.assertEquals(3, queue.getSize());
        Assertions.assertFalse(queue.push(new Order("4", 4)));

        Assertions.assertEquals(o1, queue.pop());
        Assertions.assertEquals(o2, queue.pop());
        Assertions.assertEquals(o3, queue.pop());
        Assertions.assertNull(queue.pop());
        Assertions.assertEquals(0, queue.getSize());
    }
}
