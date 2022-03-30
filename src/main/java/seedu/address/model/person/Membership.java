package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Comparator;

import seedu.address.logic.parser.Prefix;

/**
 * Represents a Person's Membership in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Membership extends Field {

    enum Tier {
        GOLD,
        SILVER,
        BRONZE
    }

    public static final Prefix PREFIX = new Prefix("m/", true);
    public static final Prefix DATE_PREFIX = new Prefix("d/", false);
    public static final String MESSAGE_CONSTRAINTS =
            "Membership tier should be either 'bronze', 'silver' or 'gold'";
    public static final String MESSAGE_DATE_CONSTRAINTS =
            "Date is in an invalid format";

    private final Tier tier;
    private final LocalDate date;

    /**
     * Constructs a {@code Membership}.
     *
     * @param name A valid membership.
     */
    public Membership(String name) {
        super(PREFIX);
        requireNonNull(name);
        name = name.trim();
        name = name.toLowerCase();
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        tier = getTierFromString(name);
        date = null;
    }

    /**
     * Overloaded Constructs a {@code Membership}.
     *
     * @param name A valid membership name.
     * @param date A valid date.
     */
    public Membership(String name, LocalDate date) {
        super(PREFIX);
        requireNonNull(name);
        name = name.trim();
        name = name.toLowerCase();
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        tier = getTierFromString(name);
        this.date = date;
    }

    /**
     * Returns a tier based on the given string.
     */
    public static Tier getTierFromString(String name) {
        if (name.equals("gold")) {
            return Tier.GOLD;
        } else if (name.equals("silver")) {
            return Tier.SILVER;
        } else {
            return Tier.BRONZE;
        }
    }

    /**
     * Returns a tier based on the given string.
     */
    public Tier getTier() {
        return tier;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        test = test.toLowerCase();
        return test.equals("gold") || test.equals("silver") || test.equals("bronze");
    }

    /**
     * Returns true if a given date is a valid date.
     */
    public static boolean isValidDate(String test) {
        try {
            LocalDate.parse(test);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public String getValue() {
        String value = "";
        if (tier == Tier.GOLD) {
            value = "GOLD";
        } else if (tier == Tier.SILVER) {
            value = "SILVER";
        } else if (tier == Tier.BRONZE) {
            value = "BRONZE";
        }
        return value;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        String datePostFix = "";
        if (date != null) {
            datePostFix = " since " + date.toString();
        }
        String value = "";

        return getValue() + " MEMBER" + datePostFix;
    }

    @Override
    public boolean equals(Object other) {
        if (date != null) {
            return other == this // short circuit if same object
                    || (other instanceof Membership // instanceof handles nulls
                    && tier.equals(((Membership) other).tier) && date.equals(((Membership) other).date));
        }
        return other == this // short circuit if same object
                || (other instanceof Membership // instanceof handles nulls
                && tier.equals(((Membership) other).tier));
    }

    @Override
    public int hashCode() {
        return tier.hashCode();
    }

    @Override
    public int compareTo(Field other) {
        if (!(other instanceof Membership)) {
            return -1;
        }

        //compare by name of membership first followed by date
        Membership otherMembership = (Membership) other;
        return Comparator.comparing(Membership::getTier)
                .thenComparing(Membership::getDate)
                .compare(this, otherMembership);
    }
}
