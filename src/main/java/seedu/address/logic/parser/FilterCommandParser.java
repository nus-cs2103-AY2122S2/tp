package seedu.address.logic.parser;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.lab.Lab;
import seedu.address.model.person.lab.LabStatus;
import seedu.address.model.person.lab.StudentHasLabPredicate;

import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class FilterCommandParser {

    private static final Pattern LAB_SPECIFICATION_FORMAT = Pattern.compile("lab(?<lab>\\d)\\s+(?<labStatus>\\w+)");
    private static final Hashtable<String,String> LAB_STATUS_IDENTIFIER = new Hashtable<>() {{
        put("g", "graded");
        put("s", "submitted");
        put("u", "unsubmitted");
    }};

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns a FilterCommand object for execution
     * @throws ParseException if the user input does not conform the expected input
     */
    public FilterCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        final Matcher matcher = LAB_SPECIFICATION_FORMAT.matcher(trimmedArgs);

        final String labString = matcher.group("lab");
        final String labStatusString = matcher.group("labStatus");

        final Lab filterLab = new Lab(labString).of(LAB_STATUS_IDENTIFIER.get(labStatusString));

        return new FilterCommand(new StudentHasLabPredicate(filterLab));

    }
}
