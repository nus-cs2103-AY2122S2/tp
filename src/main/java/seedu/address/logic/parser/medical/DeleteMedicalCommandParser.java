package seedu.address.logic.parser.medical;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.medical.DeleteMedicalCommand;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteMedicalCommand object
 */
public class DeleteMedicalCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteMedicalCommand
     * and returns a DeleteMedicalCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteMedicalCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteMedicalCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
        }
    }
}
