package seedu.address.logic.commands.meeting;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all archived meetings in the LinkyTime meeting list to the user.
 */
public class ArchiveMeetingCommand extends Command {
    public static final String COMMAND_WORD = "archive";
    public static final String MESSAGE_SUCCESS = "Listed all archived meetings";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.showCompletedMeetings(true);
        return new CommandResult(MESSAGE_SUCCESS, CommandResult.MeetingListStatusResult.ARCHIVE);
    }
}
