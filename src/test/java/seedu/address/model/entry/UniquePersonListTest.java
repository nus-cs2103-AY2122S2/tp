package seedu.address.model.entry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_JANICE_STREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEntries.ALICE;
import static seedu.address.testutil.TypicalEntries.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.entry.exceptions.DuplicateEntryException;
import seedu.address.model.entry.exceptions.EntryNotFoundException;
import seedu.address.testutil.PersonBuilder;

public class UniquePersonListTest {

    private final UniqueEntryList<Person> uniquePersonList = new UniqueEntryList<>();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniquePersonList.contains(ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniquePersonList.add(ALICE);
        assertTrue(uniquePersonList.contains(ALICE));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniquePersonList.add(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withCompanyName(VALID_COMPANY_JANICE_STREET)
                .withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniquePersonList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicateEntryException() {
        uniquePersonList.add(ALICE);
        assertThrows(DuplicateEntryException.class, () -> uniquePersonList.add(ALICE));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.setEntry(null, ALICE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.setEntry(ALICE, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsEntryNotFoundException() {
        assertThrows(EntryNotFoundException.class, () -> uniquePersonList.setEntry(ALICE, ALICE));
    }

    @Test
    public void setPerson_editedPersonIsSameEntry_success() {
        uniquePersonList.add(ALICE);
        uniquePersonList.setEntry(ALICE, ALICE);
        UniqueEntryList<Person> expectedUniquePersonList = new UniqueEntryList<>();
        expectedUniquePersonList.add(ALICE);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniquePersonList.add(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withCompanyName(VALID_COMPANY_JANICE_STREET)
                .withTags(VALID_TAG_HUSBAND)
                .build();
        uniquePersonList.setEntry(ALICE, editedAlice);
        UniqueEntryList<Person> expectedUniquePersonList = new UniqueEntryList<>();
        expectedUniquePersonList.add(editedAlice);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniquePersonList.add(ALICE);
        uniquePersonList.setEntry(ALICE, BOB);
        UniqueEntryList<Person> expectedUniquePersonList = new UniqueEntryList<>();
        expectedUniquePersonList.add(BOB);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicateEntryException() {
        uniquePersonList.add(ALICE);
        uniquePersonList.add(BOB);
        assertThrows(DuplicateEntryException.class, () -> uniquePersonList.setEntry(ALICE, BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsEntryNotFoundException() {
        assertThrows(EntryNotFoundException.class, () -> uniquePersonList.remove(ALICE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniquePersonList.add(ALICE);
        uniquePersonList.remove(ALICE);
        UniqueEntryList<Person> expectedUniquePersonList = new UniqueEntryList<>();
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.setEntries((UniqueEntryList<Person>) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniquePersonList.add(ALICE);
        UniqueEntryList<Person> expectedUniquePersonList = new UniqueEntryList<>();
        expectedUniquePersonList.add(BOB);
        uniquePersonList.setEntries(expectedUniquePersonList);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.setEntries((List<Person>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniquePersonList.add(ALICE);
        List<Person> personList = Collections.singletonList(BOB);
        uniquePersonList.setEntries(personList);
        UniqueEntryList<Person> expectedUniquePersonList = new UniqueEntryList<>();
        expectedUniquePersonList.add(BOB);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicateEntryException() {
        List<Person> listWithDuplicatePersons = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateEntryException.class, () -> uniquePersonList.setEntries(listWithDuplicatePersons));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniquePersonList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void findPerson_emptyList_returnsNull() {
        assertNull(uniquePersonList.find(ALICE));
    }

    @Test
    public void archivePerson_emptyList_throwsEntryNotFoundException() {
        assertThrows(EntryNotFoundException.class, () -> uniquePersonList.setArchived(ALICE, true));
    }

    @Test
    public void removeMatchingCompanyName_personInList_throwsEntryNotFoundException() {
        uniquePersonList.add(ALICE);
        UniqueEntryList<Person> expectedUniquePersonList = new UniqueEntryList<>();
        uniquePersonList.removeMatchingCompanyName("DBSSS");
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }
}
