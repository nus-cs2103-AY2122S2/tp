package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_EVENT_FRIENDS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FRIEND_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;

/**
 * Adds an event to Amigos.
 */
public class AddEventCommand extends Command {

    public static final String COMMAND_WORD = "addevent";
    public static final String COMMAND_ALIAS = "ae";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " / " + COMMAND_ALIAS + ": Adds an event to Amigos. "
            + "Parameters: "
            + PREFIX_NAME + "EVENT_NAME "
            + PREFIX_DATETIME + "DATE_TIME "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_FRIEND_NAME + "FRIEND_NAME]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John's Birthday "
            + PREFIX_DATETIME + "15-08-2021 1700 "
            + PREFIX_DESCRIPTION + "Remember to get a present! "
            + PREFIX_FRIEND_NAME + "John Low "
            + PREFIX_FRIEND_NAME + "Amy Lim";

    public static final String MESSAGE_SUCCESS = "New event added: %1$s";
    public static final String MESSAGE_PAST_EVENT_WARNING =
            "Warning: You have added a past event. Use 'listevents -a' if it is not visible.";
    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists!";

    private final Event toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Event}
     */
    public AddEventCommand(Event event) {
        requireNonNull(event);
        toAdd = event;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasEvent(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }
        if (!model.areEventFriendsValid(toAdd)) {
            throw new CommandException(MESSAGE_INVALID_EVENT_FRIENDS);
        }

        model.addEvent(toAdd);

        String output = String.format(MESSAGE_SUCCESS, toAdd);
        if (toAdd.isBeforeNow()) {
            output += "\n" + MESSAGE_PAST_EVENT_WARNING;
        }

        return new CommandResult(output, false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddEventCommand // instanceof handles nulls
                && toAdd.equals(((AddEventCommand) other).toAdd));
    }
}
