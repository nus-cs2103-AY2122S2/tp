package seedu.address.model.group;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_NAME_NUS_FINTECH_SOCIETY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGroups.NON_EXISTENT_GROUP;
import static seedu.address.testutil.TypicalGroups.NUS_DATA_SCIENCE_SOCIETY;
import static seedu.address.testutil.TypicalGroups.NUS_FINTECH_SOCIETY;

import org.junit.jupiter.api.Test;

import seedu.address.model.group.exceptions.DuplicateGroupException;
import seedu.address.model.group.exceptions.GroupNotFoundException;
import seedu.address.testutil.GroupBuilder;

/**
 * Contains unit tests for {@code UniqueGroupList}.
 */
public class UniqueGroupListTest {

    private final UniqueGroupList uniqueGroupList = new UniqueGroupList();

    @Test
    public void contains_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGroupList.contains(null));
    }

    @Test
    public void contains_groupNotInList_returnsFalse() {
        assertFalse(uniqueGroupList.contains(NON_EXISTENT_GROUP));
    }

    @Test
    public void contains_groupInList_returnsTrue() {
        uniqueGroupList.add(NUS_FINTECH_SOCIETY);
        assertTrue(uniqueGroupList.contains(NUS_FINTECH_SOCIETY));
    }

    @Test
    public void contains_groupWithSameGroupName_returnsTrue() {
        uniqueGroupList.add(NUS_FINTECH_SOCIETY);
        Group editedGroup = new GroupBuilder(NUS_FINTECH_SOCIETY).build();
        assertTrue(uniqueGroupList.contains(editedGroup));
    }

    @Test
    public void add_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGroupList.add(null));
    }

    @Test
    public void add_duplicateGroup_throwsDuplicateGroupException() {
        uniqueGroupList.add(NUS_FINTECH_SOCIETY);
        assertThrows(DuplicateGroupException.class, () -> uniqueGroupList.add(NUS_FINTECH_SOCIETY));
    }

    @Test
    public void setGroup_nullTargetGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGroupList.setGroup(null, NUS_FINTECH_SOCIETY));
    }

    @Test
    public void setGroup_nullEditedGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGroupList.setGroup(NUS_FINTECH_SOCIETY, null));
    }

    @Test
    public void setGroup_targetGroupNotInList_throwsGroupNotFoundException() {
        assertThrows(GroupNotFoundException.class, () -> uniqueGroupList
                .setGroup(NUS_FINTECH_SOCIETY, NUS_FINTECH_SOCIETY));
    }

    @Test
    public void setGroup_editedGroupIsSameGroup_success() {
        uniqueGroupList.add(NUS_FINTECH_SOCIETY);
        uniqueGroupList.setGroup(NUS_FINTECH_SOCIETY, NUS_FINTECH_SOCIETY);
        UniqueGroupList expectedUniqueGroupList = new UniqueGroupList();
        expectedUniqueGroupList.add(NUS_FINTECH_SOCIETY);
        assertEquals(expectedUniqueGroupList, uniqueGroupList);
    }

    @Test
    public void setGroup_editedGroupHasSameGroupName_success() {
        uniqueGroupList.add(NUS_FINTECH_SOCIETY);
        Group editedGroup = new GroupBuilder(NUS_FINTECH_SOCIETY)
                .withGroupName(VALID_GROUP_NAME_NUS_FINTECH_SOCIETY)
                .build();

        uniqueGroupList.setGroup(NUS_FINTECH_SOCIETY, editedGroup);
        UniqueGroupList expectedUniqueGroupList = new UniqueGroupList();
        expectedUniqueGroupList.add(editedGroup);
        assertEquals(expectedUniqueGroupList, uniqueGroupList);
    }

    @Test
    public void setGroup_editedGroupHasDifferentIdentity_success() {
        uniqueGroupList.add(NUS_FINTECH_SOCIETY);
        uniqueGroupList.setGroup(NUS_FINTECH_SOCIETY, NUS_DATA_SCIENCE_SOCIETY);
        UniqueGroupList expectedUniqueGroupList = new UniqueGroupList();
        expectedUniqueGroupList.add(NUS_DATA_SCIENCE_SOCIETY);
        assertEquals(expectedUniqueGroupList, uniqueGroupList);
    }

    @Test
    public void setGroup_editedGroupHasNonUniqueIdentity_throwsDuplicateGroupException() {
        uniqueGroupList.add(NUS_FINTECH_SOCIETY);
        uniqueGroupList.add(NUS_DATA_SCIENCE_SOCIETY);
        assertThrows(DuplicateGroupException.class, () ->
                uniqueGroupList.setGroup(NUS_FINTECH_SOCIETY, NUS_DATA_SCIENCE_SOCIETY));
    }
}
