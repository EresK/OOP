import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class TestSubString {

    @Test
    public void test1() {
        Substring substr = new Substring();
        String path, pattern;

        path = "./src/test/java/test12";
        pattern = "s";

        char[] str = pattern.toCharArray();

        if(substr.search(path, str) == 0) {
            Assertions.assertEquals(1, substr.list.size());
            Assertions.assertEquals(Arrays.asList(13), substr.list);
        }
        else System.out.println("Not found");
    }

    @Test
    public void test2() {
        Substring substr = new Substring();
        String path, pattern;

        path = "./src/test/java/test12";
        pattern = "aabaab";

        char[] str = pattern.toCharArray();

        if(substr.search(path, str) == 0) {
            Assertions.assertEquals(5, substr.list.size());
            Assertions.assertEquals(Arrays.asList(0, 3, 6, 15, 18), substr.list);
        }
        else System.out.println("Not found");
    }

    @Test
    public void test3() {
        Substring substr = new Substring();
        String path, pattern;

        path = "./src/test/java/test3";
        pattern = "Где-то там моя Подстрока ╚══╝";

        char[] str = pattern.toCharArray();

        if(substr.search(path, str) == 0) {
            Assertions.assertEquals(1, substr.list.size());
            Assertions.assertEquals(Arrays.asList(100), substr.list);
        }
        else System.out.println("Not found");
    }

    @Test
    public void test4() {
        Substring substr = new Substring();
        String path, pattern;

        path = "./src/test/java/test4";
        pattern = "ここにいるよ! मैं यहां हूं";

        char[] str = pattern.toCharArray();

        if(substr.search(path, str) == 0) {
            Assertions.assertEquals(1, substr.list.size());
            Assertions.assertEquals(Arrays.asList(346147), substr.list);
        }
        else System.out.println("Not found");
    }
}
