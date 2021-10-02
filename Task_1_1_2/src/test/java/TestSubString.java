import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class TestSubString {

    @Test
    public void test1() {
        Substring substr = new Substring();
        String path, pattern;

        path = "./src/test/resources/test1_2";
        pattern = "s";

        substr.search(path, pattern);
        Assertions.assertEquals(1, substr.list.size());
        Assertions.assertEquals(Arrays.asList(13), substr.list);
    }

    @Test
    public void test2() {
        Substring substr = new Substring();
        String path, pattern;

        path = "./src/test/resources/test1_2";
        pattern = "aabaab";

        substr.search(path, pattern);
        Assertions.assertEquals(5, substr.list.size());
        Assertions.assertEquals(Arrays.asList(0, 3, 6, 15, 18), substr.list);
    }

    @Test
    public void test3() {
        Substring substr = new Substring();
        String path, pattern;

        path = "./src/test/resources/test3";
        pattern = "aabaab";

        substr.search(path, pattern);
        Assertions.assertEquals(4, substr.list.size());
        Assertions.assertEquals(Arrays.asList(3, 6, 15, 18), substr.list);
    }

    @Test
    public void test4() {
        Substring substr = new Substring();
        String path, pattern;

        path = "./src/test/resources/test4";
        pattern = "Где-то там моя Подстрока ╚══╝";

        substr.search(path, pattern);
        Assertions.assertEquals(1, substr.list.size());
        Assertions.assertEquals(Arrays.asList(100), substr.list);
    }

    @Test
    public void test5() {
        Substring substr = new Substring();
        String path, pattern;

        path = "./src/test/resources/test5";
        pattern = "ここにいるよ! मैं यहां हूं";

        substr.search(path, pattern);
        Assertions.assertEquals(1, substr.list.size());
        Assertions.assertEquals(Arrays.asList(346147), substr.list);
    }

    @Test
    public void test6() {
        Substring substr = new Substring();
        String path, pattern;

        path = "./src/test/resources/test6";
        pattern = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                "sed do eiusmod tempor incididunt ut labore et dolore magna " +
                "aliqua. Ut enim ad minim veniam, quis nostrud exercitation " +
                "ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis " +
                "aute irure dolor in reprehenderit in voluptate velit esse cillum " +
                "dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat " +
                "non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

        substr.search(path, pattern);
        Assertions.assertEquals(1, substr.list.size());
        Assertions.assertEquals(Arrays.asList(53410510), substr.list);
    }

    @Test
    public void test7() {
        Substring substr = new Substring();
        String path, pattern;

        path = "./src/test/resources/test6";
        pattern = null;

        substr.search(path, pattern);
        Assertions.assertEquals(0, substr.list.size());
        Assertions.assertEquals(Arrays.asList(), substr.list);
    }
}
