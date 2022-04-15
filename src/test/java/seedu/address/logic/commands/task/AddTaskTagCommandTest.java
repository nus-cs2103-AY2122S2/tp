package seedu.address.logic.commands.task;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.TASK_FIRST_INDEX;
import static seedu.address.testutil.TypicalIndexes.TASK_SECOND_INDEX;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskBook;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.StrategyBoard;
import seedu.address.model.TaskBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

class AddTaskTagCommandTest {
    // Test tags
    // Because PersonBuilder#withTags takes String ... instead of Set<Tag>
    private static final String TAG1 = "TAG1";

    // Test model
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskBook(),
            new StrategyBoard(), new UserPrefs());

    @Test
    void execute_addTagCommandUnfilteredList_success() {
        Task firstTask = model.getFilteredTaskList().get(TASK_FIRST_INDEX.getZeroBased());

        // Adding 1 more tag to the firstTask
        Set<Tag> firstTaskTags = new HashSet<>(firstTask.getTags()); // Set<Tag> copy of Shareholders Meeting (1st task)
        firstTaskTags.add(new Tag(TAG1));

        // Convert Set<Tag> to array for TaskBuilder#withTags
        String[] firstTaskTagsStringArray = firstTaskTags
                .stream()
                .map(x -> x.tagName)
                .toArray(String[]::new);

        // Manually building the edited task
        Task editedTask = new TaskBuilder(firstTask).withTags(firstTaskTagsStringArray).build();

        AddTaskTagCommand addTagToTaskCommand = new AddTaskTagCommand(TASK_FIRST_INDEX, TAG1);

        String expectedMessage = String.format(AddTaskTagCommand.MESSAGE_ADD_TAG_SUCCESS, TAG1);

        // Manually building the expected model
        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()),
                new TaskBook(model.getTaskBook()),
                new StrategyBoard(),
                new UserPrefs());
        expectedModel.setTask(firstTask, editedTask);

        assertCommandSuccess(addTagToTaskCommand, model, expectedMessage, expectedModel);

    }

    @Test
    public void execute_invalidTagIndexFilteredList_failure() {
        showTaskAtIndex(model, TASK_FIRST_INDEX);
        Index outOfBoundIndex = TASK_SECOND_INDEX;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTaskBook().getTaskList().size());

        AddTaskTagCommand addTagToTaskCommand = new AddTaskTagCommand(outOfBoundIndex, TAG1);

        assertCommandFailure(addTagToTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final AddTaskTagCommand command = new AddTaskTagCommand(INDEX_FIRST_PERSON, TAG1);

        // If they are the same objects, they are equal
        assertTrue(command.equals(command));

    }
}
