package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.DeadlineCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.DeadlineList;

/**
 * Parses input arguments and creates a new DeadlineCommand object
 */
public class DeadlineCommandParser implements Parser<DeadlineCommand>, DetailedViewExecutableParser<DeadlineCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeadlineCommand
     * and returns a DeadlineCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeadlineCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_DEADLINE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeadlineCommand.MESSAGE_USAGE), ive);
        }
        DeadlineList deadlines = ParserUtil.parseDeadlines(argMultimap.getAllValues(PREFIX_DEADLINE));
        if (deadlines.size() == 0) {
            throw new ParseException(DeadlineCommand.MESSAGE_NO_DEADLINES_ADDED);
        }

        return new DeadlineCommand(index, deadlines);
    }

    @Override
    public DeadlineCommand parseInDetailedViewContext(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_DEADLINE);

        DeadlineList deadlines = ParserUtil.parseDeadlines(argMultimap.getAllValues(PREFIX_DEADLINE));
        if (deadlines.size() == 0) {
            throw new ParseException(DeadlineCommand.MESSAGE_NO_DEADLINES_ADDED);
        }

        return new DeadlineCommand(deadlines);
    }
}
