import java.util.Stack;

public class Calculator {
    private final Stack<Double> number;

    Calculator() {
        number = new Stack<>();
    }

    /**
     * @param expression - expression in prefix form
     * @return - result of counting of the expression
     * @throws Exception
     * Empty expression,
     * Met unknown symbol,
     * Division by zero
     */
    public double Calculate(String expression) throws Exception {
        if (expression == null || expression.equals(""))
            throw new Exception("Empty expression");

        number.clear();

        String[] words = expression.split(" ");

        double num;
        double a, b;

        for (int i = words.length - 1; i >= 0; i--) {
            switch (words[i]){
                case "+":
                    a = PeekPopNumber();
                    b = PeekPopNumber();
                    number.push(a + b);
                    break;

                case "-":
                    a = PeekPopNumber();
                    if (number.isEmpty())
                        number.push(-a);
                    else {
                        b = number.pop();
                        number.push(a - b);
                    }
                    break;

                case "*":
                    a = PeekPopNumber();
                    b = PeekPopNumber();
                    number.push(a * b);
                    break;

                case "/":
                    a = PeekPopNumber();
                    b = PeekPopNumber();
                    if(!isZero(b))
                        number.push(a / b);
                    else
                        throw new Exception("Can not be divided by zero");
                    break;

                case "pow":
                    a = PeekPopNumber();
                    b = PeekPopNumber();
                    number.push(Math.pow(a, b));
                    break;

                case "log":
                    a = PeekPopNumber();
                    number.push(Math.log(a));
                    break;

                case "sqrt":
                    a = PeekPopNumber();
                    number.push(Math.sqrt(a));
                    break;

                case "sin":
                    a = PeekPopNumber();
                    number.push(Math.sin(a));
                    break;

                case "cos":
                    a = PeekPopNumber();
                    number.push(Math.cos(a));
                    break;

                default:
                    try {
                        num = Double.parseDouble(words[i]);
                    }
                    catch (Exception e) {
                        throw new Exception("Met unknown symbol");
                    }
                    number.push(num);
                    break;
            }
        }

        if (number.size() != 1)
            throw new Exception("Exist non-calculated numbers");

        return number.pop();
    }

    /**
     * @return Head of stack of number
     * @throws Exception if stack is empty
     */
    private double PeekPopNumber() throws Exception {
        if (number.isEmpty())
            throw new Exception("Expected number");
        return number.pop();
    }

    /**
     * @param a value
     * @return if value accurate to 15 decimals after zero
     */
    private boolean isZero(double a) {
        return (a < 10e-16) && (a > -10e-16);
    }
}
