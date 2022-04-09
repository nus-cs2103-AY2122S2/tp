package manageezpz.logic.commands;

import static manageezpz.commons.core.Messages.MESSAGE_DUPLICATE_TASK;
import static manageezpz.logic.commands.CommandTestUtil.VALID_DATE;
import static manageezpz.logic.commands.CommandTestUtil.VALID_TIME;
import static manageezpz.testutil.TypicalTasks.FYP_REPORT;
import static manageezpz.testutil.TypicalTasks.getTypicalAddressBookTasks;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import manageezpz.logic.commands.exceptions.CommandException;
import manageezpz.model.Model;
import manageezpz.model.ModelManager;
import manageezpz.model.UserPrefs;
import manageezpz.model.task.Date;
import manageezpz.model.task.Deadline;
import manageezpz.model.task.Description;
import manageezpz.model.task.Time;
import manageezpz.testutil.Assert;

public class AddDeadlineTaskCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBookTasks(), new UserPrefs());
    }

    @Test
    public void addDeadline_success() throws CommandException {
        Deadline newDeadlineTask = new Deadline(new Description("new description"), new Date(VALID_DATE),
                new Time(VALID_TIME));
        AddDeadlineTaskCommand addDeadlineTaskCommand = new AddDeadlineTaskCommand(newDeadlineTask);
        String expectedMessage = String.format(AddDeadlineTaskCommand.MESSAGE_SUCCESS, newDeadlineTask);
        CommandResult commandResult = new CommandResult(expectedMessage);
        assertEquals(commandResult, addDeadlineTaskCommand.execute(model));
    }

    @Test
    public void addDeadlineTaskCommandConstructor_duplicateTask_throwsCommandException() {
        AddDeadlineTaskCommand addDeadlineTaskCommand = new AddDeadlineTaskCommand(FYP_REPORT);
        String expectedMessage = String.format(MESSAGE_DUPLICATE_TASK,
                FYP_REPORT.getDescription()) + AddDeadlineTaskCommand.MESSAGE_USAGE;
        Assert.assertThrows(CommandException.class, expectedMessage, () -> addDeadlineTaskCommand.execute(model));
    }
}
