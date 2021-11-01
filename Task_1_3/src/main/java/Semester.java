import java.util.ArrayList;

public class Semester {
    // Work by default contains in 0 index
    // If there is only passed or not for subject
    // then mark == 0 if passed
    // and  mark == 2 of not passed
    private ArrayList<String> name;
    private ArrayList<Integer> mark;
    private ArrayList<Boolean> last;

    private int MAX_SUBJECTS = 20;
    private int countSubject;

    Semester(int maxSubjects) throws Exception {
        if (maxSubjects <= 0)
            throw new Exception("Subjects number can not be negative");

        name = new ArrayList<>();
        mark = new ArrayList<>();
        last = new ArrayList<>();
        countSubject = 0;
    }

    /**
     * @return - get count of maximum possible subjects
     */
    public int getMAX_SUBJECTS() {return MAX_SUBJECTS;}

    /**
     * @return - get count of subjects
     */
    public int getCount() {return countSubject;}

    /**
     * @return - count of subjects with last flags
     */
    public int getLastCount() {
        int cnt = 0;
        for (int i = 1; i < countSubject; i++) {
            if (last.get(i)) ++cnt;
        }
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

        int index;
        if ((index = this.name.indexOf(name)) >= 0) {
            this.mark.set(index, mark);
            this.last.set(index, last);
        }
        else {
            if (countSubject >= MAX_SUBJECTS)
                throw new Exception("Too many subjects");

            this.name.add(name);
            this.mark.add(mark);
            this.last.add(last);
            ++countSubject;
        }
    }

    /**
     * @return - returns average result of subjects
     */
    public float getAverageResult() {
        float sum = 0;
        int cnt = 0;
        int tmp;

        for (int i = 0; i < countSubject; i++) {
            if ((tmp = mark.get(i)) != 0) {
                sum += tmp;
                ++cnt;
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

        for (int i = 0; i < countSubject; i++) {
            tmp = mark.get(i);
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
        if (countSubject <= 0)
            throw new Exception("No any subject");

        int ace = 0;
        int tmp;

        if (mark.get(0) == 5 || mark.get(0) == 0) {
            for (int i = 1; i < countSubject; i++) {
                if ((tmp = mark.get(i)) < 4 && tmp != 0) {
                    ace = -1;
                    break;
                }
                else if (last.get(i) && tmp == 5) ace += 1;
            }
        }

        return ace;
    }
}