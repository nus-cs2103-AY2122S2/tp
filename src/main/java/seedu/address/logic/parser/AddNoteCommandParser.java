package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_EMPTY_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddNoteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.client.Note;

/**
 * Parses input arguments and creates a new AddNoteCommand object
 */
public class AddNoteCommandParser implements Parser<AddNoteCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddNoteCommand
     * and returns a AddNoteCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public AddNoteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NOTE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NOTE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddNoteCommand.MESSAGE_USAGE));
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException((String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddNoteCommand.MESSAGE_USAGE)));
        }

        EditCommand.EditClientDescriptor editClientDescriptor = new EditCommand.EditClientDescriptor();

        if (argMultimap.getValue(PREFIX_NOTE).get().equals("")) {
            throw new ParseException(MESSAGE_INVALID_EMPTY_NOTE);
        }

        Note note = ParserUtil.parseNote(argMultimap.getValue(PREFIX_NOTE).get());

        editClientDescriptor.setNote(note);
        return new AddNoteCommand(index, editClientDescriptor);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
