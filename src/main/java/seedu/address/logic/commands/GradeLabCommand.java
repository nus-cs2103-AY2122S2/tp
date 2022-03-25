package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_LAB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LABMARK;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lab.Lab;
import seedu.address.model.lab.LabList;
import seedu.address.model.lab.LabMark;
import seedu.address.model.lab.LabStatus;
import seedu.address.model.student.Student;
import seedu.address.model.student.exceptions.DuplicateLabException;
import seedu.address.model.student.exceptions.LabNotFoundException;

public class GradeLabCommand extends EditLabCommand {

    public static final String COMMAND_WORD = "labgrad";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Changes the status of the specified lab of the "
            + "student identified by the index number used in the displayed student list to 'GRADED' "
            + "and initializes the marks to the marks specified.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_LAB + "LAB_NUMBER "
            + PREFIX_LABMARK + "LAB_MARKS\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_LAB + "1" + PREFIX_LABMARK + "10";

    public static final String MESSAGE_GRADE_LAB_SUCCESS = "Graded Lab %1$s for %2$s. Marks given: %$3s";
    public static final String MESSAGE_LAB_ALREADY_GRADED = "Lab is already graded";

    public GradeLabCommand(Index index, int labNumber, LabMark labMark) {
        super(index, labNumber, labMark);
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

        if (labToEdit.labStatus == LabStatus.GRADED) {
            throw new CommandException(MESSAGE_LAB_ALREADY_GRADED);
        }

        try {
            listToEdit.setLab(labToEdit, new Lab(String.valueOf(labNumber)).of(newStatus.name()));
        } catch (LabNotFoundException e) {
            throw new CommandException(MESSAGE_INVALID_LAB);
        } catch (DuplicateLabException e) {
            throw new CommandException(MESSAGE_IDENTICAL_STATUS);
        }

        return new CommandResult(
                String.format(MESSAGE_GRADE_LAB_SUCCESS, labNumber, studentToEdit.getName(), newMark));
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
        GradeLabCommand g = (GradeLabCommand) other;
        return index.equals(g.index)
                && labNumber == g.labNumber
                && newMark.equals(g.newMark);
    }
}
