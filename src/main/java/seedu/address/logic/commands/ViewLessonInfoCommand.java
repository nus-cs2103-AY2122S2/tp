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
 * Views the details of a class identified using it's displayed index.
 */
public class ViewLessonInfoCommand extends Command {

    public static final String COMMAND_WORD = "lesson";
    public static final String SHORTENED_COMMAND_WORD = "";
    public static final String COMMAND_DESCRIPTION = "View a lesson";


    public static final String MESSAGE_USAGE = COMMAND_WORD + ": View details of a selected Lesson.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_VIEW_SUCCESS = "Viewing details of %1$s";

    private final Index targetIndex;

    public ViewLessonInfoCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Lesson> lastShownList = model.getFilteredLessonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
        }

        Lesson lessonToSelect = lastShownList.get(targetIndex.getZeroBased());
        model.setSelectedLesson(lessonToSelect);
        return new CommandResult(String.format(MESSAGE_VIEW_SUCCESS, lessonToSelect.getName()),
                true, InfoPanelTypes.LESSON, ViewTab.LESSON);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ViewLessonInfoCommand
                && targetIndex.equals(((ViewLessonInfoCommand) other).targetIndex));
    }
}
