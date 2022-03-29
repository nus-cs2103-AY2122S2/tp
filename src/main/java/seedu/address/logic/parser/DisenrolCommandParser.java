package seedu.address.logic.parser;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DisenrolCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

/**
 * Parses input arguments and creates a new DisenrolCommand object
 */
public class DisenrolCommandParser implements Parser<Command> {

    /**
     * Parses the given {@code String} of arguments in the context of the DisenrolCommand
     * and returns a DisenrolCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    @Override
    public Command parse(String args, Model model) throws ParseException {
        return GenericEnrolCommandParser.parse(DisenrolCommand.COMMAND_WORD, args, model,
                DisenrolCommand.MESSAGE_USAGE);
    }
}
