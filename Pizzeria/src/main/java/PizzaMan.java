import java.util.Random;

public class PizzaMan {
    private Experience experience;
    private final OrderQueue<Order> orderQueue;
    private final OrderQueue<Order> stockQueue;
    private final Random random;

    PizzaMan(Experience experience, OrderQueue<Order> orderQueue, OrderQueue<Order> stockQueue) {
        this.experience = experience;
        this.orderQueue = orderQueue;
        this.stockQueue = stockQueue;
        random = new Random();
    }

    public void start() {
        Order order;

        while (true) {
            order = getOrderFromQueue();
            cook(order);
            putOrderToStock(order);
        }
    }

    private Order getOrderFromQueue() {
        Order order = null;

        while (order == null) {
            synchronized (orderQueue) {
                order = orderQueue.pop();
                try {
                    if (order == null) orderQueue.wait();
                }
                catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }

        return order;
    }

    private void putOrderToStock(Order order) {
        boolean isAddedToStock = false;

        while (!isAddedToStock) {
            synchronized (stockQueue) {
                isAddedToStock = stockQueue.push(order);
                try {
                    if (!isAddedToStock) {
                        stockQueue.notifyAll();
                    }
                }
                catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
            Thread.yield();
        }
    }

    private void cook(Order order) {
        order.state = State.COOKING;
        System.out.printf("%s [%d]: %s", order.name, order.id, order.state);

        try {
            long time = random.nextLong(200, 500);
            Thread.sleep(time * (long)experience.getCoefficient());
        }
        catch (Exception exception) {
            System.err.println(exception.getMessage());
        }

        order.state = State.COOKED;
        System.out.printf("%s [%d]: %s", order.name, order.id, order.state);
    }
}
