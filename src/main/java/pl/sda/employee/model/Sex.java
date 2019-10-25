package pl.sda.employee.model;

public enum Sex {
    MEN("MEN"),
    WOMAN("WOMAN");

    private final String displaySex;

    Sex(String displaySex) {
        this.displaySex = displaySex;
    }

    public String getDisplaySex() {
        return displaySex;
    }
}
