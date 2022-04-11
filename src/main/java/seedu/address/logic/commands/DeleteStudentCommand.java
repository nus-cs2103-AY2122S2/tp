package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.misc.InfoPanelTypes;
import seedu.address.model.Model;
import seedu.address.model.student.Student;

/**
 * Deletes a student identified using it's displayed index from the student book.
 */
public class DeleteStudentCommand extends Command {

    public static final String COMMAND_WORD = "rmstudent";
    public static final String SHORTENED_COMMAND_WORD = "rms";
    public static final String COMMAND_DESCRIPTION = "Delete a student";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the student identified by the index number used in the displayed student list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_STUDENT_SUCCESS = "Deleted Student: %1$s";

    private final Index targetIndex;

    public DeleteStudentCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        boolean isIndexOutOfBounds = targetIndex.getZeroBased() >= lastShownList.size();
        if (isIndexOutOfBounds) {
            throw new CommandException(Messages.MESSAGE_OOB_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteStudent(studentToDelete);

        boolean shouldClearInfoPanel = model.shouldClearStudentInfoPanelOnDelete(studentToDelete);
        String commandResultMessage = String.format(MESSAGE_DELETE_STUDENT_SUCCESS, studentToDelete);
        if (shouldClearInfoPanel) {
            return new CommandResult(commandResultMessage, InfoPanelTypes.EMPTY);
        }
        return new CommandResult(commandResultMessage, InfoPanelTypes.REFRESH);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteStudentCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteStudentCommand) other).targetIndex)); // state check
    }
}
