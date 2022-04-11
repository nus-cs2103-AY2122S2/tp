package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AssignTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AssignTagCommand object
 */
public class AssignTagCommandParser implements Parser<AssignTagCommand>,
        DetailedViewExecutableParser<AssignTagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AssignTagCommand
     * and returns a AssignTagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AssignTagCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignTagCommand.MESSAGE_USAGE));
        }

        String[] inputs = trimmedArgs.split("\\s+");
        if (inputs.length <= 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AssignTagCommand.MESSAGE_USAGE));
        }

        try {
            Index index = ParserUtil.parseIndex(inputs[0]);
            String tagName = inputs[1];
            return new AssignTagCommand(index, tagName);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignTagCommand.MESSAGE_USAGE), pe);
        }
    }

    @Override
    public AssignTagCommand parseInDetailedViewContext(String args) throws ParseException {
        String trimmedArgs = args.trim();
        String[] inputs = trimmedArgs.split("\\s+");
        String tag = inputs[inputs.length - 1];

        if (tag.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignTagCommand.MESSAGE_USAGE));
        }

        return new AssignTagCommand(tag);
    }
}
