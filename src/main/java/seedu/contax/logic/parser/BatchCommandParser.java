package seedu.contax.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.contax.logic.commands.BatchCommand.MESSAGE_PREFIX_NOT_EQUALS_ONE;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_END_WITH;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_EQUALS;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_SEARCH_TYPE;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_START_WITH;

import seedu.contax.logic.commands.BatchCommand;
import seedu.contax.logic.commands.BatchCommand.BatchType;
import seedu.contax.logic.parser.exceptions.ParseException;
import seedu.contax.model.util.SearchType;

/**
 * Parses input arguments and creates a new BatchCommand object.
 */
public class BatchCommandParser implements Parser<BatchCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the BatchCommandParser
     * and returns an BatchCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public BatchCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        PREFIX_SEARCH_TYPE, PREFIX_EQUALS, PREFIX_START_WITH, PREFIX_END_WITH);
        String commandInput = argMultimap.getPreamble();
        String userValue = "";
        BatchType batchType = BatchType.EQUALS;
        String searchType;

        int emptyCounter = 0;
        if (argMultimap.getValue(PREFIX_EQUALS).isEmpty()) {
            emptyCounter++;
        }
        if (argMultimap.getValue(PREFIX_START_WITH).isEmpty()) {
            emptyCounter++;
        }
        if (argMultimap.getValue(PREFIX_END_WITH).isEmpty()) {
            emptyCounter++;
        }

        if (argMultimap.getValue(PREFIX_SEARCH_TYPE).isEmpty() || argMultimap.getPreamble().isEmpty()
            || (emptyCounter == 3)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    BatchCommand.MESSAGE_USAGE));
        }
        if (emptyCounter != 2) {
            throw new ParseException(MESSAGE_PREFIX_NOT_EQUALS_ONE);
        }

        searchType = argMultimap.getValue(PREFIX_SEARCH_TYPE).get().toLowerCase();
        if (argMultimap.getValue(PREFIX_EQUALS).isPresent()) {
            userValue = argMultimap.getValue(PREFIX_EQUALS).get();
        } else if (argMultimap.getValue(PREFIX_START_WITH).isPresent()) {
            userValue = argMultimap.getValue(PREFIX_START_WITH).get();
            batchType = BatchType.START;
        } else if (argMultimap.getValue(PREFIX_END_WITH).isPresent()) {
            userValue = argMultimap.getValue(PREFIX_END_WITH).get();
            batchType = BatchType.END;
        }
        return new BatchCommand(
                commandInput.trim(),
                new SearchType(searchType),
                userValue, batchType
        );
    }
}
