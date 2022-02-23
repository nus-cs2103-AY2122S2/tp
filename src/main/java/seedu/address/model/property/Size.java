package seedu.address.model.property;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Arrays;

/**
 * Represents a Property's size.
 * Guarantees: is valid as declared in {@link #isValidSize(String)}
 */
public enum Size {
    ONE_ROOM("1-room"),
    TWO_ROOM("2-room"),
    THREE_ROOM("3-room"),
    FOUR_ROOM("4-room"),
    FIVE_ROOM("5-room");

    public static final String MESSAGE_CONSTRAINTS = "Size must be one of " + Arrays.toString(Size.values());
    public static final String VALIDATION_REGEX = "^(1-room|2-room|3-room|4-room|5-room)$";

    public final String value;

    Size(String size) {
        value = size;
    }

    /**
     * Maps a string to a {@code Size}.
     */
    public static Size fromString(String size) {
        requireNonNull(size);
        checkArgument(isValidSize(size), MESSAGE_CONSTRAINTS);
        for (Size s : Size.values()) {
            if (s.value.equals(size)) {
                return s;
            }
        }

        throw new IllegalArgumentException("Invalid size: " + size);
    }

    public static boolean isValidSize(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

}
