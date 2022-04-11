package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTag.EXPERIENCE_SR;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Tag;

public class JsonAdaptedTagTest {
    private static final String LONG_INVALID_TEXT = "30 chars text 30 chars text 30 chars text 30 chars text";
    private static final String INVALID_TEXT = "!@#$%^&**()";

    @Test
    public void toModelType_validTag_returnsTag() throws Exception {
        JsonAdaptedTag tag = new JsonAdaptedTag(EXPERIENCE_SR);
        assertEquals(EXPERIENCE_SR, tag.toModelType());
    }

    @Test
    public void toModelType_invalidText_throwsIllegalValueException() {
        JsonAdaptedTag shortTag = new JsonAdaptedTag(INVALID_TEXT);
        String expectedMessage = Tag.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, shortTag::toModelType);
        JsonAdaptedTag longTag = new JsonAdaptedTag(LONG_INVALID_TEXT);
        assertThrows(IllegalValueException.class, expectedMessage, longTag::toModelType);
    }

}
