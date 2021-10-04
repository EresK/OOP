import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestSubString {

    @Test
    public void testOneSymbol() throws Exception {
        Substring substr = new Substring();
        String path, pattern;

        path = "./src/test/resources/test1_2";
        pattern = "s";

        int[] arr = substr.search(path, pattern);
        int[] test = new int[] {13};
        Assertions.assertArrayEquals(test, arr);
    }

    @Test
    public void testEmptySubstring() throws Exception {
        Substring substr = new Substring();
        String path, pattern;

        path = "./src/test/resources/test6";
        pattern = "";

        int[] arr = substr.search(path, pattern);
        int[] test = new int[0];
        Assertions.assertArrayEquals(test, arr);
    }

    @Test
    public void testSubEntrance() throws Exception {
        Substring substr = new Substring();
        String path, pattern;

        path = "./src/test/resources/test1_2";
        pattern = "aabaab";

        int[] arr = substr.search(path, pattern);
        int[] test = new int[] {0, 3, 6, 15, 18};
        Assertions.assertArrayEquals(test, arr);
    }

    @Test
    public void testInnerSubEntrance() throws Exception {
        Substring substr = new Substring();
        String path, pattern;

        path = "./src/test/resources/test3"; // contain "aalaabaab"... fragment
        pattern = "aabaab";

        int[] arr = substr.search(path, pattern);
        int[] test = new int[] {3, 6, 15, 18};
        Assertions.assertArrayEquals(test, arr);
    }

    @Test
    public void testRussianLanguage() throws Exception {
        Substring substr = new Substring();
        String path, pattern;

        path = "./src/test/resources/test4";
        pattern = "Где-то там моя Подстрока ╚══╝";

        int[] arr = substr.search(path, pattern);
        int[] test = new int[] {100};
        Assertions.assertArrayEquals(test, arr);
    }

    @Test
    public void testUnicodeSymbols() throws Exception {
        Substring substr = new Substring();
        String path, pattern;

        path = "./src/test/resources/test5";
        pattern = "ここにいるよ! मैं यहां हूं";

        int[] arr = substr.search(path, pattern);
        int[] test = new int[] {346147};
        Assertions.assertArrayEquals(test, arr);
    }

    @Test
    public void testBigFile() throws Exception {
        Substring substr = new Substring();
        String path, pattern;

        path = "./src/test/resources/test6"; // ~ 50MB
        pattern = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                "sed do eiusmod tempor incididunt ut labore et dolore magna " +
                "aliqua. Ut enim ad minim veniam, quis nostrud exercitation " +
                "ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis " +
                "aute irure dolor in reprehenderit in voluptate velit esse cillum " +
                "dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat " +
                "non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

        int[] arr = substr.search(path, pattern);
        int[] test = new int[] {53410510};
        Assertions.assertArrayEquals(test, arr);
    }

    @Test
    public void testNullSubstring() {
        Substring substr = new Substring();
        String path, pattern;

        path = "./src/test/resources/test6";
        pattern = null;

        Exception e = Assertions.assertThrows(Exception.class, () -> substr.search(path, pattern));
        String actual = e.getMessage();
        Assertions.assertEquals("Path or substring is null", actual);
    }

    @Test
    public void testBadPath() {
        Substring substr = new Substring();
        String path, pattern;

        path = "some text";
        pattern = "find this string";

        Exception e = Assertions.assertThrows(Exception.class, () -> substr.search(path, pattern));
        String actual = e.getMessage();
        Assertions.assertEquals("Can not read file", actual);
    }
}
