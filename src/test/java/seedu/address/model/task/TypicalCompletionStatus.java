package seedu.address.model.task;

/**
 * Contains typical user inputs to {@code CompletionStatus}
 */
public class TypicalCompletionStatus {

    /**
     * Invalid inputs.
     */
    public static final String NULL_COMPLETION_STATUS = null;
    public static final String SHORT_FORM_1 = "F";
    public static final String SHORT_FORM_2 = "f";
    public static final String SHORT_FORM_3 = "T";
    public static final String SHORT_FORM_4 = "t";
    public static final String MISSPELLED_FORM = "ture";
    public static final String CONTAINS_SPACE = "tru e";
    public static final String CONTAINS_PUNCT = "fal,se";

    /**
     * Valid inputs.
     */
    public static final String TRUE_LOWER = "true";
    public static final String TRUE_UPPER = "TRUE";
    public static final String FALSE_LOWER = "false";
    public static final String FALSE_UPPER = "FALSE";
    public static final String TRUE_MIXED = "tRuE";
    public static final String FALSE_MIXED = "FaLsE";

    /**
     * Retrieves the valid inputs to {@code CompletionStatus}.
     *
     * @return the list of valid inputs
     */
    public static String[] getValidCompletionStatus() {

        return new String[] {
            TRUE_LOWER,
            TRUE_UPPER,
            FALSE_LOWER,
            FALSE_UPPER,
            TRUE_MIXED,
            FALSE_MIXED
        };
    }

    /**
     * Retrieves the invalid inputs to {@code CompletionStatus}, excluding null.
     *
     * @return the list of invalid inputs
     */
    public static String[] getInvalidCompletionStatus() {

        return new String[] {
            SHORT_FORM_1,
            SHORT_FORM_2,
            SHORT_FORM_3,
            SHORT_FORM_4,
            MISSPELLED_FORM,
            CONTAINS_SPACE,
            CONTAINS_PUNCT
        };
    }
}
