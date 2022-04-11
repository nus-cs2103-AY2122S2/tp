package seedu.unite.logic.parser;

import static seedu.unite.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.unite.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.unite.logic.parser.CliSyntax.PREFIX_COURSE;
import static seedu.unite.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.unite.logic.parser.CliSyntax.PREFIX_MATRICCARD;
import static seedu.unite.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.unite.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.unite.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.unite.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import seedu.unite.logic.commands.GrabCommand;
import seedu.unite.logic.parser.exceptions.ParseException;
import seedu.unite.model.tag.Tag;

public class GrabCommandParser implements Parser<GrabCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the GrabCommand
     * and returns an GrabCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public GrabCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_COURSE, PREFIX_MATRICCARD, PREFIX_TELEGRAM, PREFIX_TAG);

        int numOfArgument = argMultimap.getSize();

        if (numOfArgument > 2) {
            if (numOfArgument != 3 || !argMultimap.getValue(PREFIX_TAG).isPresent()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, GrabCommand.MESSAGE_TOO_MANY_ARGUMENT));
            }
        }

        if (numOfArgument == 0) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GrabCommand.MESSAGE_USAGE));
        }

        if (numOfArgument == 2 && argMultimap.getValue(PREFIX_TAG).isPresent()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GrabCommand.MESSAGE_USAGE));
        }

        Tag tagRead = null;

        if (numOfArgument == 3 || argMultimap.getValue(PREFIX_TAG).isPresent()) {
            tagRead = ParserUtil.parseTag(argMultimap.getValue(PREFIX_TAG).get());
        }

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String indexToBeGrabbed = argMultimap.getValue(PREFIX_NAME).get();
            return new GrabCommand(PREFIX_NAME, indexToBeGrabbed, tagRead);
        } else if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            String indexToBeGrabbed = argMultimap.getValue(PREFIX_PHONE).get();
            return new GrabCommand(PREFIX_PHONE, indexToBeGrabbed, tagRead);
        } else if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            String indexToBeGrabbed = argMultimap.getValue(PREFIX_EMAIL).get();
            return new GrabCommand(PREFIX_EMAIL, indexToBeGrabbed, tagRead);
        } else if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            String indexToBeGrabbed = argMultimap.getValue(PREFIX_ADDRESS).get();
            return new GrabCommand(PREFIX_ADDRESS, indexToBeGrabbed, tagRead);
        } else if (argMultimap.getValue(PREFIX_COURSE).isPresent()) {
            String indexToBeGrabbed = argMultimap.getValue(PREFIX_COURSE).get();
            return new GrabCommand(PREFIX_COURSE, indexToBeGrabbed, tagRead);
        } else if (argMultimap.getValue(PREFIX_MATRICCARD).isPresent()) {
            String indexToBeGrabbed = argMultimap.getValue(PREFIX_MATRICCARD).get();
            return new GrabCommand(PREFIX_MATRICCARD, indexToBeGrabbed, tagRead);
        } else if (argMultimap.getValue(PREFIX_TELEGRAM).isPresent()) {
            String indexToBeGrabbed = argMultimap.getValue(PREFIX_TELEGRAM).get();
            return new GrabCommand(PREFIX_TELEGRAM, indexToBeGrabbed, tagRead);
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GrabCommand.MESSAGE_USAGE));
        }
    }
}
