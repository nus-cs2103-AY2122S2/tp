package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.Arrays;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.model.Model;
import seedu.address.model.entry.EventContainsKeywordsPredicate;

/**
 * Keyword matching is case insensitive
 */
public class FindEventCommand extends Command {

    public static final String COMMAND_WORD = "finde";
    public static final String MESSAGE_NOT_QUERIED = "At least one field to find must be provided.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds events with given details of the event "
            + "by name, company, date, time, location and tag "
            + "Parameters: "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_COMPANY + "COMPANY] "
            + "[" + PREFIX_DATE + "DATE] "
            + "[" + PREFIX_TIME + "TIME] "
            + "[" + PREFIX_LOCATION + "LOCATION] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_COMPANY + " sgshop "
            + PREFIX_TIME + " 13:30";

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
