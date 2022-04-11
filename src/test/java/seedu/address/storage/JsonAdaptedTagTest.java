package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTags.VALID_TAG_FRIENDS;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Tag;

public class JsonAdaptedTagTest {

    @Test
    public void toModelType_validTag_returnsTag() throws Exception {
        JsonAdaptedTag tag = new JsonAdaptedTag(VALID_TAG_FRIENDS);
        assertEquals(VALID_TAG_FRIENDS, tag.toModelType());
    }

    @Test
    public void toModelType_invalidTagName_throwsIllegalValueException() {
        JsonAdaptedTag tag =
                new JsonAdaptedTag("+");
        String expectedMessage = Tag.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tag::toModelType);
    }

}
