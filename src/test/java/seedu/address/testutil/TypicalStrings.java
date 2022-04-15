package seedu.address.testutil;

/**
 * Contains the typical strings that could be supplied as user inputs.
 */
public class TypicalStrings {

    /**
     * Common invalid test strings used.
     */
    public static final String EMPTY_STRING = "";
    public static final String NULL_STRING = null;
    public static final String WHITESPACES_ONLY_STRING = "   ";
    public static final String EMOJI_STRING = "☝️";
    public static final String SYMBOLS_STRING = "ʕ·ᴥ·ʔ";
    public static final String PUNCTUATION_STRING = ",";
    public static final String NON_ENGLISH_STRING = "ㅁㅂㅇㅈㅊㅋㅌㅍㅎ";
    public static final String LEADING_WHITESPACE_STRING = " task";
    public static final String LONG_STRING_256_CHAR = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, "
            + "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis "
            + "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure "
            + "dolor em";
    public static final String LONG_STRING_51_CHAR = "extraordinarymachinelearningandartificialintelligen";
    public static final String LONG_STRING_64_CHAR = "extraordinarymachinelearningandartificialintelligenceandbenleong";
    public static final String CONTAIN_SLASH_STRING = "AStringThatContains/SlashIsNotAllowed";
    public static final String START_WITH_PUNCTUATION_STRING = ",heyThisIsAStringStartingWithComma";
    public static final String STRING_CONTAIN_SPACE = "This is a string that contains space";
    public static final String INVALID_SORT_ORDER = "Highest";
    public static final String INVALID_SORT_KEY_DEADLINE_ABBR = "dl";
    public static final String INVALID_SORT_KEY_PRIORITY_ABBR = "p";
    public static final String INVALID_SORT_KEY_NAME_ABBR = "n";
    public static final String INVALID_SORT_KEY = "description";

    /**
     * Common valid test strings used.
     */
    public static final String ONE_LETTER_1 = "H";
    public static final String ONE_LETTER_2 = "h";
    public static final String LONG_STRING_255_CHAR = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, "
            + "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis "
            + "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure "
            + "dolor e";
    public static final String LONG_STRING_50_CHAR = "extraordinarymachinelearningandartificialintellige";
    public static final String LONG_STRING_63_CHAR = "extraordinarymachinelearningandartificialintelligenceandbenleon";
    public static final String START_WITH_DIGIT_STRING = "12345ABCDE";
    public static final String ALNUM_STRING = "ThisIsAnAlnumString1That2Contains3Letters4And5Digits";
    public static final String MANY_SPACES_STRING = "This is \n a \t string \n\n\n that \n contains \t "
            + "lots of    spaces\n.";
    public static final String CONTAINS_PUNCTUATION_STRING = "This is a string that is alphanumeric, and contains , 5"
            + " punctuations] and )some *spaces.";
    public static final String SORT_ORDER_ASCENDING = "asc";
    public static final String SORT_ORDER_DESCENDING = "desc";
    public static final String SORT_KEY_DEADLINE = "deadline";
    public static final String SORT_KEY_NAME = "name";
    public static final String SORT_KEY_PRIORITY = "priority";

    /**
     * Other common strings.
     */
    public static final String SORT_ORDER_ASC_STRING = "ascending";
    public static final String SORT_ORDER_DESC_STRING = "descending";
    public static final String SORT_KEY_DEADLINE_STRING = SORT_KEY_DEADLINE;
    public static final String SORT_KEY_PRIORITY_STRING = SORT_KEY_PRIORITY;
    public static final String SORT_KEY_NAME_STRING = SORT_KEY_NAME;
    public static final String DIGITS = "123";
    public static final String WHITESPACE_SORT_ORDER_ASC = " asc";
    public static final String WHITESPACE_SORT_KEY_DEADLINE = " deadline";

    /**
     * Retrieves all invalid names from this class, excluding null.
     *
     * @return a list of invalid name strings
     */
    public static String[] getInvalidStringsForName() {
        return new String[] {
            EMPTY_STRING,
            WHITESPACES_ONLY_STRING,
            EMOJI_STRING,
            SYMBOLS_STRING,
            PUNCTUATION_STRING,
            NON_ENGLISH_STRING,
            LEADING_WHITESPACE_STRING,
            LONG_STRING_256_CHAR,
            CONTAIN_SLASH_STRING,
            START_WITH_PUNCTUATION_STRING
        };
    }

