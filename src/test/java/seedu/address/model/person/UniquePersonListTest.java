package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains unit tests for UniquePersonList class and its methods.
 */
public class UniquePersonListTest {

    private final UniquePersonList uniquePersonList = new UniquePersonList();

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
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniquePersonList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniquePersonList.add(ALICE);
        assertThrows(DuplicatePersonException.class, () -> uniquePersonList.add(ALICE));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.setPerson(null, ALICE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.setPerson(ALICE, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniquePersonList.setPerson(ALICE, ALICE));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniquePersonList.add(ALICE);
        uniquePersonList.setPerson(ALICE, ALICE);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(ALICE);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniquePersonList.add(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniquePersonList.setPerson(ALICE, editedAlice);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(editedAlice);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniquePersonList.add(ALICE);
        uniquePersonList.setPerson(ALICE, BOB);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(BOB);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniquePersonList.add(ALICE);
        uniquePersonList.add(BOB);
        assertThrows(DuplicatePersonException.class, () -> uniquePersonList.setPerson(ALICE, BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniquePersonList.remove(ALICE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniquePersonList.add(ALICE);
        uniquePersonList.remove(ALICE);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.setPersons((UniquePersonList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniquePersonList.add(ALICE);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(BOB);
        uniquePersonList.setPersons(expectedUniquePersonList);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.setPersons((List<Person>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniquePersonList.add(ALICE);
        List<Person> personList = Collections.singletonList(BOB);
        uniquePersonList.setPersons(personList);
        UniquePersonList expectedUniquePersonList = new UniquePersonList();
        expectedUniquePersonList.add(BOB);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Person> listWithDuplicatePersons = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicatePersonException.class, () -> uniquePersonList.setPersons(listWithDuplicatePersons));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniquePersonList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void getNonUniqueAttributeTypesMessage_testForPhoneAttributeOnly() {
        String nonUniquePhoneMessage = "Phone";
        UniquePersonList uniquePersonList = new UniquePersonList();
        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("Phone");
        assertEquals(nonUniquePhoneMessage, uniquePersonList.getNonUniqueAttributeTypesMessage(arrayList));
    }

    @Test
    public void getNonUniqueAttributeTypesMessage_testForEmailAttributeOnly() {
        String nonUniqueEmailMessage = "Email";
        UniquePersonList uniquePersonList = new UniquePersonList();
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Email");

        assertEquals(nonUniqueEmailMessage, uniquePersonList.getNonUniqueAttributeTypesMessage(arrayList));
    }

    @Test
    public void getNonUniqueAttributeTypesMessage_testForMatriculationNumberAttributeOnly() {
        String nonUniqueMatriculationNumberMessage = "Matriculation Number";
        UniquePersonList uniquePersonList = new UniquePersonList();
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Matriculation Number");

        assertEquals(nonUniqueMatriculationNumberMessage,
                uniquePersonList.getNonUniqueAttributeTypesMessage(arrayList));
    }

    @Test
    public void getNonUniqueAttributeTypesMessage_testForAllUniqueAttributes() {
        String nonUniquePhoneAndEmailMessage = "Phone and Email";
        String nonUniquePhoneAndMatriculationNumberMessage = "Phone and Matriculation Number";
        String nonUniqueEmailAndMatriculationNumberMessage = "Email and Matriculation Number";
        String nonUniqueAllMessage = "Phone, Email and Matriculation Number";
        UniquePersonList uniquePersonList = new UniquePersonList();
        ArrayList<String> arrayList = new ArrayList<>();

        // test for Phone and Email
        arrayList.add("Phone");
        arrayList.add("Email");

        assertEquals(nonUniquePhoneAndEmailMessage, uniquePersonList.getNonUniqueAttributeTypesMessage(arrayList));

        // test for Phone and Matriculation Number
        arrayList.clear();
        arrayList.add("Phone");
        arrayList.add("Matriculation Number");

        assertEquals(nonUniquePhoneAndMatriculationNumberMessage,
                uniquePersonList.getNonUniqueAttributeTypesMessage(arrayList));

        // test for Email and Matriculation Number
        arrayList.clear();
        arrayList.add("Email");
        arrayList.add("Matriculation Number");

        assertEquals(nonUniqueEmailAndMatriculationNumberMessage,
                uniquePersonList.getNonUniqueAttributeTypesMessage(arrayList));

        // test for all unique attributes
        arrayList.clear();
        arrayList.add("Phone");
        arrayList.add("Email");
        arrayList.add("Matriculation Number");

        assertEquals(nonUniqueAllMessage, uniquePersonList.getNonUniqueAttributeTypesMessage(arrayList));
    }

    @Test
    public void getNonUniqueAttributeTypesMessage_testForSyntax() {
        String placeHolderText = "text";
        String expectedMessageForLengthOne = "text";
        String expectedMessageForLengthTwo = "text and text";
        String expectedMessageForLengthFive = "text, text, text, text and text";
        String expectedMessageForLengthTen = "text, text, text, text, text, " + expectedMessageForLengthFive;
        UniquePersonList uniquePersonList = new UniquePersonList();
        ArrayList<String> arrayList = new ArrayList<>();

        // test for length = 1
        arrayList.add(placeHolderText);
        assertEquals(expectedMessageForLengthOne, uniquePersonList.getNonUniqueAttributeTypesMessage(arrayList));

        // test for length = 2
        arrayList.add(placeHolderText);
        assertEquals(expectedMessageForLengthTwo, uniquePersonList.getNonUniqueAttributeTypesMessage(arrayList));

        // test for length = 5
        for (int i = 0; i < 3; i++) {
            arrayList.add(placeHolderText);
        }
        assertEquals(expectedMessageForLengthFive, uniquePersonList.getNonUniqueAttributeTypesMessage(arrayList));

        // test for length = 10
        for (int i = 0; i < 5; i++) {
            arrayList.add(placeHolderText);
        }
        assertEquals(expectedMessageForLengthTen, uniquePersonList.getNonUniqueAttributeTypesMessage(arrayList));
    }
}
