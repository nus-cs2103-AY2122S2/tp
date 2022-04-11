package seedu.address.testutil;

import seedu.address.model.person.Memo;

/**
 * A utility class for Memo.
 */
public class MemoUtil {

    /** A long string that is one less than the maximum allowed characters of {@code Memo}. */
    public static final String ONE_LESS_THAN_MAXIMUM_MEMO_STRING = getStringOfLength(Memo.CHARACTER_LIMIT - 1);

    /** A long string that is equal to the maximum allowed characters of {@code Memo}. */
    public static final String MAXIMUM_MEMO_STRING = getStringOfLength(Memo.CHARACTER_LIMIT);

    /** A long string that is one more than the maximum allowed characters of {@code Memo}. */
    public static final String ONE_MORE_THAN_MAXIMUM_MEMO_STRING = getStringOfLength(Memo.CHARACTER_LIMIT + 1);

    /**
     * Returns a string of a specified length.
     *
     * @param length Length of the string to be created.
     * @return String of a specified length.
     */
    private static String getStringOfLength(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append('a');
        }
        return sb.toString();
    }
}
