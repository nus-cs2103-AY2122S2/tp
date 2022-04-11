package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATETIME;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_NEED_AT_LEAST_ONE_VALID_PARAMETER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASKNAME;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditTaskCommand;
import seedu.address.logic.commands.EditTaskCommand.EditTaskDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditTaskCommand object
 */
public class EditTaskCommandParser implements Parser<EditTaskCommand> {
    private final String dateTimePattern = "dd-MM-yyyy HHmm";
    private final SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(dateTimePattern);

    /**
     * Parses the given {@code String} of arguments in the context of the EditTaskCommand
     * and returns an EditTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TASKNAME, PREFIX_DATETIME, PREFIX_TAG, PREFIX_LINK);

        if (!arePrefixesPresent(argMultimap, PREFIX_TASKNAME) && !arePrefixesPresent(argMultimap, PREFIX_DATETIME)
                && !arePrefixesPresent(argMultimap, PREFIX_TAG) && !arePrefixesPresent(argMultimap, PREFIX_LINK)) {
            String errorMessage = MESSAGE_NEED_AT_LEAST_ONE_VALID_PARAMETER;
            throw new ParseException(String.format(errorMessage, EditTaskCommand.MESSAGE_USAGE));
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        EditTaskDescriptor editTaskDescriptor = new EditTaskDescriptor();
        if (argMultimap.getValue(PREFIX_TASKNAME).isPresent()) {
            String taskName = ParserUtil.parseTaskName(argMultimap.getValue(PREFIX_TASKNAME));
            editTaskDescriptor.setName(taskName);
        }
        if (argMultimap.getValue(PREFIX_DATETIME).isPresent()) {
            try {
                dateTimeFormatter.setLenient(false);
                String dateTimeString = argMultimap.getValue(PREFIX_DATETIME).get();
                if (dateTimeString.contains(",")) {
                    String[] splits = dateTimeString.split(",");
                    editTaskDescriptor.setDate(convertToLocalDateTime(dateTimeFormatter.parse(splits[0])));
                    editTaskDescriptor.setEndDate(convertToLocalDateTime(dateTimeFormatter.parse(splits[1])));
                } else {
                    editTaskDescriptor.setDate(convertToLocalDateTime(dateTimeFormatter.parse(dateTimeString)));
                    editTaskDescriptor.setEndDate(null);
                }
            } catch (java.text.ParseException e) {
                throw new ParseException(String.format(MESSAGE_INVALID_DATETIME, EditTaskCommand.MESSAGE_USAGE));
            }
        }

        if (argMultimap.getValue(PREFIX_LINK).isPresent()) {
            editTaskDescriptor.setLink(ParserUtil.parseLink(argMultimap.getValue(PREFIX_LINK)));
        }

        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editTaskDescriptor::setTags);

        if (!editTaskDescriptor.isAnyFieldEdited()) {
            throw new ParseException(String.format(EditTaskCommand.MESSAGE_NOT_EDITED, EditTaskCommand.MESSAGE_USAGE));
        }

        return new EditTaskCommand(index, editTaskDescriptor);
    }

    private LocalDateTime convertToLocalDateTime(Date dateToConvert) {
        return new java.sql.Timestamp(
                dateToConvert.getTime()).toLocalDateTime();
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}
