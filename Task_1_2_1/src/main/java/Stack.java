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

    private void resize(int newSize) {
        T[] tmp = (T[]) new Object[newSize];
        System.arraycopy(stack, 0, tmp, 0, count);
        stack = tmp;
    }

    /**
     * @param elem - element to add to top of current stack
     */
    public void push(T elem) {
        if (count >= stack.length) resize();
        stack[count++] = elem;
    }

    /**
     * @param elements - elements to add to current stack
     */
    public void pushStack(Stack<T> elements) {
        if (elements.count + count >= stack.length) resize(elements.count + count);

        int elemCount = elements.count + count;

        for (int i = elemCount - 1; i >= count; i--) {
            stack[i] = elements.pop();
        }
        count = elemCount;
    }

    /**
     * @return - get element from the top of current stack
     */
    public T pop() {
        if (count > 0) return stack[--count];
        return null;
    }

    /**
     * @param num - count of elements to get from the top of stack
     * @return - new stack with the elements
     * @throws Exception - it shows when num greater than total number of elements in the current stack
     */
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
