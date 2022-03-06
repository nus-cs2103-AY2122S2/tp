package unibook.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import unibook.logic.commands.CommandTestUtil;
import unibook.model.module.Module;
import unibook.model.person.Person;
import unibook.model.person.exceptions.DuplicatePersonException;
import unibook.testutil.Assert;
import unibook.testutil.PersonBuilder;
import unibook.testutil.TypicalPersons;

public class UniBookTest {

    private final UniBook uniBook = new UniBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), uniBook.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyUniBook_replacesData() {
        UniBook newData = TypicalPersons.getTypicalUniBook();
        uniBook.resetData(newData);
        assertEquals(newData, uniBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(TypicalPersons.ALICE).withTags(CommandTestUtil.VALID_TAG_HUSBAND)
            .build();
        List<Person> newPersons = Arrays.asList(TypicalPersons.ALICE, editedAlice);
        List<Module> newModules = new ArrayList<>();
        UniBookStub newData = new UniBookStub(newPersons, newModules);
        Assert.assertThrows(DuplicatePersonException.class, () -> uniBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInUniBook_returnsFalse() {
        assertFalse(uniBook.hasPerson(TypicalPersons.ALICE));
    }

    @Test
    public void hasPerson_personInUniBook_returnsTrue() {
        uniBook.addPerson(TypicalPersons.ALICE);
        assertTrue(uniBook.hasPerson(TypicalPersons.ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInUniBook_returnsTrue() {
        uniBook.addPerson(TypicalPersons.ALICE);
        Person editedAlice = new PersonBuilder(TypicalPersons.ALICE).withTags(CommandTestUtil.VALID_TAG_HUSBAND)
            .build();
        assertTrue(uniBook.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, () -> uniBook.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyUniBook whose persons list can violate interface constraints.
     */
    private static class UniBookStub implements ReadOnlyUniBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        private final ObservableList<Module> modules = FXCollections.observableArrayList();

        UniBookStub(Collection<Person> persons, Collection<Module> modules) {
            this.persons.setAll(persons);
            this.modules.setAll(modules);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        public ObservableList<Module> getModuleList() {
            return modules;
        }
    }

}
