package manageezpz.logic.commands;

import static manageezpz.logic.commands.TagTaskPriorityCommand.MESSAGE_TAG_PRIORITY_SUCCESS;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import manageezpz.commons.core.index.Index;
import manageezpz.logic.commands.exceptions.CommandException;
import manageezpz.logic.parser.ParserUtil;
import manageezpz.logic.parser.exceptions.ParseException;
import manageezpz.model.Model;
import manageezpz.model.ModelManager;
import manageezpz.model.UserPrefs;
import manageezpz.model.task.Priority;
import manageezpz.model.task.Todo;
import manageezpz.testutil.TodoBuilder;
import manageezpz.testutil.TypicalTasks;

public class TagPriorityTaskCommandTest {

    private final Model model = new ModelManager(TypicalTasks.getTypicalAddressBookTasks(),
            new UserPrefs());

    @Test
    public void tagTaskPriorityCommand_success_assertEquals() throws CommandException, ParseException {
        // Create a similar Todo Task
        Todo sameTodo = new TodoBuilder().withDescription("Weekly Quiz").build();

        // Get the Index of the same Todo Task
        Index index = ParserUtil.parseIndex("1");

        String priority = "HIGH";

        // Create TagTaskPriorityCommand
        TagTaskPriorityCommand tagTaskPriorityCommand = new TagTaskPriorityCommand(index, Priority.valueOf(priority));

        // Expected Message if the created task were to be tagged with Priority "HIGH"
        String expectedMessage = String.format(MESSAGE_TAG_PRIORITY_SUCCESS, priority) + sameTodo;
        CommandResult commandResult = new CommandResult(expectedMessage);

        // Compare Created Task Tagged Priority and actual model's task.
        assertEquals(commandResult, tagTaskPriorityCommand.execute(model));
    }
}
