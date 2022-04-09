package manageezpz.logic.commands;

import static manageezpz.logic.commands.TagTaskCommand.MESSAGE_PERSON_TAGGED_TO_TASK;
import static manageezpz.logic.commands.TagTaskCommand.MESSAGE_USAGE;
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




public class TagTaskCommandTest {

    private final Model model = new ModelManager(TypicalTasks.getTypicalAddressBookTasks(),
            new UserPrefs());

    @Test
    public void tagTaskCommand_success_assertEquals() throws CommandException, ParseException {

        Todo sameTodo = new TodoBuilder().withDescription("Weekly Quiz").build();
        model.addPerson(BOB);
        sameTodo.addAssignees(BOB);
        Index index = ParserUtil.parseIndex("1");
        Task task = model.getAddressBook().getTaskList().get(index.getZeroBased());
        if (task.getAssignees().contains(BOB)) {
            model.untagEmployeeFromTask(task, BOB);
        }
        TagTaskCommand tagTaskCommand = new TagTaskCommand(index, BOB.getName().toString());
        String expectedMessage = String.format(TagTaskCommand.MESSAGE_TAG_TASK_SUCCESS,
                BOB.getName().toString()) + sameTodo;
        CommandResult commandResult = new CommandResult(expectedMessage);
        assertEquals(commandResult, tagTaskCommand.execute(model));
    }

    @Test
    public void tagTaskCommand_duplicateTag_throwsCommandException() throws ParseException {
        // Add Bob to UniquePersonList
        model.addPerson(BOB);

        // Tag task with Bob.
        Index index = ParserUtil.parseIndex("1");
        Task task = model.getAddressBook().getTaskList().get(index.getZeroBased());
        model.tagEmployeeToTask(task, BOB);
        TagTaskCommand tagTaskCommand = new TagTaskCommand(index, BOB.getName().toString());

        String expectedMessage = String.format(MESSAGE_PERSON_TAGGED_TO_TASK,
                BOB.getName().toString())
                + task
                + "\n\n"
                + MESSAGE_USAGE;
        assertThrows(CommandException.class, expectedMessage, () -> tagTaskCommand.execute(model));
    }

}
