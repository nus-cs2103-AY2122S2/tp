package seedu.address.logic.parser.applicants;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.applicant.DeleteApplicantCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteApplicantCommand object
 */
public class DeleteApplicantCommandParser implements Parser<DeleteApplicantCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteApplicantCommand
     * and returns a DeleteApplicantCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteApplicantCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteApplicantCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteApplicantCommand.MESSAGE_USAGE), pe);
        }
    }

}
