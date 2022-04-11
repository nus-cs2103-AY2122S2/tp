package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalContents.getTypicalContent;
import static seedu.address.testutil.TypicalPersons.ALICE;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniqueModuleList;
import seedu.address.model.tasks.PriorityTaskList;
import seedu.address.testutil.ContentBuilder;

class VersionedContentsTest {
    private final Content typicalContent = getTypicalContent();
    private final Content emptyContent = new ContentBuilder().build();

    @Test
    void constructor() {
        VersionedContents versionedContents = new VersionedContents(emptyContent);
        assertEquals(versionedContents.getContentStateList(), List.of(emptyContent));

        VersionedContents newVersionedContents = new VersionedContents(new VersionedContents(typicalContent));
        assertEquals(newVersionedContents, new VersionedContents(typicalContent));
    }

    @Test
    void addContentVersion() {
        VersionedContents versionedContents = new VersionedContents(emptyContent);
        versionedContents.addContentVersion(typicalContent);
        assertEquals(versionedContents.getContentStateList(), List.of(emptyContent, typicalContent));
    }

    @Test
    void addContentVersionAfterUndo() {
        VersionedContents versionedContents = new VersionedContents(emptyContent);
        versionedContents.addContentVersion(typicalContent);
        versionedContents.undo();
        versionedContents.addContentVersion(typicalContent);
        versionedContents.addContentVersion(emptyContent);
        assertEquals(versionedContents.getContentStateList(), List.of(emptyContent, typicalContent, emptyContent));
    }

    @Test
    void addContentVersion_null_throwsNullPointerException() {
        VersionedContents versionedContents = new VersionedContents(emptyContent);
        assertThrows(NullPointerException.class, () -> versionedContents.addContentVersion(null));
    }

    @Test
    void getCurrentContent() {
        VersionedContents versionedContents = new VersionedContents(emptyContent);
        assertEquals(versionedContents.getCurrentContent(), emptyContent);
    }

    @Test
    void undo() {
        List<Person> newPersons = List.of(ALICE);
        ContactListStub newData = new ContactListStub(newPersons);
        Content content = new Content(newData, new PriorityTaskList(), new UniqueModuleList());
        VersionedContents emptyVersionedContents = new VersionedContents(emptyContent);

        emptyVersionedContents.addContentVersion(content);
        Content undoneContent = emptyVersionedContents.undo();
        assertEquals(undoneContent, emptyContent);
    }

    @Test
    void redo() {
        List<Person> newPersons = List.of(ALICE);
        ContactListStub newData = new ContactListStub(newPersons);
        Content content = new Content(newData, new PriorityTaskList(), new UniqueModuleList());
        VersionedContents emptyVersionedContents = new VersionedContents(emptyContent);

        emptyVersionedContents.addContentVersion(content);
        Content undoneContent = emptyVersionedContents.undo();
        Content redoneContent = emptyVersionedContents.redo();
        assertEquals(redoneContent, content);
    }

    @Test
    void testCanUndo() {
        VersionedContents emptyVersionedContents = new VersionedContents(emptyContent);
        assertFalse(emptyVersionedContents.canUndo());

        emptyVersionedContents.addContentVersion(typicalContent);
        assertTrue(emptyVersionedContents.canUndo());
    }

    @Test
    void testCanRedo() {
        VersionedContents emptyVersionedContents = new VersionedContents(emptyContent);
        assertFalse(emptyVersionedContents.canRedo());

        emptyVersionedContents.addContentVersion(typicalContent);
        assertFalse(emptyVersionedContents.canRedo());

        emptyVersionedContents.undo();
        assertTrue(emptyVersionedContents.canRedo());
    }

    @Test
    void equals() {
        VersionedContents emptyVersionedContents = new VersionedContents(emptyContent);
        VersionedContents anotherEmptyVersionedContents = new VersionedContents(emptyContent);
        VersionedContents typicalVersionedContents = new VersionedContents(typicalContent);

        assertEquals(emptyVersionedContents, anotherEmptyVersionedContents);
        assertNotEquals(emptyVersionedContents, typicalVersionedContents);
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
