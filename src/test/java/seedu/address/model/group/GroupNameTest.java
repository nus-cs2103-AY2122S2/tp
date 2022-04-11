package seedu.address.model.group;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_NAME_NUS_FINTECH_SOCIETY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGroups.NUS_FINTECH_SOCIETY;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.GroupBuilder;

/**
 * Contains unit tests for {@code GroupName}.
 */
public class GroupNameTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GroupName(null));
    }

    @Test
    public void constructor_invalidGroupName_throwsIllegalArgumentException() {
        String invalidGroupName = "";
        assertThrows(IllegalArgumentException.class, () -> new GroupName(invalidGroupName));
    }

    @Test
    public void isValidGroupName() {
        // null group name -> throws NullPointerException
        assertThrows(NullPointerException.class, () -> GroupName.isValidGroupName(null));

        // invalid group name -> returns false
        assertFalse(GroupName.isValidGroupName("")); // empty string
        assertFalse(GroupName.isValidGroupName(" ")); // spaces only

        // valid group name -> returns true
        assertTrue(GroupName.isValidGroupName("coffee enthusiasts")); // alphabets only
        assertTrue(GroupName.isValidGroupName("31415")); // numbers only
        assertTrue(GroupName.isValidGroupName("2nd year students")); // alphanumeric characters
        assertTrue(GroupName.isValidGroupName("NUS Fintech Society")); // with capital letters
        assertTrue(GroupName.isValidGroupName("NUS Statistics and Data Science Society")); // long names
        assertTrue(GroupName.isValidGroupName("important*")); // contains non-alphanumeric characters
        assertTrue(GroupName.isValidGroupName("^")); // only non-alphanumeric characters
    }

    @Test
    public void equal() {
        Group editedNusFintechSocietyLowerCase = new GroupBuilder(NUS_FINTECH_SOCIETY)
                .withGroupName(VALID_GROUP_NAME_NUS_FINTECH_SOCIETY.toLowerCase()).build();

        // same group name -> equals
        assertEquals(NUS_FINTECH_SOCIETY.getGroupName(), NUS_FINTECH_SOCIETY.getGroupName());

        // same group name with different case -> equals
        assertEquals(NUS_FINTECH_SOCIETY.getGroupName(), editedNusFintechSocietyLowerCase.getGroupName());
    }
}
