package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_START;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FRIEND_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.event.Event;

/**
 * Finds and lists all events in Amigos which match all the predicates in the given list.
 */
public class FindEventCommand extends Command {

    public static final String COMMAND_WORD = "findevent";
    public static final String COMMAND_ALIAS = "fe";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " / " + COMMAND_ALIAS
            + ": Finds all events whose fields match all of "
            + "the specified search terms (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "EVENT_NAME_SUBSTRING] "
            + "[" + PREFIX_DATE_START + "DATE_START] "
            + "[" + PREFIX_DATE_END + "DATE_END] "
            + "[" + PREFIX_FRIEND_NAME + "FRIEND_NAME_SUBSTRING]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "dinner "
            + PREFIX_DATE_START + "15-08-2022 "
            + PREFIX_FRIEND_NAME + "john "
            + PREFIX_FRIEND_NAME + "joe ";

    private final List<Predicate<Event>> predicates;

    public FindEventCommand(List<Predicate<Event>> predicates) {
        this.predicates = predicates;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEventList(this::isAllPredicatesPassed);
        return new CommandResult(
                String.format(Messages.MESSAGE_EVENTS_LISTED_OVERVIEW, model.getFilteredEventList().size()),
                false, false, true);
    }

    private boolean isAllPredicatesPassed(Event event) {
        for (Predicate<Event> predicate : predicates) {
            if (!predicate.test(event)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindEventCommand // instanceof handles nulls
                && predicates.equals(((FindEventCommand) other).predicates)); // state check
    }
}
