package seedu.unite.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.unite.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.unite.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.unite.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.unite.logic.parser.CliSyntax.PREFIX_COURSE;
import static seedu.unite.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.unite.logic.parser.CliSyntax.PREFIX_MATRICCARD;
import static seedu.unite.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.unite.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.unite.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.unite.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.unite.commons.core.Messages;
import seedu.unite.model.Model;
import seedu.unite.model.ModelManager;
import seedu.unite.model.UserPrefs;
import seedu.unite.model.person.Person;
import seedu.unite.model.tag.Tag;

public class GrabCommandTest {
    @Test
    public void execute_validIndexWithValidTag_throwsCommandException() {
        String expectedMessage = GrabCommand.MESSAGE_GRAB_ONLY_BY_TAG;

        //Set up tag in models
        Model newModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        int sizeOfAddressBook = getTypicalAddressBook().getPersonList().size();
        ObservableList<Tag> listOfTags = getTypicalAddressBook().getTagList();

        for (Integer i = 1; i < sizeOfAddressBook + 1; i++) {
            for (Tag tag: listOfTags) {
                GrabCommand command = new GrabCommand(PREFIX_NAME, i.toString(), tag);
                assertCommandFailure(command, newModel, expectedMessage);
            }
        }
    }

    @Test
    public void execute_validIndexWithInvalidTag_throwsCommandException() {
        String expectedMessage = GrabCommand.MESSAGE_GRAB_ONLY_BY_TAG;

        //Set up tag in models
        Model newModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        int sizeOfAddressBook = getTypicalAddressBook().getPersonList().size();

        for (Integer i = 1; i < sizeOfAddressBook + 1; i++) {
            GrabCommand command = new GrabCommand(PREFIX_NAME, i.toString(), new Tag("friendsdd"));
            assertCommandFailure(command, newModel, expectedMessage);
        }
    }

    @Test
    public void execute_noIndexWithValidTag_grabNameSuccessful() {
        String expectedMessage = GrabCommand.MESSAGE_SUCCESS + "\n";

        //Set up tag in models
        Model newModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model newExpectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        ObservableList<Tag> listOfTags = getTypicalAddressBook().getTagList();

        for (Tag tag: listOfTags) {
            GrabCommand command = new GrabCommand(PREFIX_NAME, "", tag);

            ObservableList<Person> newPersonList =
                    getTypicalAddressBook().getPersonList().filtered(t->t.getTags().contains(tag));

            if (newPersonList.size() > 0) {
                assertCommandSuccess(command, newModel, expectedMessage, newExpectedModel);
            }
        }
    }

    @Test
    public void execute_noIndexWithInvalidTag_throwsCommandException() {
        String expectedMessage = GrabCommand.MESSAGE_MISSING_TAG;

        //Set up tag in models
        Model newModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model newExpectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        ObservableList<Tag> listOfTags = getTypicalAddressBook().getTagList();

        for (Tag tag: listOfTags) {
            GrabCommand command = new GrabCommand(PREFIX_NAME, "", new Tag("friendsdd"));
            assertCommandFailure(command, newModel, expectedMessage);
        }
    }

    @Test
    public void execute_validIndexNoTag_grabNameSuccessful() {
        String expectedMessage = GrabCommand.MESSAGE_SUCCESS;

        //Set up tag in models
        Model newModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model newExpectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        int sizeOfAddressBook = getTypicalAddressBook().getPersonList().size();

        for (Integer i = 1; i < sizeOfAddressBook + 1; i++) {
            GrabCommand command = new GrabCommand(PREFIX_NAME, i.toString(), null);
            assertCommandSuccess(command, newModel, expectedMessage, newExpectedModel);
        }
    }

    @Test
    public void execute_invalidIndexNoTag_throwsCommandException() {
        String expectedMessage = Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;

        //Set up tag in models
        Model newModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model newExpectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        int sizeOfAddressBook = getTypicalAddressBook().getPersonList().size();

        Integer zeroIndex = 0;
        Integer largeIndex = sizeOfAddressBook + 1;
        Integer negativeIndex = -3;

        GrabCommand zeroIndexCommand = new GrabCommand(PREFIX_NAME, zeroIndex.toString(), null);
        assertCommandFailure(zeroIndexCommand, newModel, expectedMessage);

        GrabCommand largeIndexCommand = new GrabCommand(PREFIX_NAME, largeIndex.toString(), null);
        assertCommandFailure(largeIndexCommand, newModel, expectedMessage);

        GrabCommand negativeIndexCommand = new GrabCommand(PREFIX_NAME, negativeIndex.toString(), null);
        assertCommandFailure(negativeIndexCommand, newModel, expectedMessage);
    }

