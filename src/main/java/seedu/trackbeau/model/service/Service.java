package seedu.trackbeau.model.service;

import static seedu.trackbeau.commons.util.CollectionUtil.requireAllNonNull;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

import seedu.trackbeau.model.uniquelist.UniqueListItem;

/**
 * Represents a Service for the beauty salon in trackbeau.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Service implements UniqueListItem {
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
        assert price.value > 0 : "price should be greater than 0";
        assert duration.value > 0 : "duration should be greater than 0";
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
    public boolean isSameItem(UniqueListItem other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Service)) {
            return false;
        }

        Service otherService = (Service) other;
        return otherService.getName().equals(getName());
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
        Locale locale = new Locale("en", "SG");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        String priceString = formatter.format(getPrice().value);
        builder.append(getName())
            .append("; Price: ")
            .append(priceString)
            .append("; Duration (in minutes): ")
            .append(getDuration());

        return builder.toString();
    }

}
