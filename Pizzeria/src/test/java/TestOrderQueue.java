import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestOrderQueue {

    @Test
    public void OrderQueueTesting() {
        OrderQueue<Order> queue = new OrderQueue<>(3);

        Order o1 = new Order("1", 1);
        Order o2 = new Order("2", 2);
        Order o3 = new Order("3", 3);

        Assertions.assertTrue(queue.push(o1));
        Assertions.assertTrue(queue.push(o2));
        Assertions.assertTrue(queue.push(o3));
        Assertions.assertFalse(queue.push(new Order("4", 4)));

        Assertions.assertEquals(o1, queue.pop());
        Assertions.assertEquals(o2, queue.pop());
        Assertions.assertEquals(o3, queue.pop());
        Assertions.assertNull(queue.pop());
    }
}
