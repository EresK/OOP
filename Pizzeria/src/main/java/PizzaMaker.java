import java.util.Random;

public class PizzaMaker implements Runnable {
    private final Pizzeria pizzeria;
    private final Random random;
    private int experience;
    private int id;

    PizzaMaker(int id, int experience, Pizzeria pizzeria) {
        this.id = id;
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
                pizzeria.addToStock(order, id);
            }
        }
    }

    public int getExperience() {
        return experience;
    }

    private void cook(Order order) {
        order.state = State.COOKING;
        System.out.printf("%s [%d]: COOKING by [%d]\n", order.name, order.id, id);

        try {
            long time = random.nextLong(1000, 1500);
            Thread.sleep(time * (long)experience);
        }
        catch (Exception exception) {
            System.err.println(exception.getMessage());
        }
        order.state = State.COOKED;
        System.out.printf("%s [%d]: COOKED by [%d]\n", order.name, order.id, id);
    }
}
