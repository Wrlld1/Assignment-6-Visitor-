package hospital.service;

import hospital.visitor.ServiceVisitor;

// Element interface
public interface MedicalService {
    void accept(ServiceVisitor visitor);
}
