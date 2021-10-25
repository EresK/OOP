public class StudentBook {
    private Semester[] semesters;
    private int countTotal;
    private int count;

    StudentBook(int countOfSemester) {
        semesters = new Semester[countOfSemester];
        countTotal = countOfSemester;
        count = 0;
    }

    public void addSemester(int semesterNum, int subjectNum) throws Exception {
        if (count >= countTotal) throw new Exception("Can not add subject");

        if (semesters[semesterNum] != null) throw new Exception("Semester already exists");

        semesters[semesterNum] = new Semester(subjectNum);
    }

    public void addSubject(int semesterNum, String name, int mark, boolean passed, boolean last) throws Exception {
        if (count >= countTotal)throw new Exception("Can not add subject");

        if (semesterNum >= count || semesters[semesterNum] == null)
            throw new Exception("Semester does not exists");

        semesters[semesterNum].addSubject(name, mark, passed, last);
    }

    public float averageScore() throws Exception {
        float sum = 0;
        float cnt = 0;

        for (int i = 0; i < count; i++) {
            sum += semesters[i].averageScore();
            cnt += 1;
        }
        if (cnt == 0) throw new Exception("No graded subjects in semesters");

        return sum / cnt;
    }

    public boolean scholarship(int semesterNum) throws Exception {
        if (semesterNum >= count) throw new Exception("Semester does not exists");
        return semesters[semesterNum].scholarship();
    }

    public boolean honorsDegree() throws Exception {
        boolean hons = true;
        float score = 0;

        for (int i = 0; i < count; i++) {
            if (!semesters[i].getWorkPassed() || semesters[i].getWorkMark() < 5)
            {
                hons = false;
                break;
            }
            score += semesters[i].highScore();
        }
        if (score / count < 75) hons = false;

        return hons;
    }
}
