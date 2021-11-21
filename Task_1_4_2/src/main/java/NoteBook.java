import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NoteBook {
    private ArrayList<Note> notes;

    /**
     * @param path - path to json file to read from, may be null
     */
    NoteBook(String path) {
        if (path == null)
            notes = new ArrayList<>();
        else {
            Gson gson = new Gson();
            try (FileReader reader = new FileReader(path)) {
                notes = gson.fromJson(reader, new TypeToken<List<Note>>() {}.getType());
            }
            catch (Exception ignored) {
                notes = new ArrayList<>();
            }
        }
    }

    /**
     * @param args - List with two Strings: title and a note
     * @throws Exception - Missing argument or too many arguments
     */
    public void addNote(ArrayList<String> args) throws Exception {
        if (args.size() == 2) {
            notes.add(new Note(args.get(0), args.get(1), new Date()));
        }
        else
            throw new Exception("Missing argument");
    }

    /**
     * @param arg - A title of a note to remove
     */
    public void removeNote(String arg) {
        if (arg == null)
            throw new NullPointerException();

        notes.removeIf(n -> n.getTitle().equals(arg));
    }

    /**
     * @param args - null or empty list to show all notes, and list: begin, end date, variable titles to remove
     * @throws Exception - Incorrect date representation, not(dd.MM.yyyy HH:mm), or missing begin or end of date
     */
    public void showNote(ArrayList<String> args) throws Exception {
        if (args == null || args.size() == 0) {
            for (Note n: notes)
                System.out.printf("%s\n%s\n", n.getTitle(), n.getBody());
        }
        else if (args.size() >= 2) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");

            Date begin, end;
            try {
                begin = sdf.parse(args.get(0));
                end = sdf.parse(args.get(1));
            }
            catch (ParseException e) {
                throw new Exception("Bad date format");
            }

            for (Note n: notes) {
                if (begin.before(n.getTime()) &&
                        end.after(n.getTime()) &&
                        args.contains(n.getTitle()))
                    System.out.printf("%s\n%s\n", n.getTitle(), n.getBody());
            }
        }
        else
            throw new Exception("Missing argument");
    }

    /**
     * @param path - path to a json file to write notes
     * @throws Exception - Problems with writing to file
     */
    public void writeJson(String path) throws Exception {
        if (path == null)
            throw new NullPointerException();

        try (FileWriter writer = new FileWriter(path)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(notes, writer);
        }
        catch (JsonIOException e) {
            throw new Exception("Can not write notes to file: " + e.getMessage());
        }
    }
}