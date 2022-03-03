import java.util.Arrays;

public class PrimeNumberStream {
    public boolean isPrime(int[] numbers) {
        return Arrays.stream(numbers).parallel().allMatch(this::isPrimeNumber);
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