    @Test
    public void execute_validIndexNoTag_grabPhoneSuccessful() {
        String expectedMessage = GrabCommand.MESSAGE_SUCCESS;

        //Set up tag in models
        Model newModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model newExpectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        int sizeOfAddressBook = getTypicalAddressBook().getPersonList().size();

        for (Integer i = 1; i < sizeOfAddressBook + 1; i++) {
            GrabCommand command = new GrabCommand(PREFIX_PHONE, i.toString(), null);
            assertCommandSuccess(command, newModel, expectedMessage, newExpectedModel);
        }
    }

    @Test
    public void execute_validIndexNoTag_grabEmailSuccessful() {
        String expectedMessage = GrabCommand.MESSAGE_SUCCESS;

        //Set up tag in models
        Model newModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model newExpectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        int sizeOfAddressBook = getTypicalAddressBook().getPersonList().size();

        for (Integer i = 1; i < sizeOfAddressBook + 1; i++) {
            GrabCommand command = new GrabCommand(PREFIX_EMAIL, i.toString(), null);
            assertCommandSuccess(command, newModel, expectedMessage, newExpectedModel);
        }
    }

    @Test
    public void execute_validIndexNoTag_grabAddressSuccessful() {
        String expectedMessage = GrabCommand.MESSAGE_SUCCESS;

        //Set up tag in models
        Model newModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model newExpectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        int sizeOfAddressBook = getTypicalAddressBook().getPersonList().size();

        for (Integer i = 1; i < sizeOfAddressBook + 1; i++) {
            GrabCommand command = new GrabCommand(PREFIX_ADDRESS, i.toString(), null);
            assertCommandSuccess(command, newModel, expectedMessage, newExpectedModel);
        }
    }

    @Test
    public void execute_validIndexNoTag_grabCourseSuccessful() {
        String expectedMessage = GrabCommand.MESSAGE_SUCCESS;

        //Set up tag in models
        Model newModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model newExpectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        int sizeOfAddressBook = getTypicalAddressBook().getPersonList().size();

        for (Integer i = 1; i < sizeOfAddressBook + 1; i++) {
            GrabCommand command = new GrabCommand(PREFIX_COURSE, i.toString(), null);
            assertCommandSuccess(command, newModel, expectedMessage, newExpectedModel);
        }
    }

    @Test
    public void execute_validIndexNoTag_grabTelegramSuccessful() {
        String expectedMessage = GrabCommand.MESSAGE_SUCCESS;

        //Set up tag in models
        Model newModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model newExpectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        int sizeOfAddressBook = getTypicalAddressBook().getPersonList().size();

        for (Integer i = 1; i < sizeOfAddressBook + 1; i++) {
            GrabCommand command = new GrabCommand(PREFIX_TELEGRAM, i.toString(), null);
            assertCommandSuccess(command, newModel, expectedMessage, newExpectedModel);
        }
    }

    @Test
    public void execute_validIndexNoTag_grabMatricCardSuccessful() {
        String expectedMessage = GrabCommand.MESSAGE_SUCCESS;

        //Set up tag in models
        Model newModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model newExpectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        int sizeOfAddressBook = getTypicalAddressBook().getPersonList().size();

        for (Integer i = 1; i < sizeOfAddressBook + 1; i++) {
            GrabCommand command = new GrabCommand(PREFIX_MATRICCARD, i.toString(), null);
            assertCommandSuccess(command, newModel, expectedMessage, newExpectedModel);
        }
    }

    @Test
    public void equals() {
        GrabCommand grabAllFirstCommandNullTag = new GrabCommand(PREFIX_NAME, "", null);
        GrabCommand grabAllSecondCommandNullTag = new GrabCommand(PREFIX_TELEGRAM, "", null);

        GrabCommand grabIndexOneFirstCommandNullTag = new GrabCommand(PREFIX_NAME, "1", null);
        GrabCommand grabIndexTwoSecondCommandNullTag = new GrabCommand(PREFIX_NAME, "2", null);

        GrabCommand grabAllFirstCommandValidTag = new GrabCommand(PREFIX_NAME, "1", new Tag("friends"));
        GrabCommand grabAllSecondCommandValidTag = new GrabCommand(PREFIX_NAME, "1", new Tag("owesmoney"));

        // same object -> returns true
        assertTrue(grabAllFirstCommandNullTag.equals(grabAllFirstCommandNullTag));

        // different index -> returns false
        assertFalse(grabIndexOneFirstCommandNullTag.equals(grabIndexTwoSecondCommandNullTag));

        // different attribute -> returns false
        assertFalse(grabAllFirstCommandNullTag.equals(grabAllSecondCommandNullTag));

        // different tag -> returns false
        assertFalse(grabAllFirstCommandValidTag.equals(grabAllSecondCommandValidTag));

        // null -> returns false
        assertFalse(grabAllFirstCommandNullTag.equals(null));
    }
}
