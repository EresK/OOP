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
            Order order = new Order(builder.build(), (int)(new Date().getTime()));
            pizzeria.addToOrder(order);

            System.out.printf("%s [%d]: %s\n", order.name, order.id, order.state);
            try {
                long time = random.nextLong(750, 1500);
                Thread.sleep(time);
            }
            catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
    }
}
