package hospital.service;

import hospital.visitor.ServiceVisitor;

public class Surgery implements MedicalService {
    private final String procedureName;
    private final int durationHours;

    public Surgery(String procedureName, int durationHours) {
        this.procedureName = procedureName;
        this.durationHours = durationHours;
    }

    public String getProcedureName() {
        return procedureName;
    }

    public int getDurationHours() {
        return durationHours;
    }

    @Override
    public void accept(ServiceVisitor visitor) {
        visitor.visitSurgery(this);
    }
}
