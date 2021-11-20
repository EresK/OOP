public class Main {
    public static void main(String[] args) {
        Parser parser = new Parser();
        SimpleCMD cmd = new SimpleCMD();
        String[] words;

        try {
            words = parser.parse(args);
            cmd.toOptions(words);
            NoteBook noteBook = new NoteBook(cmd.path);

            int as = 0;
            int rs = 0;
            int ss = 0;

            for(char c: cmd.queue) {
                switch (c) {
                    case 'a':
                        noteBook.addNote(cmd.addArgs.get(as++));
                        break;
                    case 'r':
                        noteBook.removeNote(cmd.removeArgs.get(rs++));
                        break;
                    case 's':
                        noteBook.showNote(cmd.showArgs.get(ss++));
                        break;
                }
            }

            noteBook.writeJson(cmd.path);
        }
        catch (Exception e) {
            System.err.println("Can not process arguments: " + e.getMessage());
        }
    }
}