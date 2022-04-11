package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.NoteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class NoteCommandParser implements Parser<NoteCommand>, DetailedViewExecutableParser<NoteCommand> {
    @Override
    public NoteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_NOTE);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    NoteCommand.MESSAGE_USAGE), ive);
        }

        String note = argMultimap.getValue(PREFIX_NOTE).orElse("");

        return new NoteCommand(index, note);
    }

    @Override
    public NoteCommand parseInDetailedViewContext(String args) {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_NOTE);
        String note = argMultimap.getValue(PREFIX_NOTE).orElse("");

        return new NoteCommand(note);
    }
}
