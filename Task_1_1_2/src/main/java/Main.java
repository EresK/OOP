import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        startTwo();
    }

    static void startTwo() {
        Scanner in = new Scanner(System.in, StandardCharsets.UTF_8);

        String path, pattern;
        System.out.println("File path:");
        path = in.nextLine();
        System.out.println("Substring:");
        pattern = in.nextLine();

        char[] str = pattern.toCharArray();

        Substring substring = new Substring();

        if (substring.search(path, str) == 0) {
            if (substring.list.size() > 0)
                substring.list.forEach(System.out::println);
        }
        else System.out.println("");

        in.close();
    }
}
