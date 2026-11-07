package hospital.model;

public final class Patient {
    private final String name;
    private final InsurancePlan plan;

    public Patient(String name, InsurancePlan plan) {
        this.name = name;
        this.plan = plan;
    }

    public String getName() {
        return name;
    }

    public InsurancePlan getPlan() {
        return plan;
    }
}
