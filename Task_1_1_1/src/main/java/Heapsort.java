public class Heapsort {
    public void heapSort(int[] a) {
        // creating binary tree
        for (int i = a.length / 2; i >= 0; i--) {
            siftDown(a, i, a.length - 1);
        }

        // sorting and sifting down
        for (int i = a.length - 1; i >= 1 ; i--) {
            int tmp = a[0];
            a[0] = a[i];
            a[i] = tmp;
            siftDown(a, 0, i - 1);
        }
    }

    private void siftDown(int[] a, int i, int last) {
        int maxChild;
        boolean isDone = false;

        while ((i * 2 <= last) && (!isDone)) {
            if((i * 2 == last) || (a[i * 2] > a[i * 2 + 1]))
                maxChild = i * 2;
            else
                maxChild = i * 2 + 1;

            if(a[i] < a[maxChild]) {
                int tmp = a[i];
                a[i] = a[maxChild];
                a[maxChild] = tmp;
                i = maxChild;
            }
            else
                isDone = true;
        }
    }
}
