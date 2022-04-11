package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MatchLocationCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class MatchLocationCommandParser implements Parser<MatchLocationCommand> {

    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @param userInput full user input string
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public MatchLocationCommand parse(String userInput) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(userInput);
            return new MatchLocationCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MatchLocationCommand.MESSAGE_USAGE), pe);
        }
    }
}
