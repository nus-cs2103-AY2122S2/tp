package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UploadCommand;
import seedu.address.logic.commands.UploadCommand.UploadDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UploadCommand object
 */
public class UploadCommandParser implements Parser<UploadCommand> {

    @Override
    public UploadCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_FILEPATH, PREFIX_DESCRIPTION);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UploadCommand.MESSAGE_USAGE), pe);
        }
        UploadDescriptor uploadDescriptor = new UploadCommand.UploadDescriptor();

        if (argMultimap.getValue(PREFIX_FILEPATH).isPresent()) {
           uploadDescriptor.setFilePath(argMultimap.getValue(PREFIX_FILEPATH).get());
        } else {
            throw new ParseException(UploadCommand.MESSAGE_MISSING_FILEPATH);
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            uploadDescriptor.setFilePath(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        }
        return new UploadCommand(index, uploadDescriptor);
    }
}
