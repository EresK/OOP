import java.util.Date;
import java.util.Random;

public class OrderCreator implements Runnable {
    private final Pizzeria pizzeria;
    private final Random random;
    private final PizzaBuilder builder;
    private final Date date;

    OrderCreator(Pizzeria pizzeria) {
        this.pizzeria = pizzeria;
        random = new Random();
        builder = new PizzaBuilder();
        date = new Date();
    }

    @Override
    public void run() {
        while (true) {
            Order order = new Order(builder.build(), (int)date.getTime());
            System.out.printf("%s [%d]: %s\n", order.name, order.id, order.state);

            pizzeria.addToOrder(order);

            try {
                long time = random.nextLong(2000, 3000);
                Thread.sleep(time);
            }
            catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
    }
}
