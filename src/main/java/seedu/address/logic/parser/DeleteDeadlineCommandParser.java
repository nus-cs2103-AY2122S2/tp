package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteDeadlineCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteDeadlineCommandParser implements DetailedViewExecutableParser<DeleteDeadlineCommand> {

    @Override
    public DeleteDeadlineCommand parseInDetailedViewContext(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteDeadlineCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteDeadlineCommand.MESSAGE_USAGE), pe);
        }
    }
}
