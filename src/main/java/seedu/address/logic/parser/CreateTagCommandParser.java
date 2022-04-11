package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.model.tag.Tag.VALIDATION_REGEX;

import seedu.address.logic.commands.CreateTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new CreateTagCommand object
 */
public class CreateTagCommandParser implements Parser<CreateTagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CreateTagCommand
     * and returns a CreateTagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CreateTagCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateTagCommand.MESSAGE_USAGE));
        }

        if (!trimmedArgs.matches(VALIDATION_REGEX)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateTagCommand.MESSAGE_USAGE));
        }

        return new CreateTagCommand(trimmedArgs);
    }
}
