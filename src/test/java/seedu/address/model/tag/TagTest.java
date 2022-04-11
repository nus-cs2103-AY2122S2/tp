package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_COLLEAGUE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.testutil.Assert.assertThrows;

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
        String edgeTag = "";
        for (int i = 0; i < Tag.CHARACTER_LIMIT; i++) {
            edgeTag += "a";
        }
        // invalid tag name
        assertFalse(Tag.isValidTagName("")); // empty string
        assertFalse(Tag.isValidTagName(" ")); // spaces only
        assertFalse(Tag.isValidTagName(" peter")); // starts with white spaces
        assertFalse(Tag.isValidTagName(edgeTag + "a")); //too long

        // valid tag name
        assertTrue(Tag.isValidTagName("peter Jack")); // alphabets only
        assertTrue(Tag.isValidTagName("12345")); // numbers only
        assertTrue(Tag.isValidTagName("peter123")); // alphanumeric characters
        assertTrue(Tag.isValidTagName("Capital")); // with capital letters
        assertTrue(Tag.isValidTagName("李白")); // only non-alphanumeric characters
        assertTrue(Tag.isValidTagName(edgeTag)); // long Tags
    }

    @Test
    public void equals() {

        Tag validTagFriend = new Tag(VALID_TAG_FRIEND);
        Tag validTagColleague = new Tag(VALID_TAG_COLLEAGUE);

        // same object -> returns true
        assertTrue(validTagFriend.equals(validTagFriend));

        // same values -> returns true
        Tag validTagFriendCopy = new Tag(VALID_TAG_FRIEND);
        assertTrue(validTagFriend.equals(validTagFriendCopy));

        // different capitalization -> returns true
        Tag validTagFriendAllCaps = new Tag(VALID_TAG_FRIEND.toUpperCase());
        assertTrue(validTagFriend.equals(validTagFriendAllCaps));

        // different types -> returns false
        assertFalse(validTagFriend.equals(1));

        // null -> returns false
        assertFalse(validTagFriend.equals(null));

        // different tag -> returns false
        assertFalse(validTagFriend.equals(validTagColleague));
    }

}
