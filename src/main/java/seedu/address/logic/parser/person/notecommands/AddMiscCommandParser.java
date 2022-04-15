package seedu.address.logic.parser.person.notecommands;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.person.notecommands.AddMiscCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.note.Note;

/**
 * Parses input arguments and creates a new {@code AddMiscCommand} object
 */
public class AddMiscCommandParser implements Parser<AddMiscCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code AddMiscCommand}
     * and returns a {@code AddMiscCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddMiscCommand parse(String args) throws ParseException {
        try {
            String[] splitArgs = args.trim().split("\\s+", 2);
            Index index = ParserUtil.parseIndex(splitArgs[0]);
            Note note = ParserUtil.parseNote(splitArgs[1]);
            return new AddMiscCommand(index, note);
        } catch (ParseException | IndexOutOfBoundsException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMiscCommand.MESSAGE_USAGE), pe);
        }
    }
}
