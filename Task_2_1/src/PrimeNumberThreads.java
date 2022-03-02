public class PrimeNumberThreads {
    public boolean isPrime(int[] numbers, int threadsNumber) {
        /* if (threadsNumber < 0) throws new Exception(); */
        int arraysSize = Math.min(numbers.length, threadsNumber);
        Thread[] threads = new Thread[arraysSize];
        PrimePart[] parts = new PrimePart[arraysSize];

        int partSize = (numbers.length <= threadsNumber) ? 1 : (numbers.length / threadsNumber);

        for (int i = 0; i < arraysSize; i++) {
            if (i == arraysSize - 1)
                parts[i] = new PrimePart(numbers, partSize * i, numbers.length - 1);
            else
                parts[i] = new PrimePart(numbers, partSize * i, partSize * (i + 1));

            threads[i] = new Thread(parts[i]);
            threads[i].start();
        }

        /* Waiting for terminating all threads */
        boolean isRunning = true;
        while (isRunning) {
            isRunning = false;
            for (Thread t: threads) {
                if (t.isAlive()) {
                    isRunning = true;
                    break;
                }
            }
        }

        boolean isPrimeFlag = true;
        for (int i = 0; i < arraysSize; i++) {
            if (!parts[i].isPrime()) {
                isPrimeFlag = false;
                break;
            }
        }

        return isPrimeFlag;
    }

    private class PrimePart implements Runnable {
        private final int begin;
        private final int end;
        private final int[] array;
        private boolean isPrimeFlag;

        PrimePart(int[] array, int begin, int end) {
            /*if (begin < 0 || end < 0 || begin >= array.length || end >= array.length)
                throw new ArrayIndexOutOfBoundsException();*/

            this.array = array;
            this.begin = begin;
            this.end = end;
            isPrimeFlag = false;
        }

        @Override
        public void run() {
            isPrimeFlag = true;

            for (int i = begin; i <= end; i++) {
                if (!isPrime(array[i])) {
                    isPrimeFlag = false;
                    break;
                }
            }
        }

        public boolean isPrime() {
            return isPrimeFlag;
        }

        private boolean isPrime(long number) {
            double numSqrt = Math.sqrt((double) number);
            long numBound = Math.round(numSqrt);

            boolean isPrimeFlag = true;

            if (number % 2 == 0) {
                if (number != 2)
                    isPrimeFlag = false;
            }
            else {
                for (long i = 3; i <= numBound; i += 2) {
                    if (number % i == 0) {
                        isPrimeFlag = false;
                        break;
                    }
                }
            }

            return isPrimeFlag;
        }
    }
}
