package seedu.address.model.classgroup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CG_ID_CS2103T_TUT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CG_TYPE_CS2103T_TUT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MOD_CODE_CS2103T_TUT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClassGroups.CS2103T_TUT;
import static seedu.address.testutil.TypicalClassGroups.CS2106_LAB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.classgroup.exceptions.ClassGroupNotFoundException;
import seedu.address.model.classgroup.exceptions.DuplicateClassGroupException;
import seedu.address.testutil.ClassGroupBuilder;

public class UniqueClassGroupListTest {
    private final UniqueClassGroupList uniqueClassGroupList = new UniqueClassGroupList();

    @Test
    public void contains_nullClassGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClassGroupList.contains(null));
    }

    @Test
    public void contains_classGroupNotInList_returnsFalse() {
        assertFalse(uniqueClassGroupList.contains(CS2103T_TUT));
    }

    @Test
    public void contains_classGroupInList_returnsTrue() {
        uniqueClassGroupList.add(CS2103T_TUT);
        assertTrue(uniqueClassGroupList.contains(CS2103T_TUT));
    }

    @Test
    public void contains_classGroupWithSameIdentityFieldsInList_returnsTrue() {
        uniqueClassGroupList.add(CS2103T_TUT);
        ClassGroup editedCs2103t = new ClassGroupBuilder(CS2103T_TUT).withClassGroupId(VALID_CG_ID_CS2103T_TUT)
                .withClassGroupType(VALID_CG_TYPE_CS2103T_TUT)
                .withModuleCode(VALID_MOD_CODE_CS2103T_TUT).build();
        assertTrue(uniqueClassGroupList.contains(editedCs2103t));
    }

    @Test
    public void add_nullClassGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClassGroupList.add(null));
    }

    @Test
    public void add_duplicateClassGroup_throwsDuplicateClassGroupException() {
        uniqueClassGroupList.add(CS2103T_TUT);
        assertThrows(DuplicateClassGroupException.class, () -> uniqueClassGroupList.add(CS2103T_TUT));
    }

    @Test
    public void setClassGroup_nullTargetClassGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClassGroupList.setClassGroup(null, CS2103T_TUT));
    }

    @Test
    public void setClassGroup_nullEditedClassGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClassGroupList.setClassGroup(CS2103T_TUT, null));
    }

    @Test
    public void setClassGroup_targetClassGroupNotInList_throwsClassGroupNotFoundException() {
        assertThrows(ClassGroupNotFoundException.class, () ->
                uniqueClassGroupList.setClassGroup(CS2103T_TUT, CS2103T_TUT));
    }

    @Test
    public void setClassGroup_editedClassGroupIsSameClassGroup_success() {
        uniqueClassGroupList.add(CS2103T_TUT);
        uniqueClassGroupList.setClassGroup(CS2103T_TUT, CS2103T_TUT);
        UniqueClassGroupList expectedUniqueClassGroupList = new UniqueClassGroupList();
        expectedUniqueClassGroupList.add(CS2103T_TUT);
        assertEquals(expectedUniqueClassGroupList, uniqueClassGroupList);
    }

    @Test
    public void setClassGroup_editedClassGroupHasSameIdentity_success() {
        uniqueClassGroupList.add(CS2103T_TUT);
        ClassGroup editedCs2103t = new ClassGroupBuilder(CS2103T_TUT).withClassGroupId(VALID_CG_ID_CS2103T_TUT)
                .withClassGroupType(VALID_CG_TYPE_CS2103T_TUT)
                .withModuleCode(VALID_MOD_CODE_CS2103T_TUT).build();
        uniqueClassGroupList.setClassGroup(CS2103T_TUT, editedCs2103t);
        UniqueClassGroupList expectedUniqueClassGroupList = new UniqueClassGroupList();
        expectedUniqueClassGroupList.add(editedCs2103t);
        assertEquals(expectedUniqueClassGroupList, uniqueClassGroupList);
    }

    @Test
    public void setClassGroup_editedClassGroupHasDifferentIdentity_success() {
        uniqueClassGroupList.add(CS2103T_TUT);
        uniqueClassGroupList.setClassGroup(CS2103T_TUT, CS2106_LAB);
        UniqueClassGroupList expectedUniqueClassGroupList = new UniqueClassGroupList();
        expectedUniqueClassGroupList.add(CS2106_LAB);
        assertEquals(expectedUniqueClassGroupList, uniqueClassGroupList);
    }

    @Test
    public void setClassGroup_editedClassGroupHasNonUniqueIdentity_throwsDuplicateClassGroupException() {
        uniqueClassGroupList.add(CS2103T_TUT);
        uniqueClassGroupList.add(CS2106_LAB);
        assertThrows(DuplicateClassGroupException.class, () ->
                uniqueClassGroupList.setClassGroup(CS2103T_TUT, CS2106_LAB));
    }

    @Test
    public void remove_nullClassGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClassGroupList.remove(null));
    }

    @Test
    public void remove_classGroupDoesNotExist_throwsClassGroupNotFoundException() {
        assertThrows(ClassGroupNotFoundException.class, () -> uniqueClassGroupList.remove(CS2103T_TUT));
    }

    @Test
    public void remove_existingClassGroup_removesClassGroup() {
        uniqueClassGroupList.add(CS2103T_TUT);
        uniqueClassGroupList.remove(CS2103T_TUT);
        UniqueClassGroupList expectedUniqueClassGroupList = new UniqueClassGroupList();
        assertEquals(expectedUniqueClassGroupList, uniqueClassGroupList);
    }

    @Test
    public void setClassGroups_nullUniqueClassGroupList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueClassGroupList.setClassGroups((UniqueClassGroupList) null));
    }

    @Test
    public void setClassGroups_uniqueClassGroupList_replacesOwnListWithProvidedUniqueClassGroupList() {
        uniqueClassGroupList.add(CS2103T_TUT);
        UniqueClassGroupList expectedUniqueClassGroupList = new UniqueClassGroupList();
        expectedUniqueClassGroupList.add(CS2106_LAB);
        uniqueClassGroupList.setClassGroups(expectedUniqueClassGroupList);
        assertEquals(expectedUniqueClassGroupList, uniqueClassGroupList);
    }

    @Test
    public void setClassGroups_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClassGroupList.setClassGroups((List<ClassGroup>) null));
    }

    @Test
    public void setClassGroups_list_replacesOwnListWithProvidedList() {
        uniqueClassGroupList.add(CS2103T_TUT);
        List<ClassGroup> classGroupList = Collections.singletonList(CS2106_LAB);
        uniqueClassGroupList.setClassGroups(classGroupList);
        UniqueClassGroupList expectedUniqueClassGroupList = new UniqueClassGroupList();
        expectedUniqueClassGroupList.add(CS2106_LAB);
        assertEquals(expectedUniqueClassGroupList, uniqueClassGroupList);
    }

    @Test
    public void setClassGroups_listWithDuplicateClassGroups_throwsDuplicateClassGroupException() {
        List<ClassGroup> listWithDuplicateClassGroups = Arrays.asList(CS2103T_TUT, CS2103T_TUT);
        assertThrows(DuplicateClassGroupException.class, () ->
                uniqueClassGroupList.setClassGroups(listWithDuplicateClassGroups));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniqueClassGroupList.asUnmodifiableObservableList().remove(0));
    }
}
