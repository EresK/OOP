import java.util.ArrayList;

public class OrderQueue<T> {
    private final ArrayList<T> list;
    private int maxSize;

    OrderQueue(int size) {
        list = new ArrayList<>();
        this.maxSize = size;
    }

    public boolean push(T value) throws ArrayStoreException {
        return (list.size() + 1 <= maxSize) && list.add(value);
    }

    public T pop() {
        return list.size() > 0 ? list.remove(0) : null;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public int getSize() { return list.size(); }
}
