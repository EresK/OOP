import javax.management.InvalidAttributeValueException;

public class PrimeNumberThreads {
    public boolean isPrime(int[] numbers, int threadsNumber) throws InvalidAttributeValueException {
        if (threadsNumber < 1)
            throw new InvalidAttributeValueException("Threads number must be positive");

        int arraysSize = Math.min(numbers.length, threadsNumber);

        Thread[] threads = new Thread[arraysSize];
        PrimePart[] parts = new PrimePart[arraysSize];

        int partSize = (numbers.length <= threadsNumber) ? 1 : (numbers.length / threadsNumber);
        int residue = numbers.length - partSize * threadsNumber;
        int last = 0;

        /* Creating new threads */
        for (int i = 0; i < arraysSize; i++) {
            if (residue > 0) {
                parts[i] = new PrimePart(numbers, last, last + partSize);
                last += partSize + 1;
                residue -= 1;
            }
            else {
                parts[i] = new PrimePart(numbers, last, last + partSize - 1);
                last += partSize;
            }

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
            this.array = array;
            this.begin = begin;
            this.end = end;
            isPrimeFlag = false;
        }

        @Override
        public void run() {
            isPrimeFlag = true;

            for (int i = begin; i <= end; i++) {
                if (!isPrimeNumber(array[i])) {
                    isPrimeFlag = false;
                    break;
                }
            }
        }

        public boolean isPrime() {
            return isPrimeFlag;
        }

        private boolean isPrimeNumber(int number) {
            double numSqrt = Math.sqrt(number);
            int numBound = (int) Math.round(numSqrt);

            boolean isPrimeFlag = true;

            if (number % 2 == 0) {
                if (number != 2)
                    isPrimeFlag = false;
            }
            else {
                for (int i = 3; i <= numBound; i += 2) {
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
