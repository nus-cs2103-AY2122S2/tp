package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's class code in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidClassCode(String)}
 */
public class ClassCode {


    /**
     * Constructs a {@code classCode}.
     *
     * @param classCode A valid class code.
     */
    public ClassCode(String classCode) {
        requireNonNull(classCode);
//        checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS);
//        value = phone;
    }

    /**
     * Returns true if a given string is a valid class code.
     */
    public static boolean isValidClassCode(String test) {
        return true;
    }
}
