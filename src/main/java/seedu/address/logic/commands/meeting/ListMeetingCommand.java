package seedu.address.logic.commands.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MEETINGS;
import static seedu.address.model.Model.PREDICATE_SHOW_UPCOMING_MEETINGS;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.meeting.Meeting;


/**
 * Lists all clients in the address book to the user.
 */
public class ListMeetingCommand extends Command {

    public static final String COMMAND_WORD = "meetings";
    public static final String MESSAGE_SUCCESS = "Listed upcoming meetings";
    public static final String MESSAGE_SUCCESS_ALL = "Listed all meetings";
    public static final String MESSAGE_SUCCESS_WITH_CLIENT = "Listed upcoming meetings with:\n%s";
    public static final String MESSAGE_SUCCESS_WITH_CLIENT_ALL = "Listed all meetings with:\n%s";
    private final Index index;
    private final boolean isShowAll;

    /**
     * Command to view all meetings with the client specified by the index.
     * @param index of the client to view meetings with.
     * @param isShowAll determines whether to show all meetings or only upcoming meetings.
     */
    public ListMeetingCommand(Index index, boolean isShowAll) {
        this.index = index;
        this.isShowAll = isShowAll;
    }

    /**
     * Helper functions to generate the success message based on the provided inputs.
     * @param isShowAll determines whether to show all meetings or only upcoming meetings.
     * @param client details
     */
    private String generateSuccessMessage(boolean isShowAll, Client client) {
        if (client != null) {
            if (isShowAll) {
                return String.format(MESSAGE_SUCCESS_WITH_CLIENT_ALL, client);
            } else {
                return String.format(MESSAGE_SUCCESS_WITH_CLIENT, client);
            }
        } else {
            if (isShowAll) {
                return MESSAGE_SUCCESS_ALL;
            } else {
                return MESSAGE_SUCCESS;
            }
        }
    }

    /**
     * Helper functions to generate the predicate based on the provided inputs.
     * @param isShowAll determines whether to show all meetings or only upcoming meetings.
     * @param client details
     */
    private Predicate<Meeting> generatePredicate(boolean isShowAll, Client client) {
        if (client != null) {
            if (isShowAll) {
                return meeting -> meeting.getClient().equals(client);
            } else {
                return meeting -> meeting.getClient().equals(client) && meeting.isUpcoming();
            }
        } else {
            if (isShowAll) {
                return PREDICATE_SHOW_ALL_MEETINGS;
            } else {
                return PREDICATE_SHOW_UPCOMING_MEETINGS;
            }
        }

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownList = model.getClientList();

        Client client = null;
        if (index != null) {
            if (index.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
            }

            client = model.getClientList().get(index.getZeroBased());
        }

        model.updateFilteredMeetingList(generatePredicate(isShowAll, client), isShowAll);

        return new CommandResult(generateSuccessMessage(isShowAll, client), false, false, false, true,
                false, null, null);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ListMeetingCommand)) {
            return false;
        }

        // state check
        ListMeetingCommand e = (ListMeetingCommand) other;

        if (index == null && e.index == null) {
            return isShowAll == e.isShowAll;
        } else if (index != null && e.index != null) {
            return index.equals(e.index) && isShowAll == e.isShowAll;
        } else {
            return false;
        }
    }
}
