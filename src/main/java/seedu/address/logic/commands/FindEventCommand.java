package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEARCH_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.entry.predicate.EventContainsKeywordsPredicate;

/**
 * Keyword matching is case insensitive
 * Find event command uses User input to specify what attributes the displayed events should have.
 * The relationship between attributes is "AND" while the relationship between keywords of the same attribute
 * is "OR".
 * For example, "finde n/ interview c/ shopsg dbsss" will return any events that has "interview" as its name
 * AND has ("shopsg" OR "dbsss") as its companyName.
 */
public class FindEventCommand extends Command {

    public static final String COMMAND_WORD = "finde";
    public static final String MESSAGE_NOT_QUERIED = "At least one field to find must be provided.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds events with the same details as the "
            + "given parameters\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_COMPANY + "COMPANY_NAME] "
            + "[" + PREFIX_START_DATE + "START DATE] "
            + "[" + PREFIX_END_DATE + "END DATE] "
            + "[" + PREFIX_TIME + "TIME] "
            + "[" + PREFIX_LOCATION + "LOCATION] "
            + "[" + PREFIX_SEARCH_TYPE + "SEARCH_TYPE] "
            + "[" + PREFIX_TAG + "TAG]... \n"

            + "Example: " + COMMAND_WORD + " "
            + PREFIX_COMPANY + "sgshop "
            + PREFIX_TIME + "zoom";

    private final EventContainsKeywordsPredicate predicate;

    /**
     * Constructs FindEventCommand object
     * @param predicate A predicate containing all Event's attributes queried by user
     */
    public FindEventCommand(EventContainsKeywordsPredicate predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.showEventList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_EVENTS_LISTED_OVERVIEW, model.getFilteredEventList().size()),
                false, false, false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindEventCommand // instanceof handles nulls
                && predicate.equals(((FindEventCommand) other).predicate)); // state check
    }
}
