import java.util.ArrayList;

public class StudentBook {
    private ArrayList<Semester> semester;

    private int MAX_SEMESTERS;
    private int countSemester;

    StudentBook(int maxSemesters) throws Exception {
        if (maxSemesters <= 0)
            throw new Exception("Semester number can not be negative");

        semester = new ArrayList<>();
        MAX_SEMESTERS = maxSemesters;
        countSemester = 0;
    }

    /**
     * @return - get count of maximum possible semesters
     */
    public int getMAX_SEMESTERS() { return MAX_SEMESTERS; }

    /**
     * @return - get count of semesters
     */
    public int getCountSemester() { return countSemester; }

    /**
     * @param maxSubjects - number of maximum possible subjects
     * @throws Exception - throws when tries to add more than maximum semesters number MAX_SEMESTERS
     */
    public void addSemester(int maxSubjects) throws Exception {
        if (countSemester >= MAX_SEMESTERS)
            throw new Exception("Too many semesters");

        if (maxSubjects <= 0)
            throw new Exception("Subjects number can not be negative");

        semester.add(new Semester(maxSubjects));
        ++countSemester;
    }

    /**
     * @param semNum - number of the semester
     * @return - Semester by semNum index
     * @throws Exception - throws when try to get Semester which not in range
     */
    public Semester getSemester(int semNum) throws Exception {
        if (semNum <= 0 || semNum > countSemester)
            throw new Exception("Non-existent semester");

        return semester.get(semNum - 1);
    }

    /**
     * @param semNum - number of the semester
     * @param name - name of a subject
     * @param mark - mark from 0 to 5
     * @param last - flag means if it is the last semester of the subject
     * @throws Exception - throws when try to add to Semester which not in range
     */
    public void addSubject(int semNum, String name, int mark, boolean last) throws Exception {
        if (semNum <= 0 || semNum > countSemester)
            throw new Exception("Non-existent semester");
        if (name == null || name.equals("") || mark < 0 || mark > 5)
            throw new Exception("Incorrect parameters");

        semester.get(semNum - 1).addSubject(name, mark, last);
    }

    /**
     * @return - average result of all subjects of all semesters
     */
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

    /**
     * @param semNum - number of the semester
     * @return - true if it is possible to get scholarship
     * false - otherwise
     * @throws Exception - throws when try to index by Semester which not in range
     */
    public boolean isScholarship(int semNum) throws Exception {
        if (semNum <= 0 || semNum > countSemester)
            throw new Exception("Incorrect semester number");

        return semester.get(semNum - 1).isScholarship();
    }

    /**
     * @return - true if it is possible to get Hons
     * false - otherwise
     * @throws Exception - throws if there are no subjects
     */
    public boolean isHonorsDegree() throws Exception {
        boolean hons = true;
        int ace = 0;
        int cnt = 0;
        int tmp;

        for (int i = 0; i < countSemester; i++) {
            if ((tmp = semester.get(i).getAceNumber()) < 0) {
                hons = false;
                break;
            }
            else if (tmp > 0) {
                ace += tmp;
                cnt += semester.get(i).getLastCount();
            }
        }

        if ((cnt != 0) && (((float) ace / (float) cnt) * 100 < 75.0))
            hons = false;

        return hons;
    }
}
