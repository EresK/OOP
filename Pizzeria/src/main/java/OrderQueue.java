import java.util.ArrayList;

public class OrderQueue<T> {
    private final ArrayList<T> list;
    private final int maxSize;

    OrderQueue(int size) {
        list = new ArrayList<>();
        this.maxSize = size;
    }

    public boolean push(T value) {
        if (list.size() + 1 > maxSize)
            return false;

        return list.add(value);
    }

    public T pop() {
        if (list.size() > 0)
            return list.remove(0);

        return null;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public int getSize() {
        return list.size();
    }
}
