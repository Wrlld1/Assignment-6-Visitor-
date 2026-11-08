package hospital.visitor;

import hospital.model.Money;
import hospital.model.PriceList;
import hospital.model.ServiceCategory;
import hospital.service.Consultation;
import hospital.service.LabTest;
import hospital.service.Medication;
import hospital.service.Surgery;

// Operation 3: builds a printable list of invoice lines
public class InvoicePrinter implements ServiceVisitor {
    private final StringBuilder lines = new StringBuilder();

    @Override
    public void visitConsultation(Consultation consultation) {
        addLine(ServiceCategory.CONSULTATION,
                consultation.getDoctorName() + ", " + consultation.getDurationMinutes() + " min",
                PriceList.consultation(consultation.getDurationMinutes()));
    }

    @Override
    public void visitLabTest(LabTest labTest) {
        String suffix = labTest.isUrgent() ? " (urgent)" : "";
        addLine(ServiceCategory.LAB_TEST,
                labTest.getTestName() + suffix,
                PriceList.labTest(labTest.getBasePrice(), labTest.isUrgent()));
    }

    @Override
    public void visitSurgery(Surgery surgery) {
        addLine(ServiceCategory.SURGERY,
                surgery.getProcedureName() + ", " + surgery.getDurationHours() + " h",
                PriceList.surgery(surgery.getDurationHours()));
    }

    @Override
    public void visitMedication(Medication medication) {
        addLine(ServiceCategory.MEDICATION,
                medication.getName() + " x" + medication.getQuantity(),
                PriceList.medication(medication.getUnitPrice(), medication.getQuantity()));
    }

    private void addLine(ServiceCategory category, String description, Money price) {
        lines.append(String.format("%-14s %-34s %14s%n", category.getDisplayName(), description, price));
    }

    public String getInvoice() {
        return lines.toString();
    }
}
