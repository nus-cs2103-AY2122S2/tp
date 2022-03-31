package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ArchiveCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ArchiveCommand object
 */
public class ArchiveCommandParser implements Parser<ArchiveCommand> {

    // Tells us whether it's archive or unarchive
    private String mode;

    public ArchiveCommandParser(String mode) {
        this.mode = mode;
    }

    /**
     * Parses {@code userInput} into a command and returns it.
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public ArchiveCommand parse(String userInput) throws ParseException {

        // Either it is archive
        if (mode.equals(ArchiveCommand.COMMAND_WORD)) {
            try {
                Index index = ParserUtil.parseIndex(userInput);
                return new ArchiveCommand(index, ArchiveCommand.COMMAND_WORD);
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ArchiveCommand.MESSAGE_USAGE), pe);
            }
        } else {
            try {
                Index index = ParserUtil.parseIndex(userInput);
                return new ArchiveCommand(index, ArchiveCommand.ALT_COMMAND_WORD);
            } catch (ParseException pe) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, ArchiveCommand.ALT_MESSAGE_USAGE), pe);
            }
        }
    }
}
