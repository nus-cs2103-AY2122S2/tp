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
import static seedu.unite.testutil.TypicalPersons.getTypicalUnite;

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
        Model newModel = new ModelManager(getTypicalUnite(), new UserPrefs());

        int sizeOfAddressBook = getTypicalUnite().getPersonList().size();
        ObservableList<Tag> listOfTags = getTypicalUnite().getTagList();

        for (int i = 1; i < sizeOfAddressBook + 1; i++) {
            for (Tag tag: listOfTags) {
                GrabCommand command = new GrabCommand(PREFIX_NAME, Integer.toString(i), tag);
                assertCommandFailure(command, newModel, expectedMessage);
            }
        }
    }

    @Test
    public void execute_validIndexWithInvalidTag_throwsCommandException() {
        String expectedMessage = GrabCommand.MESSAGE_GRAB_ONLY_BY_TAG;

        //Set up tag in models
        Model newModel = new ModelManager(getTypicalUnite(), new UserPrefs());

        int sizeOfAddressBook = getTypicalUnite().getPersonList().size();

        for (int i = 1; i < sizeOfAddressBook + 1; i++) {
            GrabCommand command = new GrabCommand(PREFIX_NAME, Integer.toString(i), new Tag("friendsdd"));
            assertCommandFailure(command, newModel, expectedMessage);
        }
    }

    @Test
    public void execute_noIndexWithValidTag_grabNameSuccessful() {
        String expectedMessage = GrabCommand.MESSAGE_SUCCESS + "\n";

        //Set up tag in models
        Model newModel = new ModelManager(getTypicalUnite(), new UserPrefs());
        Model newExpectedModel = new ModelManager(getTypicalUnite(), new UserPrefs());

        ObservableList<Tag> listOfTags = getTypicalUnite().getTagList();

        for (Tag tag: listOfTags) {
            GrabCommand command = new GrabCommand(PREFIX_NAME, "", tag);

            ObservableList<Person> newPersonList =
                    getTypicalUnite().getPersonList().filtered(t->t.getTags().contains(tag));

            if (newPersonList.size() > 0) {
                assertCommandSuccess(command, newModel, expectedMessage, newExpectedModel);
            }
        }
    }

    @Test
    public void execute_noIndexWithInvalidTag_throwsCommandException() {
        String expectedMessage = GrabCommand.MESSAGE_MISSING_TAG;

        //Set up tag in models
        Model newModel = new ModelManager(getTypicalUnite(), new UserPrefs());

        ObservableList<Tag> listOfTags = getTypicalUnite().getTagList();

        for (int i = 0; i < listOfTags.size(); i++) {
            GrabCommand command = new GrabCommand(PREFIX_NAME, "", new Tag("friendsdd"));
            assertCommandFailure(command, newModel, expectedMessage);
        }
    }

    @Test
    public void execute_validIndexNoTag_grabNameSuccessful() {
        String expectedMessage = GrabCommand.MESSAGE_SUCCESS;

        //Set up tag in models
        Model newModel = new ModelManager(getTypicalUnite(), new UserPrefs());
        Model newExpectedModel = new ModelManager(getTypicalUnite(), new UserPrefs());

        int sizeOfAddressBook = getTypicalUnite().getPersonList().size();

        for (int i = 1; i < sizeOfAddressBook + 1; i++) {
            GrabCommand command = new GrabCommand(PREFIX_NAME, Integer.toString(i), null);
            assertCommandSuccess(command, newModel, expectedMessage, newExpectedModel);
        }
    }

    @Test
    public void execute_invalidIndexNoTag_throwsCommandException() {
        String expectedMessage = Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;

        //Set up tag in models
        Model newModel = new ModelManager(getTypicalUnite(), new UserPrefs());

        int sizeOfAddressBook = getTypicalUnite().getPersonList().size();

        int zeroIndex = 0;
        int largeIndex = sizeOfAddressBook + 1;
        int negativeIndex = -3;

        GrabCommand zeroIndexCommand = new GrabCommand(PREFIX_NAME, String.valueOf(zeroIndex), null);
        assertCommandFailure(zeroIndexCommand, newModel, expectedMessage);

        GrabCommand largeIndexCommand = new GrabCommand(PREFIX_NAME, String.valueOf(largeIndex), null);
        assertCommandFailure(largeIndexCommand, newModel, expectedMessage);

        GrabCommand negativeIndexCommand = new GrabCommand(PREFIX_NAME, String.valueOf(negativeIndex), null);
        assertCommandFailure(negativeIndexCommand, newModel, expectedMessage);
    }

    @Test
    public void execute_validIndexNoTag_grabPhoneSuccessful() {
        String expectedMessage = GrabCommand.MESSAGE_SUCCESS;

        //Set up tag in models
        Model newModel = new ModelManager(getTypicalUnite(), new UserPrefs());
        Model newExpectedModel = new ModelManager(getTypicalUnite(), new UserPrefs());

        int sizeOfAddressBook = getTypicalUnite().getPersonList().size();

        for (int i = 1; i < sizeOfAddressBook + 1; i++) {
            GrabCommand command = new GrabCommand(PREFIX_PHONE, String.valueOf(i), null);
            assertCommandSuccess(command, newModel, expectedMessage, newExpectedModel);
        }
    }

    @Test
    public void execute_validIndexNoTag_grabEmailSuccessful() {
        String expectedMessage = GrabCommand.MESSAGE_SUCCESS;

        //Set up tag in models
        Model newModel = new ModelManager(getTypicalUnite(), new UserPrefs());
        Model newExpectedModel = new ModelManager(getTypicalUnite(), new UserPrefs());

        int sizeOfAddressBook = getTypicalUnite().getPersonList().size();

        for (int i = 1; i < sizeOfAddressBook + 1; i++) {
            GrabCommand command = new GrabCommand(PREFIX_EMAIL, String.valueOf(i), null);
            assertCommandSuccess(command, newModel, expectedMessage, newExpectedModel);
        }
    }

    @Test
    public void execute_validIndexNoTag_grabAddressSuccessful() {
        String expectedMessage = GrabCommand.MESSAGE_SUCCESS;

        //Set up tag in models
        Model newModel = new ModelManager(getTypicalUnite(), new UserPrefs());
        Model newExpectedModel = new ModelManager(getTypicalUnite(), new UserPrefs());

        int sizeOfAddressBook = getTypicalUnite().getPersonList().size();

        for (int i = 1; i < sizeOfAddressBook + 1; i++) {
            GrabCommand command = new GrabCommand(PREFIX_ADDRESS, String.valueOf(i), null);
            assertCommandSuccess(command, newModel, expectedMessage, newExpectedModel);
        }
    }

    @Test
    public void execute_validIndexNoTag_grabCourseSuccessful() {
        String expectedMessage = GrabCommand.MESSAGE_SUCCESS;

        //Set up tag in models
        Model newModel = new ModelManager(getTypicalUnite(), new UserPrefs());
        Model newExpectedModel = new ModelManager(getTypicalUnite(), new UserPrefs());

        int sizeOfAddressBook = getTypicalUnite().getPersonList().size();

        for (int i = 1; i < sizeOfAddressBook + 1; i++) {
            GrabCommand command = new GrabCommand(PREFIX_COURSE, String.valueOf(i), null);
            assertCommandSuccess(command, newModel, expectedMessage, newExpectedModel);
        }
    }

    @Test
    public void execute_validIndexNoTag_grabTelegramSuccessful() {
        String expectedMessage = GrabCommand.MESSAGE_SUCCESS;

        //Set up tag in models
        Model newModel = new ModelManager(getTypicalUnite(), new UserPrefs());
        Model newExpectedModel = new ModelManager(getTypicalUnite(), new UserPrefs());

        int sizeOfAddressBook = getTypicalUnite().getPersonList().size();

        for (int i = 1; i < sizeOfAddressBook + 1; i++) {
            GrabCommand command = new GrabCommand(PREFIX_TELEGRAM, String.valueOf(i), null);
            assertCommandSuccess(command, newModel, expectedMessage, newExpectedModel);
        }
    }

    @Test
    public void execute_validIndexNoTag_grabMatricCardSuccessful() {
        String expectedMessage = GrabCommand.MESSAGE_SUCCESS;

        //Set up tag in models
        Model newModel = new ModelManager(getTypicalUnite(), new UserPrefs());
        Model newExpectedModel = new ModelManager(getTypicalUnite(), new UserPrefs());

        int sizeOfAddressBook = getTypicalUnite().getPersonList().size();

        for (int i = 1; i < sizeOfAddressBook + 1; i++) {
            GrabCommand command = new GrabCommand(PREFIX_MATRICCARD, String.valueOf(i), null);
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
