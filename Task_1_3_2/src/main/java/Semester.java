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

    Semester() {
        name = new ArrayList<>();
        mark = new ArrayList<>();
        last = new ArrayList<>();
        countSubject = 0;
    }

    public int getMAX_SUBJECTS() {return MAX_SUBJECTS;}
    public int getCount() {return countSubject;}

    // if subject contains it will be rewrite
    public void addSubject(String name, int mark, boolean last) throws Exception {
        if (name == null || name.equals("") || mark < 0 || mark > 5)
            throw new Exception("Incorrect name or mark");

        int index;
        if ((index = this.name.indexOf(name)) > 0) {
            this.mark.set(index, mark);
            this.last.set(index, last);
        }
        else {
            if (countSubject + 1 >= MAX_SUBJECTS)
                throw new Exception("Too many subjects");

            this.name.add(name);
            this.mark.add(mark);
            this.last.add(last);
            ++countSubject;
        }
    }

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

    public float getHonorsResult() throws Exception {
        if (countSubject <= 0)
            throw new Exception("No any subject");

        boolean hons = true;
        int sum = 0;
        int cnt = 0;
        int tmp;

        if (mark.get(0) < 5 && mark.get(0) != 0)
            hons = false;
        else {
            for (int i = 1; i < countSubject; i++) {
                if ((tmp = mark.get(i)) < 4 && tmp != 0) {
                    hons = false;
                    break;
                }
                else if (last.get(i) && tmp != 0) {
                    sum += tmp;
                    ++cnt;
                }
            }
        }

        if (!hons) return -1;
        if (cnt <= 0) return 0;
        return (float) (sum / cnt);
    }
}