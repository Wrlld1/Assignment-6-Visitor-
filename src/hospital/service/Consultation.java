package hospital.service;

import hospital.visitor.ServiceVisitor;

public class Consultation implements MedicalService {
    private final String doctorName;
    private final int durationMinutes;

    public Consultation(String doctorName, int durationMinutes) {
        this.doctorName = doctorName;
        this.durationMinutes = durationMinutes;
    }

    // Getters are public so visitors can read the data (encapsulation trade-off of Visitor)
    public String getDoctorName() {
        return doctorName;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    @Override
    public void accept(ServiceVisitor visitor) {
        visitor.visitConsultation(this);
    }
}
