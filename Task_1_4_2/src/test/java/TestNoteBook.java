import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class TestNoteBook {
    private static final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @Test
    public void TestParser() throws Exception {
        Parser parser = new Parser();

        String[] words = {"-p", "\"filename\"", "-add", "title", "body"};
        String str = "-p \"filename\" -add title body";
        Assertions.assertArrayEquals(words, parser.parse(str.split(" ")));

        String str2 = "\"file";
        Exception e = Assertions.assertThrows(Exception.class, () -> parser.parse(str2.split(" ")));
        Assertions.assertEquals("Expected end of quotation", e.getMessage());
    }

    @Test
    public void TestSimpleCMD() throws Exception {
        Parser parser = new Parser();
        SimpleCMD cmd = new SimpleCMD();

        String str = "-p filename -a title \"inner data\" -a \"new title\" data -s -r title";
        String[] words = parser.parse(str.split(" "));
        cmd.toOptions(words);

        String[] args = {"title", "\"inner data\""};
        String[] opts = cmd.addArgs.get(0).toArray(new String[0]);
        Assertions.assertArrayEquals(args, opts);

        args = new String[] {"\"new title\"", "data"};
        opts = cmd.addArgs.get(1).toArray(new String[0]);
        Assertions.assertArrayEquals(args, opts);

        Assertions.assertEquals("filename", cmd.path);

        Assertions.assertEquals("title", cmd.removeArgs.get(0));

        Assertions.assertEquals(0, cmd.showArgs.get(0).size());

        str = "-p filename -s \"20.11.2014 1:00\" \"20.11.2021 21:00\" title";
        words = parser.parse(str.split(" "));
        cmd.toOptions(words);

        args = new String[] {"\"20.11.2014 1:00\"", "\"20.11.2021 21:00\"", "title"};
        Assertions.assertArrayEquals(args, cmd.showArgs.get(0).toArray(new String[0]));

        // Assertions missed arguments
        Exception e;
        e = Assertions.assertThrows(Exception.class, () ->
                cmd.toOptions(new String[] {"-p"}));
        Assertions.assertEquals("Missing path", e.getMessage());

        e = Assertions.assertThrows(Exception.class, () ->
                cmd.toOptions(new String[] {"-p", "filename.json", "--add", "header"}));
        Assertions.assertEquals("Missing argument", e.getMessage());

        e = Assertions.assertThrows(Exception.class, () ->
                cmd.toOptions(new String[] {"-p", "filename.json", "--rm"}));
        Assertions.assertEquals("Missing argument", e.getMessage());
    }

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    @Test
    public void TestTheNoteBook() throws Exception {
        SimpleCMD cmd = new SimpleCMD();
        NoteBook noteBook = new NoteBook(null);

        cmd.toOptions(new String[] {"-p", "filename", "-a", "title name", "inner data", "--add", "name", "note"});

        // Assertion showNote without arguments

        noteBook.addNote(cmd.addArgs.get(0));
        noteBook.showNote(null);
        Assertions.assertEquals("title name\ninner data\n", output.toString());

        output.reset();

        noteBook.addNote(cmd.addArgs.get(1));
        noteBook.showNote(null);
        Assertions.assertEquals("title name\ninner data\nname\nnote\n", output.toString());

        output.reset();

        noteBook.removeNote("title name");
        noteBook.showNote(null);
        Assertions.assertEquals("name\nnote\n", output.toString());


        // Assertion showNote with arguments
        output.reset();

        noteBook.addNote(cmd.addArgs.get(0));
        ArrayList<String> args = new ArrayList<>();
        args.add("20.11.2020 1:00");
        args.add("20.12.2021 21:00");
        args.add("title name");

        noteBook.showNote(args);
        Assertions.assertEquals("title name\ninner data\n", output.toString());

        output.reset();
        args.clear();

        args.add("20.11.2020 1:00");
        args.add("incorrect data");
        args.add("title name");
        Exception e = Assertions.assertThrows(Exception.class, () -> noteBook.showNote(args));
        Assertions.assertEquals("Bad date format", e.getMessage());

        output.reset();
        args.clear();

        args.add("20.11.2020 1:00");
        e = Assertions.assertThrows(Exception.class, () -> noteBook.showNote(args));
        Assertions.assertEquals("Missing argument", e.getMessage());
    }

    @AfterEach
    public void cleanUpStreams() {
        System.setOut(null);
    }
}