    /**
     * Retrieves all valid names from this class.
     *
     * @return a list of valid name strings
     */
    public static String[] getValidStringsForName() {
        return new String[] {
            ONE_LETTER_1,
            ONE_LETTER_2,
            LONG_STRING_255_CHAR,
            START_WITH_DIGIT_STRING,
            ALNUM_STRING,
            MANY_SPACES_STRING,
            CONTAINS_PUNCTUATION_STRING
        };
    }

    /**
     * Retrieves all invalid descriptions from this class, excluding null.
     *
     * @return a list of invalid description strings
     */
    public static String[] getInvalidStringsForDescription() {
        return new String[] {
            EMPTY_STRING,
            WHITESPACES_ONLY_STRING,
            EMOJI_STRING,
            SYMBOLS_STRING,
            PUNCTUATION_STRING,
            NON_ENGLISH_STRING,
            LEADING_WHITESPACE_STRING,
            LONG_STRING_256_CHAR,
            CONTAIN_SLASH_STRING,
            START_WITH_PUNCTUATION_STRING
        };
    }

    /**
     * Retrieves all valid descriptions from this class.
     *
     * @return a list of valid description strings
     */
    public static String[] getValidStringsForDescription() {
        return new String[] {
            ONE_LETTER_1,
            ONE_LETTER_2,
            LONG_STRING_255_CHAR,
            START_WITH_DIGIT_STRING,
            ALNUM_STRING,
            MANY_SPACES_STRING,
            CONTAINS_PUNCTUATION_STRING
        };
    }

    /**
     * Retrieves all valid labels from this class.
     *
     * @return a list of valid sort order strings
     */
    public static String[] getValidStringsForSortOrder() {
        return new String[] {
            SORT_ORDER_ASCENDING,
            SORT_ORDER_DESCENDING
        };
    }

    /**
     * Retrieves all valid labels from this class.
     *
     * @return a list of invalid sort order strings
     */
    public static String[] getInvalidStringsForSortOrder() {
        return new String[] {
            EMPTY_STRING,
            WHITESPACES_ONLY_STRING,
            SORT_KEY_DEADLINE,
            SORT_KEY_NAME,
            SORT_KEY_PRIORITY,
            SORT_ORDER_ASC_STRING,
            SORT_ORDER_DESC_STRING,
            DIGITS,
            WHITESPACE_SORT_ORDER_ASC,
            INVALID_SORT_ORDER
        };
    }

    /**
     * Retrieves all valid labels from this class.
     *
     * @return a list of valid sort key strings
     */
    public static String[] getValidStringsForSortKey() {
        return new String[] {
            SORT_KEY_DEADLINE,
            SORT_KEY_NAME,
            SORT_KEY_PRIORITY
        };
    }

    /**
     * Retrieves all valid labels from this class.
     *
     * @return a list of invalid sort key strings
     */
    public static String[] getInvalidStringsForSortKey() {
        return new String[] {
            EMPTY_STRING,
            WHITESPACES_ONLY_STRING,
            SORT_ORDER_ASCENDING,
            SORT_ORDER_DESCENDING,
            DIGITS,
            INVALID_SORT_KEY_DEADLINE_ABBR,
            INVALID_SORT_KEY_NAME_ABBR,
            INVALID_SORT_KEY_PRIORITY_ABBR,
            WHITESPACE_SORT_KEY_DEADLINE,
            INVALID_SORT_KEY
        };
    }


    /**
     * Retrieves all valid labels from this class.
     *
     * @return a list of valid keyword strings
     */
    public static String[] getValidStringsForKeyword() {
        return new String[] {
            DIGITS,
            LONG_STRING_50_CHAR,
            LONG_STRING_51_CHAR,
            LONG_STRING_63_CHAR,
            ONE_LETTER_1,
            ONE_LETTER_2,
            START_WITH_DIGIT_STRING
        };
    }

    /**
     * Retrieves all valid labels from this class.
     *
     * @return a list of invalid keyword strings
     */
    public static String[] getInvalidStringsForKeyword() {
        return new String[] {
            EMPTY_STRING,
            WHITESPACES_ONLY_STRING,
            EMOJI_STRING,
            SYMBOLS_STRING,
            PUNCTUATION_STRING,
            NON_ENGLISH_STRING,
            LONG_STRING_64_CHAR,
            LONG_STRING_256_CHAR,
            CONTAIN_SLASH_STRING,
            START_WITH_PUNCTUATION_STRING
        };
    }
}
