package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_USERIMAGE;

import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UploadCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.userimage.UserImage;

/**
 * Parses input arguments and creates a new UploadCommand object
 */
public class UploadCommandParser implements Parser<UploadCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UploadCommand
     * and returns a UploadCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UploadCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_USERIMAGE);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UploadCommand.MESSAGE_USAGE), pe);
        }
        Set<UserImage> userImages = ParserUtil.parseUserImages(argMultimap.getAllValues(PREFIX_USERIMAGE));
        return new UploadCommand(index, userImages);
    }
}
