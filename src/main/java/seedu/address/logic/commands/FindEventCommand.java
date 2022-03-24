package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FRIEND_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.event.EventFilterPredicate;

/**
 * Finds and lists all events in address book which match the given predicate. Matching of Strings
 * is case-insensitive.
 */
public class FindEventCommand extends Command {

    public static final String COMMAND_WORD = "findevent";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all events whose fields contain all of "
            + "the specified search terms (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "EVENT_NAME_SUBSTRING] "
            + "[" + PREFIX_DATE + "DATE] "
            + "[" + PREFIX_FRIEND_NAME + "FRIEND_NAME_SUBSTRING]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "dinner "
            + PREFIX_DATE + "15-08-2022 "
            + PREFIX_FRIEND_NAME + "john "
            + PREFIX_FRIEND_NAME + "joe ";

    private final EventFilterPredicate predicate;

    public FindEventCommand(EventFilterPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEventList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_EVENTS_LISTED_OVERVIEW, model.getFilteredEventList().size())
                ,false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindEventCommand // instanceof handles nulls
                && predicate.equals(((FindEventCommand) other).predicate)); // state check
    }
}
