package hospital.service;

import hospital.model.Patient;
import hospital.visitor.ServiceVisitor;

import java.util.ArrayList;
import java.util.List;

// Object structure: holds the services and lets a visitor walk through them
public class Bill {
    private final Patient patient;
    private final List<MedicalService> services = new ArrayList<>();

    public Bill(Patient patient) {
        this.patient = patient;
    }

    public void add(MedicalService service) {
        services.add(service);
    }

    public Patient getPatient() {
        return patient;
    }

    public void accept(ServiceVisitor visitor) {
        for (MedicalService service : services) {
            service.accept(visitor);
        }
    }
}
