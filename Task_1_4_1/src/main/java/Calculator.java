import java.util.*;

enum Operator {
    plus,
    minus,
    multiply,
    division,
    log,
    pow,
    sqrt,
    sin,
    cos
}

public class Calculator {

    private Stack<Double> number;
    private Stack<Object> token;

    Calculator() {
        number = new Stack<>();
        token = new Stack<>();
    }

    /**
     * @param expression - expression in prefix form
     * @return - result of counting of the expression
     * @throws Exception
     * Empty expression,
     * Met unknown symbol,
     * Not in prefix form,
     * Division by zero
     */
    public double Calculate(String expression) throws Exception {
        if (expression == null || expression.equals(""))
            throw new Exception("Empty expression");

        number.clear();
        token.clear();

        Tokenize(expression);

        double a, b;
        Operator op;
        Object tmp;

        while (token.size() > 0) {
            tmp = token.pop();
            if (tmp instanceof Double) {
                number.push((double) tmp);
            }
            else if (tmp instanceof Operator) {
                op = (Operator) tmp;
                a = PeekPopNumber();
                switch (op) {
                    case plus:
                        b = PeekPopNumber();
                        number.push(a + b);
                        break;

                    case minus:
                        if(!number.isEmpty()) {
                            b = PeekPopNumber();
                            number.push(a - b);
                        }
                        else
                            number.push(-a);
                        break;

                    case multiply:
                        b = PeekPopNumber();
                        number.push(a * b);
                        break;

                    case division:
                        b = PeekPopNumber();
                        if (!isNull(b))
                            number.push(a / b);
                        else
                            throw new Exception("Can not be divided by zero");
                        break;

                    case pow:
                        b = PeekPopNumber();
                        number.push(Math.pow(a, b));
                        break;

                    case log:
                        number.push(Math.log(a));
                        break;

                    case sqrt:
                        number.push(Math.sqrt(a));
                        break;

                    case sin:
                        number.push(Math.sin(a));
                        break;

                    case cos:
                        number.push(Math.cos(a));
                        break;
                }
            }
            else
                throw new Exception("Unknown token");
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
    private boolean isNull(double a) {
        return (a < 10e-16) && (a > -10e-16);
    }

    /**
     * @param expression - expression in prefix form
     * @throws Exception
     * Empty expression,
     * Met unknown token
     */
    private void Tokenize(String expression) throws Exception {
        if (expression == null || expression.equals(""))
            throw new Exception("Empty expression");

        String[] words = expression.split(" ");

        double num;

        for (String word: words) {
            switch (word) {
                case "+":
                    token.push(Operator.plus);
                    break;

                case "-":
                    token.push(Operator.minus);
                    break;

                case "*":
                    token.push(Operator.multiply);
                    break;

                case "/":
                    token.push(Operator.division);
                    break;

                case "log":
                    token.push(Operator.log);
                    break;

                case "pow":
                    token.push(Operator.pow);
                    break;

                case "sqrt":
                    token.push(Operator.sqrt);
                    break;

                case "sin":
                    token.push(Operator.sin);
                    break;

                case "cos":
                    token.push(Operator.cos);
                    break;

                default:
                    try {
                        num = Double.parseDouble(word);
                    }
                    catch (Exception e) {
                        throw new Exception("Met unknown symbol");
                    }
                    token.push(num);
                    break;
            }
        }
    }
}
