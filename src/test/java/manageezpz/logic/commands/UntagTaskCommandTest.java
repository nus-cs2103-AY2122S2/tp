package manageezpz.logic.commands;

import static manageezpz.logic.commands.UntagTaskCommand.MESSAGE_PERSON_NOT_TAGGED_TO_TASK;
import static manageezpz.logic.commands.UntagTaskCommand.MESSAGE_UNTAG_TASK_SUCCESS;
import static manageezpz.logic.commands.UntagTaskCommand.MESSAGE_USAGE;
import static manageezpz.testutil.Assert.assertThrows;
import static manageezpz.testutil.TypicalPersons.BOB;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import manageezpz.commons.core.index.Index;
import manageezpz.logic.commands.exceptions.CommandException;
import manageezpz.logic.parser.ParserUtil;
import manageezpz.logic.parser.exceptions.ParseException;
import manageezpz.model.Model;
import manageezpz.model.ModelManager;
import manageezpz.model.UserPrefs;
import manageezpz.model.task.Task;
import manageezpz.model.task.Todo;
import manageezpz.testutil.TodoBuilder;
import manageezpz.testutil.TypicalTasks;

public class UntagTaskCommandTest {

    private final Model model = new ModelManager(TypicalTasks.getTypicalAddressBookTasks(),
            new UserPrefs());

    @Test
    public void untagTaskCommand_success_assertEquals() throws CommandException, ParseException {

        // Create a Todo task.
        Todo sameTodo = new TodoBuilder().withDescription("Weekly Quiz").build();

        // Populate model's UniquePersonList
        model.addPerson(BOB);

        // Add Bob to created Todo Task.
        sameTodo.addAssignees(BOB);

        // Get the Same task in model's UniqueTaskList.
        Index index = ParserUtil.parseIndex("1");
        Task task = model.getAddressBook().getTaskList().get(index.getZeroBased());

        // Tag Employee to same Task.
        model.tagEmployeeToTask(task, BOB);

        // Simulate untag task for both, compare results.
        if (task.getAssignees().contains(BOB)) {
            UntagTaskCommand untagTaskCommand = new UntagTaskCommand(index, BOB.getName().toString());
            String expectedMessage = String.format(MESSAGE_UNTAG_TASK_SUCCESS,
                    BOB.getName().toString()) + sameTodo;
            CommandResult commandResult = new CommandResult(expectedMessage);
            assertEquals(commandResult, untagTaskCommand.execute(model));
        }
    }

    @Test
    public void untagTaskCommand_notTagged_throwsCommandException() throws ParseException {

        // Populate model's UniquePersonList
        model.addPerson(BOB);

        // Create an untagTaskCommand with valid Index, and valid Name.
        Index index = ParserUtil.parseIndex("1");
        UntagTaskCommand untagTaskCommand = new UntagTaskCommand(index, BOB.getName().toString());
        Task task = model.getAddressBook().getTaskList().get(index.getZeroBased());

        // If task is already assigned with Person Bob, untag first.
        if (task.getAssignees().contains(BOB)) {
            task.removeAssigned(BOB);
        }

        // Simulate untag task
        String expectedMessage = String.format(MESSAGE_PERSON_NOT_TAGGED_TO_TASK,
                BOB.getName().toString())
                + task
                + "\n\n"
                + MESSAGE_USAGE;
        assertThrows(CommandException.class, expectedMessage, () -> untagTaskCommand.execute(model));
    }

}
