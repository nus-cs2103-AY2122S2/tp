package manageezpz.logic.parser;

import static manageezpz.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static manageezpz.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static manageezpz.logic.parser.CliSyntax.PREFIX_TIME;

import java.time.LocalTime;
import java.util.stream.Stream;

import manageezpz.logic.commands.AddEventTaskCommand;
import manageezpz.logic.parser.exceptions.ParseException;
import manageezpz.model.person.Date;
import manageezpz.model.task.Description;
import manageezpz.model.task.Event;

public class AddEventTaskCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the AddEventTaskCommand
     * and returns an AddEventTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddEventTaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimapEvent =
                ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_TIME);

        if (!arePrefixesPresent(argMultimapEvent, PREFIX_DESCRIPTION, PREFIX_TIME)
                || !argMultimapEvent.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventTaskCommand.MESSAGE_USAGE));
        }

        Description desc = new Description(argMultimapEvent.getValue(PREFIX_DESCRIPTION).get());

        String atDateTime = argMultimapEvent.getValue(PREFIX_TIME).get();
        String[] parseAtDateTime = atDateTime.split(" ");
        Date date = ParserUtil.parseDate(parseAtDateTime[0]);
        LocalTime startTime = LocalTime.parse(parseAtDateTime[1]);
        LocalTime endTime = LocalTime.parse(parseAtDateTime[2]);
        Event event = new Event(desc, date, startTime, endTime);
        return new AddEventTaskCommand(event);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
