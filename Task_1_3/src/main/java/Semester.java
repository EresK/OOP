import java.util.ArrayList;

public class Semester {

    private ArrayList<Subject> subjects;

    private int MAX_SUBJECTS = 20;
    private int subjectCount;

    Semester(int maxSubjects) throws Exception {
        if (maxSubjects <= 0)
            throw new Exception("Subjects number can not be negative");

        subjects = new ArrayList<>();
        subjectCount = 0;
    }

    /**
     * @return - get count of maximum possible subjects
     */
    public int getMAX_SUBJECTS() {return MAX_SUBJECTS;}

    /**
     * @return - get count of subjects
     */
    public int getSubjectCount() {return subjectCount;}

    /**
     * @return - count of subjects with last flags
     */
    public int getLastCount() {
        int cnt = 0;
        for (int i = 1; i < subjectCount; i++)
            if (subjects.get(i).isLast()) cnt++;
        return cnt;
    }

    /**
     * if subject contains it will be rewritten
     * @param name - name of a subject
     * @param mark - mark from 0 to 5
     * @param last - flag means if it is the last semester of the subject
     * @throws Exception - if name is "" or null, mark negative or greater then 5, or
     * tries to add more subjects than max number MAX_SUBJECTS
     */
    public void addSubject(String name, int mark, boolean last) throws Exception {
        if (name == null || name.equals("") || mark < 0 || mark > 5)
            throw new Exception("Incorrect name or mark");

        int index = -1;
        for(Subject s : subjects) {
            if (s.getName().equals(name))
                index = subjects.indexOf(s);
        }
        if (index >= 0) {
            subjects.get(index).setMark(mark);
            subjects.get(index).setLast(last);
        }
        else {
            if (subjectCount >= MAX_SUBJECTS)
                throw new Exception("Too many subjects");
            subjects.add(new Subject(name, mark, last));
            subjectCount++;
        }
    }

    /**
     * @return - returns average result of subjects
     */
    public float getAverageResult() {
        float sum = 0;
        int cnt = 0;
        int tmp;

        for (int i = 1; i < subjectCount; i++) {
            if ((tmp = subjects.get(i).getMark()) != 0) {
                sum += tmp;
                cnt++;
            }
        }

        if (cnt <= 0) return 0;
        return sum / cnt;
    }

    /**
     * @return - true if it is possible to get scholarship
     * false - otherwise
     */
    public boolean isScholarship() {
        boolean ship = true;
        int tmp;

        for (int i = 0; i < subjectCount; i++) {
            tmp = subjects.get(i).getMark();
            if (tmp < 4 && tmp != 0) {
                ship = false;
                break;
            }
        }

        return ship;
    }

    /**
     * @return - count of ace marks
     * @throws Exception - throws if there are no subjects
     */
    public int getAceNumber() throws Exception {
        if (subjectCount <= 0)
            throw new Exception("No any subject");

        int ace = 0;
        int tmp = subjects.get(0).getMark();
        if (tmp == 5 || tmp == 0) {
            for (int i = 1; i < subjectCount; i++) {
                if ((tmp = subjects.get(i).getMark()) < 4 && tmp != 0) {
                    ace = -1;
                    break;
                }
                else if (subjects.get(i).isLast() && tmp == 5)
                    ace++;
            }
        }

        return ace;
    }
}