package seedu.address.logic.parser.person.notecommands;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.person.notecommands.DeleteStrengthCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code DeleteStrengthCommand} object
 */
public class DeleteStrengthCommandParser implements Parser<DeleteStrengthCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code DeleteStrengthCommand}
     * and returns a {@code DeleteStrengthCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public DeleteStrengthCommand parse(String args) throws ParseException {
        try {
            String[] splitArgs = args.trim().split("\\s+", 2);
            Index index = ParserUtil.parseIndex(splitArgs[0]);
            Index noteIndex = ParserUtil.parseNoteIndex(splitArgs[1]);
            return new DeleteStrengthCommand(index, noteIndex);
        } catch (ParseException | IndexOutOfBoundsException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteStrengthCommand.MESSAGE_USAGE), pe);
        }
    }
}
