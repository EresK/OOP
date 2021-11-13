import org.apache.commons.cli.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Option jpath = new Option("p", "path", true, "path to .json file");
        jpath.setRequired(true);
        jpath.setArgs(1);

        Option add = new Option("a", "add", true,"add new note");
        add.setArgs(2);

        Option rm = new Option("r", "rm", true,"remove existing note");
        rm.setArgs(1);

        Option show = new Option("s", "show", true,"shows appropriate notes");
        show.setOptionalArg(true);
        show.setArgs(Option.UNLIMITED_VALUES);

        Options options = new Options();
        options.addOption(add);
        options.addOption(rm);
        options.addOption(show);
        options.addOption(jpath);

        try {
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);

            if (!cmd.hasOption(jpath))
                throw new Exception("Needs to path to json file");

            String path = cmd.getOptionValue("path");
            NoteBook noteBook = new NoteBook(path);

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

            noteBook.writeJson(path);
        }
        catch (ParseException e) {
            System.err.println("Parsing failed: " + e.getMessage());
        }
    }
}