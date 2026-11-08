# Hospital Billing - Visitor Pattern

Assignment #6 (Software Design Patterns). A patient's hospital bill holds different medical
services. New operations on the bill (price, insurance split, invoice text) are added as visitors,
without touching the service classes.

## Classes
- `model.Money` - immutable amount in tenge with plus/minus/times/percent
- `model.ServiceCategory` - enum of service kinds (used for insurance rates and printing)
- `model.InsurancePlan` - enum NONE/BASIC/PREMIUM with coverage percent per category
- `model.Patient` - patient name and insurance plan
- `model.PriceList` - all tariffs and pricing rules in one place
- `service.MedicalService` - Element interface with `accept(ServiceVisitor)`
- `service.Consultation`, `LabTest`, `Surgery`, `Medication` - concrete elements
- `service.Bill` - object structure, loops over services and calls `accept`
- `visitor.ServiceVisitor` - Visitor interface, one `visitX` per element
- `visitor.CostCalculator` - total price of the bill
- `visitor.InsuranceClaimCalculator` - insurer share vs. patient share
- `visitor.InvoicePrinter` - printable invoice lines
- `Demo` - builds a bill and runs all three visitors

## Example
```java
Bill bill = new Bill(new Patient("Aruzhan S.", InsurancePlan.BASIC));
bill.add(new Surgery("Appendectomy", 2));

CostCalculator cost = new CostCalculator();
bill.accept(cost);                 // Bill -> Surgery.accept -> cost.visitSurgery(this)
System.out.println(cost.getTotal());
```

## Clean Code principles
1. Meaningful names: elements are nouns (`Surgery`), visitors are operations (`CostCalculator`).
2. No `instanceof` chains: double dispatch (`accept` -> `visitX(this)`) picks the right method.
3. Single Responsibility: each visitor does exactly one operation for all element types.
4. DRY: tariffs live only in `PriceList`, insurance rates only in `InsurancePlan`.
5. Extensibility without modification: a new visitor needs no change in services or other visitors.

## Run
Windows (PowerShell):
```
javac -d out -sourcepath src src\hospital\Demo.java
java -cp out hospital.Demo
```
Linux/macOS:
```
javac -d out -sourcepath src src/hospital/Demo.java && java -cp out hospital.Demo
```

## Commits
1. Add domain model: Money, ServiceCategory, InsurancePlan, Patient, PriceList
2. Implement Visitor pattern: elements, Bill, visitors
3. Add Demo, README and .gitignore
