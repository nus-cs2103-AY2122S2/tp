package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;

import seedu.address.logic.commands.ListClassCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tutorial.Day;
import seedu.address.model.tutorial.TutorialHasSameDay;

public class ListClassCommandParser implements Parser<ListClassCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ListClassCommand
     * and returns a ListClassCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListClassCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DAY);

        if (argMultimap.getValue(PREFIX_DAY).isPresent() && argMultimap.getPreamble().isEmpty()) {
            Day day = ParserUtil.parseDay(argMultimap.getValue(PREFIX_DAY).get());
            return new ListClassCommand(new TutorialHasSameDay(day));
        }
        String trimmedArgs = args.trim();
        if (!trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListClassCommand.MESSAGE_USAGE));
        }
        return new ListClassCommand(null);
    }
}
