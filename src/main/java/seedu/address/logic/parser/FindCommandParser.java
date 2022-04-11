package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEADER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INFORMATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.TYPE_INTERVIEW;
import static seedu.address.logic.parser.CliSyntax.TYPE_PERSON;
import static seedu.address.logic.parser.CliSyntax.TYPE_TASK;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindInterviewCommand;
import seedu.address.logic.commands.FindPersonCommand;
import seedu.address.logic.commands.FindTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Date;
import seedu.address.model.Time;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Job;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Stage;
import seedu.address.model.tasks.Header;
import seedu.address.model.tasks.Information;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    public static final String EXTRA_FLAG_ERROR = "Extra g/ flag found.\n";
    public static final String NO_KEYWORDS_ERROR = "Some g/ flags are empty.\n";
    protected static String padding = " ";
    private static Logger logger = LogsCenter.getLogger(FindCommandParser.class);

    /**
     * Parses the initial input taken from the application
     *
     * @param input The input passed from the application.
     * @return A Find(Person/Interview/Tasks)Command object ready for execution or an error when no type is found
     * @throws ParseException A search term has an invalid format.
     */
    public FindCommand parse(String input) throws ParseException {
        logger.info("Parsing Input for Find -> \"" + input + "\"");

        String type = ArgumentTokenizer.getType(input.trim());
        String args = input.trim().substring(3);

        // splits tokens by its groups
        ArgumentMultimap groupTokens = ArgumentTokenizer.tokenize(args, PREFIX_GROUP);
        List<String> allGroups = groupTokens.getAllValues(PREFIX_GROUP);

        switch (type) {
        case TYPE_PERSON:
            checkValidGroups(allGroups, args, FindPersonCommand.MESSAGE_USAGE);
            return new FindPersonCommandParser().parse(allGroups);
        case TYPE_INTERVIEW:
            checkValidGroups(allGroups, args, FindInterviewCommand.MESSAGE_USAGE);
            return new FindInterviewCommandParser().parse(allGroups);
        case TYPE_TASK:
            checkValidGroups(allGroups, args, FindTaskCommand.MESSAGE_USAGE);
            return new FindTaskCommandParser().parse(allGroups);
        default:
            return null;
        }
    }

    // ------ Checks input for any invalid data ------ //

    private void checkValidGroups(List<String> allGroups, String args, String msg) throws ParseException {
        boolean isCompletelyEmpty = args.isEmpty();
        boolean hasNoSpaceBetweenInitialFlags = allGroups.isEmpty();
        boolean hasNoKeywords = allGroups.stream().anyMatch(grp -> grp.strip().equals(""));
        boolean stillHasGroupFlag = allGroups.stream()
                .anyMatch(grp -> grp.strip().startsWith(String.valueOf(PREFIX_GROUP)));

        if (hasNoKeywords) {
            throw new ParseException(NO_KEYWORDS_ERROR + msg);
        }

        if (stillHasGroupFlag) {
            throw new ParseException(EXTRA_FLAG_ERROR + msg);
        }

        if (isCompletelyEmpty || hasNoSpaceBetweenInitialFlags) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, msg));
        }

        // check for cases where there are no flags after the 'g/' flag
        for (String group : allGroups) {
            String firstArg = group.strip().split("\\s+")[0];

            if (!havePrefixesPresent(padding + firstArg, PREFIX_NAME, PREFIX_PHONE,
                    PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_JOB, PREFIX_STAGE, PREFIX_DATE, PREFIX_TIME,
                    PREFIX_GROUP, PREFIX_INFORMATION, PREFIX_HEADER)) {
                throw new ParseException(NO_KEYWORDS_ERROR + msg);
            }
        }
    }

    /**
     * Checks the current group for any invalid dates.
     *
     * @param group Current group of tokens.
     * @throws ParseException A date was found to have invalid format.
     */
    protected static void checkInvalidDates(List<String> dates, String group) throws ParseException {
        if (dates.stream().anyMatch(d -> !Date.isValidDate(d.strip()))) {
            throw new ParseException("[" + group + "] contains invalid date\n" + Date.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Checks the current group for any invalid time.
     *
     * @param group Current group of tokens.
     * @throws ParseException A time was found to have invalid format.
     */
    protected static void checkInvalidTime(List<String> times, String group) throws ParseException {
        if (times.stream().anyMatch(t -> !Time.isValidTime(t.strip()))) {
            throw new ParseException("[" + group + "] contains invalid time\n" + Time.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Checks the current group for any invalid names.
     *
     * @param group Current group of tokens.
     * @throws ParseException A name was found to have invalid format.
     */
    protected static void checkInvalidName(List<String> names, String group) throws ParseException {
        if (names.stream().anyMatch(n -> !Name.isValidName(n.strip()))) {
            throw new ParseException("[" + group + "] contains invalid name\n" + Name.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Checks the current group for any invalid header.
     *
     * @param group Current group of tokens.
     * @throws ParseException A header was found to have invalid format.
     */
    public static void checkInvalidHeader(List<String> header, String group) throws ParseException {
        if (header.stream().anyMatch(h -> !Header.isValidHeader(h.strip()))) {
            throw new ParseException("[" + group + "] contains invalid header\n" + Header.MESSAGE_CONSTRAINTS);
        }
    }


    /**
     * Checks the current group for any invalid jobs.
     *
     * @param group Current group of tokens.
     * @throws ParseException A job was found to have invalid format.
     */
    protected static void checkInvalidJob(List<String> jobs, String group) throws ParseException {
        if (jobs.stream().anyMatch(j -> !Job.isValidJob(j.strip()))) {
            throw new ParseException("[" + group + "] contains invalid job\n" + Job.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Checks the current group for any invalid phone numbers.
     *
     * @param group Current group of tokens.
     * @throws ParseException A phone number was found to have invalid format.
     */
    protected static void checkInvalidPhone(List<String> phones, String group) throws ParseException {
        if (phones.stream().anyMatch(p -> !Phone.isValidPhone(p))) {
            throw new ParseException("[" + group + "] contains invalid phone number\n"
                    + Phone.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Checks the current group for any invalid emails.
     *
     * @param group Current group of tokens.
     * @throws ParseException An email was found to have invalid format.
     */
    protected static void checkInvalidEmail(List<String> emails, String group) throws ParseException {
        if (emails.stream().anyMatch(e -> !Email.isValidEmail(e))) {
            throw new ParseException("[" + group + "] contains invalid email\n" + Email.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Checks the current group for any invalid addresses.
     *
     * @param group Current group of tokens.
     * @throws ParseException An address was found to have invalid format.
     */
    protected static void checkInvalidAddress(List<String> addresses, String group) throws ParseException {
        if (addresses.stream().anyMatch(a -> !Address.isValidAddress(a))) {
            throw new ParseException("[" + group + "] contains invalid address\n" + Address.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Checks the current group for any invalid stages.
     *
     * @param group Current group of tokens.
     * @throws ParseException A stage was found to have invalid format.
     */
    protected static void checkInvalidStage(List<String> stages, String group) throws ParseException {
        if (stages.stream().anyMatch(s -> !Stage.isValidStage(s))) {
            throw new ParseException("[" + group + "] contains invalid stage\n" + Stage.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Checks the current group for any invalid information.
     *
     * @param group Current group of tokens.
     * @throws ParseException An information  was found to have invalid format.
     */
    protected static void checkInvalidInformation(List<String> infos, String group) throws ParseException {
        if (infos.stream().anyMatch(i -> !Information.isValidInformation(i))) {
            throw new ParseException(
                    "[" + group + "] contains invalid information\n" + Information.MESSAGE_CONSTRAINTS);
        }
    }

    protected static boolean havePrefixesPresent(String group, Prefix... prefixes) {
        ArgumentMultimap allFlags = ArgumentTokenizer.tokenize(padding + group, PREFIX_NAME, PREFIX_PHONE,
                PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_JOB, PREFIX_STAGE, PREFIX_DATE, PREFIX_TIME,
                PREFIX_GROUP, PREFIX_INFORMATION, PREFIX_HEADER);

        return Stream.of(prefixes).anyMatch(prefix -> allFlags.getValue(prefix).isPresent());
    }
}
