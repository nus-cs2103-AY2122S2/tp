package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteNoteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.client.Note;

/**
 * Parses input arguments and creates a new DeleteNoteCommand object
 */
public class DeleteNoteCommandParser implements Parser<DeleteNoteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteNoteCommand
     * and returns a DeleteNoteCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public DeleteNoteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(args);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultiMap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException((String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteNoteCommand.MESSAGE_USAGE)));
        }

        EditCommand.EditClientDescriptor editClientDescriptor = new EditCommand.EditClientDescriptor();
        Note note = new Note(Note.DEFAULT_NOTE);
        editClientDescriptor.setNote(note);
        return new DeleteNoteCommand(index, editClientDescriptor);
    }
}
