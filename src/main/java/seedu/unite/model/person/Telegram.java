package seedu.unite.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.unite.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's Telegram ID in the unite.
 * Guarantees: immutable; is valid as declared in {@link #isValidTelegramId(String)}
 */
public class Telegram {

    public static final String MESSAGE_CONSTRAINTS = "Telegram ID should only contain "
            + "alphanumeric characters and underscore. "
            + "It should be one word and must not start with an underscore.";

    /*
     * Input can be blank space, if you input blank spaces,
     * it is treated as if the course field is unfilled and blank.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}\\_]*";

    public final String id;

    /**
     * Constructs a {@code Telegram}.
     *
     * @param id A valid telegram id.
     */
    public Telegram(String id) {
        requireNonNull(id);
        checkArgument(isValidTelegramId(id), MESSAGE_CONSTRAINTS);
        this.id = id;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidTelegramId(String telegram) {
        return telegram.equals("") || ((telegram.matches(VALIDATION_REGEX) && !(telegram.charAt(0) == '_')));
    }


    @Override
    public String toString() {
        return id;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Telegram // instanceof handles nulls
                && id.equalsIgnoreCase(((Telegram) other).id)); // state check

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
