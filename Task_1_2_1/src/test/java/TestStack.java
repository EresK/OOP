import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestStack {

    @Test
    public void pushPop() throws Exception {
        Stack<Integer> sInt = new Stack<>();

        //push
        Assertions.assertEquals(0, sInt.getCount());
        sInt.push(1);
        sInt.push(3);
        sInt.push(5);
        Assertions.assertEquals(3, sInt.getCount());

        //pop
        Assertions.assertEquals(5, sInt.pop());
        Assertions.assertEquals(2, sInt.getCount());

        Assertions.assertEquals(3, sInt.pop());
        Assertions.assertEquals(1, sInt.getCount());

        Assertions.assertEquals(1, sInt.pop());
        Assertions.assertEquals(0, sInt.getCount());

        //Expecting null for empty stack
        Exception e = Assertions.assertThrows(Exception.class, () -> sInt.pop());
        String expected = "Can not get element from empty stack";
        Assertions.assertEquals(expected, e.getMessage());
    }

    @Test
    public void pushPopStack() throws Exception {
        Stack<String> strOne = new Stack<>();
        strOne.push("1: First");

        Stack<String> strTwo = new Stack<>();
        strTwo.push("2: This is");
        strTwo.push("3: some string");
        strTwo.push("4: .");
        Assertions.assertEquals(3, strTwo.getCount());

        //pushStack
        strOne.pushStack(strTwo);
        Assertions.assertEquals(4, strOne.getCount());
        Assertions.assertEquals("4: .", strOne.pop());
        Assertions.assertEquals("3: some string", strOne.pop());
        Assertions.assertEquals(2, strOne.getCount());

        //popStack
        strTwo = strOne.popStack(2);
        Assertions.assertEquals(0, strOne.getCount());
        Assertions.assertEquals(2, strTwo.getCount());

        //Exception check
        Exception e = Assertions.assertThrows(Exception.class, () -> strOne.popStack(5));
        String expected = "Can not get more number of elements than the current";
        Assertions.assertEquals(expected, e.getMessage());
    }
}
