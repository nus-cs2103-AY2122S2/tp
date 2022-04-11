package seedu.address.logic.parser;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ArgumentMultimap.arePrefixesPresent;
import static seedu.address.logic.parser.CliSyntax.FLAG_ALL;

import seedu.address.logic.commands.ShowEventsCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ShowEventsCommandParser implements Parser<ShowEventsCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ShowEventsCommand
     * and returns a ShowEventsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ShowEventsCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, FLAG_ALL);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowEventsCommand.MESSAGE_USAGE));
        }

        return new ShowEventsCommand(arePrefixesPresent(argMultimap, FLAG_ALL));
    }

}
