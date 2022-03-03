public class Main {
    public static void main(String[] args) throws Exception {
        int[] numbers = PrimeNumberGenerator.Generate(1000000);

        /*long linearBeg, linearEnd;
        PrimeNumber primeNumber = new PrimeNumber();
        linearBeg = System.nanoTime();
        boolean linear = primeNumber.isAllPrime(numbers);
        linearEnd = System.nanoTime();
        long linearDuration = linearEnd - linearBeg;
        System.out.println("Linear: " + linear + " " + linearDuration);*/

        /*long multiBeg, multiEnd;
        PrimeNumberThreads threads = new PrimeNumberThreads();
        multiBeg = System.nanoTime();
        boolean multiThread = threads.isPrime(numbers, 4);
        multiEnd = System.nanoTime();
        long multiDuration = multiEnd - multiBeg;
        System.out.println("Multi: " + multiThread + " " + multiDuration);*/

        /*long parallelBeg, parallelEnd;
        PrimeNumberStream stream = new PrimeNumberStream();
        parallelBeg = System.nanoTime();
        boolean parallelStream = stream.isPrime(numbers);
        parallelEnd = System.nanoTime();
        long parallelDuration = parallelEnd - parallelBeg;
        System.out.println("Parallel: " + parallelStream + " " + parallelDuration);*/

        /*System.out.printf("Linear: %b\nMulti-thread: %b\nParallel stream: %b\n", linear, multiThread, parallelStream);
        System.out.printf("Linear: %d\nMulti-thread: %d\nParallel stream: %d",
                linearDuration, multiDuration, parallelDuration);*/
    }
}
