package hospital.service;

import hospital.model.Money;
import hospital.visitor.ServiceVisitor;

public class LabTest implements MedicalService {
    private final String testName;
    private final Money basePrice;
    private final boolean urgent;

    public LabTest(String testName, Money basePrice, boolean urgent) {
        this.testName = testName;
        this.basePrice = basePrice;
        this.urgent = urgent;
    }

    public String getTestName() {
        return testName;
    }

    public Money getBasePrice() {
        return basePrice;
    }

    public boolean isUrgent() {
        return urgent;
    }

    @Override
    public void accept(ServiceVisitor visitor) {
        visitor.visitLabTest(this);
    }
}
