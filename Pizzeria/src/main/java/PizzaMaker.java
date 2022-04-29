import java.util.Random;

public class PizzaMaker implements Runnable {
    private final Pizzeria pizzeria;
    private final Random random;
    private int experience;

    PizzaMaker(int experience, Pizzeria pizzeria) {
        this.experience = experience;
        this.pizzeria = pizzeria;
        random = new Random();
    }

    @Override
    public void run() {
        while (true) {
            Order order = pizzeria.getFromOrder();
            if (order != null) {
                cook(order);
                pizzeria.addToStock(order);
            }
        }
    }

    private void cook(Order order) {
        order.state = State.COOKING;
        System.out.printf("%s [%d]: %s\n", order.name, order.id, order.state);

        try {
            long time = random.nextLong(500, 1000);
            Thread.sleep(time * (long)experience);
        }
        catch (Exception exception) {
            System.err.println(exception.getMessage());
        }

        order.state = State.COOKED;
        System.out.printf("%s [%d]: %s\n", order.name, order.id, order.state);
    }
}
