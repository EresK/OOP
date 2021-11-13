import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Date;

public class Note implements Comparable<Note>, Serializable {
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

    public String getBody() {
        return body;
    }

    public Date getTime() {
        return time;
    }

    @Override
    public int compareTo(@NotNull Note note) {
        if (time.before(note.getTime()))
            return 1;
        return 0;
    }
}
