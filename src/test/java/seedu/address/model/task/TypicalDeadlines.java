package seedu.address.model.task;

/**
 * Contains the typical inputs to the {@code Deadline} field.
 */
public class TypicalDeadlines {

    /**
     * Invalid deadlines.
     */
    public static final String NULL_DATE = null;
    public static final String MISSING_YEAR = "10-22";
    public static final String MISSING_MONTH = "2022-22";
    public static final String MISSING_DAY = "2022-10";
    public static final String INVALID_DAY_1 = "2022-10-32";
    public static final String INVALID_DAY_2 = "2022-10-00";
    public static final String INVALID_DAY_3 = "2022-02-29";
    public static final String INVALID_MONTH_1 = "2022-13-22";
    public static final String INVALID_MONTH_2 = "2022-00-22";
    public static final String INVALID_YEAR_1 = "999-12-26";
    public static final String INVALID_YEAR_2 = "10000-12-26";
    public static final String DD_MM_YY = "26-10-2022";
    public static final String MM_DD_YY = "10-26-2022";
    public static final String YYYY_MM_DD_SLASH = "2022/10/22";
    public static final String ISO_0601 = "20090106";
    public static final String FULL_FORM_1 = "2nd October 2007";
    public static final String FULL_FORM_2 = "2 October 2007";

    /**
     * Valid deadlines.
     */
    public static final String NORMAL_DATE = "2022-07-20";
    public static final String FIRST_DAY_OF_YEAR = "2022-01-01";
    public static final String LAST_DAY_OF_YEAR = "2022-12-31";
    public static final String FEB_29_2024 = "2024-02-29";
    public static final String LARGEST_DATE = "9999-12-31";
    public static final String SMALLEST_DATE = "0000-01-01";


    /**
     * Retrieves all the valid strings that represent a {@code Deadline}.
     *
     * @return the list of strings
     */
    public static String[] getValidDeadlines() {
        return new String[] {
            NORMAL_DATE,
            FIRST_DAY_OF_YEAR,
            LAST_DAY_OF_YEAR,
            FEB_29_2024,
            LARGEST_DATE,
            SMALLEST_DATE
        };
    }

    /**
     * Retrieves all the invalid strings that represent a {@code Deadline}, excluding null.
     *
     * @return the list of strings
     */
    public static String[] getInvalidDeadlines() {
        return new String[] {
            MISSING_YEAR,
            MISSING_MONTH,
            MISSING_DAY,
            INVALID_DAY_1,
            INVALID_DAY_2,
            INVALID_DAY_3,
            INVALID_MONTH_1,
            INVALID_MONTH_2,
            INVALID_YEAR_1,
            INVALID_YEAR_2,
            DD_MM_YY,
            MM_DD_YY,
            YYYY_MM_DD_SLASH,
            ISO_0601,
            FULL_FORM_1,
            FULL_FORM_2
        };
    }
}
