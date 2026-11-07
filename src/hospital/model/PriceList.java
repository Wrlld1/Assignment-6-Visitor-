package hospital.model;

// Single place for tariffs, so visitors never duplicate pricing rules
public final class PriceList {
    public static final long CONSULTATION_PER_MINUTE = 500;
    public static final long SURGERY_PER_HOUR = 150_000;
    public static final long ANESTHESIA_PER_HOUR = 40_000;
    public static final int URGENT_LAB_SURCHARGE_PERCENT = 25;

    private PriceList() {
    }

    public static Money consultation(int minutes) {
        return new Money(CONSULTATION_PER_MINUTE * minutes);
    }

    public static Money labTest(Money basePrice, boolean urgent) {
        return urgent ? basePrice.plus(basePrice.percent(URGENT_LAB_SURCHARGE_PERCENT)) : basePrice;
    }

    public static Money surgery(int hours) {
        return new Money((SURGERY_PER_HOUR + ANESTHESIA_PER_HOUR) * hours);
    }

    public static Money medication(Money unitPrice, int quantity) {
        return unitPrice.times(quantity);
    }
}
