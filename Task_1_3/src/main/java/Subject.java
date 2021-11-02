public class Subject {
    /**
     *  Work by default contains in 0 index
     *  If there is only passed or not for subject
     *  then mark == 0 if passed
     *  and mark == 2 of not passed
     */
    private String name;
    private int mark;
    private boolean last;

    Subject(String name, int mark, boolean last) throws Exception {
        if (name == null || name.equals("") || mark < 0 || mark > 5)
            throw new Exception("Incorrect parameters");

        this.name = name;
        this.mark = mark;
        this.last = last;
    }

    public String getName() {return name;}
    public int getMark() {return mark;}
    public boolean isLast() {return last;}

    public void setName(String name) throws Exception {
        if (name == null || name.equals(""))
            throw new Exception("Incorrect parameters");
        this.name = name;
    }

    public void setMark(int mark) throws Exception {
        if (mark < 0 || mark > 5)
            throw new Exception("Incorrect parameters");
        this.mark = mark;
    }

    public void setLast(boolean last) {
        this.last = last;
    }
}
