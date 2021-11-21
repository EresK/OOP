import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Parser {
    /**
     * @param words - arguments split by space
     * @return - arguments connected by quotes or stayed the same as in words
     * @throws Exception - met argument with start quote but without end one
     */
    public String[] parse(String @NotNull [] words) throws Exception {
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> inQuote = new ArrayList<>();

        boolean beginOfQuote = false;
        for(String w: words) {
            if (!beginOfQuote && w.charAt(0) == '\"' && w.charAt(w.length() - 1) == '\"') {
                list.add(w);
            }
            else if (!beginOfQuote && w.charAt(0) == '\"') {
                beginOfQuote = true;
                inQuote.add(w);
            }
            else if (!beginOfQuote) {
                list.add(w);
            }
            else if (w.charAt(w.length() - 1) == '\"') {
                beginOfQuote = false;
                inQuote.add(w);
                list.add(String.join(" ", inQuote));
                inQuote.clear();
            }
            else {
                inQuote.add(w);
            }
        }

        if (beginOfQuote)
            throw new Exception("Expected end of quotation");
        return list.toArray(new String[0]);
    }
}
