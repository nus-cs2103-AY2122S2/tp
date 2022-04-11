package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTACTED_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEMO_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_COMPANION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_WIFE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

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

        // same name, all other attributes different -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // same name amd phone, all other attributes different -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // same name, phone and email, all other attributes different -> returns true
        editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns true
        Person editedBob = new PersonBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertTrue(BOB.isSamePerson(editedBob));

        // same name, all other attributes same -> returns true
        editedBob = new PersonBuilder(BOB).withName(VALID_NAME_BOB).build();
        assertTrue(BOB.isSamePerson(editedBob));

    }

    @Test
    public void exactEquals() {
        // same values -> returns true
        Person aliceCopy = new PersonBuilder(ALICE).build();
        assertTrue(ALICE.exactEquals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.exactEquals(ALICE));

        // null -> returns false
        assertFalse(ALICE.exactEquals(null));

        // different person -> returns false
        assertFalse(ALICE.exactEquals(BOB));

        // different name -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.exactEquals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.exactEquals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.exactEquals(editedAlice));

        // different address -> returns false
        editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.exactEquals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.exactEquals(editedAlice));

        // different memos -> returns false
        editedAlice = new PersonBuilder(ALICE).withMemo(VALID_MEMO_BOB).build();
        assertFalse(ALICE.exactEquals(editedAlice));

        // different contacted dates -> returns false
        editedAlice = new PersonBuilder(ALICE).withContactedDate(VALID_CONTACTED_DATE_BOB).build();
        assertFalse(ALICE.exactEquals(editedAlice));

        // letter case difference testing: At most one invalid field per input
        // same name different case -> returns false
        String newName = ALICE.getName().toString().toUpperCase();
        editedAlice = new PersonBuilder(ALICE).withName(newName).build();
        assertFalse(ALICE.exactEquals(editedAlice));

        // same email difference case -> returns false
        String newEmail = ALICE.getEmail().toString().toUpperCase();
        editedAlice = new PersonBuilder(ALICE).withEmail(newEmail).build();
        assertFalse(ALICE.exactEquals(editedAlice));

        // same address difference case -> returns false
        String newAddress = ALICE.getAddress().toString().toUpperCase();
        editedAlice = new PersonBuilder(ALICE).withAddress(newAddress).build();
        assertFalse(ALICE.exactEquals(editedAlice));

        // same memo difference case -> returns false
        String newMemo = ALICE.getMemo().toString().toUpperCase();
        editedAlice = new PersonBuilder(ALICE).withMemo(newMemo).build();
        assertFalse(ALICE.exactEquals(editedAlice));

        // same tags different case -> returns false
        Person alice1 = new PersonBuilder(ALICE).withTags(VALID_TAG_FRIEND).build();
        Person alice2 = new PersonBuilder(ALICE).withTags(VALID_TAG_FRIEND.toUpperCase()).build();
        assertFalse(alice1.exactEquals(alice2));

        // multiple same tags, one different case -> returns false
        alice1 = new PersonBuilder(ALICE).withTags(VALID_TAG_FRIEND,
                VALID_TAG_COMPANION,
                VALID_TAG_WIFE).build();
        alice2 = new PersonBuilder(ALICE).withTags(VALID_TAG_FRIEND,
                VALID_TAG_COMPANION.toUpperCase(),
                VALID_TAG_WIFE).build();
        assertFalse(alice1.exactEquals(alice2));

        // same tags different order -> returns true
        alice1 = new PersonBuilder(ALICE).withTags(VALID_TAG_FRIEND, VALID_TAG_WIFE).build();
        alice2 = new PersonBuilder(ALICE).withTags(VALID_TAG_WIFE, VALID_TAG_FRIEND).build();
        assertTrue(alice1.exactEquals(alice2));
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

        // different memos -> returns false
        editedAlice = new PersonBuilder(ALICE).withMemo(VALID_MEMO_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different contacted dates -> returns false
        editedAlice = new PersonBuilder(ALICE).withContactedDate(VALID_CONTACTED_DATE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // same tags different order -> returns true
        Person alice1 = new PersonBuilder(ALICE).withTags(VALID_TAG_FRIEND, VALID_TAG_WIFE).build();
        Person alice2 = new PersonBuilder(ALICE).withTags(VALID_TAG_WIFE, VALID_TAG_FRIEND).build();
        assertTrue(alice1.equals(alice2));
    }
}
