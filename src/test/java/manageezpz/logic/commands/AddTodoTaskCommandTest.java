package manageezpz.logic.commands;

import static manageezpz.commons.core.Messages.MESSAGE_DUPLICATE_TASK;
import static manageezpz.testutil.Assert.assertThrows;
import static manageezpz.testutil.TypicalTasks.WEEKLY_QUIZ;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import manageezpz.logic.commands.exceptions.CommandException;
import manageezpz.model.Model;
import manageezpz.model.ModelManager;
import manageezpz.model.UserPrefs;
import manageezpz.model.task.Description;
import manageezpz.model.task.Todo;
import manageezpz.testutil.TypicalTasks;

public class AddTodoTaskCommandTest {

    private final Model model = new ModelManager(TypicalTasks.getTypicalAddressBookTasks(),
            new UserPrefs());

    @Test
    public void addTodoTaskCommand_success_assertEquals() throws CommandException {
        Description description = new Description("Go on a holiday!");
        Todo newTodo = new Todo(description);
        AddTodoTaskCommand addTodoTaskCommand = new AddTodoTaskCommand(newTodo);
        String expectedMessage = String.format(AddTodoTaskCommand.MESSAGE_SUCCESS, newTodo);
        CommandResult commandResult = new CommandResult(expectedMessage);
        assertEquals(commandResult, addTodoTaskCommand.execute(model));
    }

    @Test
    public void addTodoTaskCommandConstructor_duplicateTask_throwsCommandException() {
        AddTodoTaskCommand addTodoTaskCommand = new AddTodoTaskCommand(WEEKLY_QUIZ);
        String expectedMessage = String.format(MESSAGE_DUPLICATE_TASK,
                WEEKLY_QUIZ.getDescription()) + AddTodoTaskCommand.MESSAGE_USAGE;
        assertThrows(CommandException.class, expectedMessage, () -> addTodoTaskCommand.execute(model));
    }

}
