import java.util.Date;
import java.util.Random;

public class OrderCreator implements Runnable {
    private final Pizzeria pizzeria;
    private final Random random;
    private final PizzaBuilder builder;

    OrderCreator(Pizzeria pizzeria) {
        this.pizzeria = pizzeria;
        random = new Random();
        builder = new PizzaBuilder();
    }

    @Override
    public void run() {
        while (true) {
            Order order = new Order(builder.build(), pizzeria.getId());
            pizzeria.addToOrder(order);

            try {
                long time = random.nextLong(1000, 1750);
                Thread.sleep(time);
            }
            catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
    }
}
