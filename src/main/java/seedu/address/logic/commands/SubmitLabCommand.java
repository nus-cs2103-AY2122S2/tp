package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_LAB;

import seedu.address.commons.core.index.Index;
import seedu.address.model.lab.LabMark;
import seedu.address.model.lab.LabStatus;

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
    public boolean isValidCommand() {
        return newStatus == LabStatus.SUBMITTED && newMark.isEmpty();
    }

    @Override
    public String getInvalidCommandMessage() {
        return MESSAGE_LAB_NOT_UNSUBMITTED;
    }

    @Override
    public String getExecutionSuccessMessage() {
        return MESSAGE_SUBMIT_LAB_SUCCESS;
    }

}
