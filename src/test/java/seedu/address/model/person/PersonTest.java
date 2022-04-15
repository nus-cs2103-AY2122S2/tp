package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MATRICULATION_NUMBER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

/**
 * Contains unit tests for Person class and its methods.
 */
public class PersonTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();

        // different phone number, all other attributes same --> returns true
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different matriculation number, all the other attributes same --> returns true
        editedAlice = new PersonBuilder(ALICE).withMatriculationNumber(VALID_MATRICULATION_NUMBER_BOB).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different email, all the other attributes the same --> returns true
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different phone number and matriculation, all other attributes same --> returns true
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB)
                .withMatriculationNumber(VALID_MATRICULATION_NUMBER_BOB).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different phone number and email, all other attributes same --> returns true
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different matriculation number and email, all other attributes same --> returns true
        editedAlice = new PersonBuilder(ALICE).withMatriculationNumber(VALID_MATRICULATION_NUMBER_BOB)
                .withEmail(VALID_EMAIL_BOB).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different phone, matriculation number and email, all the other attributes the same --> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB)
                .withMatriculationNumber(VALID_MATRICULATION_NUMBER_BOB).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // email differs in case, all other attributes same -> returns true
        Person editedBob = new PersonBuilder(BOB).withEmail(VALID_EMAIL_BOB.toUpperCase()).build();
        assertTrue(BOB.isSamePerson(editedBob));

        // matriculation number differs in case, all other attributes same -> returns true
        editedBob = new PersonBuilder(BOB)
                .withMatriculationNumber(VALID_MATRICULATION_NUMBER_BOB.toUpperCase()).build();
        assertTrue(BOB.isSamePerson(editedBob));

        editedBob = new PersonBuilder(BOB)
                .withMatriculationNumber(VALID_MATRICULATION_NUMBER_BOB.toLowerCase()).build();
        assertTrue(BOB.isSamePerson(editedBob));

        // First character is upper case last character is lower case eg. A0253625m --> returns true
        String editedMatriculationNumber = VALID_MATRICULATION_NUMBER_BOB.substring(0, 1).toUpperCase()
                + VALID_MATRICULATION_NUMBER_BOB.substring(1).toLowerCase();

        editedBob = new PersonBuilder(BOB).withMatriculationNumber(editedMatriculationNumber).build();
        assertTrue(BOB.isSamePerson(editedBob));

        // First character is lower case last character is upper case eg. a0253625M --> returns true
        editedMatriculationNumber = VALID_MATRICULATION_NUMBER_BOB.substring(0, 1).toLowerCase()
                + VALID_MATRICULATION_NUMBER_BOB.substring(1).toUpperCase();
        editedBob = new PersonBuilder(BOB).withMatriculationNumber(editedMatriculationNumber).build();
        assertTrue(BOB.isSamePerson(editedBob));

        // email has trailing spaces, all other attributes same -> returns true
        String emailWithTrailingSpaces = VALID_EMAIL_BOB + " ";
        editedBob = new PersonBuilder(BOB).withEmail(emailWithTrailingSpaces).build();
        assertTrue(BOB.isSamePerson(editedBob));

        // phone has trailing spaces, all other attributes same -> returns true
        String phoneWithTrailingSpaces = VALID_PHONE_BOB + " ";
        editedBob = new PersonBuilder(BOB).withPhone(phoneWithTrailingSpaces).build();
        assertTrue(BOB.isSamePerson(editedBob));

        // matriculation number has trailing spaces, all other attributes same -> returns true
        String matriculationNumberWithTrailingSpaces = VALID_MATRICULATION_NUMBER_BOB + " ";
        editedBob = new PersonBuilder(BOB).withMatriculationNumber(matriculationNumberWithTrailingSpaces).build();
        assertTrue(BOB.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new PersonBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
