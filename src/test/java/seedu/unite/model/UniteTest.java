package seedu.unite.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.unite.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.unite.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.unite.testutil.Assert.assertThrows;
import static seedu.unite.testutil.TypicalPersons.ALICE;
import static seedu.unite.testutil.TypicalPersons.getTypicalUnite;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.unite.model.person.Person;
import seedu.unite.model.person.exceptions.DuplicatePersonException;
import seedu.unite.model.tag.Tag;
import seedu.unite.testutil.PersonBuilder;

public class UniteTest {

    private final Unite unite = new Unite();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), unite.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> unite.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyUnite_replacesData() {
        Unite newData = getTypicalUnite();
        unite.resetData(newData);
        assertEquals(newData, unite);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        UniteStub newData = new UniteStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> unite.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> unite.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInUnite_returnsFalse() {
        assertFalse(unite.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInUnite_returnsTrue() {
        unite.addPerson(ALICE);
        assertTrue(unite.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInUnite_returnsTrue() {
        unite.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(unite.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> unite.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyUnite whose persons list can violate interface constraints.
     */
    private static class UniteStub implements ReadOnlyUnite {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Tag> tags = FXCollections.observableArrayList();

        UniteStub(Collection<Person> persons) {
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
