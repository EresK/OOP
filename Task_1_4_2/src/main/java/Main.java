import org.apache.commons.cli.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Options options = new Options();

        options.addOption("add", true, "add new note");
        options.addOption("rm", true, "remove existing note");
        options.addOption("show", true, "shows appropriate notes");

        NoteBook noteBook = new NoteBook();

        try {
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);

            String[] value;
            for (Option opt: cmd.getOptions()) {
                switch (opt.getOpt()) {
                    case "add":
                        value = cmd.getOptionValues("add");
                        noteBook.addNote(value);
                        break;

                    case "rm":
                        value = cmd.getOptionValues("rm");
                        noteBook.removeNote(value);
                        break;

                    case "show":
                        value = cmd.getOptionValues("show");
                        noteBook.showNotes();
                        break;
                }
            }
        }
        catch (ParseException e) {
            System.err.println("Parsing failed.  Reason: " + e.getMessage());
        }
    }
}
