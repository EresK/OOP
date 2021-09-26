import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestHeapsort {
    Heapsort hp = new Heapsort();

    @org.junit.jupiter.api.Test
    void test1() {
        int[] a = new int[] {5, 4, 3, 2, 1};
        int[] b = new int[] {1, 2, 3, 4, 5};

        hp.heapSort(a);
        Assertions.assertArrayEquals(b, a);
    }

    @org.junit.jupiter.api.Test
    void test2() {
        int[] c = new int[] {5, 123, 312, 2, -123, 545345, 0, 2, 3, 4, -123123};
        int[] d = new int[] {-123123, -123, 0, 2, 2, 3, 4, 5, 123, 312, 545345};

        hp.heapSort(c);
        Assertions.assertArrayEquals(d, c);
    }

    @org.junit.jupiter.api.Test
    void test3() {
        int[] e = new int[] {2, 1, 3};
        int[] f = new int[] {1, 2, 3};

        hp.heapSort(e);
        Assertions.assertArrayEquals(f, e);
    }
}
