import java.util.Date;

public class Note implements Comparable<Note> {
    private String title;
    private String body;
    private Date time;

    Note(String title, String body, Date time) {
        this.title = title;
        this.body = body;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public Date getTime() {
        return time;
    }

    @Override
    public int compareTo(Note note) {
        if (time.before(note.getTime()))
            return 1;
        return 0;
    }
}
