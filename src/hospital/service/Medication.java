package hospital.service;

import hospital.model.Money;
import hospital.visitor.ServiceVisitor;

public class Medication implements MedicalService {
    private final String name;
    private final Money unitPrice;
    private final int quantity;

    public Medication(String name, Money unitPrice, int quantity) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public Money getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public void accept(ServiceVisitor visitor) {
        visitor.visitMedication(this);
    }
}
