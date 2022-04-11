package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.misc.InfoPanelTypes;
import seedu.address.model.Model;
import seedu.address.model.lesson.Lesson;

/**
 * Deletes a lesson identified using it's displayed index from the lesson book.
 */
public class DeleteLessonCommand extends Command {

    public static final String COMMAND_WORD = "rmlesson";
    public static final String SHORTENED_COMMAND_WORD = "rml";
    public static final String COMMAND_DESCRIPTION = "Delete a lesson";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the lesson identified by the index number used in the displayed lesson list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_LESSON_SUCCESS = "Deleted Lesson: %1$s";

    private final Index targetIndex;

    public DeleteLessonCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Lesson> lastShownList = model.getFilteredLessonList();

        boolean isIndexOutOfBounds = targetIndex.getZeroBased() >= lastShownList.size();
        if (isIndexOutOfBounds) {
            throw new CommandException(Messages.MESSAGE_OOB_LESSON_DISPLAYED_INDEX);
        }

        Lesson lessonToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteLesson(lessonToDelete);

        boolean shouldClearInfoPanel = model.shouldClearLessonInfoPanelOnDelete(lessonToDelete);
        String commandResultMessage = String.format(MESSAGE_DELETE_LESSON_SUCCESS, lessonToDelete);
        if (shouldClearInfoPanel) {
            return new CommandResult(commandResultMessage, InfoPanelTypes.EMPTY);
        }
        return new CommandResult(commandResultMessage, InfoPanelTypes.REFRESH);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteLessonCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteLessonCommand) other).targetIndex)); // state check
    }
}
