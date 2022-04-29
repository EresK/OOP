import java.util.ArrayList;
import java.util.Random;

public class Deliverer implements Runnable {
    private final Pizzeria pizzeria;
    private final Random random;
    private int backpackSize;
    private final ArrayList<Order> backpack;

    Deliverer(int backpackSize, Pizzeria pizzeria) {
        this.backpackSize = backpackSize;
        backpack = new ArrayList<>();

        this.pizzeria = pizzeria;
        random = new Random();
    }

    @Override
    public void run() {
        while (true) {
            pizzeria.getFromStock(backpackSize, backpack);
            for (Order order : backpack)
                deliver(order);
            backpack.clear();
        }
    }

    private void deliver(Order order) {
        try {
            long time = random.nextLong(1000, 1500);
            Thread.sleep(time);
        }
        catch (Exception exception) {
            System.err.println(exception.getMessage());
        }

        order.state = State.DELIVERED;
        System.out.printf("%s [%d]: %s\n", order.name, order.id, order.state);
    }
}
