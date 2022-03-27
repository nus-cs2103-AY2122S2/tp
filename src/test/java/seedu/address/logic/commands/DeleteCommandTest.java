package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.lineup.Lineup;
import seedu.address.model.lineup.LineupName;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;


import seedu.address.testutil.LineupBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalPersons;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {
    private static final Name INVALID_NAME = new Name("RAcHel");
    private static final LineupName INVALID_LINEUP_NAME = new LineupName("Not a lineup");

    private static final Person VALID_PERSON = TypicalPersons.BENSON;
    private static final Lineup VALID_LINEUP = new LineupBuilder().build();
    private static final Lineup ANOTHER_VALID_LINEUP = new Lineup(new LineupName("Dummy Lineup"));
    private static final LineupName VALID_LINEUP_NAME = VALID_LINEUP.getLineupName();
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        // to be removed after AddressBook can store lineups
        model.addLineup(VALID_LINEUP);
        model.putPersonIntoLineup(VALID_PERSON, VALID_LINEUP);
        model.addLineup(ANOTHER_VALID_LINEUP);
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addLineup(VALID_LINEUP);
        expectedModel.putPersonIntoLineup(VALID_PERSON, VALID_LINEUP);
        expectedModel.addLineup(ANOTHER_VALID_LINEUP);
    }


    @Test
    public void execute_deletePerson_success() throws CommandException {
        expectedModel.deletePerson(expectedModel.getPerson(VALID_PERSON.getName()));
        CommandResult commandResult = new DeleteCommand(VALID_PERSON.getName()).execute(model);
        assertEquals(commandResult.getFeedbackToUser(),
                String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, VALID_PERSON.getName()));
    }

    @Test
    public void execute_deleteLineup_success() throws CommandException {
        expectedModel.deleteLineup(expectedModel.getLineup(VALID_LINEUP_NAME));
        Command command = new DeleteCommand(VALID_LINEUP_NAME);
        CommandResult commandResult = command.execute(model);
        assertEquals(commandResult.getFeedbackToUser(),
                String.format(DeleteCommand.MESSAGE_DELETE_LINEUP_SUCCESS, VALID_LINEUP_NAME));
    }

    @Test
    public void execute_deletePersonFromLineup_success() throws CommandException {
        expectedModel.deletePersonFromLineup(model.getPerson(VALID_PERSON.getName()),
                model.getLineup(VALID_LINEUP_NAME));
        CommandResult commandResult = new DeleteCommand(VALID_PERSON.getName(), VALID_LINEUP_NAME).execute(model);
        assertEquals(commandResult.getFeedbackToUser(),
                String.format(DeleteCommand.MESSAGE_DELETE_PERSON_FROM_LINEUP_SUCCESS,
                        VALID_LINEUP_NAME, VALID_PERSON.getName()));
    }

    @Test
    public void getExecute_invalidDeletePerson() {
        Command command = new DeleteCommand(INVALID_NAME);
        try {
            command.execute(model);
        } catch (CommandException e) {
            assertEquals(DeleteCommand.MESSAGE_NO_SUCH_PERSON, e.getMessage());
        }
    }

    @Test
    public void getExecute_invalidDeleteLineup() {
        Command command = new DeleteCommand(INVALID_LINEUP_NAME);
        try {
            command.execute(model);
        } catch (CommandException e) {
            assertEquals(DeleteCommand.MESSAGE_NO_SUCH_LINEUP, e.getMessage());
        }
    }

    @Test
    public void getExecute_invalidDeletePlayerFromLineup() {
        Command command = new DeleteCommand(VALID_PERSON.getName(), ANOTHER_VALID_LINEUP.getLineupName());
        try {
            command.execute(model);
        } catch (CommandException e) {
            assertEquals(DeleteCommand.MESSAGE_PERSON_NOT_IN_LINEUP, e.getMessage());
        }
    }

}
