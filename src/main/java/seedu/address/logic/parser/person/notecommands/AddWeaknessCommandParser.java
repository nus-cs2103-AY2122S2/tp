package seedu.address.logic.parser.person.notecommands;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.person.notecommands.AddWeaknessCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.note.Note;

/**
 * Parses input arguments and creates a new {@code AddWeaknessCommand} object
 */
public class AddWeaknessCommandParser implements Parser<AddWeaknessCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code AddWeaknessCommand}
     * and returns a {@code AddWeaknessCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddWeaknessCommand parse(String args) throws ParseException {
        try {
            String[] splitArgs = args.trim().split("\\s+", 2);
            Index index = ParserUtil.parseIndex(splitArgs[0]);
            Note note = ParserUtil.parseNote(splitArgs[1]);
            return new AddWeaknessCommand(index, note);
        } catch (ParseException | IndexOutOfBoundsException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddWeaknessCommand.MESSAGE_USAGE), pe);
        }
    }
}
