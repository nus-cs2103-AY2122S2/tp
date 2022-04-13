package seedu.tinner.model.role;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tinner.logic.commands.CommandTestUtil.VALID_STATUS_SOFTWARE_ENGINEER;
import static seedu.tinner.testutil.Assert.assertThrows;
import static seedu.tinner.testutil.TypicalRoles.MOBILE_ENGINEER;
import static seedu.tinner.testutil.TypicalRoles.NETWORK_ENGINEER;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.tinner.model.role.exceptions.DuplicateRoleException;
import seedu.tinner.model.role.exceptions.RoleNotFoundException;
import seedu.tinner.testutil.RoleBuilder;

public class UniqueRoleListTest {

    private final UniqueRoleList uniqueRoleList = new UniqueRoleList();

    @Test
    public void contains_nullRole_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoleList.contains(null));
    }

    @Test
    public void contains_roleNotInList_returnsFalse() {
        assertFalse(uniqueRoleList.contains(MOBILE_ENGINEER));
    }

    @Test
    public void contains_roleInList_returnsTrue() {
        uniqueRoleList.add(MOBILE_ENGINEER);
        assertTrue(uniqueRoleList.contains(MOBILE_ENGINEER));
    }

    @Test
    public void contains_roleWithSameIdentityFieldsInList_returnsTrue() {
        uniqueRoleList.add(MOBILE_ENGINEER);
        Role editedMobileEngineer = new RoleBuilder(MOBILE_ENGINEER).withStatus(VALID_STATUS_SOFTWARE_ENGINEER).build();
        assertTrue(uniqueRoleList.contains(editedMobileEngineer));
    }

    @Test
    public void add_nullRole_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoleList.add(null));
    }

    @Test
    public void add_duplicateRole_throwsDuplicateRoleException() {
        uniqueRoleList.add(MOBILE_ENGINEER);
        assertThrows(DuplicateRoleException.class, () -> uniqueRoleList.add(MOBILE_ENGINEER));
    }

    @Test
    public void setRole_nullTargetRole_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoleList.setRole(null, MOBILE_ENGINEER));
    }

    @Test
    public void setRole_nullEditedRole_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoleList.setRole(MOBILE_ENGINEER, null));
    }

    @Test
    public void setRole_targetRoleNotInList_throwsRoleNotFoundException() {
        assertThrows(RoleNotFoundException.class, () -> uniqueRoleList.setRole(MOBILE_ENGINEER, MOBILE_ENGINEER));
    }

    @Test
    public void setRole_editedRoleIsSameRole_success() {
        uniqueRoleList.add(MOBILE_ENGINEER);
        uniqueRoleList.setRole(MOBILE_ENGINEER, MOBILE_ENGINEER);
        UniqueRoleList expectedUniqueRoleList = new UniqueRoleList();
        expectedUniqueRoleList.add(MOBILE_ENGINEER);
        assertEquals(expectedUniqueRoleList, uniqueRoleList);
    }

    @Test
    public void setRole_editedRoleHasSameIdentity_success() {
        uniqueRoleList.add(MOBILE_ENGINEER);
        Role editedMobileEngineer = new RoleBuilder(MOBILE_ENGINEER).withStatus(VALID_STATUS_SOFTWARE_ENGINEER).build();
        uniqueRoleList.setRole(MOBILE_ENGINEER, editedMobileEngineer);
        UniqueRoleList expectedUniqueRoleList = new UniqueRoleList();
        expectedUniqueRoleList.add(editedMobileEngineer);
        assertEquals(expectedUniqueRoleList, uniqueRoleList);
    }

    @Test
    public void setRole_editedRoleHasDifferentIdentity_success() {
        uniqueRoleList.add(MOBILE_ENGINEER);
        uniqueRoleList.setRole(MOBILE_ENGINEER, NETWORK_ENGINEER);
        UniqueRoleList expectedUniqueRoleList = new UniqueRoleList();
        expectedUniqueRoleList.add(NETWORK_ENGINEER);
        assertEquals(expectedUniqueRoleList, uniqueRoleList);
    }

    @Test
    public void setRole_editedRoleHasNonUniqueIdentity_throwsDuplicateRoleException() {
        uniqueRoleList.add(MOBILE_ENGINEER);
        uniqueRoleList.add(NETWORK_ENGINEER);
        assertThrows(DuplicateRoleException.class, () -> uniqueRoleList.setRole(MOBILE_ENGINEER, NETWORK_ENGINEER));
    }

    @Test
    public void remove_nullRole_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoleList.remove(null));
    }

    @Test
    public void remove_roleDoesNotExist_throwsRoleNotFoundException() {
        assertThrows(RoleNotFoundException.class, () -> uniqueRoleList.remove(MOBILE_ENGINEER));
    }

    @Test
    public void remove_existingRole_removesRole() {
        uniqueRoleList.add(MOBILE_ENGINEER);
        uniqueRoleList.remove(MOBILE_ENGINEER);
        UniqueRoleList expectedUniqueRoleList = new UniqueRoleList();
        assertEquals(expectedUniqueRoleList, uniqueRoleList);
    }

    @Test
    public void setRoles_nullUniqueRoleList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoleList.setRoles((UniqueRoleList) null));
    }

    @Test
    public void setRoles_uniqueRoleList_replacesOwnListWithProvidedUniqueRoleList() {
        uniqueRoleList.add(MOBILE_ENGINEER);
        UniqueRoleList expectedUniqueRoleList = new UniqueRoleList();
        expectedUniqueRoleList.add(NETWORK_ENGINEER);
        uniqueRoleList.setRoles(expectedUniqueRoleList);
        assertEquals(expectedUniqueRoleList, uniqueRoleList);
    }

    @Test
    public void setRoles_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoleList.setRoles((List<Role>) null));
    }

    @Test
    public void setRoles_list_replacesOwnListWithProvidedList() {
        uniqueRoleList.add(MOBILE_ENGINEER);
        List<Role> roleList = Collections.singletonList(NETWORK_ENGINEER);
        uniqueRoleList.setRoles(roleList);
        UniqueRoleList expectedUniqueRoleList = new UniqueRoleList();
        expectedUniqueRoleList.add(NETWORK_ENGINEER);
        assertEquals(expectedUniqueRoleList, uniqueRoleList);
    }

    @Test
    public void setRoles_listWithDuplicateRoles_throwsDuplicateRoleException() {
        List<Role> listWithDuplicateRoles = Arrays.asList(MOBILE_ENGINEER, MOBILE_ENGINEER);
        assertThrows(DuplicateRoleException.class, () -> uniqueRoleList.setRoles(listWithDuplicateRoles));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueRoleList.asUnmodifiableObservableList().remove(0));
    }
}
