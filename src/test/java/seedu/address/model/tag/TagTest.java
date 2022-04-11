package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTags.VALID_TAG_FRIENDS;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));
    }

    @Test
    public void isSameTag() {
        // same object -> returns true
        assertTrue(VALID_TAG_FRIENDS.isSameTag(VALID_TAG_FRIENDS));

        // null -> returns false
        assertFalse(VALID_TAG_FRIENDS.isSameTag(null));

        // name differs in case -> returns true
        Tag editedTag = new Tag("frIeNds");
        assertTrue(VALID_TAG_FRIENDS.isSameTag(editedTag));
    }

}
