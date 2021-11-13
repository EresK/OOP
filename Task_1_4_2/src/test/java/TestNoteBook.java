import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestNoteBook {
    private static final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @Test
    public void Adding() throws Exception {
        NoteBook noteBook = new NoteBook();

        String[] args1 = "onlyOne".split(" ");
        Exception e = Assertions.assertThrows(Exception.class, () -> noteBook.addNote(args1));
        Assertions.assertEquals("Missed argument", e.getMessage());

        String[] args2 = "FirstTitle FirstBody SecondTitle".split(" ");
        e = Assertions.assertThrows(Exception.class, () -> noteBook.addNote(args2));
        Assertions.assertEquals("Missed argument", e.getMessage());

        String[] args = "FirstTitle FirstBody SecondTitle SecondBody".split(" ");
        noteBook.addNote(args);
    }

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    @Test
    public void Shows() throws Exception {
        NoteBook noteBook = new NoteBook();

        String[] args = "FirstTitle FirstBody".split(" ");
        noteBook.addNote(args);
        noteBook.showNote(null);

        Assertions.assertEquals("FirstTitle\nFirstBody\n", output.toString());
    }

    @Test
    public void ShowsMultiple() throws Exception {
        NoteBook noteBook = new NoteBook();

        String[] args = "FirstTitle FirstBody".split(" ");
        noteBook.addNote(args);

        args = "Second Note".split(" ");
        noteBook.addNote(args);

        noteBook.showNote(null);

        Assertions.assertEquals("FirstTitle\nFirstBody\nSecond\nNote\n", output.toString());
    }

    @Test
    public void ShowsRange() throws Exception {
        NoteBook noteBook = new NoteBook();

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String[] newArg = new String[3];

        String[] args = "FirstTitle FirstBody".split(" ");
        noteBook.addNote(args);

        // Begin
        newArg[0] = sdf.format(new Date());

        args = "Second Note".split(" ");
        noteBook.addNote(args);

        Thread.sleep(1100);

        args = "Third Third".split(" ");
        noteBook.addNote(args);

        // End
        newArg[1] = sdf.format(new Date());
        newArg[2] = "SeCoND";

        noteBook.showNote(newArg);

        Assertions.assertEquals("Second\nNote\n", output.toString());
    }

    @Test
    public void Removing() throws Exception {
        NoteBook noteBook = new NoteBook();

        String[] args = "FirstTitle FirstBody SecondTitle SecondBody".split(" ");
        noteBook.addNote(args);

        args = "Third Third".split(" ");
        noteBook.addNote(args);

        noteBook.showNote(null);
        Assertions.assertEquals("FirstTitle\nFirstBody\nSecondTitle\nSecondBody\nThird\nThird\n",output.toString());

        output.reset();

        String[] title = {"FirstTitle"};
        noteBook.removeNote(title);

        noteBook.showNote(null);
        Assertions.assertEquals("SecondTitle\nSecondBody\nThird\nThird\n",output.toString());

        noteBook.showNote(null);
    }

    @AfterEach
    public void cleanUpStreams() {
        output.reset();
        System.setOut(null);
    }
}
