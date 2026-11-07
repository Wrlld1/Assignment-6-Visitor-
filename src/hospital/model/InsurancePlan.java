package hospital.model;

import java.util.EnumMap;
import java.util.Map;

public enum InsurancePlan {
    NONE(0, 0, 0, 0),
    BASIC(80, 50, 70, 30),
    PREMIUM(100, 90, 95, 70);

    private final Map<ServiceCategory, Integer> coverage = new EnumMap<>(ServiceCategory.class);

    InsurancePlan(int consultation, int labTest, int surgery, int medication) {
        coverage.put(ServiceCategory.CONSULTATION, consultation);
        coverage.put(ServiceCategory.LAB_TEST, labTest);
        coverage.put(ServiceCategory.SURGERY, surgery);
        coverage.put(ServiceCategory.MEDICATION, medication);
    }

    // Percent of the price the insurer pays for this category
    public int coveragePercent(ServiceCategory category) {
        return coverage.get(category);
    }
}
