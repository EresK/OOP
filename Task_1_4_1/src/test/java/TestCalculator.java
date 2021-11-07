import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestCalculator {

    @Test
    public void EmptyExpression() {
        Calculator calculator = new Calculator();

        Exception e = Assertions.assertThrows(Exception.class, () -> calculator.Calculate(null));
        Assertions.assertEquals("Empty expression", e.getMessage());

        e = Assertions.assertThrows(Exception.class, () -> calculator.Calculate(""));
        Assertions.assertEquals("Empty expression", e.getMessage());
    }

    @Test
    public void IncorrectExpressions() {
        Calculator calculator = new Calculator();

        // Checks to prefix format
        Exception e = Assertions.assertThrows(Exception.class, () -> calculator.Calculate("5 + 4 2"));
        Assertions.assertEquals("Exist non-calculated numbers", e.getMessage());

        // Checks to number or operator
        e = Assertions.assertThrows(Exception.class, () -> calculator.Calculate("a + b"));
        Assertions.assertEquals("Met unknown symbol", e.getMessage());

        // Checks binary operations
        e = Assertions.assertThrows(Exception.class, () -> calculator.Calculate("+ 5"));
        Assertions.assertEquals("Expected number", e.getMessage());

        e = Assertions.assertThrows(Exception.class, () -> calculator.Calculate("* 5"));
        Assertions.assertEquals("Expected number", e.getMessage());

        e = Assertions.assertThrows(Exception.class, () -> calculator.Calculate("/ 5"));
        Assertions.assertEquals("Expected number", e.getMessage());

        e = Assertions.assertThrows(Exception.class, () -> calculator.Calculate("pow 5"));
        Assertions.assertEquals("Expected number", e.getMessage());

        // Checks a division by zero
        e = Assertions.assertThrows(Exception.class, () -> calculator.Calculate("/ 550 0"));
        Assertions.assertEquals("Can not be divided by zero", e.getMessage());
    }

    @Test
    public void ComparingValues() throws Exception {
        Calculator calculator = new Calculator();

        Assertions.assertEquals(0, calculator.Calculate("sin + - 1 2 1"));
        Assertions.assertEquals(20, calculator.Calculate("+ 14 6"));
        Assertions.assertEquals(8, calculator.Calculate("- 14 6"));
        Assertions.assertEquals(-60, calculator.Calculate("* 10 - 6"));

        Assertions.assertEquals(5, calculator.Calculate("- * / 15 - 7 + 1 1 3 + 2 + 1 1"));
        Assertions.assertEquals(0, calculator.Calculate("* log 1 2"));

        Assertions.assertEquals(16, calculator.Calculate("pow 2 sqrt 16"));
        Assertions.assertEquals(Math.cos(2), calculator.Calculate("cos 2"));
        Assertions.assertEquals(Math.sin(5), calculator.Calculate("sin 5"));
    }
}
