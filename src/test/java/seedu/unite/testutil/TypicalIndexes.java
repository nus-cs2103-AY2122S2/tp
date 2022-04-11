package seedu.unite.testutil;

import seedu.unite.commons.core.index.Index;

/**
 * A utility class containing a list of {@code Index} objects to be used in tests.
 */
public class TypicalIndexes {
    public static final Index INDEX_FIRST_PERSON = Index.fromOneBased(1);
    public static final Index INDEX_SECOND_PERSON = Index.fromOneBased(2);
    public static final Index INDEX_THIRD_PERSON = Index.fromOneBased(3);
    public static final Index INDEX_PERSON_NOT_EXIST = Index.fromOneBased(Integer.MAX_VALUE);
    public static final Index INDEX_FIRST_TAG = Index.fromOneBased(1);
    public static final Index INDEX_SECOND_TAG = Index.fromOneBased(2);
    public static final Index INDEX_THIRD_TAG = Index.fromOneBased(3);
}
