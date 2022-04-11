package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD_TASK_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD_TASK_DESCRIPTION;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code AddTaskCommand} object
 */
public class AddTaskCommandParser implements Parser<AddTaskCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code AddTaskCommand}
     * and returns a {@code AddTaskCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ADD_TASK_DESCRIPTION,
                PREFIX_ADD_TASK_DEADLINE);

        // Throw exception if more than 1 description or deadline prefix exists
        checkMultiplePrefixTokens(argMultimap);

        // Throw exception if user input does not contain any description prefix
        checkDescriptionPrefixEmpty(argMultimap);

        // Throw exception if deadline does not adhere to specified format
        checkDeadlineFormat(argMultimap);

        // Throw exception if deadline is invalid
        checkDeadlineValidity(argMultimap);

        // Throw exception if deadline is before today's date
        checkDeadlineIsBeforeToday(argMultimap);

        // Throw exception if description contains deadline prefix
        checkDeadlinePrefixInDescription(argMultimap);

        String description = argMultimap.getValue(PREFIX_ADD_TASK_DESCRIPTION).orElse("");
        String deadline = argMultimap.getValue(PREFIX_ADD_TASK_DEADLINE).orElse("");

        if (deadline.equals("")) {
            // task without deadline set
            return new AddTaskCommand(description);
        } else {
            // task with deadline set
            return new AddTaskCommand(description, deadline);
        }
    }

    /**
     * Check if more than 1 description or deadline prefix token exists in user input.
     *
     * @param argMultimap Tokenized user input
     * @throws ParseException Throw exception if more than 1 of the same prefix exists
     */
    private void checkMultiplePrefixTokens(ArgumentMultimap argMultimap) throws ParseException {
        List<String> description = argMultimap.getAllValues(PREFIX_ADD_TASK_DESCRIPTION);
        List<String> deadline = argMultimap.getAllValues(PREFIX_ADD_TASK_DEADLINE);

        // Throw exception if more than 1 description or deadline token is used
        if (description.size() > 1 || deadline.size() > 1) {
            // more than 1 "d/" or "t/" prefix were used, meaning that it is wrong format
            throw new ParseException("Duplicated prefix detected in input!\n" + AddTaskCommand.MESSAGE_USAGE);
        }
    }

    /**
     * Check if description prefix token exists in user input.
     *
     * @param argMultimap Tokenized user input
     * @throws ParseException Throw exception if description prefix token does not exist
     */
    private void checkDescriptionPrefixEmpty(ArgumentMultimap argMultimap) throws ParseException {
        String description = argMultimap.getValue(PREFIX_ADD_TASK_DESCRIPTION).orElse("");
        if (description.equals("")) {
            throw new ParseException("Description is compulsory!\n" + AddTaskCommand.MESSAGE_USAGE);
        }
    }

    /**
     * Check if user's deadline input follows dd/MM/yyyy format.
     *
     * @param argMultimap Tokenized user input
     * @throws ParseException Throw exception if deadline does not follow the specified format
     */
    private void checkDeadlineFormat(ArgumentMultimap argMultimap) throws ParseException {
        String deadline = argMultimap.getValue(PREFIX_ADD_TASK_DEADLINE).orElse("");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // If deadline is empty, return immediately without checking the format
        if (deadline.equals("")) {
            return;
        }

        try {
            dateTimeFormatter.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new ParseException("Deadline is not in dd/mm/yyyy!\n" + AddTaskCommand.MESSAGE_USAGE);
        }
    }

    /**
     * Check if user's deadline is a valid date.
     *
     * @param argMultimap Tokenized user input
     * @throws ParseException Throw exception if deadline is not a valid date
     */
    private void checkDeadlineValidity(ArgumentMultimap argMultimap) throws ParseException {
        String deadline = argMultimap.getValue(PREFIX_ADD_TASK_DEADLINE).orElse("");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        if (deadline.equals("")) {
            return;
        }

        try {
            // Check if user's deadline input is a valid date
            simpleDateFormat.setLenient(false);
            simpleDateFormat.parse(deadline);
        } catch (java.text.ParseException e) {
            throw new ParseException("Invalid date input!\n" + AddTaskCommand.MESSAGE_USAGE);
        }
    }

    /**
     * Check if user's deadline is before today's date.
     *
     * @param argMultimap Tokenized user input
     * @throws ParseException Throw exception if deadline is before today's date
     */
    private void checkDeadlineIsBeforeToday(ArgumentMultimap argMultimap) throws ParseException {
        String deadline = argMultimap.getValue(PREFIX_ADD_TASK_DEADLINE).orElse("");

        if (deadline.equals("")) {
            return;
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate today = LocalDate.now(ZoneId.systemDefault());
        LocalDate date = LocalDate.parse(deadline, dateTimeFormatter);
        if (date.isBefore(today)) {
            throw new ParseException("Deadline is before today's date!\n" + AddTaskCommand.MESSAGE_USAGE);
        }
    }

    /**
     * Check if user's description contains deadline prefix.
     *
     * @param argMultimap Tokenized user input
     * @throws ParseException Throw exception if description contains deadline prefix
     */
    private void checkDeadlinePrefixInDescription(ArgumentMultimap argMultimap) throws ParseException {
        String description = argMultimap.getValue(PREFIX_ADD_TASK_DESCRIPTION).orElse("");

        if (description.contains("t/") || description.contains("d/")) {
            // if deadline or description token is used in the description
            throw new ParseException("You cannot have 't/' or 'd/' prefix in the description!\n"
                    + AddTaskCommand.MESSAGE_USAGE);
        }
    }
}
