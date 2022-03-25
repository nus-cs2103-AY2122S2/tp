package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_LAB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAB;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lab.Lab;
import seedu.address.model.lab.LabList;
import seedu.address.model.lab.LabStatus;
import seedu.address.model.student.Student;
import seedu.address.model.student.exceptions.DuplicateLabException;
import seedu.address.model.student.exceptions.LabNotFoundException;

import java.util.List;

public class SubmitLabCommand extends EditLabCommand {

    public static final String COMMAND_WORD = "labsub";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Changes the status of the specified lab of the "
            + "student identified by the index number used in the displayed student list to 'SUBMITTED'.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_LAB + "LAB_NUMBER\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_LAB + "1";

    public static final String MESSAGE_SUBMIT_LAB_SUCCESS = "Submitted Lab %1$s for %2$s";
    public static final String MESSAGE_LAB_NOT_UNSUBMITTED = "Lab must be UNSUBMITTED for this command to work";

    public SubmitLabCommand(Index index, int labNumber) {
        super(index, labNumber, LabStatus.SUBMITTED);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        LabList listToEdit = studentToEdit.getLabs();
        Lab labToEdit = listToEdit.getLab(labNumber);

        if (labToEdit.labStatus != LabStatus.UNSUBMITTED) {
            throw new CommandException(MESSAGE_LAB_NOT_UNSUBMITTED);
        }

        try {
            listToEdit.setLab(labToEdit, new Lab(String.valueOf(labNumber)).of(newStatus.name()));
        } catch (LabNotFoundException e) {
            throw new CommandException(MESSAGE_INVALID_LAB);
        } catch (DuplicateLabException e) {
            throw new CommandException(MESSAGE_IDENTICAL_STATUS);
        }

        return new CommandResult(
                String.format(MESSAGE_SUBMIT_LAB_SUCCESS, labNumber, studentToEdit.getName()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SubmitLabCommand)) {
            return false;
        }

        // state check
        SubmitLabCommand s = (SubmitLabCommand) other;
        return index.equals(s.index) && labNumber == s.labNumber;
    }
}
