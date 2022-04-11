package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MESSAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENTID;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

import seedu.address.logic.commands.AddCommentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.attendance.Comment;
import seedu.address.model.person.NusNetId;

public class AddCommentCommandParser implements Parser<AddCommentCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddCommentCommand
     * and returns an AddCommentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STUDENTID, PREFIX_MESSAGE);

        if (!arePrefixesPresent(argMultimap, PREFIX_STUDENTID, PREFIX_MESSAGE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommentCommand.MESSAGE_USAGE));
        }

        NusNetId studentId = ParserUtil.parseStudentId(argMultimap.getValue(PREFIX_STUDENTID).get());
        Comment comment = ParserUtil.parseComment(argMultimap.getValue(PREFIX_MESSAGE).get());

        return new AddCommentCommand(studentId, comment);
    }
}
