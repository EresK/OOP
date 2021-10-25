import java.util.ArrayList;

public class StudentBook {
    private ArrayList<Semester> semester;

    private int MAX_SEMESTERS = 10;
    private int countSemester;

    StudentBook() {
        semester = new ArrayList<>();
        countSemester = 0;
    }

    public void addSemester() throws Exception {
        if (countSemester + 1 > MAX_SEMESTERS)
            throw new Exception("Too many semesters");

        semester.add(new Semester());
        ++countSemester;
    }

    public void addSubject(int semNum, String name, int mark, boolean last) throws Exception {
        if (semNum <= 0 || semNum > countSemester || name == null || name.equals("") || mark < 0 || mark > 5)
            throw new Exception("Incorrect parameters");

        semester.get(semNum - 1).addSubject(name, mark, last);
    }

    public float getAverageResult() {
        float sum = 0;
        int cnt = 0;
        float tmp;

        for (int i = 0; i < countSemester; i++) {
            if ((tmp = semester.get(i).getAverageResult()) > 0) {
                sum += tmp;
                ++cnt;
            }
        }

        if (cnt <= 0) return 0;
        return sum / cnt;
    }

    public boolean isScholarship(int semNum) throws Exception {
        if (semNum <= 0 || semNum > countSemester)
            throw new Exception("Incorrect semester number");

        return semester.get(semNum).isScholarship();
    }

    public boolean isHonorsDegree() throws Exception {
        boolean hons = true;
        float sum = 0;
        int cnt = 0;
        float tmp;

        for (int i = 0; i < countSemester; i++) {
            if ((tmp = semester.get(i).getHonorsResult()) < 0) {
                hons = false;
                break;
            }
            if (tmp > 0) {
                sum += tmp;
                ++cnt;
            }
        }

        if (cnt <= 0) return hons;
        if ((tmp = sum / cnt) >= 75) return hons;
        return false;
    }
}
