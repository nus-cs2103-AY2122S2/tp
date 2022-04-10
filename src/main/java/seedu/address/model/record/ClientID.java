package seedu.address.model.record;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Record's clientID.
 * Guarantees: immutable; is valid as declared in {@link #isValidClientID(String)}
 */
public class ClientID {
    public static final String MESSAGE_CONSTRAINTS =
            "Client Index should only contain numbers, and it should not be blank";
    public static final String MESSAGE_INDEX_CONSTRAINT = "Client Index cannot be 0!";
    public static final String VALIDATION_REGEX = "\\d{1,}";
    public final String id;

    /**
     * Constructs a {@code ClientId}.
     *
     * @param clientID A valid clientID.
     */
    public ClientID(String clientID) {
        requireNonNull(clientID);
        checkArgument(isValidClientID(clientID), MESSAGE_CONSTRAINTS);
        checkArgument(isNotZeroClientID(clientID), MESSAGE_INDEX_CONSTRAINT);
        id = clientID;
    }

    /**
     * Constructs a {@code Name}.
     *
     * @param clientName A valid name.
     */
    public ClientID(String clientName, Boolean yes) {
        requireNonNull(clientName);
        id = clientName;
    }

    /**
     * Returns true if a given string is a valid clientID.
     */
    public static boolean isValidClientID(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a clientID is more than zero
     */
    public static boolean isNotZeroClientID(String test) {

        int clientID = Integer.parseInt(test);
        if (clientID > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClientID // instanceof handles nulls
                && id.equals(((ClientID) other).id)); // state check
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
