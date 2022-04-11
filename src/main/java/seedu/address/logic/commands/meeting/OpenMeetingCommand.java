package seedu.address.logic.commands.meeting;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.UrlOpener;
import seedu.address.model.meeting.UrlOpenerManager;
import seedu.address.model.meeting.exceptions.UnsupportedDesktopException;

/**
 * Opens a meeting's URL on the device's default browser. <br>
 * The {@code execute} method of this class creates a {@code UrlOpenerManager} which checks the running environment
 * for desktop functionality support and passes it to {@code executeWithUrlOpener}.
 */
public class OpenMeetingCommand extends Command {
    public static final String COMMAND_WORD = "open";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Opens the URL of the meeting identified by the index number used in the "
            + "displayed meeting list in the system default browser.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Opened Meeting URL: %1$s";
    public static final String MESSAGE_INVALID_URL = "URL provided cannot be opened!";
    public static final String MESSAGE_SYSTEM_PERMISSION_DENIED = "Permission denied by user system!";
    public static final String MESSAGE_SYSTEM_BROWSER_ERROR = "Unable to launch default system browser!";

    private final Index targetIndex;

    public OpenMeetingCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        final UrlOpener urlOpener;
        try {
            urlOpener = new UrlOpenerManager();
        } catch (UnsupportedDesktopException ex) {
            throw new CommandException(ex.getMessage(), ex);
        }

        return executeWithUrlOpener(model, urlOpener);
    }

    /**
     * The logic of {@code OpenMeetingCommand::execute} is abstracted into this method to isolate the command
     * logic from the running environment for testing purposes. This method should ideally be private/protected
     * but is made public for testing purposes.
     */
    protected CommandResult executeWithUrlOpener(Model model, UrlOpener urlOpener) throws CommandException {
        requireNonNull(model);
        final List<Meeting> lastShownList = model.getFilteredMeetingList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }

        final Meeting meetingToOpen = lastShownList.get(targetIndex.getZeroBased());
        final URL urlToOpen = meetingToOpen.getUrl().meetingUrl;
        requireNonNull(urlToOpen);

        try {
            urlOpener.open(urlToOpen);
        } catch (URISyntaxException ex) {
            throw new CommandException(MESSAGE_INVALID_URL);
        } catch (IOException ex) {
            throw new CommandException(MESSAGE_SYSTEM_BROWSER_ERROR);
        } catch (SecurityException ex) {
            throw new CommandException(MESSAGE_SYSTEM_PERMISSION_DENIED);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, meetingToOpen));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OpenMeetingCommand // instanceof handles nulls
                && targetIndex.equals(((OpenMeetingCommand) other).targetIndex));
    }
}
