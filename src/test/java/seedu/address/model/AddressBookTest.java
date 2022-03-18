package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalHustleBook;

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

public class HustleBookTest {

    private final HustleBook hustleBook = new HustleBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), hustleBook.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> hustleBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyHustleBook_replacesData() {
        HustleBook newData = getTypicalHustleBook();
        hustleBook.resetData(newData);
        assertEquals(newData, hustleBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        HustleBookStub newData = new HustleBookStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> hustleBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> hustleBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInHustleBook_returnsFalse() {
        assertFalse(hustleBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInHustleBook_returnsTrue() {
        hustleBook.addPerson(ALICE);
        assertTrue(hustleBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInHustleBook_returnsTrue() {
        hustleBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(hustleBook.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> hustleBook.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyHustleBook whose persons list can violate interface constraints.
     */
    private static class HustleBookStub implements ReadOnlyHustleBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        HustleBookStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

}
