package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalLogs;

public class PersonTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void create_personWithoutOptionalFields_allFieldsAreNonNull() {
        Person person = new Person(new FriendName("Alex Teo"));
        assertTrue(person.getAddress() != null);
        assertTrue(person.getDescription() != null);
        assertTrue(person.getEmail() != null);
        assertTrue(person.getPhone() != null);
        assertTrue(person.getTags() != null);
        assertTrue(person.getLogs() != null);
    }

    @Test
    public void create_personWithoutOptionalFields_valuesInFieldsAreNull() {
        Person person = new Person(new FriendName("Alex Teo"));
        assertTrue(person.getAddress().value == null);
        assertTrue(person.getDescription().value == null);
        assertTrue(person.getEmail().value == null);
        assertTrue(person.getPhone().value == null);
        assertTrue(person.getTags().size() == 0);
        assertTrue(person.getLogs().size() == 0);
    }

    @Test
    public void create_personUsingConstructorWithoutDescription_success() {
        Person person = new Person(new FriendName("Brandon Tan"), new Phone("98765252"),
                new Email("test@yahoo.com.sg"), new Address("Kings Drive"),
                new HashSet<>(), new ArrayList<>());

        assertTrue(person.getDescription() != null);
        assertTrue(person.getDescription().value == null);
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns true
        Person editedBob = new PersonBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertTrue(BOB.isSamePerson(editedBob));

        // name differs in case, all other attributes different -> returns true
        editedBob = new PersonBuilder(ALICE).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertTrue(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PersonBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSamePerson(editedBob));
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

        //different descriptions -> returns false
        editedAlice = new PersonBuilder(ALICE).withDescription(VALID_DESCRIPTION_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));


        // different logs (titles && description must match) -> returns false
        editedAlice = new PersonBuilder(ALICE).withLogs(TypicalLogs.getTypicalLogs()).build();
        assertFalse(ALICE.equals(editedAlice));
        Person editedAliceWithDifferentDescriptions = new PersonBuilder(ALICE)
                .withLogs(TypicalLogs.getIdenticalButDifferentTypicalLogs()).build();
        assertFalse(ALICE.equals(editedAliceWithDifferentDescriptions));

    }

    @Test
    public void hasSameName() {

        // no other fields
        FriendName name = new FriendName("some valid name");
        FriendName repeatedName = new FriendName("some valid name");

        Person person = new Person(name);
        Person otherPerson = new Person(repeatedName);
        assertTrue(person.hasSameName(otherPerson));

        // returns true even with different attributes
        Person editedAlice = new PersonBuilder(ALICE).withLogs(TypicalLogs.getTypicalLogs()).build();
        assertTrue(ALICE.hasSameName(editedAlice));

        //returns true even if cases are different
        FriendName repeatedNameDifferentCase = new FriendName("Some Valid Name");
        otherPerson = new Person(repeatedNameDifferentCase);
        assertTrue(person.hasSameName(otherPerson));

        // returns false
        assertFalse(ALICE.hasSameName(BOB));

    }

    @Test
    void compareFriends() {
        Person first = new PersonBuilder().withName("A").build();
        Person second = new PersonBuilder().withName("B").build();
        Person third = new PersonBuilder().withName("C").build();
        Person fourth = new PersonBuilder().withName("D").build();
        Person equalsFirst = new PersonBuilder().withName("A").build();

        assertTrue(first.compareTo(second) < 0);
        assertTrue(first.compareTo(third) < 0);
        assertTrue(first.compareTo(fourth) < 0);
        assertTrue(first.compareTo(equalsFirst) == 0);
        assertTrue(second.compareTo(first) > 0);
        assertFalse(fourth.compareTo(third) < 0);
    }

    @Test
    public void hasName() {

        // return true
        FriendName name = new FriendName("some valid name");
        FriendName repeatedName = new FriendName("some valid name");

        Person person = new Person(name);
        assertTrue(person.hasName(repeatedName));

        // returns false
        assertFalse(ALICE.hasName(BOB.getName()));
    }
}
