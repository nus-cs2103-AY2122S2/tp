package seedu.address.logic.parser.person.notecommands;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.person.notecommands.DeleteWeaknessCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code DeleteWeaknessCommand} object
 */
public class DeleteWeaknessCommandParser implements Parser<DeleteWeaknessCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code DeleteWeaknessCommand}
     * and returns a {@code DeleteWeaknessCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public DeleteWeaknessCommand parse(String args) throws ParseException {
        try {
            String[] splitArgs = args.trim().split("\\s+", 2);
            Index index = ParserUtil.parseIndex(splitArgs[0]);
            Index noteIndex = ParserUtil.parseNoteIndex(splitArgs[1]);
            return new DeleteWeaknessCommand(index, noteIndex);
        } catch (ParseException | IndexOutOfBoundsException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteWeaknessCommand.MESSAGE_USAGE), pe);
        }
    }
}
