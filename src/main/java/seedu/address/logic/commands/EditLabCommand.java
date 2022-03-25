package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_LAB;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LABMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LABSTATUS;

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
import seedu.address.model.student.exceptions.InvalidLabStatusException;
import seedu.address.model.student.exceptions.LabNotFoundException;

/**
 * Edits the LabStatus of a Lab of a Student in the TAddressBook.
 */
public class EditLabCommand extends Command {

    public static final String COMMAND_WORD = "labedit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the specified lab of the "
            + "student identified by the index number used in the displayed student list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_LAB + "LAB_NUMBER "
            + "[" + PREFIX_LABSTATUS + "LAB_STATUS (u/s/g)] "
            + "[" + PREFIX_LABMARK + "LAB_SCORE]\n"
            + "Example (changing status): " + COMMAND_WORD + " 1 " + PREFIX_LAB + "1 " + PREFIX_LABSTATUS + "s\n"
            + "Example (changing marks): " + COMMAND_WORD + " 1 " + PREFIX_LAB + "2 " + PREFIX_LABMARK + "10";

    // Edited Lab {LAB_NUMBER} of {STUDENT_NAME}
    public static final String MESSAGE_EDIT_LAB_SUCCESS = "Edited Lab %1$s of %2$s";

    public static final String MESSAGE_IDENTICAL_LAB = "Edited lab would be identical to previous";
    public static final String MESSAGE_INVALID_COMBINATION = "Invalid combination of lab status and marks";

    protected final Index index;
    protected final int labNumber;
    protected final LabStatus newStatus;
    protected final LabMark newMark;

    /**
     * Creates an EditLabCommand to edit the status of a Student's Lab
     *
     * @param index The index of the Student (according to the displayed list)
     * @param labNumber The Lab number to edit
     * @param newStatus The new LabStatus of the Lab
     * @param newMark The new LabMark of the Lab
     */
    public EditLabCommand(Index index, int labNumber, LabStatus newStatus, LabMark newMark)
            throws IllegalArgumentException {
        requireAllNonNull(index, labNumber, newStatus);
        this.index = index;
        this.labNumber = labNumber;
        this.newStatus = newStatus;
        this.newMark = newMark;
    }

    /**
     * Creates an EditLabCommand with the given status and uninitialized marks.
     */
    public EditLabCommand(Index index, int labNumber, LabStatus newStatus) {
        this(index, labNumber, newStatus, new LabMark());
    }

    /**
     * Creates an EditLabCommand with "GRADED" status and the given marks.
     */
    public EditLabCommand(Index index, int labNumber, LabMark newMark) {
        this(index, labNumber, LabStatus.GRADED, newMark);
    }

    /**
     * Returns true if the given EditLabCommand is a valid EditLabCommand.
     */
    public boolean isValidCommand() {
        if (newStatus == LabStatus.GRADED && newMark.isEmpty()) {
            return false;
        }
        if (newStatus != LabStatus.GRADED && !newMark.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * This is represented by a method so it can be overridden by subclasses.
     */
    public String getInvalidCommandMessage() {
        return MESSAGE_INVALID_COMBINATION;
    }

    public String getExecutionSuccessMessage() {
        return MESSAGE_EDIT_LAB_SUCCESS;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        if (!isValidCommand()) {
            throw new CommandException(getInvalidCommandMessage());
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        LabList listToEdit = studentToEdit.getLabs();

        try {
            Lab labToEdit = listToEdit.getLab(labNumber);
            if (newMark.isEmpty()) {
                listToEdit.setLab(labToEdit, labToEdit.editLabStatus(newStatus));
            } else {
                listToEdit.setLab(labToEdit, labToEdit.editLabMark(newMark));
            }
        } catch (LabNotFoundException e) {
            throw new CommandException(MESSAGE_INVALID_LAB);
        } catch (DuplicateLabException e) {
            throw new CommandException(MESSAGE_IDENTICAL_LAB);
        } catch (InvalidLabStatusException e) {
            throw new CommandException(e.getMessage());
        }

        return new CommandResult(
                String.format(getExecutionSuccessMessage(), labNumber, studentToEdit.getName()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditLabCommand)) {
            return false;
        }

        // state check
        EditLabCommand e = (EditLabCommand) other;
        return index.equals(e.index) && labNumber == e.labNumber && newStatus.equals(e.newStatus);
    }
}
