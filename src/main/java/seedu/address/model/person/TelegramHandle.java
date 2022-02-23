package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Students's telegram handle in TAPA.
 * Guarantees: immutable; is valid as declared in {@link #isValidTelegramHandle(String)}
 */
public class TelegramHandle {
    // Telegram handle has a limit of 5 to 32 characters excluding the @symbol at the front.
    public static final String MESSAGE_CONSTRAINTS = "Telegram handles should contain 5 to 32 alphanumeric characters.";
    public static final String VALIDATION_REGEX = "[a-zA-Z0-9]{5,32}"; // TODO: Check if this is correct

    public final String telegramHandle;

    /**
     * Constructs an {@code TelegramHandle}.
     *
     * @param telegramHandle A valid telegram handle.
     */
    public TelegramHandle(String telegramHandle) {
        if (telegramHandle == null) {
            this.telegramHandle = null;
        } else {
            checkArgument(isValidTelegramHandle(telegramHandle), MESSAGE_CONSTRAINTS);
            this.telegramHandle = telegramHandle;
        }
    }

    /**
     * Returns if a given string is a valid telegram handle.
     *
     * @param test the string that is being tested.
     * @return a boolean value (true/false) depending if the id is valid.
     */
    public static boolean isValidTelegramHandle(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return telegramHandle;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TelegramHandle // instanceof handles nulls
                && telegramHandle.equals(((TelegramHandle) other).telegramHandle)); // state check
    }

    @Override
    public int hashCode() {
        return telegramHandle.hashCode();
    }

}
