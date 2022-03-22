package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LABSTATUS;

import java.util.List;

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

/**
 * Edits the LabStatus of a Lab of a Student in the TAddressBook.
 */
public class EditLabStatusCommand extends Command {

    public static final String COMMAND_WORD = "labstat";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the lab status of a specific lab of the student "
            + "identified by the index number used in the displayed student list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_LAB + "LAB_NUMBER "
            + PREFIX_LABSTATUS + "LAB_STATUS (u/s/g)\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_LAB + "1 " + PREFIX_LABSTATUS + "s";

    // Edited status of Lab {LAB_NUMBER} for {STUDENT_NAME} to {NEW_STATUS}
    public static final String MESSAGE_EDIT_LAB_STATUS_SUCCESS = "Edited status of Lab %1$s for %2$s to %3$s";
    public static final String MESSAGE_INVALID_STATUS_CHANGE = "New lab status provided is identical to previous";
    // add this to commons.core.Messages if it also applies to other commands
    public static final String MESSAGE_INVALID_LAB_NUMBER = "The lab number provided is invalid";

    private final Index index;
    private final int labNumber;
    private final LabStatus newStatus;

    /**
     * Creates an EditLabStatusCommand to edit the status of a Student's Lab
     *
     * @param index The index of the Student (according to the displayed list)
     * @param labNumber The Lab number to edit
     * @param newStatus The new LabStatus of the Lab
     */
    public EditLabStatusCommand(Index index, int labNumber, LabStatus newStatus) {
        requireAllNonNull(index, labNumber, newStatus);

        this.index = index;
        this.labNumber = labNumber;
        this.newStatus = newStatus;
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

        try {
            listToEdit.setLab(listToEdit.getLab(labNumber),
                    new Lab(String.valueOf(labNumber)).of(newStatus.name()));
        } catch (LabNotFoundException e) {
            throw new CommandException(MESSAGE_INVALID_LAB_NUMBER);
        } catch (DuplicateLabException e) {
            throw new CommandException(MESSAGE_INVALID_STATUS_CHANGE);
        }

        return new CommandResult(
                String.format(MESSAGE_EDIT_LAB_STATUS_SUCCESS, labNumber, studentToEdit.getName(), newStatus.name()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditLabStatusCommand)) {
            return false;
        }

        // state check
        EditLabStatusCommand e = (EditLabStatusCommand) other;
        return index.equals(e.index) && labNumber == e.labNumber && newStatus.equals(e.newStatus);
    }
}
