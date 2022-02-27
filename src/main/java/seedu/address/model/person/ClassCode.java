package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's classroom in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidClassroom(String)}
 */
public class StudentClass {


    /**
     * Constructs a {@code Classroom}.
     *
     * @param classroom A valid classroom.
     */
    public StudentClass(String classroom) {
        requireNonNull(classroom);
//        checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS);
//        value = phone;
    }

    /**
     * Returns true if a given string is a valid classroom.
     */
    public static boolean isValidClassroom(String test) {
        return true;
    }
}
