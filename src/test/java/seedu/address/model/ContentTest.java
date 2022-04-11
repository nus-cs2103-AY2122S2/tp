package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalContents.getTypicalContent;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalContactList;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.UniqueModuleList;
import seedu.address.testutil.PersonBuilder;

class ContentTest {
    private final Content typicalContent = getTypicalContent();

    @Test
    void constructor() {
        assertEquals(typicalContent.getContactList(), getTypicalContactList());
    }

    @Test
    void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Content(null, getTypicalTaskList(),
                new UniqueModuleList()));
        assertThrows(NullPointerException.class, () -> new Content(getTypicalContactList(), null,
                new UniqueModuleList()));
    }

    @Test
    void getContentCopy() {
        Content typicalContentCopy = Content.getContentCopy(typicalContent);
        assertEquals(typicalContentCopy, typicalContent);
        assertNotSame(typicalContentCopy, typicalContent);
    }

    @Test
    void getContactList() {
        assertEquals(typicalContent.getContactList(), getTypicalContactList());
    }

    @Test
    void getTaskList() {
        assertEquals(typicalContent.getTaskList(), getTypicalTaskList());
    }

    @Test
    void testEquals() {
        assertEquals(typicalContent, new Content(getTypicalContactList(), getTypicalTaskList(),
                new UniqueModuleList()));
    }

    @Test
    void testNotEquals() {
        ContactList editedContactList = new ContactList(getTypicalContactList());
        editedContactList.addPerson(new PersonBuilder(ALICE).withName("TEST")
                .withStudentNumber("A0000000Q").withEmail("x@xdqx").build());
        Content editedContent = new Content(editedContactList, getTypicalTaskList(), new UniqueModuleList());
        assertNotEquals(typicalContent, editedContent);
    }
}
