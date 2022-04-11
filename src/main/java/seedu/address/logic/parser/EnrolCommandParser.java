package seedu.address.logic.parser;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.EnrolCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

/**
 * Parses input arguments and creates a new EnrolCommand object
 */
public class EnrolCommandParser implements Parser<Command> {

    /**
     * Parses the given {@code String} of arguments in the context of the EnrolCommand
     * and returns a EnrolCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    @Override
    public Command parse(String args, Model model) throws ParseException {
        return GenericEnrolCommandParser.parse(EnrolCommand.COMMAND_WORD, args, model, EnrolCommand.MESSAGE_USAGE);
    }
}
