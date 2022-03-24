package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class FriendNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FriendName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new FriendName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> FriendName.isValidFriendName(null));

        // invalid name
        assertFalse(FriendName.isValidFriendName("")); // empty string
        assertFalse(FriendName.isValidFriendName(" ")); // spaces only
        assertFalse(FriendName.isValidFriendName("^")); // only non-alphanumeric characters
        assertFalse(FriendName.isValidFriendName("peter*")); // contains non-alphanumeric characters
        assertFalse(FriendName.isValidFriendName("12345")); // numbers only

        // valid name
        assertTrue(FriendName.isValidFriendName("peter jack")); // alphabets only
        assertTrue(FriendName.isValidFriendName("peter the 2nd")); // alphanumeric characters
        assertTrue(FriendName.isValidFriendName("Capital Tan")); // with capital letters
        assertTrue(FriendName.isValidFriendName("David Roger Jackson Ray Jr 2nd")); // long names
    }

    public void isEqual() {
        FriendName friendNameOne = new FriendName("Arthur Neo");
        FriendName friendNameTwo = new FriendName("Bernice Teng");

        assertFalse(friendNameOne.equals(friendNameTwo));

        //Friend name is case insensitive. eg arthur neo is the same as Arthur Neo
        assertTrue(friendNameOne.equals(new FriendName("arthur neo")));

        assertFalse(friendNameOne.equals(null));
    }
}
