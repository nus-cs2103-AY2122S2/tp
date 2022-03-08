package seedu.address.model.tamodule;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a Module's Academic Year in the TAssist.
 * Guarantees: immutable; is valid as declared in {@link #isValidAcademicYear(String)}
 */
public class AcademicYear {

    public static final String MESSAGE_CONSTRAINTS =
            "Academic year should start with the last two digit of the year, "
                    + "end with the semester, and it should not be blank";
    public static final String VALIDATION_REGEX = "([\\p{Digit}]{2})(S[1-8])";

    private static final int YEAR_REGEX_GROUP_INDEX = 0;
    private static final int SEMESTER_REGEX_GROUP_INDEX = 1;
    private static final DateTimeFormatter parser = new DateTimeFormatterBuilder()
            .appendPattern("yy")
            .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
            .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
            .toFormatter();
    private static final Map<String, String> semesters = new HashMap<>() {{
            put("S1", "Semester 1");
            put("S2", "Semester 2");
            put("S3", "Special Term I");
            put("S4", "Special Term II");
            put("S5", "Mini-Semester 1A");
            put("S6", "Mini-Semester 1B");
            put("S7", "Mini-Semester 2A");
            put("S8", "Mini-Semester 2B");
        }};

    public final String value;

    /**
     * Constructs a {@code AcademicYear}.
     *
     * @param academicYear A valid academic year.
     */
    public AcademicYear(String academicYear) {
        requireNonNull(academicYear);
        checkArgument(isValidAcademicYear(academicYear), MESSAGE_CONSTRAINTS);
        value = academicYear;
    }

    /**
     * Returns true if a given string is a valid academic year.
     */
    public static boolean isValidAcademicYear(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    /**
     * Returns the academic year as a full string.
     * E.g. 21S1 will return the string 2021/2022 Semester 1.
     *
     * @return The academic year in full string.
     */
    public String toStringInFull() {
        Pattern pattern = Pattern.compile(VALIDATION_REGEX);
        Matcher matcher = pattern.matcher(value);
        String year = getYear(matcher.group(YEAR_REGEX_GROUP_INDEX));
        String semester = getSemester(matcher.group(SEMESTER_REGEX_GROUP_INDEX));
        return String.format("%s %s", year, semester);
    }

    /**
     * Generates the year string based on the year input.
     * E.g. 21 will return the string as 2021/2022
     *
     * @return The year string in full.
     */
    private static String getYear(String yearInput) {
        LocalDate year = LocalDate.parse(yearInput, parser);
        return String.format("%s/%s", year.getYear(), year.plusYears(1).getYear());
    }

    /**
     * Generates the semester string based on the semester input.
     * E.g. S1 will return Semester 1.
     *
     * @return The semester string in full.
     */
    private static String getSemester(String semInput) {
        return semesters.get(semInput);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AcademicYear // instanceof handles nulls
                && value.equals(((AcademicYear) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
