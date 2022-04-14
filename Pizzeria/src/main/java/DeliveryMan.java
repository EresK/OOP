import java.util.ArrayList;
import java.util.Random;

public class DeliveryMan {
    private int backpackSize;
    private final ArrayList<Order> backpack;

    private final OrderQueue<Order> stockQueue;
    private final Random random;

    DeliveryMan(int backpackSize, OrderQueue<Order> stockQueue) {
        this.backpackSize = backpackSize;
        backpack = new ArrayList<>();

        this.stockQueue = stockQueue;
        random = new Random();
    }

    public void start() {
        while(true) {
            getOrderFromStock();
            deliver();
        }
    }

    private void getOrderFromStock() {
        while (true) {
            synchronized (stockQueue) {
                if (stockQueue.getSize() < 1) {
                    try {
                        stockQueue.wait();
                    }
                    catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                }
                else {
                    Order order;
                    do {
                        order = stockQueue.pop();
                        backpack.add(order);
                    } while (order != null && (backpack.size() < backpackSize));
                    break;
                }
            }
        }
    }

    private void deliver() {
        for (Order order : backpack) {
            try {
                Thread.sleep(random.nextLong(300, 600));
            }
            catch (Exception exception) {
                System.err.println(exception.getMessage());
            }
            order.state = State.ORDERED;
            System.out.printf("%s [%d]: %s", order.name, order.id, order.state);
        }
        backpack.clear();
    }
}
