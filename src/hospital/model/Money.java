package hospital.model;

import java.util.Locale;

// Immutable amount in whole tenge
public final class Money {
    public static final Money ZERO = new Money(0);

    private final long amount;

    public Money(long amount) {
        this.amount = amount;
    }

    public Money plus(Money other) {
        return new Money(amount + other.amount);
    }

    public Money minus(Money other) {
        return new Money(amount - other.amount);
    }

    public Money times(int factor) {
        return new Money(amount * factor);
    }

    public Money percent(int percent) {
        return new Money(amount * percent / 100);
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "%,d KZT", amount);
    }
}
