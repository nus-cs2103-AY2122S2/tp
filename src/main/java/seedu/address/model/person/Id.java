package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.UUID;

/**
 * Represents a Person's ID in the address book.
 * Guarantees: immutable
 */
public class Id {

    /*
     * The format is quite elaborate and seeing the user should never be manually creating an ID,
     * the message is kept short for brevity.
     */
    public static final String MESSAGE_CONSTRAINTS =
            "IDs must follow the string representation as defined by the UUID format";

    public final UUID id;

    /**
     * Constructs an {@code Id} with a randomly generated ID.
     */
    public Id() {
        this.id = UUID.randomUUID();
    }

    /**
     * Constructs an {@code Id} from a predesignated {@code String} ID.
     *
     * @param id The designated ID.
     */
    public Id(String id) {
        checkArgument(isValidId(id), MESSAGE_CONSTRAINTS);
        this.id = UUID.fromString(id);
    }

    /**
     * Returns true if a given string is a valid id.
     */
    public static boolean isValidId(String test) {
        try {
            UUID uuid = UUID.fromString(test);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return id.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Id // instanceof handles nulls
                && id.equals(((Id) other).id)); // state check
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
