package seedu.address.logic.commands.meeting;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all upcoming meetings in the LinkyTime meeting list to the user.
 */
public class ListMeetingCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_SUCCESS = "Listed all upcoming meetings";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.showCompletedMeetings(false);
        return new CommandResult(MESSAGE_SUCCESS, CommandResult.MeetingListStatusResult.UPCOMING);
    }
}
