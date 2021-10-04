import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Substring {

    private ArrayList<Integer> list;
    private int[] entrance;

    private int fullOffset;
    private int nextOffset;
    private int innerOffset;

    Substring() {
        list = new ArrayList<>();
        fullOffset = 0;
        nextOffset = 0;
        innerOffset = 0;
    }

    /**
     * @param path - file path
     * @param substring - searching substring
     * @return array with all entrance indexes
     */
    public int[] search(String path, String substring) throws Exception {
        if (path == null || substring == null) throw new Exception("Path or substring is null");

        if (substring.length() == 0) {
            entrance = list.stream().mapToInt(Integer::intValue).toArray();
            return entrance;
        }

        char[] str = substring.toCharArray();

        char[] buffer = new char[str.length];
        int[] offset = new int[str.length];

        for (int i = 0; i < str.length - 1; i++)
            offset[i] = str.length - i - 1;
        offset[str.length - 1] = str.length;

        try (FileReader fr = new FileReader(path, StandardCharsets.UTF_8)) {
            while (true) {
                int numHasRead = fr.read(buffer, nextOffset, str.length - nextOffset);
                nextOffset = compare(buffer, numHasRead + nextOffset, str, offset);

                if (nextOffset == 0) {
                    list.add(fullOffset);

                    if(innerOffset != 0) {
                        fullOffset += innerOffset;
                        System.arraycopy(buffer, innerOffset, buffer, 0, str.length - innerOffset);
                        nextOffset = innerOffset;
                    }
                }
                else if (nextOffset == -1) break;
                else {
                    fullOffset += nextOffset;
                    if (nextOffset != str.length) {
                        System.arraycopy(buffer, nextOffset, buffer, 0, str.length - nextOffset);
                        nextOffset = str.length - nextOffset;
                    }
                    else nextOffset = 0;
                }
            }
        }
        catch (IOException e) {
            throw new Exception("Can not read file");
        }

        entrance = list.stream().mapToInt(Integer::intValue).toArray();
        return entrance;
    }

    /**
     * using Boyer-Moore-Horspool algorithm
     * @param buffer - current block in file
     * @param symNum - actual number of char in buffer
     * @param str - searching pattern
     * @param offset - numbers of offset for every char in str
     * @return
     * -1 - file end, if actual numbers of chars in buffer less than chars in str
     * 0 - success, it means buffer == str
     * number from 1 to str.length is offset
     */
    private int compare(char[] buffer, int symNum, char[] str, int[] offset) {
        if (symNum < str.length) return  -1;

        for (int i = str.length - 1; i >= 0; i--) {
            if (str[i] != buffer[i]) {
                for (int j = i - 1; j >= 0; j--) {
                    if(str[j] == buffer[i]) return offset[j];
                }
                return i + 1;
            }
        }

        if (str.length > 1) {
            int i = 0;
            boolean begin = true;
            for (int j = 1; j < str.length; j++) {
                if (str[i] != buffer[j]) {
                    i = 0;
                    innerOffset = 0;
                    begin = true;
                    continue;
                }
                if (begin) innerOffset = j;
                i++;
                begin = false;
            }
        }

        return 0;
    }
}
