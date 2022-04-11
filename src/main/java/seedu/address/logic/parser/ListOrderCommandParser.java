package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.ListOrderCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.order.OrderStatus;
import seedu.address.model.order.OrderStatusContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new ListOrderCommand object
 */
public class ListOrderCommandParser implements Parser<ListOrderCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListOrderCommand
     * and returns a ListOrderCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListOrderCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListOrderCommand.MESSAGE_USAGE));
        }

        if (trimmedArgs.equalsIgnoreCase("in progress")) {
            trimmedArgs = "in_progress";
        }

        String[] nameKeywords = trimmedArgs.toLowerCase().split("\\s+");

        if (nameKeywords.length > 1) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListOrderCommand.MESSAGE_USAGE));
        }

        if (!orderStatusHasKeyword(nameKeywords[0])) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListOrderCommand.MESSAGE_USAGE));
        }

        return new ListOrderCommand(new OrderStatusContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

    /**
     * Checks the given {@code String} argument and
     * returns a boolean representing if enum OrderStatus contains argument or
     * argument is equal to "all" (not case-sensitive)
     */
    private boolean orderStatusHasKeyword (String keyword) {
        if (keyword.equalsIgnoreCase("ALL")) {
            return true;
        }
        boolean hasKeyword = false;
        for (OrderStatus status : OrderStatus.values()) {
            if (status.name().equalsIgnoreCase(keyword)) {
                hasKeyword = true;
                break;
            }
        }

        return hasKeyword;
    }

}

