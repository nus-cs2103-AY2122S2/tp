package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_CLEAR;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parser for {@code ClearCommand}.
 * Ensure that users type in a certain command before the program is cleared.
 */
public class ClearCommandParser implements Parser<ClearCommand> {
    @Override
    public ClearCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_CLEAR);

        if (CheckPrefixes.arePrefixesAbsent(argumentMultimap, PREFIX_CLEAR)
                || !argumentMultimap.getPreamble().isEmpty()) {
            throw new ParseException(ClearCommand.MESSAGE_USAGE);
        }
        String extractedString = argumentMultimap.getValue(PREFIX_CLEAR).get();
        if (!extractedString.isEmpty()) {
            throw new ParseException(ClearCommand.MESSAGE_USAGE);
        }
        return new ClearCommand();
    }
}
