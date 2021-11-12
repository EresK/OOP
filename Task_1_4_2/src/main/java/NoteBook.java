import java.util.*;

public class NoteBook {
    private ArrayList<Note> notes;

    NoteBook() {
        notes = new ArrayList<>();
    }

    public void addNote(String[] args) throws Exception {
        if (args.length != 2)
            throw new Exception("Needs only two arguments title and body");

        if (args[0] == null || args[1] == null ||
        args[0].equals("") || args[1].equals(""))
            throw new Exception("Empty argument");

        //SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        //Date date = new Date();

        notes.add(new Note(args[0], args[1], new Date()));
    }

    public void removeNote(String[] args) throws Exception {
        if (args == null)
            throw new NullPointerException();

        for (String arg: args)
            notes.removeIf(n -> n.getTitle().equals(arg));
    }

    public void showNotes() {
        notes.sort(Note::compareTo);

        for (Note note: notes) {
            System.out.println(note);
        }
    }

    private Date compareNotes(Note a, Note b) {
        Date date_a = a.getTime();
        Date date_b = b.getTime();

        if (date_a.after(date_b))
            return date_a;
        else
            return date_b;
    }
}
