package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteNoteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteNoteCommandParser implements DetailedViewExecutableParser<DeleteNoteCommand> {

    @Override
    public DeleteNoteCommand parseInDetailedViewContext(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteNoteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteNoteCommand.MESSAGE_USAGE), pe);
        }
    }
}
