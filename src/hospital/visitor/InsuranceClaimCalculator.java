package hospital.visitor;

import hospital.model.InsurancePlan;
import hospital.model.Money;
import hospital.model.PriceList;
import hospital.model.ServiceCategory;
import hospital.service.Consultation;
import hospital.service.LabTest;
import hospital.service.Medication;
import hospital.service.Surgery;

// Operation 2: splits the bill between the insurer and the patient
public class InsuranceClaimCalculator implements ServiceVisitor {
    private final InsurancePlan plan;
    private Money coveredByInsurance = Money.ZERO;
    private Money patientPays = Money.ZERO;

    public InsuranceClaimCalculator(InsurancePlan plan) {
        this.plan = plan;
    }

    @Override
    public void visitConsultation(Consultation consultation) {
        record(ServiceCategory.CONSULTATION, PriceList.consultation(consultation.getDurationMinutes()));
    }

    @Override
    public void visitLabTest(LabTest labTest) {
        record(ServiceCategory.LAB_TEST, PriceList.labTest(labTest.getBasePrice(), labTest.isUrgent()));
    }

    @Override
    public void visitSurgery(Surgery surgery) {
        record(ServiceCategory.SURGERY, PriceList.surgery(surgery.getDurationHours()));
    }

    @Override
    public void visitMedication(Medication medication) {
        record(ServiceCategory.MEDICATION,
                PriceList.medication(medication.getUnitPrice(), medication.getQuantity()));
    }

    private void record(ServiceCategory category, Money price) {
        Money covered = price.percent(plan.coveragePercent(category));
        coveredByInsurance = coveredByInsurance.plus(covered);
        patientPays = patientPays.plus(price.minus(covered));
    }

    public Money getCoveredByInsurance() {
        return coveredByInsurance;
    }

    public Money getPatientPays() {
        return patientPays;
    }
}
