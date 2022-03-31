package seedu.address.model.applicant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.applicant.exceptions.DuplicatePersonException;
import seedu.address.model.applicant.exceptions.PersonNotFoundException;
import seedu.address.testutil.PersonBuilder;

public class UniqueApplicantListTest {

    private final UniqueApplicantList uniqueApplicantList = new UniqueApplicantList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicantList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueApplicantList.contains(ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueApplicantList.add(ALICE);
        assertTrue(uniqueApplicantList.contains(ALICE));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueApplicantList.add(ALICE);
        Applicant editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueApplicantList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicantList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueApplicantList.add(ALICE);
        assertThrows(DuplicatePersonException.class, () -> uniqueApplicantList.add(ALICE));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicantList.setApplicant(null, ALICE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicantList.setApplicant(ALICE, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueApplicantList.setApplicant(ALICE, ALICE));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniqueApplicantList.add(ALICE);
        uniqueApplicantList.setApplicant(ALICE, ALICE);
        UniqueApplicantList expectedUniqueApplicantList = new UniqueApplicantList();
        expectedUniqueApplicantList.add(ALICE);
        assertEquals(expectedUniqueApplicantList, uniqueApplicantList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniqueApplicantList.add(ALICE);
        Applicant editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueApplicantList.setApplicant(ALICE, editedAlice);
        UniqueApplicantList expectedUniqueApplicantList = new UniqueApplicantList();
        expectedUniqueApplicantList.add(editedAlice);
        assertEquals(expectedUniqueApplicantList, uniqueApplicantList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniqueApplicantList.add(ALICE);
        uniqueApplicantList.setApplicant(ALICE, BOB);
        UniqueApplicantList expectedUniqueApplicantList = new UniqueApplicantList();
        expectedUniqueApplicantList.add(BOB);
        assertEquals(expectedUniqueApplicantList, uniqueApplicantList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueApplicantList.add(ALICE);
        uniqueApplicantList.add(BOB);
        assertThrows(DuplicatePersonException.class, () -> uniqueApplicantList.setApplicant(ALICE, BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicantList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueApplicantList.remove(ALICE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueApplicantList.add(ALICE);
        uniqueApplicantList.remove(ALICE);
        UniqueApplicantList expectedUniqueApplicantList = new UniqueApplicantList();
        assertEquals(expectedUniqueApplicantList, uniqueApplicantList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicantList.setApplicants((UniqueApplicantList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniqueApplicantList.add(ALICE);
        UniqueApplicantList expectedUniqueApplicantList = new UniqueApplicantList();
        expectedUniqueApplicantList.add(BOB);
        uniqueApplicantList.setApplicants(expectedUniqueApplicantList);
        assertEquals(expectedUniqueApplicantList, uniqueApplicantList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicantList.setApplicants((List<Applicant>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniqueApplicantList.add(ALICE);
        List<Applicant> applicantList = Collections.singletonList(BOB);
        uniqueApplicantList.setApplicants(applicantList);
        UniqueApplicantList expectedUniqueApplicantList = new UniqueApplicantList();
        expectedUniqueApplicantList.add(BOB);
        assertEquals(expectedUniqueApplicantList, uniqueApplicantList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Applicant> listWithDuplicateApplicants = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicatePersonException.class, () ->
                uniqueApplicantList.setApplicants(listWithDuplicateApplicants));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueApplicantList.asUnmodifiableObservableList().remove(0));
    }
}
