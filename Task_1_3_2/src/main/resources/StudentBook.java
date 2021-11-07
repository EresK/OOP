/*import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StudentBook {
    private Map<String, ArrayList<Result>> subjects;

    StudentBook() {
        subjects = new HashMap<>();
    }

    public void addSubject(String name, int semester, int grade, boolean passed) throws Exception {
        if (name == null || name.equals("") || semester <= 0 || grade < 0) throw new Exception("Incorrect data");

        Result res = new Result();
        res.semester = semester;
        res.grade = grade;
        res.passed = passed;

        ArrayList<Result> value = subjects.get(name);

        if (value == null) {
            value = new ArrayList<>();
            value.add(res);
            subjects.put(name, value);
        }

        value.add(res);
    }

    public float averageScore() throws Exception{
        int sum = 0;
        int count = 0;

        if (subjects.size() == 0) throw new Exception("There are no any subjects");

        for (Map.Entry<String, ArrayList<Result>> entry : subjects.entrySet()) {
            ArrayList<Result> list = entry.getValue();
            Result res;
            for (Result result : list) {
                res = result;
                if (res.grade != 0) {
                    sum += res.grade;
                    count++;
                }
            }
        }

        if (count == 0) throw new Exception("There are no graded subjects");
        return  sum / count;
    }

*/

    /*private ArrayList<Subject> subjects;

    StudentBook() {
        subjects = new ArrayList<>();
    }

    public void addSubject(String name, int semester, int grade, boolean passed) throws Exception {
        if (name == null || name.equals("") || semester <= 0 || grade <= 0) throw new Exception("Incorrect data");

        subjects.contains();

        Result res = new Result();
        res.semester = semester;
        res.grade = grade;
        res.passed = passed;

        Subject sub = new Subject(name);
        sub.addResult(res);

        subjects.add(sub);
    }*/

    /*public short averageResult() {
        return 5;
    }
    public boolean highScholarship() {
        return true;
    }
    public boolean honorsDegree() {
        return true;
    }
    */
}
