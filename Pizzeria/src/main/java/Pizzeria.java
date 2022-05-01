import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Pizzeria {
    private final OrderQueue<Order> orderQueue;
    private final OrderQueue<Order> stockQueue;
    private final ArrayList<PizzaMaker> makers;
    private final ArrayList<Deliverer> deliverers;
    private final int orderCreators;

    Pizzeria(int orderQueueSize, int stockQueueSize, int orderCreators,
             ArrayList<Integer> experiences, ArrayList<Integer> backpackSizes) {
        orderQueue = new OrderQueue<>(orderQueueSize);
        stockQueue = new OrderQueue<>(stockQueueSize);
        this.orderCreators = orderCreators;

        makers = new ArrayList<>();
        deliverers = new ArrayList<>();

        for (int i = 0; i < experiences.size(); i++)
            makers.add(new PizzaMaker(i, experiences.get(i), this));

        for (int i = 0; i < backpackSizes.size(); i++)
            deliverers.add(new Deliverer(i, backpackSizes.get(i), this));
    }

    public void run() {
        ExecutorService pool = Executors.newFixedThreadPool(16);

        for (int i = 0; i < orderCreators; i++)
            pool.submit(new OrderCreator(this));

        for (PizzaMaker p : makers)
            pool.submit(p);

        for (Deliverer d : deliverers)
            pool.submit(d);
    }

    protected synchronized boolean addToOrder(Order order) {
        return orderQueue.push(order);
    }

    protected synchronized boolean addToStock(Order order) {
        return stockQueue.push(order);
    }

    protected synchronized Order getFromOrder() {
        return orderQueue.pop();
    }

    protected synchronized void getFromStock(int backpackSize, ArrayList<Order> backpack) {
        int min = Math.min(backpackSize, stockQueue.getSize());

        for (int i = 0; i < min; i++)
            backpack.add(stockQueue.pop());
    }
}
