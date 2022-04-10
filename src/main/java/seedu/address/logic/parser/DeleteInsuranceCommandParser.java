package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteInsuranceCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteInsuranceCommand object
 */
public class DeleteInsuranceCommandParser implements Parser<DeleteInsuranceCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteInsuranceCommand
     * and returns a DeleteInsuranceCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteInsuranceCommand parse(String args) throws ParseException {
        try {
            Index index = InsuranceParserUtil.parseIndex(args);
            return new DeleteInsuranceCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteInsuranceCommand.MESSAGE_USAGE), pe);
        }
    }

}
