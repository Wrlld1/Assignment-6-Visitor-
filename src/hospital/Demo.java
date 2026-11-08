package hospital;

import hospital.model.InsurancePlan;
import hospital.model.Money;
import hospital.model.Patient;
import hospital.service.Bill;
import hospital.service.Consultation;
import hospital.service.LabTest;
import hospital.service.Medication;
import hospital.service.Surgery;
import hospital.visitor.CostCalculator;
import hospital.visitor.InsuranceClaimCalculator;
import hospital.visitor.InvoicePrinter;

public class Demo {
    public static void main(String[] args) {
        Patient patient = new Patient("Aruzhan S.", InsurancePlan.BASIC);
        Bill bill = buildBill(patient);

        InvoicePrinter printer = new InvoicePrinter();
        bill.accept(printer);

        CostCalculator cost = new CostCalculator();
        bill.accept(cost);

        InsuranceClaimCalculator claim = new InsuranceClaimCalculator(patient.getPlan());
        bill.accept(claim);

        System.out.println("Invoice for " + patient.getName() + " (plan: " + patient.getPlan() + ")");
        System.out.println("------------------------------------------------------------------");
        System.out.print(printer.getInvoice());
        System.out.println("------------------------------------------------------------------");
        System.out.println("Total:               " + cost.getTotal());
        System.out.println("Covered by insurer:  " + claim.getCoveredByInsurance());
        System.out.println("Patient pays:        " + claim.getPatientPays());
    }

    private static Bill buildBill(Patient patient) {
        Bill bill = new Bill(patient);
        bill.add(new Consultation("Dr. Omarov", 30));
        bill.add(new LabTest("Blood count", new Money(4_000), false));
        bill.add(new LabTest("Troponin test", new Money(6_000), true));
        bill.add(new Surgery("Appendectomy", 2));
        bill.add(new Medication("Amoxicillin", new Money(1_200), 3));
        return bill;
    }
}
