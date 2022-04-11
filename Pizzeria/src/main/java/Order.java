public class Order {
    private final String name;
    private final int orderId;

    protected State state;

    Order (String name, int orderId) {
        this.name = name;
        this.orderId = orderId;
        state = State.ORDERED;
    }
}
