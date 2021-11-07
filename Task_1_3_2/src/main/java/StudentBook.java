import java.util.ArrayList;
import java.util.Map;

public class StudentBook {
    private Semester[] sem;
    private int semNum;

    StudentBook(int semNum) {
        sem = new Semester[semNum];
        this.semNum = semNum;
    }

    public void addSubject(String name, int semester, int grade, boolean passed) throws Exception {
        if (name == null || name.equals("") || semester < 1 || semNum < semester || grade < 0) {
            throw new Exception("Incorrect data");
        }

        Result result = new Result();
        result.grade = grade;
        result.passed = passed;

        ArrayList<Result> value = sem[semester - 1].subject.get(name);
        if (value == null) {
            value = new ArrayList<>();
            value.add(result);
            sem[semester - 1].subject.put(name, value);
        }
        value.add(result);
    }

    public float averageScore() throws Exception {
        int sum = 0;
        int count = 0;

        for (int i = 0; i < semNum; i++) {
            if (sem[i].subject.size() > 0) {
                for (Map.Entry<String, ArrayList<Result>> entry: sem[i].subject.entrySet()) {
                    ArrayList<Result> list = entry.getValue();
                    for (Result result : list) {
                        if (result.grade > 0) {
                            sum += result.grade;
                            count++;
                        }
                    }
                }
            }
        }

        if (count == 0) throw new Exception("No graded subjects");
        return sum / count;
    }

    public boolean scholarship(int semester) throws Exception {
        if (semester < 1 || semNum < semester) throw new Exception("Incorrect semester number");

        boolean ship = true;
        if (sem[semester].subject.size() > 0) {
            for (Map.Entry<String, ArrayList<Result>> entry : sem[semester].subject.entrySet()) {
                ArrayList<Result> list = entry.getValue();
                for (Result result : list) {
                    if (!result.passed || (result.grade < 4 && result.grade > 0)) {
                        ship = false;
                        break;
                    }
                }
                if (!ship) break;
            }
        }

        return ship;
    }

    public boolean honorsDegree() {

        boolean hons = true;

        int maxSem = 0;
        int lastRes = 0;

        for (int i = 0; i < semNum; i++) {
            if (sem[i].subject.size() < 1) continue;

            for (Map.Entry<String, ArrayList<Result>> entry : sem[i].subject.entrySet()) {
                ArrayList<Result> list = entry.getValue();
                for (Result result : list) {
                    if (!result.passed || (result.grade < 4 && result.grade > 0)) {
                        return false;
                    }
                    i
                }
            }
        }
    }

}
