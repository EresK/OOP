import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestStudentBook {

    @Test
    public void AddingSemesters() throws Exception {
        StudentBook book = new StudentBook(10);
        Exception e;

        e = Assertions.assertThrows(Exception.class, () -> {
            for (int i = 0; i < book.getMAX_SEMESTERS() + 1; i++) {
                book.addSemester(20);
            }
        });
        Assertions.assertEquals("Too many semesters", e.getMessage());

        e = Assertions.assertThrows(Exception.class, () -> {
            StudentBook b = new StudentBook(-20);
        });
        Assertions.assertEquals("Semester number can not be negative", e.getMessage());
    }

    @Test
    public void AddingSubject() throws Exception {
        StudentBook book = new StudentBook(10);
        Exception e;

        book.addSemester(20);

        // Non-existent semester
        e = Assertions.assertThrows(Exception.class, () -> {
            book.addSubject(-1, "Algebra", 5, false);
        });
        Assertions.assertEquals("Non-existent semester", e.getMessage());

        // Incorrect parameters
        e = Assertions.assertThrows(Exception.class, () -> {
            book.addSubject(1, "", 5, false);
        });
        Assertions.assertEquals("Incorrect parameters", e.getMessage());

        e = Assertions.assertThrows(Exception.class, () -> {
            book.addSubject(1, null, 5, false);
        });
        Assertions.assertEquals("Incorrect parameters", e.getMessage());

        e = Assertions.assertThrows(Exception.class, () -> {
            book.addSubject(1, "Algebra", 10, false);
        });
        Assertions.assertEquals("Incorrect parameters", e.getMessage());

        book.addSubject(1, "Algebra", 4, false);
        book.addSubject(1, "Set Theory", 4, false);
        book.addSubject(1, "Programming", 4, false);
        Assertions.assertEquals(3, book.getSemester(1).getSubjectCount());
    }

    @Test
    public void AverageResult() throws Exception {
        StudentBook book = new StudentBook(10);
        Exception e;

        book.addSemester(20);

        book.addSubject(1, "Project", 0, true);
        book.addSubject(1, "Analysis", 3, false);
        book.addSubject(1, "Discrete Math", 4, false);
        book.addSubject(1, "Declarative Programming", 5, false);
        book.addSubject(1, "Imperative Programming", 4, false);
        book.addSubject(1, "History", 5, true);
        book.addSubject(1, "Speech", 5, true);

        Assertions.assertEquals(4.3, Math.round(book.getAverageResult() *  10.0) / 10.0 );

        book.addSemester(20);

        book.addSubject(2, "Project", 5, true);
        book.addSubject(2, "Analysis", 4, true);
        book.addSubject(2, "Discrete Math", 4, true);
        book.addSubject(2, "Declarative Programming", 5, true);
        book.addSubject(2, "Imperative Programming", 5, true);
        book.addSubject(2, "English", 4, false);
        book.addSubject(2, "Digital Platforms", 5, true);

        Assertions.assertEquals(4.4, Math.round(book.getAverageResult() *  10.0) / 10.0 );
    }

    @Test
    public void Scholarship() throws Exception {
        StudentBook book = new StudentBook(10);
        Exception e;

        e = Assertions.assertThrows(Exception.class, () -> {
            book.isScholarship(1);
        });
        Assertions.assertEquals("Incorrect semester number", e.getMessage());

        book.addSemester(20);
        book.addSubject(1, "Project", 0, true);
        book.addSubject(1, "Analysis", 3, false);
        book.addSubject(1, "Discrete Math", 4, false);
        book.addSubject(1, "Declarative Programming", 5, false);
        book.addSubject(1, "Imperative Programming", 4, false);
        book.addSubject(1, "History", 5, true);
        book.addSubject(1, "Speech", 5, true);

        Assertions.assertFalse(book.isScholarship(1));

        book.addSemester(20);
        book.addSubject(2, "Project", 5, true);
        book.addSubject(2, "Analysis", 4, true);
        book.addSubject(2, "Discrete Math", 4, true);
        book.addSubject(2, "Declarative Programming", 5, true);
        book.addSubject(2, "Imperative Programming", 5, true);
        book.addSubject(2, "English", 4, false);
        book.addSubject(2, "Digital Platforms", 5, true);

        Assertions.assertTrue(book.isScholarship(2));
    }

    @Test
    public void HonorsDegree() throws Exception {
        StudentBook book = new StudentBook(10);

        book.addSemester(20);
        book.addSubject(1, "Project", 0, true);
        book.addSubject(1, "Analysis", 3, false);
        book.addSubject(1, "Discrete Math", 4, false);
        book.addSubject(1, "Declarative Programming", 5, false);
        book.addSubject(1, "Imperative Programming", 4, false);
        book.addSubject(1, "History", 5, true);
        book.addSubject(1, "Speech", 5, true);

        book.addSemester(20);
        book.addSubject(2, "Project", 5, true);
        book.addSubject(2, "Analysis", 4, true);
        book.addSubject(2, "Discrete Math", 4, true);
        book.addSubject(2, "Declarative Programming", 5, true);
        book.addSubject(2, "Imperative Programming", 5, true);
        book.addSubject(2, "English", 4, false);
        book.addSubject(2, "Digital Platforms", 5, true);

        Assertions.assertFalse(book.isHonorsDegree());

        book.addSubject(1, "Analysis", 5, false);
        book.addSubject(2, "Analysis", 5, true);
        book.addSubject(2, "Discrete Math", 5, true);

        Assertions.assertTrue(book.isHonorsDegree());

        book.addSubject(1, "Analysis", 3, false);

        Assertions.assertFalse(book.isHonorsDegree());
    }
}
