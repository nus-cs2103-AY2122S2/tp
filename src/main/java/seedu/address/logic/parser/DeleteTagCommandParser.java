package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new DeleteTagCommand object.
 */
public class DeleteTagCommandParser implements Parser<DeleteTagCommand> {

    private static final Logger LOGGER = LogsCenter.getLogger(DeleteTagCommandParser.class.getName());

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteTagCommand
     * and returns an DeleteTagCommand object for execution.
     *
     * @param args String to be parsed into DeleteTagCommand.
     * @return DeleteTagCommand.
     * @throws ParseException If the user input does not conform the expected format.
     */
    public DeleteTagCommand parse(String args) throws ParseException {
        LOGGER.log(Level.INFO, "Executing DeleteTagCommandParser#parse(String)");
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            LOGGER.log(Level.INFO, "Invalid command format");
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagCommand.MESSAGE_USAGE), pe);
        }

        if (argMultimap.getValue(PREFIX_TAG).isEmpty()) {
            LOGGER.log(Level.INFO, "PREFIX_TAG not specified");
            throw new ParseException(DeleteTagCommand.MESSAGE_MISSING_PREFIX);
        }

        Set<Tag> tagsToDelete = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        LOGGER.log(Level.INFO, "DeleteTagCommandParser#parse(String) success");
        return new DeleteTagCommand(index, tagsToDelete);
    }

}
