import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);

        Calculator calculator = new Calculator();
        String expression;
        double res;

        while (true) {
            expression = scan.nextLine();
            if (expression.equals(""))
                break;
            res = calculator.Calculate(expression);
            System.out.println(res);
        }

        scan.close();
    }
}
