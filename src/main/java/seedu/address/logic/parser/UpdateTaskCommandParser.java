package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD_TASK_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD_TASK_DESCRIPTION;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import seedu.address.logic.commands.UpdateTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class UpdateTaskCommandParser implements Parser <UpdateTaskCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the UpdateTaskCommand
     * and returns an UpdateTask object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UpdateTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_ADD_TASK_DESCRIPTION, PREFIX_ADD_TASK_DEADLINE);

        Integer taskId;

        taskId = ParserUtil.parseNumber(argMultimap.getPreamble());

        // Throw exception if more than 1 description or deadline prefix exists
        checkMultiplePrefixTokens(argMultimap);

        // Throw exception if no prefix exist
        checkMinimumOnePrefix(argMultimap);

        // Throw exception if deadline does not adhere to specified format
        checkDeadlineFormat(argMultimap);

        // Throw exception if empty prefix tokens are found
        checkEmptyPrefix(argMultimap);

        // Throw exception if deadline is invalid
        checkDeadlineValidity(argMultimap);

        // Throw exception if deadline is before today's date
        checkDeadlineIsBeforeToday(argMultimap);

        // Throw exception if description contains deadline prefix
        checkDeadlinePrefixInDescription(argMultimap);

        UpdateTaskCommand.UpdateTaskDescriptor updateTaskDescriptor = new UpdateTaskCommand.UpdateTaskDescriptor();

        if (argMultimap.getValue(PREFIX_ADD_TASK_DESCRIPTION).isPresent()) {
            updateTaskDescriptor.setDescription(argMultimap.getValue(PREFIX_ADD_TASK_DESCRIPTION).get());
        }
        if (argMultimap.getValue(PREFIX_ADD_TASK_DEADLINE).isPresent()) {
            updateTaskDescriptor.setDeadline(argMultimap.getValue(PREFIX_ADD_TASK_DEADLINE).get());
        }

        return new UpdateTaskCommand(taskId, updateTaskDescriptor);
    }

    /**
     * Check if more than 1 description or deadline prefix token exists in user input.
     * Adapted from AddTaskCommandParser
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
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateTaskCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Check if description prefix token exists in user input.
     * Adapted from AddTaskCommandParser
     *
     * @param argMultimap Tokenized user input
     * @throws ParseException Throw exception if prefix token does not exist
     */
    private void checkMinimumOnePrefix(ArgumentMultimap argMultimap) throws ParseException {
        List<String> description = argMultimap.getAllValues(PREFIX_ADD_TASK_DESCRIPTION);
        List<String> deadline = argMultimap.getAllValues(PREFIX_ADD_TASK_DEADLINE);

        if (description.size() + deadline.size() < 1) {
            // no prefix
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UpdateTaskCommand.MESSAGE_NOT_EDITED));
        }
    }

    /**
     * Check if prefix token values are empty.
     * Adapted from AddTaskCommandParser
     *
     * @param argMultimap Tokenized user input
     * @throws ParseException Throw exception if either token prefix value is empty
     */
    private void checkEmptyPrefix(ArgumentMultimap argMultimap) throws ParseException {
        if (argMultimap.getValue(PREFIX_ADD_TASK_DESCRIPTION).isPresent()) {
            String description = argMultimap.getValue(PREFIX_ADD_TASK_DESCRIPTION).orElse("");
            if (description.equals("")) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    UpdateTaskCommand.MESSAGE_EMPTY_PARAMETERS));
            }
        }
        if (argMultimap.getValue(PREFIX_ADD_TASK_DEADLINE).isPresent()) {
            String deadline = argMultimap.getValue(PREFIX_ADD_TASK_DEADLINE).orElse("");
            if (deadline.equals("")) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    UpdateTaskCommand.MESSAGE_EMPTY_PARAMETERS));
            }
        }
    }

    /**
     * Check if user's deadline input follows dd/MM/yyyy format.
     * Adapted from AddTaskCommandParser
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
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateTaskCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Check if user's deadline is a valid date.
     * Adapated from AddTaskCommandParser
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
            throw new ParseException("Invalid date input!\n" + UpdateTaskCommand.MESSAGE_USAGE);
        }
    }

    /**
     * Check if user's deadline is before today's date.
     * Adapted from AddTaskCommandParser
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
            throw new ParseException("Deadline is before today's date!\n" + UpdateTaskCommand.MESSAGE_USAGE);
        }
    }

    /**
     * Check if user's description contains deadline prefix.
     * Adapted from AddTaskCommandParser
     *
     * @param argMultimap Tokenized user input
     * @throws ParseException Throw exception if description contains deadline prefix
     */
    private void checkDeadlinePrefixInDescription(ArgumentMultimap argMultimap) throws ParseException {
        String description = argMultimap.getValue(PREFIX_ADD_TASK_DESCRIPTION).orElse("");

        if (description.contains("t/")) {
            // if deadline token is used in the description
            throw new ParseException("You cannot have 't/' prefix in the description!\n"
                + UpdateTaskCommand.MESSAGE_USAGE);
        }
    }

}
