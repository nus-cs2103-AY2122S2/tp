package seedu.address.logic.commands;


import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.model.student.ViewDetails;

public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays a student's details. \n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_VIEW_SUCCESS = "Displayed details of %1$s";

    private final Index targetIndex;

    public ViewCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToView = lastShownList.get(targetIndex.getZeroBased());

        return new CommandResult(String.format(MESSAGE_VIEW_SUCCESS, studentToView.getName()),
                false, false, true,
                getStudentDetails(studentToView));
    }

    /**
     * Returns details of a student for ViewCommand
     *
     * @param studentToView student to view
     * @return {@code StudentDetail} of {@code studentToVIew}
     */
    private ViewDetails getStudentDetails(Student studentToView) {
        requireNonNull(studentToView);

        return studentToView.getViewDetails();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // shortcut circuit if same object
                || (other instanceof ViewCommand // instanceof handles nulls
                && targetIndex.equals(((ViewCommand) other).targetIndex)); // state check
    }

}
