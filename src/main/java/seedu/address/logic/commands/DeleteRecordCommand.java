package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.record.Record;

public class DeleteRecordCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_DELETE_RECORD_SUCCESS = "Record deleted: %1$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes an record from the address book. "
            + "Parameters: "
            + "RECORD INDEX";
    private final Index targetIndex;

    /**
     * Creates an DeleteAppointmentCommand to delete the specified {@code Appointment}
     */
    public DeleteRecordCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Record> lastShownList = model.getFilteredRecordList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_RECORD_DISPLAYED_INDEX);
        }

        Record recordToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteRecord(recordToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_RECORD_SUCCESS, recordToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteRecordCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteRecordCommand) other).targetIndex));
    }


}
