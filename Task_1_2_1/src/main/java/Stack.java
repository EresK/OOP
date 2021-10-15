public class Stack<T> {
    public int count;
    private T[] stack;

    Stack() {
        count = 0;
        stack = (T[]) new Object[1];
    }

    private void resize() {
        T[] tmp = (T[]) new Object[stack.length * 2];
        System.arraycopy(stack, 0, tmp, 0, count);
        stack = tmp;
    }

    private void resize(int newSize) throws Exception{
        if (newSize <= stack.length) throw new Exception("Can not reduce memory");

        T[] tmp = (T[]) new Object[newSize];
        System.arraycopy(stack, 0, tmp, 0, count);
        stack = tmp;
    }

    public void push(T elem) {
        if (count >= stack.length) resize();
        stack[count++] = elem;
    }

    //Here is the problem that the Stack elements are extracted.
    public void pushStack(Stack<T> elements) throws Exception {
        if (elements.count + count >= stack.length) resize(elements.count + count);

        int elemCount = elements.count + count;

        for (int i = elemCount - 1; i >= count; i--) {
            stack[i] = elements.pop();
        }
        count = elemCount;
    }

    public T pop() {
        if (count > 0) return stack[--count];
        return null;
    }

    public Stack<T> popStack(int num) throws Exception {
        if (num > count) throw new Exception("Can not get more number of elements than the current");

        Stack<T> res = new Stack<>();
        for (int i = count - num; i < count; i++) {
            res.push(stack[i]);
        }
        count -= num;

        return res;
    }
}
