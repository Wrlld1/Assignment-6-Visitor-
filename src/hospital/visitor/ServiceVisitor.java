package hospital.visitor;

import hospital.service.Consultation;
import hospital.service.LabTest;
import hospital.service.Medication;
import hospital.service.Surgery;

// One visit method per concrete element type
public interface ServiceVisitor {
    void visitConsultation(Consultation consultation);

    void visitLabTest(LabTest labTest);

    void visitSurgery(Surgery surgery);

    void visitMedication(Medication medication);
}
