package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the agenda for the meeting
 */
public class Agenda implements Comparable<Agenda> {
    public static final String MESSAGE_CONSTRAINTS =
            "Agenda can contain any character, and it should not be blank";

    /*
     * The first character of the Agenda must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String description;

    /**
     * Constructs a {@code Agenda}.
     *
     * @param description A valid description of the agenda.
     */
    public Agenda(String description) {
        requireNonNull(description);
        checkArgument(isValidAgenda(description), MESSAGE_CONSTRAINTS);
        this.description = description;
    }

    /**
     * Returns true if a given string is a valid description of the agenda.
     */
    public static boolean isValidAgenda(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return this.description;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Agenda // instanceof handles nulls
                && description.equals(((Agenda) other).description)); // state check
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }

    @Override
    public int compareTo(Agenda other) {
        return this.description.compareTo(other.description);
    }
}
