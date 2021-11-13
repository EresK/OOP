import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class NoteBook {
    ArrayList<Note> notes;

    NoteBook() {
        notes = new ArrayList<>();
    }

    /**
     * @param path - path to json file to read from
     * @throws Exception - Can not read from file: notes list will empty
     */
    NoteBook(String path) throws Exception {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(path)) {
            notes = gson.fromJson(reader, new TypeToken<List<Note>>() {}.getType());
        }
        if (notes == null)
            notes = new ArrayList<>();
    }

    /**
     * @param args - arguments represented like TitleOfNote and TheNote
     * @throws Exception - Null args, missed title or the note body
     */
    public void addNote(String[] args) throws Exception {
        if (args == null)
            throw new NullPointerException();

        if (args.length % 2 == 0) {
            for (int i = 0; i < args.length; i += 2) {
                notes.add(new Note(args[i], args[i + 1], new Date()));
            }
        }
        else
            throw new Exception("Missed argument");
    }

    /**
     * @param args - arguments represented like Titles to delete
     */
    public void removeNote(String[] args) {
        if (args == null)
            throw new NullPointerException();

        ArrayList<String> tmp = new ArrayList<>(Arrays.asList(args));

        notes.removeIf(n -> tmp.contains(n.getTitle()));
    }

    /**
     * @param args
     * empty args to show all notes or string represented Begin, End, VariableTitles...,
     * Begin, End are dd.MM.yyyy HH:mm
     * @throws Exception - Bad date representation or missed one of: Begin, End, VariableTitles.
     */
    public void showNote(String[] args) throws Exception {
        if (args == null || args.length == 0) {
            for (Note n: notes) {
                System.out.printf("%s\n%s\n", n.getTitle(), n.getBody());
            }
        }
        else if (args.length >= 2) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

            Date begin, end;
            try {
                begin = sdf.parse(args[0]);
                end = sdf.parse(args[1]);
            }
            catch (ParseException e) {
                throw new Exception("Can not parse date: " + e.getMessage());
            }

            List<String> keywords = Arrays.stream(args).map(String::toLowerCase).collect(Collectors.toList());
            keywords.remove(0);
            keywords.remove(0);

            for (Note n: notes) {
                if (begin.before(n.getTime()) &&
                        end.after(n.getTime()) &&
                        keywords.contains(n.getTitle().toLowerCase()))
                    System.out.printf("%s\n%s\n", n.getTitle(), n.getBody());
            }
        }
        else
            throw new Exception("Missing argument");
    }

    /**
     * @param path - path to json file
     * @throws Exception - Can not write to the file
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