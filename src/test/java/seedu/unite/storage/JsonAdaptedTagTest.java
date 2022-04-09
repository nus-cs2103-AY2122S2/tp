package seedu.unite.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.unite.testutil.Assert.assertThrows;
import static seedu.unite.testutil.TypicalTags.FRIEND;

import org.junit.jupiter.api.Test;

import seedu.unite.commons.exceptions.IllegalValueException;

public class JsonAdaptedTagTest {

    private static final String INVALID_TAG = "#friend";

    @Test
    public void toModelType_validTagDetails_returnsTag() throws Exception {
        JsonAdaptedTag tag = new JsonAdaptedTag(FRIEND);
        assertEquals(FRIEND, tag.toModelType());
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        JsonAdaptedTag tag = new JsonAdaptedTag(INVALID_TAG, null);
        assertThrows(IllegalValueException.class, tag::toModelType);
    }

}
