package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_USERIMAGE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UploadCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.userimage.UserImage;

/**
 * Parses input arguments and creates a new UploadCommand object
 */
public class UploadCommandParser implements Parser<UploadCommand> {

    @Override
    public UploadCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_USERIMAGE);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UploadCommand.MESSAGE_USAGE), pe);
        }
        UserImage userImage = ParserUtil.parseUserImage(argMultimap.getValue(PREFIX_USERIMAGE).get());
        return new UploadCommand(index, userImage);
    }
}
