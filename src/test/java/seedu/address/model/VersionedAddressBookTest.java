package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalPersons.getTypicalVersionedAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;


class VersionedAddressBookTest {

    private VersionedAddressBook versionedAddressBook;
    private VersionedAddressBook typicalVersionedAddressBook;

    @BeforeEach
    void setup() {
        typicalVersionedAddressBook = getTypicalVersionedAddressBook();
    }

    @Test
    void commit() {
        // initial state -> add AMY -> add BOB
        //                          -> add IDA

        typicalVersionedAddressBook.addPerson(AMY);
        typicalVersionedAddressBook.commit();

        typicalVersionedAddressBook.addPerson(BOB);
        typicalVersionedAddressBook.commit();

        typicalVersionedAddressBook.undo();

        typicalVersionedAddressBook.addPerson(IDA);
        typicalVersionedAddressBook.commit();

        versionedAddressBook = new VersionedAddressBook(getTypicalVersionedAddressBook());
        versionedAddressBook.addPerson(AMY);
        versionedAddressBook.commit();
        versionedAddressBook.addPerson(IDA);
        versionedAddressBook.commit();

        assertEquals(typicalVersionedAddressBook, versionedAddressBook);
    }

    @Test
    void undo_noCommandInHistory_doesNothing() {
        ObservableList personsList = typicalVersionedAddressBook.getPersonList();
        typicalVersionedAddressBook.undo();
        assertEquals(personsList, typicalVersionedAddressBook.getPersonList());

        typicalVersionedAddressBook.addPerson(AMY);
        typicalVersionedAddressBook.commit();
        typicalVersionedAddressBook.undo();
        typicalVersionedAddressBook.redo();

        personsList = typicalVersionedAddressBook.getPersonList();
        typicalVersionedAddressBook.undo();
        assertEquals(personsList, typicalVersionedAddressBook.getPersonList());
    }

    @Test
    void undo_hasCommandInHistory_personsRestored() {
        ObservableList personsList = typicalVersionedAddressBook.getPersonList();
        typicalVersionedAddressBook.addPerson(AMY);
        typicalVersionedAddressBook.commit();
        typicalVersionedAddressBook.undo();
        assertEquals(personsList, typicalVersionedAddressBook.getPersonList());
    }

    @Test
    void redo_hasCommandInHistory_personsRestored() {
        typicalVersionedAddressBook.addPerson(AMY);
        typicalVersionedAddressBook.commit();
        ObservableList personsList = typicalVersionedAddressBook.getPersonList();
        typicalVersionedAddressBook.undo();
        typicalVersionedAddressBook.redo();
        assertEquals(personsList, typicalVersionedAddressBook.getPersonList());
    }

    @Test
    void redo_noCommandInHistory_doesNothing() {
        ObservableList personsList = typicalVersionedAddressBook.getPersonList();
        typicalVersionedAddressBook.redo();
        assertEquals(personsList, typicalVersionedAddressBook.getPersonList());

        typicalVersionedAddressBook.addPerson(AMY);
        typicalVersionedAddressBook.commit();
        typicalVersionedAddressBook.undo();
        typicalVersionedAddressBook.redo();

        personsList = typicalVersionedAddressBook.getPersonList();
        typicalVersionedAddressBook.undo();
        assertEquals(personsList, typicalVersionedAddressBook.getPersonList());
    }

    @Test
    void canUndo_noMoreCommandHistory_returnsFalse() {
        // new VersionedAddressBook -> no history
        assertFalse(new VersionedAddressBook().canUndo());
        assertFalse(typicalVersionedAddressBook.canUndo());

        typicalVersionedAddressBook.addPerson(AMY);
        typicalVersionedAddressBook.commit();
        typicalVersionedAddressBook.undo();

        // No more history to undo -> cannot undo
        assertFalse(typicalVersionedAddressBook.canUndo());
    }

    @Test
    void canUndo_hasCommandHistory_returnsTrue() {
        typicalVersionedAddressBook.addPerson(AMY);
        typicalVersionedAddressBook.commit();
        typicalVersionedAddressBook.addPerson(BOB);
        typicalVersionedAddressBook.commit();


        // Two Commands in history -> can undo
        assertTrue(typicalVersionedAddressBook.canUndo());

        typicalVersionedAddressBook.undo();

        // One Commands in history -> can undo
        assertTrue(typicalVersionedAddressBook.canUndo());
    }

    @Test
    void canRedo_noMoreCommandHistory_returnsFalse() {
        // new VersionedAddressBook -> no history
        assertFalse(typicalVersionedAddressBook.canRedo());

        typicalVersionedAddressBook.addPerson(AMY);
        typicalVersionedAddressBook.commit();
        typicalVersionedAddressBook.undo();
        typicalVersionedAddressBook.redo();

        // One history undone and redone -> no history to redo
        assertFalse(typicalVersionedAddressBook.canRedo());
    }

    @Test
    void canRedo_hasCommandHistory_returnsTrue() {
        typicalVersionedAddressBook.addPerson(AMY);
        typicalVersionedAddressBook.commit();
        typicalVersionedAddressBook.addPerson(BOB);
        typicalVersionedAddressBook.commit();
        typicalVersionedAddressBook.undo();
        typicalVersionedAddressBook.undo();

        // 2 Commands in history -> can redo
        assertTrue(typicalVersionedAddressBook.canRedo());

        typicalVersionedAddressBook.redo();

        // 1 Command in history -> can redo
        assertTrue(typicalVersionedAddressBook.canRedo());
    }
}
