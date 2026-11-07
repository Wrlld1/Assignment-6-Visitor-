package hospital.model;

public enum ServiceCategory {
    CONSULTATION("Consultation"),
    LAB_TEST("Lab test"),
    SURGERY("Surgery"),
    MEDICATION("Medication");

    private final String displayName;

    ServiceCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
