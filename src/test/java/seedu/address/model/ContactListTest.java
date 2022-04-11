package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MOD_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalContactList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.PersonBuilder;

public class ContactListTest {

    private final ContactList contactList = new ContactList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), contactList.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> contactList.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyContactList_replacesData() {
        ContactList newData = getTypicalContactList();
        contactList.resetData(newData);
        assertEquals(newData, contactList);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withMod(VALID_MOD_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        ContactListStub newData = new ContactListStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> contactList.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> contactList.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInContactList_returnsFalse() {
        assertFalse(contactList.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInContactList_returnsTrue() {
        contactList.addPerson(ALICE);
        assertTrue(contactList.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInContactList_returnsTrue() {
        contactList.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withMod(VALID_MOD_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(contactList.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> contactList.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyContactList whose persons list can violate interface constraints.
     */
    private static class ContactListStub implements ReadOnlyContactList {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        ContactListStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

}
