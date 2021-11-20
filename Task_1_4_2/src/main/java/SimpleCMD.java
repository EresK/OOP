import java.util.ArrayList;

public class SimpleCMD {
    public String path;
    public ArrayList<ArrayList<String>> addArgs;
    public ArrayList<String> removeArgs;
    public ArrayList<ArrayList<String>> showArgs;

    public ArrayList<Character> queue;

    SimpleCMD() {
        addArgs = new ArrayList<>();
        removeArgs = new ArrayList<>();
        showArgs = new ArrayList<>();
        queue = new ArrayList<>();
    }

    private void clearLists() {
        addArgs.clear();
        removeArgs.clear();
        showArgs.clear();
        queue.clear();
    }

    public void toOptions(String[] words) throws Exception {
        clearLists();

        boolean isPath = false;

        for (int i = 0; i < words.length; i++) {
            switch (words[i]) {
                case "-a":
                case "--add":
                    if (i + 2 < words.length) {
                        ArrayList<String> list = new ArrayList<>();
                        list.add(words[i + 1]);
                        list.add(words[i + 2]);
                        addArgs.add(list);
                        i += 2;
                        queue.add('a');
                    }
                    else
                        throw new Exception("Missing argument");
                    break;

                case "-r":
                case "--rm":
                    if (i + 1 < words.length) {
                        removeArgs.add(words[++i]);
                        queue.add('r');
                    }
                    else
                        throw new Exception("Missing argument");
                    break;

                case "-s":
                case "--show":
                    ArrayList<String> list = new ArrayList<>();
                    while (true) {
                        if (i + 1 < words.length && words[i + 1].charAt(0) != '-') {
                            list.add(words[++i]);
                        }
                        else
                            break;
                    }
                    showArgs.add(list);
                    queue.add('s');
                    break;

                case "-p":
                case "--path":
                    if (i + 1 < words.length) {
                        isPath = true;
                        path = words[++i];
                    }
                    else
                        throw new Exception("Missing path");
                    break;
            }
        }

        if (!isPath)
            throw new Exception("Expected path to json file");
    }
}
