import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestSemester {

    @Test
    public void AddingSubjects() throws Exception {
        Semester sem = new Semester(20);

        String s = "1";
        for (int i = 0; i < sem.getMAX_SUBJECTS(); i++) {
            sem.addSubject(s, 0, false);
            s += "1";
        }
        Exception e = Assertions.assertThrows(Exception.class, () -> {
            sem.addSubject("new", 0, false);
        });

        Assertions.assertEquals("Too many subjects", e.getMessage());

        Semester sem2 = new Semester(20);

        e = Assertions.assertThrows(Exception.class, () -> {
           sem2.addSubject("", 5, true);
        });
        Assertions.assertEquals("Incorrect name or mark", e.getMessage());

        e = Assertions.assertThrows(Exception.class, () -> {
            sem2.addSubject(null, -2, true);
        });
        Assertions.assertEquals("Incorrect name or mark", e.getMessage());

        e = Assertions.assertThrows(Exception.class, () -> {
            sem2.addSubject("Math", -2, true);
        });
        Assertions.assertEquals("Incorrect name or mark", e.getMessage());
    }

    @Test
    public void AverageResult() throws Exception {
        Semester sem = new Semester(20);

        Assertions.assertEquals(0, sem.getSubjectCount());
        Assertions.assertEquals(0, sem.getAverageResult());

        sem.addSubject("Algebra", 4, false);
        sem.addSubject("English", 4, false);
        sem.addSubject("Programing", 4, true);

        Assertions.assertEquals(3, sem.getSubjectCount());
        Assertions.assertEquals(4, sem.getAverageResult());

        sem.addSubject("Algebra", 5, false);
        sem.addSubject("English", 5, false);
        sem.addSubject("Programing", 5, true);

        Assertions.assertEquals(3, sem.getSubjectCount());
        Assertions.assertEquals(5, sem.getAverageResult());

        sem.addSubject("Haskell", 4, false);
        sem.addSubject("PE", 0, false);
        sem.addSubject("Platforms", 4, true);
        sem.addSubject("History", 4, true);

        Assertions.assertEquals(7, sem.getSubjectCount());
        Assertions.assertEquals(4.4,Math.round(sem.getAverageResult() * 10.0) / 10.0);
    }

    @Test
    public void Scholarship() throws Exception {
        Semester sem = new Semester(20);

        sem.addSubject("Algebra", 4, false);
        sem.addSubject("English", 4, false);
        sem.addSubject("Programing", 4, true);

        Assertions.assertTrue(sem.isScholarship());

        sem.addSubject("PE", 0, false);
        sem.addSubject("Platforms", 4, true);
        sem.addSubject("History", 4, true);

        Assertions.assertTrue(sem.isScholarship());

        sem.addSubject("PE", 3, true);

        Assertions.assertFalse(sem.isScholarship());
    }

    @Test
    public void HonorsDegree() throws Exception {
        Semester sem = new Semester(20);

        Exception e = Assertions.assertThrows(Exception.class, () -> {
            sem.getAceNumber();
        });
        Assertions.assertEquals("No any subject", e.getMessage());

        sem.addSubject("Project", 5, true);
        sem.addSubject("Algebra", 5, true);
        sem.addSubject("English", 5, true);
        sem.addSubject("Programing", 4, true);
        sem.addSubject("Haskell", 4, true);

        Assertions.assertEquals(2, sem.getAceNumber());

        sem.addSubject("Project", 4, true);

        Assertions.assertEquals(0, sem.getAceNumber());
    }
}
