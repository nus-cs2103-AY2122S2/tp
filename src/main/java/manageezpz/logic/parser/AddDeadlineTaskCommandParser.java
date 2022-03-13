package manageezpz.logic.parser;

import static manageezpz.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static manageezpz.logic.parser.CliSyntax.PREFIX_DATETIME;
import static manageezpz.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import java.time.LocalTime;
import java.util.stream.Stream;

import manageezpz.logic.commands.AddDeadlineTaskCommand;
import manageezpz.logic.commands.AddEventTaskCommand;
import manageezpz.logic.parser.exceptions.ParseException;
import manageezpz.model.person.Date;
import manageezpz.model.task.Deadline;
import manageezpz.model.task.Description;

public class AddDeadlineTaskCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the AddDeadlineTaskCommand
     * and returns an AddDeadlineTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddDeadlineTaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimapDeadline =
                ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_DATETIME);

        if (!arePrefixesPresent(argMultimapDeadline, PREFIX_DESCRIPTION, PREFIX_DATETIME)
                || !argMultimapDeadline.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventTaskCommand.MESSAGE_USAGE));
        }

        Description desc = new Description(argMultimapDeadline.getValue(PREFIX_DESCRIPTION).get());
        String byDateTime = argMultimapDeadline.getValue(PREFIX_DATETIME).get();

        String[] parseByDateTime = byDateTime.split(" ");
        Date date = ParserUtil.parseDate(parseByDateTime[0]);
        LocalTime time = LocalTime.parse(parseByDateTime[1]);

        Deadline deadline = new Deadline(desc, date, time);

        return new AddDeadlineTaskCommand(deadline);
    }


    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
