package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMMENT;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Comment;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class CommentCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new AddressBook(), new UserPrefs());


    @Test
    public void execute_addCommentUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withComment(VALID_COMMENT).build();

        CommentCommand commentCommand = new CommentCommand(INDEX_FIRST_PERSON, new Comment(VALID_COMMENT));
        String expectedMessage = String.format(CommentCommand.MESSAGE_ADD_SUCCESS,
                firstPerson.getName(), VALID_COMMENT);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new AddressBook(), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(commentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        CommentCommand emptyCommentCommand = new CommentCommand(INDEX_FIRST_PERSON, new Comment(""));
        CommentCommand firstCommentCommand = new CommentCommand(INDEX_FIRST_PERSON, new Comment("Test"));
        CommentCommand secondCommentCommand = new CommentCommand(INDEX_SECOND_PERSON, new Comment("Test"));

        // same object -> returns true
        assertTrue(emptyCommentCommand.equals(emptyCommentCommand));

        // same values -> returns true
        CommentCommand emptyCommentCommandCopy = new CommentCommand(INDEX_FIRST_PERSON, new Comment(""));
        assertTrue(emptyCommentCommandCopy.equals(emptyCommentCommand));

        // different types -> returns false
        assertFalse(emptyCommentCommand.equals("Test"));

        // null -> returns false
        assertFalse(emptyCommentCommand.equals(null));

        // different person -> returns false
        assertFalse(firstCommentCommand.equals(secondCommentCommand));

        // different comment -> returns false
        assertFalse(firstCommentCommand.equals(emptyCommentCommand));
    }
}
