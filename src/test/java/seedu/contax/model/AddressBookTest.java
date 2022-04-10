package seedu.contax.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.contax.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.contax.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.contax.testutil.Assert.assertThrows;
import static seedu.contax.testutil.TypicalPersons.ALICE;
import static seedu.contax.testutil.TypicalPersons.BENSON;
import static seedu.contax.testutil.TypicalPersons.BOB;
import static seedu.contax.testutil.TypicalPersons.CARL;
import static seedu.contax.testutil.TypicalPersons.FIONA;
import static seedu.contax.testutil.TypicalPersons.FRIENDS;
import static seedu.contax.testutil.TypicalPersons.GEORGE;
import static seedu.contax.testutil.TypicalPersons.HOON;
import static seedu.contax.testutil.TypicalPersons.OWES_MONEY;
import static seedu.contax.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.contax.testutil.TypicalPersons.getTypicalTags;
import static seedu.contax.testutil.TypicalTags.CLIENTS;
import static seedu.contax.testutil.TypicalTags.COLLEAGUES;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.contax.model.person.Person;
import seedu.contax.model.person.exceptions.DuplicatePersonException;
import seedu.contax.model.person.exceptions.PersonNotFoundException;
import seedu.contax.model.tag.Tag;
import seedu.contax.testutil.AddressBookBuilder;
import seedu.contax.testutil.PersonBuilder;
import seedu.contax.testutil.TagBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPersonList());
    }

    @Test
    public void copyConstructor() {
        AddressBook addressBook2 = new AddressBook();
        addressBook2.addPerson(ALICE);
        addressBook2.addTag(CLIENTS);

        assertEquals(addressBook2, new AddressBook(addressBook2));
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_personHasMissingTag_replacesData() {
        AddressBook newAB = getTypicalAddressBook();
        Tag newTag = new TagBuilder().withName("brand new tag").build();
        Person hoonWithTag = HOON.withTag(newTag);
        newAB.addTag(newTag);
        newAB.addPerson(hoonWithTag);

        addressBook.resetData(newAB);
        assertEquals(newAB, addressBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void setPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.setPerson(null, null));
        assertThrows(NullPointerException.class, () -> addressBook.setPerson(ALICE, null));
        assertThrows(NullPointerException.class, () -> addressBook.setPerson(null, ALICE));
    }

    @Test
    public void setPerson_personNotInList_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> addressBook.setPerson(ALICE, ALICE));
    }

    @Test
    public void setPerson_personInList_success() {
        addressBook.addPerson(ALICE);
        addressBook.addPerson(BOB);
        addressBook.setPerson(ALICE, CARL);

        AddressBook expectedAddressBook =
                new AddressBookBuilder().withTag(FRIENDS).withPerson(CARL).withPerson(BOB).build();
        assertEquals(expectedAddressBook, addressBook);
    }

    @Test
    public void setPersons_hasMissingTag_success() {
        // AddressBook should add the missing tag that exists in the person
        Tag newTag = new TagBuilder().withName("new tag").build();
        Person hoonWithTag = HOON.withTag(newTag);

        AddressBook expectedAddressBook = new AddressBookBuilder().withTag(newTag).withPerson(hoonWithTag).build();

        List<Person> personList = List.of(hoonWithTag);
        addressBook.setPersons(personList);
        assertEquals(expectedAddressBook, addressBook);

    }

    @Test
    public void setPersons_tagsExist_success() {
        // AddressBook should not add any tag in the address book
        AddressBook addressBook = getTypicalAddressBook();

        // FRIENDS and OWES_MONEY already exists in the address book
        // FIONA and GEORGE chosen as they do not have any tags
        Person fiona = FIONA.withTag(FRIENDS).withTag(OWES_MONEY);
        Person george = GEORGE.withTag(FRIENDS);

        List<Person> personList = List.of(fiona, george);

        AddressBook expectedAddressBook =
                new AddressBookBuilder().withTag(FRIENDS).withTag(OWES_MONEY).withTag(COLLEAGUES)
                        .withPerson(fiona).withPerson(george).build();

        addressBook.setPersons(personList);
        assertEquals(expectedAddressBook, addressBook);
    }

    @Test
    public void setTags_personTagsDoesNotExist_stripTag() {
        // Empty tag list - Strip all
        AddressBook addressBook = new AddressBookBuilder().withTag(FRIENDS).withPerson(ALICE).build();
        List<Tag> emptyList = List.of();
        addressBook.setTags(emptyList);
        AddressBook expectedAddressBook = new AddressBookBuilder().withPerson(ALICE.withoutTag(FRIENDS)).build();

        assertEquals(expectedAddressBook, addressBook);


        // Strip only missing tags
        addressBook =
                new AddressBookBuilder().withTag(FRIENDS).withTag(OWES_MONEY)
                        .withPerson(ALICE).withPerson(BENSON).build();

        List<Tag> tagList = List.of(FRIENDS);

        // OWES_MONEY should be removed
        addressBook.setTags(tagList);
        expectedAddressBook =
                new AddressBookBuilder().withTag(FRIENDS).withPerson(ALICE)
                        .withPerson(BENSON.withoutTag(OWES_MONEY)).build();

        assertEquals(expectedAddressBook, addressBook);
    }

    @Test
    public void setTags_allTagsExist_noStripping() {
        // Exactly the same as previous address book
        AddressBook addressBook = getTypicalAddressBook();
        List<Tag> tagList = getTypicalTags();

        addressBook.setTags(tagList);
        AddressBook expectedAddressBook = getTypicalAddressBook();
        assertEquals(expectedAddressBook, addressBook);

        // Tag not associated with any persons
        addressBook = getTypicalAddressBook();
        tagList = List.of(FRIENDS, OWES_MONEY);
        addressBook.setTags(tagList);

        expectedAddressBook = getTypicalAddressBook();
        expectedAddressBook.removeTag(COLLEAGUES);
        assertEquals(expectedAddressBook, addressBook);
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        assertTrue(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    @Test
    public void removePerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.removePerson(null));
    }

    @Test
    public void removePerson_personNotInList_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> addressBook.removePerson(ALICE));
    }

    @Test
    public void removePerson_personInList_success() {
        addressBook.addPerson(ALICE);
        addressBook.addPerson(BOB);
        addressBook.removePerson(ALICE);

        AddressBook expectedAddressBook = new AddressBookBuilder().withTag(FRIENDS).withPerson(BOB).build();
        assertEquals(expectedAddressBook, addressBook);
    }

    @Test
    public void equals() {
        AddressBook refAddressBook = new AddressBook();
        AddressBook addedPersonAddressBook = new AddressBookBuilder().withPerson(ALICE).build();
        AddressBook otherAddressBook = new AddressBookBuilder().withPerson(ALICE).build();

        assertTrue(refAddressBook.equals(new AddressBook()));
        assertTrue(refAddressBook.equals(refAddressBook));
        assertTrue(addedPersonAddressBook.equals(otherAddressBook));

        assertFalse(refAddressBook.equals(null));
        assertFalse(refAddressBook.equals(2));
        assertFalse(refAddressBook.equals(addedPersonAddressBook));
    }

    @Test
    public void hashCodeTest() {
        AddressBook refAddressBook = new AddressBook();
        AddressBook addedPersonAddressBook = new AddressBookBuilder().withPerson(ALICE).build();
        AddressBook otherAddressBook = new AddressBookBuilder().withPerson(ALICE).build();

        assertEquals(refAddressBook.hashCode(), (new AddressBook()).hashCode());
        assertEquals(refAddressBook.hashCode(), refAddressBook.hashCode());
        assertEquals(addedPersonAddressBook, otherAddressBook);

        assertNotEquals(refAddressBook.hashCode(), addedPersonAddressBook.hashCode());
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Tag> tags = FXCollections.observableArrayList();

        AddressBookStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Tag> getTagList() {
            return tags;
        }
    }

}
