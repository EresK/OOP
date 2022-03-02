public class Main {
    public static void main(String[] args) {
        PrimeNumber pn = new PrimeNumber();

        boolean isPrimeFlag = pn.isPrime(127);
        boolean isAllPrimeFlag = pn.isAllPrime(new long[] {2, 3, 5, 7, 11, 13, 17, 19, 23, 29});

        System.out.println(isPrimeFlag + ", " + isAllPrimeFlag);

        PrimeNumberThreads pnt = new PrimeNumberThreads();
        boolean isPrime = pnt.isPrime(new int[] {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 101}, 1);

        System.out.println(isPrime);
    }
}
