package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's class code in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidClassCode(String)}
 */
public class ClassCode {

    public static final String MESSAGE_CONSTRAINTS =
            "Class code should have the number 1-6 as its first character and alphabets as its second character";
    public static final String VALIDATION_REGEX = "\\[1-6][a-zA-Z]";
    public final String value;

    /**
     * Constructs a {@code classCode}.
     *
     * @param value A valid class code.
     */
    public ClassCode(String value) {
        requireNonNull(value);
//        checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS);
//        value = phone;
        this.value = value;
    }

    /**
     * Returns true if a given string is a valid class code.
     */
    public static boolean isValidClassCode(String test) {
        return test.matches(VALIDATION_REGEX);
    }
}
