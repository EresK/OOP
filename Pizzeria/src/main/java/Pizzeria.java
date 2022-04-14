import java.util.ArrayList;

public class Pizzeria {
    private int orderQueueSize;
    private int stockQueueSize;

    private final ArrayList<PizzaMan> pizzaMen;
    private final ArrayList<DeliveryMan> deliveryMen;
    private final OrderQueue<Order> orderQueue;
    private final OrderQueue<Order> stockQueue;

    Pizzeria(int orderQueueSize, int stockQueueSize,
             ArrayList<PizzaMan> pizzaMen, ArrayList<DeliveryMan> deliveryMen) throws IllegalArgumentException {
        if (pizzaMen.size() < 1 || deliveryMen.size() < 1)
            throw new IllegalArgumentException("Arrays must contain elements");

        this.orderQueueSize = orderQueueSize;
        this.stockQueueSize = stockQueueSize;

        this.pizzaMen = pizzaMen;
        this.deliveryMen = deliveryMen;

        orderQueue = new OrderQueue<>(orderQueueSize);
        stockQueue = new OrderQueue<>(stockQueueSize);
    }

    public void run() {

    }
}
