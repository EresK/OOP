import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class PizzaMaker {
    private Experience experience;
    private final ReentrantLock orderLock;
    private final ArrayList<Order> orderQueue;

    private final ReentrantLock stockLock;
    private final ArrayList<Order> stockQueue;

    PizzaMaker(Experience experience,
               ReentrantLock orderLock, ArrayList<Order> orderQueue,
               ReentrantLock stockLock, ArrayList<Order> stockQueue) {
        this.experience = experience;

        this.orderLock = orderLock;
        this.orderQueue = orderQueue;

        this.stockLock = stockLock;
        this.stockQueue = stockQueue;
    }

    public void start() {
        Order order;

        while (true) {
            /* Взять заказ */
            orderLock.lock();
            order = orderQueue.isEmpty() ? null : orderQueue.remove(0);
            orderLock.unlock();

            if (order != null) {
                /* Приготовить заказ */
                cookOrder(order);

                /* Передать на склад */
                while (true) {
                    stockLock.lock();
                    if (stockQueue.add(order)) {
                        stockLock.unlock();
                        break;
                    }
                    stockLock.unlock();
                    Thread.yield();
                }
            }
            Thread.yield();
        }
    }

    private void cookOrder(Order order) {
        order.state = State.COOKING;

        try {
            Random random = new Random();
            long time = random.nextLong(200, 500);

            Thread.sleep(time * (long)experience.getCoefficient());
        }
        catch (Exception exception) {
            System.err.println(exception.getMessage());
        }

        order.state = State.COOKED;
    }
}
