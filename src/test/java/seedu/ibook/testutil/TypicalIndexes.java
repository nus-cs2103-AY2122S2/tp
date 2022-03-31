package seedu.ibook.testutil;

import seedu.ibook.commons.core.index.CompoundIndex;
import seedu.ibook.commons.core.index.Index;

/**
 * A utility class containing a list of {@code Index} objects to be used in tests.
 */
public class TypicalIndexes {
    public static final Index INDEX_FIRST_PRODUCT = Index.fromOneBased(1);
    public static final Index INDEX_SECOND_PRODUCT = Index.fromOneBased(2);
    public static final Index INDEX_THIRD_PRODUCT = Index.fromOneBased(3);

    public static final CompoundIndex INDEX_FIRST_PRODUCT_FIRST_ITEM = CompoundIndex.fromOneBased(1, 1);
    public static final CompoundIndex INDEX_FIRST_PRODUCT_SECOND_ITEM = CompoundIndex.fromOneBased(1, 2);
    public static final CompoundIndex INDEX_SECOND_PRODUCT_FIRST_ITEM = CompoundIndex.fromOneBased(2, 1);
    public static final CompoundIndex INDEX_SECOND_PRODUCT_SECOND_ITEM = CompoundIndex.fromOneBased(2, 2);
}
