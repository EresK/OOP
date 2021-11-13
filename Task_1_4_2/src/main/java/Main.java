import org.apache.commons.cli.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Option add = new Option("add", true,"add new note");
        add.setArgs(2);

        Option rm = new Option("rm", true,"remove existing note");
        rm.setArgs(1);

        Option show = new Option("show", true,"shows appropriate notes");
        show.setOptionalArg(true);
        show.setArgs(Option.UNLIMITED_VALUES);

        Options options = new Options();
        options.addOption(add);
        options.addOption(rm);
        options.addOption(show);

        String path = "C:\\Users\\Eres Swan\\IDEAProjects\\NoteBook\\src\\main\\resources\\file.json";

        NoteBook noteBook = new NoteBook(path);

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
                        noteBook.showNote(value);
                        break;
                }
            }
        }
        catch (ParseException e) {
            System.err.println("Parsing failed: " + e.getMessage());
        }

        noteBook.writeJson(path);
    }
}