public class Semester {
    private String[] name;
    private int[] mark;
    private boolean[] passed;
    private boolean[] last;
    private int countTotal;
    private int count;

    private String work_name;
    private int work_mark;
    private boolean work_passed;

    Semester(int countOfSubject) {
        name = new String[countOfSubject];
        mark = new int[countOfSubject];
        passed = new boolean[countOfSubject];
        last = new boolean[countOfSubject];
        countTotal = countOfSubject;
        count = 0;
    }

    public int getCountTotal() {return countTotal;}
    public int getCount() {return count;}
    public int getWorkMark() {return work_mark;}
    public boolean getWorkPassed() {return work_passed;}

    public void addSubject(String name, int mark, boolean passed, boolean last) throws Exception {
        if (count >= countTotal) throw new Exception("Can not add subject");
        this.name[count] = name;
        this.mark[count] = mark;
        this.passed[count] = passed;
        this.last[count] = last;
        count++;
    }

    public void addWork(String work_name, int work_mark, boolean work_passed) {
        this.work_name = work_name;
        this.work_mark = work_mark;
        this.work_passed = work_passed;
    }

    public float averageScore() throws Exception {
        int sum = 0;
        int cnt = 0;

        for (int i = 0; i < count; i++) {
            if (mark[i] < 1) continue;
            sum += mark[i];
            cnt++;
        }
        if (cnt <= 0) throw new Exception("No graded subjects");

        return sum / cnt;
    }

    public boolean scholarship() {
        boolean ship = true;

        for (int i = 0; i < count; i++) {
            if (!passed[i] || (mark[i] < 4 && mark[i] > 0)) {
                ship = false;
                break;
            }
        }

        return ship;
    }

    public float highScore() throws Exception {
        boolean high = true;

        int cntAce = 0;
        int cntAll = 0;

        for (int i = 0; i < count; i++) {
            if (!last[i]) continue;
            if (!passed[i] || (mark[i] < 4 && mark[i] > 0))
            {
                high = false;
                break;
            }
            if (mark[i] == 5) cntAce++;
            if (mark[i] > 0) cntAll++;
        }
        if (cntAll <= 0) throw new Exception("No graded subjects");

        return cntAce / cntAll;
    }
}
