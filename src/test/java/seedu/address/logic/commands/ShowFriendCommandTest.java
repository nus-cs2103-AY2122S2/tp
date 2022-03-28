package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertShowFriendCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.FriendName;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ShowFriendCommand}.
 */
public class ShowFriendCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    //ensures that GUI will only display single person if valid name is given
    @Test
    public void execute_showValidFriendName_success() {
        Person personToShow = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        ShowFriendCommand showFriendCommand = new ShowFriendCommand(personToShow);

        String expectedMessage = String.format(ShowFriendCommand.MESSAGE_SUCCESS, personToShow.getName());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredPersonList(x -> x.isSamePerson(personToShow));

        assert(expectedModel.getFilteredPersonList().size() == 1);

        assertShowFriendCommandSuccess(showFriendCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_showNameDoesNotExist_throwsCommandException() {
        //Tommy Ang does not exist in the sample model that is used for testing here
        Person personToShow = new Person(new FriendName("Tommy Ang"));
        ShowFriendCommand showFriendCommand = new ShowFriendCommand(personToShow);
        assertCommandFailure(showFriendCommand, model, Messages.MESSAGE_PERSON_DOES_NOT_EXIST);
    }

    @Test
    public void execute_switchingWindows_success() {

        Person personToShow = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        ShowFriendCommand showFriendCommand = new ShowFriendCommand(personToShow);
        String expectedMessage = String.format(ShowFriendCommand.MESSAGE_SUCCESS, personToShow.getName());
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredPersonList(x -> x.isSamePerson(personToShow));

        CommandResult commandResult = null;
        try {
            commandResult = showFriendCommand.execute(model);
        } catch (CommandException ce) {
            assertCommandFailure(showFriendCommand, model, Messages.MESSAGE_INVALID_COMMAND_FORMAT);
        }

        //ensures that window is switched from normal to expanded
        assert (commandResult.isShowFriendCommand());

        assert (expectedModel.getFilteredPersonList().size() == 1);
        assertShowFriendCommandSuccess(showFriendCommand, model, expectedMessage, expectedModel);

        //when that when a new command(not showfriend command) is made, the window is
        //switched from expanded to normal
        ListCommand listCommand = new ListCommand();
        CommandResult listCommandResult = listCommand.execute(model);

        //ensures that window is switched from expanded to normal
        assert (!listCommandResult.isShowFriendCommand());
    }


    @Test
    public void equals() {

        Person one = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person two = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        ShowFriendCommand showFriendCommandOne = new ShowFriendCommand(one);
        ShowFriendCommand showFriendCommandTwo = new ShowFriendCommand(two);

        // same object -> returns true
        assertTrue(showFriendCommandOne.equals(showFriendCommandOne));

        // same values -> returns true
        ShowFriendCommand showFriendCommandOneCopy = new ShowFriendCommand(one);
        assertTrue(showFriendCommandOne.equals(showFriendCommandOneCopy));

        // different friends -> returns false
        assertFalse(showFriendCommandOne.equals(showFriendCommandTwo));

        // different types -> returns false
        assertFalse(showFriendCommandOne.equals(1));

        // null -> returns false
        assertFalse(showFriendCommandOne.equals(null));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}

