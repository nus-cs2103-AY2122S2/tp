package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATETIME;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_INTERVAL;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_RECURRENCE;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_RECURRENCE_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURRING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASKNAME;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import seedu.address.commons.util.TranslatorUtil;
import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Link;

/**
 * Parses input arguments and creates a new AddTaskCommand object
 */
public class AddTaskCommandParser implements Parser<AddTaskCommand> {

    private final String dateTimePattern = "dd-MM-yyyy HHmm";
    private final SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(dateTimePattern);

    /**
     * Parses the given {@code String} of arguments in the context of the AddTaskCommand for TASKNAME, DATETIME, TAG
     * and returns an AddTaskCommand object for execution.
     *
     * @param args String object of user input to be parsed.
     * @return AddTaskCommand object
     * @throws ParseException If the input does not conform to the expected format.
     */
    public AddTaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TASKNAME, PREFIX_DATETIME,
                        PREFIX_TAG, PREFIX_LINK, PREFIX_RECURRING);

        if (!arePrefixesPresent(argMultimap, PREFIX_TASKNAME, PREFIX_DATETIME)
                || !argMultimap.getPreamble().isEmpty()) {
            StringBuffer sb = displayInvalidParameters(argMultimap);
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, sb + "\n"
                    + AddTaskCommand.MESSAGE_USAGE));
        }

        String taskName = ParserUtil.parseTaskName(argMultimap.getValue(PREFIX_TASKNAME));
        String dateTimeString = argMultimap.getValue(PREFIX_DATETIME).get();
        LocalDateTime dateTime;
        LocalDateTime endDateTime;
        Set<Tag> tags = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Link link = ParserUtil.parseLink(argMultimap.getValue(PREFIX_LINK));

        try {
            dateTimeFormatter.setLenient(false);

            if (dateTimeString.contains(",")) {
                String[] splits = dateTimeString.split(",");
                dateTime = convertToLocalDateTime(dateTimeFormatter.parse(splits[0]));
                endDateTime = convertToLocalDateTime(dateTimeFormatter.parse(splits[1]));
            } else {
                dateTime = convertToLocalDateTime(dateTimeFormatter.parse(dateTimeString));
                endDateTime = null;
            }
        } catch (java.text.ParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_DATETIME, AddTaskCommand.MESSAGE_USAGE));
        }

        // If recurring tag is present in argument
        if (arePrefixesPresent(argMultimap, PREFIX_RECURRING)) {
            int periodInt = 0;
            int recurrenceInt = 0;

            String[] periodMultipleArr = ParserUtil.parseRecurring(argMultimap.getValue(PREFIX_RECURRING));

            String periodStr = periodMultipleArr[0].toLowerCase();
            String recurrenceStr = periodMultipleArr[1];

            Map<String, Integer> periodMapping = TranslatorUtil.getPeriodMapping();

            if (periodMapping.containsKey(periodStr)) {
                periodInt = periodMapping.get(periodStr);
            } else {
                periodInt = parsePeriod(periodStr);
            }

            try {
                recurrenceInt = Integer.parseInt(recurrenceStr);
            } catch (NumberFormatException e) {
                throw new ParseException(String.format(MESSAGE_INVALID_RECURRENCE,
                        AddTaskCommand.MESSAGE_USAGE));
            }

            if (periodInt <= 0 || recurrenceInt <= 0) {
                throw new ParseException(String.format(MESSAGE_INVALID_RECURRENCE_INDEX,
                        AddTaskCommand.MESSAGE_USAGE));
            }

            return new AddTaskCommand(taskName, dateTime, endDateTime, tags, link, periodInt, recurrenceInt);
        }

        return new AddTaskCommand(taskName, dateTime, endDateTime, tags, link);
    }

    /**
     * Converts a date object to LocalDateTime object.
     *
     * @param dateToConvert Date object to be converted.
     * @return LocalDateTime object.
     */
    LocalDateTime convertToLocalDateTime(Date dateToConvert) {
        return new java.sql.Timestamp(
                dateToConvert.getTime()).toLocalDateTime();
    }

    /**
     * Checks what parameters are missing in user's input. Returns the tags that are missing.
     * Example: If tn/ and dt/ are missing, return "Missing/Invalid parameters: tn/, dt/".
     *
     * @param argMultimap Argument Multimap of user input that is read.
     * @return StringBuffer format of missing parameters.
     */
    public StringBuffer displayInvalidParameters(ArgumentMultimap argMultimap) {
        String errorString = "Missing/Invalid parameters: ";
        if (!arePrefixesPresent(argMultimap, PREFIX_TASKNAME)) {
            errorString += PREFIX_TASKNAME + ", ";
        }
        if (!arePrefixesPresent(argMultimap, PREFIX_DATETIME)) {
            errorString += PREFIX_DATETIME + ", ";
        }
        StringBuffer sb = new StringBuffer(errorString);
        sb.delete(sb.length() - 2, sb.length() - 1); //Deleting last comma
        return sb;
    }

    private int parsePeriod(String periodStr) throws ParseException {
        try {
            return Integer.parseInt(periodStr);
        } catch (NumberFormatException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_INTERVAL,
                    AddTaskCommand.MESSAGE_USAGE));
        }
    }
}
