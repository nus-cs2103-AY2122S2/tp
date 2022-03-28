package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.RemoveMembershipCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new RemoveMembershipCommand object
 */
public class RemoveMembershipParser implements Parser<RemoveMembershipCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RemoveMembershipCommand
     * and returns an RemoveMembershipCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemoveMembershipCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        Index index;
        try {
            index = IndexParser.parse(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RemoveMembershipCommand.MESSAGE_USAGE), ive);
        }

        return new RemoveMembershipCommand(index);
    }
}
