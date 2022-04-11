import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestEnum {

    @Test
    public void TestingState() {
        State state1 = State.ORDERED;
        State state2 = State.COOKING;
        State state3 = State.COOKED;
        State state4 = State.READY;

        Assertions.assertEquals("ORDERED", state1.toString());
        Assertions.assertEquals("COOKING", state2.toString());
        Assertions.assertEquals("COOKED", state3.toString());
        Assertions.assertEquals("READY", state4.toString());
    }

    @Test
    public void TestingExperience() {
        Experience exp1 = Experience.SENIOR;
        Experience exp2 = Experience.MIDDLE;
        Experience exp3 = Experience.JUNIOR;

        Assertions.assertEquals(1, exp1.getCoefficient());
        Assertions.assertEquals(2, exp2.getCoefficient());
        Assertions.assertEquals(3, exp3.getCoefficient());
    }
}
