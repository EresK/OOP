import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class Pizzeria {
    private int pizzaMakerNum;
    private int deliverymanNum;
    private int orderSize;
    private int stockSize;

    /* Разделяемые ресурсы */
    private final ReentrantLock orderLock;
    private final ArrayList<Order> orderQueue; // очередь заказов

    private final ReentrantLock stockLock;
    private final ArrayList<Order> stockQueue; // складская очередь

    Pizzeria(int pizzaMakerNum, int deliverymanNum, int orderSize, int stockSize) {
        this.pizzaMakerNum = pizzaMakerNum;
        this.deliverymanNum = deliverymanNum;
        this.orderSize = orderSize;
        this.stockSize = stockSize;

        orderLock = new ReentrantLock();
        orderQueue = new ArrayList<>(orderSize);

        stockLock = new ReentrantLock();
        stockQueue = new ArrayList<>(stockSize);
    }

    public void start() {

    }

    public void makeAnOrder() {

    }
}
