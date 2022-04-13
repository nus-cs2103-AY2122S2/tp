package seedu.tinner.model.role;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tinner.testutil.Assert.assertThrows;
import static seedu.tinner.testutil.TypicalRoles.NETWORK_ENGINEER;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.tinner.model.company.RoleManager;
import seedu.tinner.model.role.exceptions.RoleNotFoundException;

public class RoleManagerTest {

    private RoleManager roleManager = new RoleManager();

    @Test
    public void hasRole_nullRole_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> roleManager.hasRole(null));
    }

    @Test
    public void hasRole_roleNotInRoleList_returnsFalse() {
        assertFalse(roleManager.hasRole(NETWORK_ENGINEER));
    }

    @Test
    public void hasRole_roleInRoleList_returnsTrue() {
        roleManager.addRole(NETWORK_ENGINEER);
        assertTrue(roleManager.hasRole(NETWORK_ENGINEER));
    }

    @Test
    public void deleteRole_roleNotInRoleList_throwsRoleNotFoundException() {
        assertThrows(RoleNotFoundException.class, () -> roleManager.deleteRole(NETWORK_ENGINEER));
    }

    @Test
    public void setRole_nullRole_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> roleManager.setRole(null, NETWORK_ENGINEER));
    }

    @Test
    public void getFilteredRoleList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> roleManager.getFilteredRoleList().remove(0));
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RoleManager(null));
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(roleManager.equals(roleManager));

        // null -> returns false
        assertFalse(roleManager.equals(null));

        // different types -> returns false
        assertFalse(roleManager.equals(2));

        // different roleList -> returns false
        RoleManager differentRoleManager = new RoleManager();
        differentRoleManager.addRole(NETWORK_ENGINEER);
        assertFalse(roleManager.equals(differentRoleManager));

        // same roleList -> returns true
        roleManager.addRole(NETWORK_ENGINEER);
        assertTrue(roleManager.equals(differentRoleManager));

        // different filteredRoles -> returns false
        String[] keywords = {"software", "mobile"};
        roleManager.updateFilteredRoleList(new RoleNameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(roleManager.equals(differentRoleManager));
    }
}
