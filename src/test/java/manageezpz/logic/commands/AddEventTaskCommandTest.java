package manageezpz.logic.commands;


import static manageezpz.commons.core.Messages.MESSAGE_DUPLICATE_TASK;
import static manageezpz.logic.commands.CommandTestUtil.VALID_DATE;
import static manageezpz.logic.commands.CommandTestUtil.VALID_END_TIME;
import static manageezpz.logic.commands.CommandTestUtil.VALID_START_TIME;
import static manageezpz.testutil.TypicalTasks.HOUSE_VISTING;
import static manageezpz.testutil.TypicalTasks.getTypicalAddressBookTasks;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import manageezpz.logic.commands.exceptions.CommandException;
import manageezpz.model.Model;
import manageezpz.model.ModelManager;
import manageezpz.model.UserPrefs;
import manageezpz.model.task.Date;
import manageezpz.model.task.Description;
import manageezpz.model.task.Event;
import manageezpz.model.task.Time;
import manageezpz.testutil.Assert;

public class AddEventTaskCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBookTasks(), new UserPrefs());
    }

    @Test
    public void addEventTask_success() throws CommandException {
        Event newEventTask = new Event(new Description("new description"), new Date(VALID_DATE),
                new Time(VALID_START_TIME), new Time(VALID_END_TIME));
        AddEventTaskCommand addEventTaskCommand = new AddEventTaskCommand(newEventTask);
        String expectedMessage = String.format(AddEventTaskCommand.MESSAGE_SUCCESS, newEventTask);
        CommandResult commandResult = new CommandResult(expectedMessage);
        assertEquals(commandResult, addEventTaskCommand.execute(model));
    }

    @Test
    public void addEventTaskCommandConstructor_duplicateTask_throwsCommandException() {
        AddEventTaskCommand addEventTaskCommand = new AddEventTaskCommand(HOUSE_VISTING);
        String expectedMessage = String.format(MESSAGE_DUPLICATE_TASK,
                HOUSE_VISTING.getDescription()) + AddEventTaskCommand.MESSAGE_USAGE;
        Assert.assertThrows(CommandException.class, expectedMessage, () -> addEventTaskCommand.execute(model));
    }
}
