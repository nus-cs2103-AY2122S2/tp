package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Students's telegram handle in TAPA.
 * Guarantees: immutable; is valid as declared in {@link #isValidTelegramHandle(String)}
 */
public class TelegramHandle {
    // Telegram handle has a limit of 5 to 32 characters excluding the @symbol at the front.
    public static final String MESSAGE_CONSTRAINTS = "Telegram handles should contain 5 to 32 alphanumeric characters.";
    public static final String VALIDATION_REGEX = "[a-zA-Z0-9]{5,32}";

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
        if (other == this) { // short circuit if same object
            return true;
        }
        if (!(other instanceof TelegramHandle)) { // target object is of different type
            return false;
        }

        TelegramHandle targetObject = (TelegramHandle) other;

        if (targetObject.telegramHandle == null && this.telegramHandle == null) {
            return true;
        } else if (targetObject.telegramHandle == null) {
            return false;
        } else if (this.telegramHandle == null) {
            return false;
        } else {
            return telegramHandle.equals(targetObject.telegramHandle);
        }
    }

    @Override
    public int hashCode() {
        if (telegramHandle == null) {
            return 0;
        }
        return telegramHandle.hashCode();
    }

}
