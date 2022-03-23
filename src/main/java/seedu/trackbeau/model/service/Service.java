package seedu.trackbeau.model.service;

import static seedu.trackbeau.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Service for the beauty salon in trackbeau.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Service {
    // Identity fields
    private final ServiceName name;

    // Data fields
    private final Price price;
    private final Duration duration;

    /**
     * Every field must be present and not null.
     */
    public Service(ServiceName name, Price price, Duration duration) {
        requireAllNonNull(name, price, duration);
        this.name = name;
        this.price = price;
        this.duration = duration;
    }

    public ServiceName getName() {
        return name;
    }

    public Price getPrice() {
        return price;
    }

    public Duration getDuration() {
        return duration;
    }

    /**
     * Returns true if both services have the same name.
     * This defines a weaker notion of equality between two services.
     */
    public boolean isSameService(Service otherService) {
        if (otherService == this) {
            return true;
        }

        return otherService != null
                && otherService.getName().equals(getName());
    }

    /**
     * Returns true if both services have the same identity and data fields.
     * This defines a stronger notion of equality between two services.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Service)) {
            return false;
        }

        Service otherService = (Service) other;
        return otherService.getName().equals(getName())
                && otherService.getPrice().equals(getPrice())
                && otherService.getDuration().equals(getDuration());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, price, duration);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Price: $")
                .append(getPrice())
                .append("; Duration (in hours): ")
                .append(getDuration());

        return builder.toString();
    }

}
