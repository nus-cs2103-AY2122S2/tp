package woofareyou.logic.parser;

/**
 * Utility class for Sort.
 * Contains fields and methods for SortCommand and SortCommandParser classes.
 */
public class SortUtil {
    /** Valid Sort Command Parameters. */
    public static final String SORT_BY_NAME = "name";
    public static final String SORT_BY_OWNER = "owner";
    public static final String SORT_BY_APPOINTMENT = "app";
    public static final String SORT_BY_PICK_UP_TIME = "pick up";
    public static final String SORT_BY_DROP_OFF_TIME = "drop off";

    /**
     * Checks if input is a valid sort command parameter.
     * Used in SortCommandParser.
     * @param args Input argument.
     * @return True if input is valid sort parameter and false otherwise.
     */
    public static Boolean isValidSortParameter(String args) {
        return args.equals(SORT_BY_APPOINTMENT)
                || args.equals(SORT_BY_OWNER)
                || args.equals(SORT_BY_NAME)
                || args.equals(SORT_BY_DROP_OFF_TIME)
                || args.equals(SORT_BY_PICK_UP_TIME);
    }

}
