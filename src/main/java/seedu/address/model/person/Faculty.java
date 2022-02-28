package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.stream.Stream;

/**
 * Represents a Person's faculty in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidFaculty(String)}
 */
public class Faculty {

    public static final String MESSAGE_CONSTRAINTS =
            "Only the following faculties exists: " + getFacultyEnumAsString();

    public final String studentFaculty;

    /**
     * Constructs a {@code Faculty}.
     *
     * @param faculty A valid faculty.
     */
    public Faculty(String faculty) {
        requireNonNull(faculty);
        checkArgument(isValidFaculty(faculty), MESSAGE_CONSTRAINTS);
        studentFaculty = faculty.toUpperCase();
    }

    public enum Nus {
        FASS,
        BIZ,
        SOC,
        SCALE,
        FOD,
        CDE,
        DUKE,
        FOL,
        YLLSOM,
        YSTCOM,
        SOPP,
        LKYSPP,
        SPH,
        TEST,
        FOS
    }

    /**
     * Returns true if a given string is a valid faculty.
     */
    public static boolean isValidFaculty(String test) {
        return Stream.of(Nus.values())
                .anyMatch(faculty -> faculty.name()
                        .equalsIgnoreCase(test));
    }

    /**
     * Returns the list enum values from the Nus enum class.
     *
     * @return String of Nus enum values.
     */
    public static String getFacultyEnumAsString() {
        StringBuilder stringBuilder = new StringBuilder();
        Stream.of(Nus.values()).forEach(faculty -> stringBuilder.append(faculty + " "));
        return stringBuilder.toString();
    }

    public String toString() {
        return studentFaculty;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Faculty // instanceof handles nulls
                && studentFaculty.equals(((Faculty) other).studentFaculty)); // state check
    }

    @Override
    public int hashCode() {
        return studentFaculty.hashCode();
    }
}
