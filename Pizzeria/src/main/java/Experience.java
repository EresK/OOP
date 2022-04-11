public enum Experience {
    SENIOR(1),
    MIDDLE(2),
    JUNIOR(3);

    private final int experience;

    Experience(int experience) {
        this.experience = experience;
    }

    public int getCoefficient() {
        return experience;
    }
}
