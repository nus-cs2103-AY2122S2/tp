package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.ListCommand.COMMAND_ORDER_WORD_ASCENDING;
import static seedu.address.logic.commands.ListCommand.COMMAND_ORDER_WORD_DESCENDING;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.sort.ApplicationStatusComparator;
import seedu.address.logic.sort.InterviewSlotComparator;
import seedu.address.logic.sort.NameComparator;
import seedu.address.logic.sort.PriorityComparator;

/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListCommandParser implements Parser<ListCommand> {

    public static final ListCommand SORT_NONE = new ListCommand();

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a ListCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) /* Handles default list */ {
            return SORT_NONE;
        }

        String[] sortingArgs = trimmedArgs.split("\\s+");

        if (sortingArgs.length == 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        } else {
            String sortingField = sortingArgs[0].toLowerCase();
            String orderBy = sortingArgs[1].toUpperCase();

            if (validateOrderBy(orderBy)) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
            } else if (sortingField.equals(NameComparator.COMMAND_WORD)) {
                return new ListCommand(new NameComparator(), orderBy);
            } else if (sortingField.equals(InterviewSlotComparator.COMMAND_WORD)) {
                return new ListCommand(new InterviewSlotComparator(), orderBy);
            } else if (sortingField.equals(PriorityComparator.COMMAND_WORD)) {
                return new ListCommand(new PriorityComparator(), orderBy);
            } else if (sortingField.equals(ApplicationStatusComparator.COMMAND_WORD)) {
                return new ListCommand(new ApplicationStatusComparator(), orderBy);
            }
        }

        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    private boolean validateOrderBy(String s) {
        return !s.equals(COMMAND_ORDER_WORD_DESCENDING) && !s.equals(COMMAND_ORDER_WORD_ASCENDING);
    }
}
