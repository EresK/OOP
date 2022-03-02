public class PrimeNumber {
    public boolean isAllPrime(long[] numbers) {
        boolean isPrimeFlag = true;

        for (long num: numbers) {
            if (!isPrime(num)) {
                isPrimeFlag = false;
                break;
            }
        }

        return isPrimeFlag;
    }

    public boolean isPrime(long number) {
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
