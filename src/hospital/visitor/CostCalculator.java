package hospital.visitor;

import hospital.model.Money;
import hospital.model.PriceList;
import hospital.service.Consultation;
import hospital.service.LabTest;
import hospital.service.Medication;
import hospital.service.Surgery;

// Operation 1: total price of the bill
public class CostCalculator implements ServiceVisitor {
    private Money total = Money.ZERO;

    @Override
    public void visitConsultation(Consultation consultation) {
        total = total.plus(PriceList.consultation(consultation.getDurationMinutes()));
    }

    @Override
    public void visitLabTest(LabTest labTest) {
        total = total.plus(PriceList.labTest(labTest.getBasePrice(), labTest.isUrgent()));
    }

    @Override
    public void visitSurgery(Surgery surgery) {
        total = total.plus(PriceList.surgery(surgery.getDurationHours()));
    }

    @Override
    public void visitMedication(Medication medication) {
        total = total.plus(PriceList.medication(medication.getUnitPrice(), medication.getQuantity()));
    }

    public Money getTotal() {
        return total;
    }
}
