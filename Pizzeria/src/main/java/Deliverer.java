import java.util.ArrayList;
import java.util.Random;

public class Deliverer implements Runnable {
    private final Pizzeria pizzeria;
    private final Random random;
    private final ArrayList<Order> backpack;
    private int backpackSize;
    private int id;

    Deliverer(int id, int backpackSize, Pizzeria pizzeria) {
        this.id = id;
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

    public int getBackpackSize() {
        return backpackSize;
    }

    private void deliver(Order order) {
        try {
            long time = random.nextLong(1500, 2000);
            Thread.sleep(time);
        }
        catch (Exception exception) {
            System.err.println(exception.getMessage());
        }
        order.state = State.DELIVERED;
        System.out.printf("%s [%d]: DELIVERED - deliverer[%d]\n", order.name, order.id, id);
    }
}
