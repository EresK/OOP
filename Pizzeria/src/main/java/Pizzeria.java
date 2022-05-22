import com.fasterxml.jackson.annotation.*;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@JsonPropertyOrder({"orderSize", "stockSize", "orderCreators", "experiences", "backpackSizes"})
public class Pizzeria {
    private final OrderQueue<Order> orderQueue;
    private final OrderQueue<Order> stockQueue;
    private final ArrayList<PizzaMaker> makers;
    private final ArrayList<Deliverer> deliverers;
    private final int orderCreators;
    private int id;

    Pizzeria(int orderQueueSize, int stockQueueSize, int orderCreators,
             int[] experiences, int[] backpackSizes) {
        orderQueue = new OrderQueue<>(orderQueueSize);
        stockQueue = new OrderQueue<>(stockQueueSize);

        this.orderCreators = orderCreators;
        id = 0;

        makers = new ArrayList<>();
        deliverers = new ArrayList<>();

        for (int i = 0; i < experiences.length; i++)
            makers.add(new PizzaMaker(i, experiences[i], this));

        for (int i = 0; i < backpackSizes.length; i++)
            deliverers.add(new Deliverer(i, backpackSizes[i], this));
    }

    Pizzeria(int orderQueueSize, int stockQueueSize, int orderCreators,
             ArrayList<Integer> experiences, ArrayList<Integer> backpackSizes) {
        orderQueue = new OrderQueue<>(orderQueueSize);
        stockQueue = new OrderQueue<>(stockQueueSize);

        this.orderCreators = orderCreators;
        id = 0;

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

    @JsonGetter("orderSize")
    public int getOrderQueueSize() {
        return orderQueue.getMaxSize();
    }

    @JsonGetter("stockSize")
    public int getStockQueueSize() {
        return stockQueue.getMaxSize();
    }

    @JsonGetter("orderCreators")
    public int getOrderCreators() {
        return orderCreators;
    }

    @JsonGetter("experiences")
    public ArrayList<Integer> getExperiences() {
        ArrayList<Integer> experiences = new ArrayList<>();
        for (PizzaMaker p : makers)
            experiences.add(p.getExperience());
        return experiences;
    }

    @JsonGetter("backpackSizes")
    public ArrayList<Integer> getBackpackSizes() {
        ArrayList<Integer> backpackSizes = new ArrayList<>();
        for (Deliverer d : deliverers)
            backpackSizes.add(d.getBackpackSize());
        return backpackSizes;
    }

    protected synchronized int getId() {
        return id++;
    }

    protected void addToOrder(Order order) {
        try {
            synchronized (orderQueue) {
                do {
                    if (!orderQueue.push(order))
                        orderQueue.wait();
                    else {
                        System.out.printf("%s [%d]: ORDERED\n", order.name, order.id);
                        break;
                    }
                } while (true);
            }
        }
        catch (InterruptedException ex) {
            System.err.println(ex.getMessage());
        }
    }

    protected void addToStock(Order order, int makerId) {
        try {
            synchronized (stockQueue) {
                do {
                    if (!stockQueue.push(order))
                        stockQueue.wait();
                    else {
                        System.out.printf("%s [%d]: ADDED TO STOCK by [%d]\n", order.name, order.id, makerId);
                        break;
                    }
                } while (true);
            }
        }
        catch (InterruptedException ex) {
            System.err.println(ex.getMessage());
        }
    }

    protected Order getFromOrder() {
        Order order = null;
        try {
            synchronized (orderQueue) {
                order = orderQueue.pop();
                orderQueue.notifyAll();
            }
        }
        catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        return order;
    }

    protected void getFromStock(int backpackSize, ArrayList<Order> backpack) {
        try {
            synchronized (stockQueue) {
                int min = Math.min(backpackSize, stockQueue.getSize());

                for (int i = 0; i < min; i++)
                    backpack.add(stockQueue.pop());

                stockQueue.notifyAll();
            }
        }
        catch (IllegalMonitorStateException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
