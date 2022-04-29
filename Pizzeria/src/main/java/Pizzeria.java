import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Pizzeria {
    private final OrderQueue<Order> orderQueue;
    private final OrderQueue<Order> stockQueue;
    private final ArrayList<PizzaMaker> makers;
    private final ArrayList<Deliverer> deliverers;

    Pizzeria(int orderQueueSize, int stockQueueSize,
             ArrayList<Integer> experiences, ArrayList<Integer> backpackSizes) {
        orderQueue = new OrderQueue<>(orderQueueSize);
        stockQueue = new OrderQueue<>(stockQueueSize);

        makers = new ArrayList<>();
        deliverers = new ArrayList<>();

        for (Integer exp : experiences)
            makers.add(new PizzaMaker(exp, this));

        for (Integer size : backpackSizes)
            deliverers.add(new Deliverer(size, this));
    }

    public void run() {
        ExecutorService pool = Executors.newFixedThreadPool(16);

        //pool.submit(new OrderCreator(this));

        /*orderQueue.push(new Order("Pizza", 1));
        pool.submit(makers.get(0));
        pool.submit(deliverers.get(0));*/

        /*for (PizzaMaker p : makers)
            pool.submit(p);

        for (Deliverer d : deliverers)
            pool.submit(d);*/

        Thread[] threads = new Thread[16];

        threads[0] = new Thread(new OrderCreator(this));
        threads[0].start();

        threads[1] = new Thread(new PizzaMaker(1, this));
        threads[1].start();

        threads[2] = new Thread(new Deliverer(3, this));
        threads[2].start();
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
