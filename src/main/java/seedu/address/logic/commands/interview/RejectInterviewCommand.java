package seedu.address.logic.commands.interview;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.DataType;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.interview.Interview;

public class RejectInterviewCommand extends Command {
    public static final String COMMAND_WORD = "reject";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_REJECT_INTERVIEW_SUCCESS = "Rejected Interview: %1$s";

    private final Index targetIndex;

    public RejectInterviewCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Interview> lastShownList = model.getFilteredInterviewList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERVIEW_DISPLAYED_INDEX);
        }

        Interview interviewToReject = lastShownList.get(targetIndex.getZeroBased());
        model.rejectInterview(interviewToReject);
        return new CommandResult(String.format(MESSAGE_REJECT_INTERVIEW_SUCCESS, interviewToReject),
                getCommandDataType());
    }

    @Override
    public DataType getCommandDataType() {
        return DataType.INTERVIEW;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RejectInterviewCommand // instanceof handles nulls
                && targetIndex.equals(((RejectInterviewCommand) other).targetIndex)); // state check
    }
}

