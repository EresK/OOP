import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class Deliveryman {
    private int backpackSize;
    private ReentrantLock stockLock;
    private final ArrayList<Order> stockQueue;

    Deliveryman(int backpackSize, ReentrantLock stockLock, ArrayList<Order> stockQueue) {
        this.backpackSize = backpackSize;
        this.stockLock = stockLock;
        this.stockQueue = stockQueue;
    }

    public void start() {

    }
}
