package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENTID;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

import seedu.address.logic.commands.RemoveCommentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NusNetId;

public class RemoveCommentCommandParser implements Parser<RemoveCommentCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the RemoveCommentCommand
     * and returns an RemoveCommentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemoveCommentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STUDENTID);

        if (!arePrefixesPresent(argMultimap, PREFIX_STUDENTID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveCommentCommand.MESSAGE_USAGE));
        }

        NusNetId studentId = ParserUtil.parseStudentId(argMultimap.getValue(PREFIX_STUDENTID).get());

        return new RemoveCommentCommand(studentId);
    }
}
