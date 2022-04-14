public class Order {
    protected final String name;
    protected final int id;

    protected State state;

    Order(String name, int id) {
        this.name = name;
        this.id = id;
        state = State.ORDERED;
    }
}
