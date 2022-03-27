package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.model.Model;
import seedu.address.model.entry.*;

import java.util.*;

/**
 * Keyword matching is case insensitive
 */
public class FindEventCommand extends Command {

    public static final String COMMAND_WORD = "finde";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all events whose companyName contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " shopee";

    public static final String MESSAGE_NOT_QUERIED = "At least one field to find must be provided.";

    private final ArgumentMultimap argumentMultimap;

    /**
     * Constructs FindEventCommand object
     * @param argumentMultimap A hashmap containing event prefixes and its value from user input
     */
    public FindEventCommand(ArgumentMultimap argumentMultimap) {
        requireNonNull(argumentMultimap);
        this.argumentMultimap = argumentMultimap;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String[] nameKeywords = argumentMultimap.getValue(PREFIX_NAME).orElse("").split("\\s+");
        String[] companyNameKeywords = argumentMultimap.getValue(PREFIX_COMPANY).orElse("").split("\\s+");
        String[] dateKeywords = argumentMultimap.getValue(PREFIX_DATE).orElse("").split("\\s+");
        String[] timeKeywords = argumentMultimap.getValue(PREFIX_TIME).orElse("").split("\\s+");
        String[] locationKeywords = argumentMultimap.getValue(PREFIX_LOCATION).orElse("").split("\\s+");
        String[] tagKeywords = argumentMultimap.getValue(PREFIX_TAG).orElse("").split("\\s+");

        EventContainsKeywordsPredicate predicate = new EventContainsKeywordsPredicate(Arrays.asList(nameKeywords),
                Arrays.asList(companyNameKeywords),
                Arrays.asList(dateKeywords),
                Arrays.asList(timeKeywords),
                Arrays.asList(locationKeywords),
                Arrays.asList(tagKeywords));

        model.updateFilteredEventList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_EVENTS_LISTED_OVERVIEW, model.getFilteredEventList().size()),
                false, false, false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindEventCommand // instanceof handles nulls
                && argumentMultimap.equals(((FindEventCommand) other).argumentMultimap)); // state check
    }
}
