//package seedu.address.model.transaction;
//
//import  static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.testutil.Assert.assertThrows;
//import static seedu.address.testutil.PersonUtil.AMY;
//import static seedu.address.testutil.PersonUtil.BOB;
//import static seedu.address.testutil.PersonUtil.VALID_ADDRESS_BOB;
//import static seedu.address.testutil.PersonUtil.VALID_TAG_COWORKER;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//
//import seedu.address.model.person.exceptions.DuplicatePersonException;
//import seedu.address.model.person.exceptions.PersonNotFoundException;
//import seedu.address.model.tag.Tag;
//
//public class TransactionListTest {
//
//    private final UniquePersonList uniquePersonList = new UniquePersonList();
//
//    @Test
//    public void contains_nullPerson_throwsNullPointerException() {
//        assertThrows(NullPointerException.class, () -> uniquePersonList.contains(null));
//    }
//
//    @Test
//    public void add_nullPerson_throwsNullPointerException() {
//        assertThrows(NullPointerException.class, () -> uniquePersonList.add(null));
//    }
//
//    @Test
//    public void setPerson_nullTargetPerson_throwsNullPointerException() {
//        assertThrows(NullPointerException.class, () -> uniquePersonList.setPerson(null, AMY));
//    }
//
//    @Test
//    public void setPerson_nullEditedPerson_throwsNullPointerException() {
//        assertThrows(NullPointerException.class, () -> uniquePersonList.setPerson(AMY, null));
//    }
//
//    @Test
//    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
//        assertThrows(PersonNotFoundException.class, () -> uniquePersonList.setPerson(AMY, AMY));
//    }
//
//    @Test
//    public void setPerson_editedPersonIsSamePerson_success() {
//        uniquePersonList.add(AMY);
//        uniquePersonList.setPerson(AMY, AMY);
//        UniquePersonList expectedUniquePersonList = new UniquePersonList();
//        expectedUniquePersonList.add(AMY);
//        assertEquals(expectedUniquePersonList, uniquePersonList);
//    }
//
//    @Test
//    public void setPerson_editedPersonHasSameIdentity_success() {
//        uniquePersonList.add(AMY);
//        Person editedPerson = AMY.addFields(new Address(VALID_ADDRESS_BOB)).setTags(new Tag(VALID_TAG_COWORKER));
//        uniquePersonList.setPerson(AMY, editedPerson);
//        UniquePersonList expectedUniquePersonList = new UniquePersonList();
//        expectedUniquePersonList.add(editedPerson);
//        assertEquals(expectedUniquePersonList, uniquePersonList);
//    }
//
//    @Test
//    public void setPerson_editedPersonHasDifferentIdentity_success() {
//        uniquePersonList.add(AMY);
//        uniquePersonList.setPerson(AMY, BOB);
//        UniquePersonList expectedUniquePersonList = new UniquePersonList();
//        expectedUniquePersonList.add(BOB);
//        assertEquals(expectedUniquePersonList, uniquePersonList);
//    }
//
//    @Test
//    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
//        uniquePersonList.add(AMY);
//        uniquePersonList.add(BOB);
//        assertThrows(DuplicatePersonException.class, () -> uniquePersonList.setPerson(AMY, BOB));
//    }
//
//    @Test
//    public void remove_nullPerson_throwsNullPointerException() {
//        assertThrows(NullPointerException.class, () -> uniquePersonList.remove(null));
//    }
//
//    @Test
//    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
//        assertThrows(PersonNotFoundException.class, () -> uniquePersonList.remove(AMY));
//    }
//
//    @Test
//    public void remove_existingPerson_removesPerson() {
//        uniquePersonList.add(AMY);
//        uniquePersonList.remove(AMY);
//        UniquePersonList expectedUniquePersonList = new UniquePersonList();
//        assertEquals(expectedUniquePersonList, uniquePersonList);
//    }
//
//    @Test
//    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
//        assertThrows(UnsupportedOperationException.class, ()
//                -> uniquePersonList.asUnmodifiableObservableList().remove(0));
//    }
//}
