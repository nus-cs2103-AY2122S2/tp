package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICATION_STATUS_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DETAILS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_SLOT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOBTITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditApplicationDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_JOBTITLE, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_ADDRESS, PREFIX_INTERVIEW_SLOT, PREFIX_DETAILS, PREFIX_TAG, PREFIX_PRIORITY_TAG,
                        PREFIX_APPLICATION_STATUS_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditApplicationDescriptor editApplicationDescriptor = new EditApplicationDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editApplicationDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_JOBTITLE).isPresent()) {
            editApplicationDescriptor.setJobTitle(ParserUtil
                    .parseJobTitle(argMultimap.getValue(PREFIX_JOBTITLE).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editApplicationDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editApplicationDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editApplicationDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }

        if (argMultimap.getValue(PREFIX_INTERVIEW_SLOT).isPresent()) {
            editApplicationDescriptor.setInterviewSlot(ParserUtil
                    .parseInterviewSlot(argMultimap.getValue(PREFIX_INTERVIEW_SLOT).get()));
        }

        if (argMultimap.getValue(PREFIX_DETAILS).isPresent()) {
            editApplicationDescriptor.setDetails(ParserUtil.parseDetails(argMultimap.getValue(PREFIX_DETAILS).get()));
        }

        parseTagsForEdit(argMultimap).ifPresent(editApplicationDescriptor::setTags);

        if (!editApplicationDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editApplicationDescriptor);
    }

    /**
     * Parses {@code ArgumentMultimap argMultimap} into a {@code Set<Tag>} if {@code argMultimap} is non-empty.
     * If {@code argMultimap} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     * Tag includes Tags and PriorityTag.
     */
    private Optional<Set<Tag>> parseTagsForEdit(ArgumentMultimap argMultimap) throws ParseException {
        Collection<String> genericTagsSet = argMultimap.getAllValues(PREFIX_TAG);
        Optional<String> priorityTagSet = argMultimap.getValue(PREFIX_PRIORITY_TAG);
        Optional<String> applicationStatusSet = argMultimap.getValue(PREFIX_APPLICATION_STATUS_TAG);

        // Checks if argMultimap contains any generic tags, priority tags or application status tag else returns an
        // empty Optional instance.
        if (genericTagsSet.isEmpty() && !priorityTagSet.isPresent() && !applicationStatusSet.isPresent()) {
            return Optional.empty();
        }

        // Create tagSet using generic tags if any.
        // This tagSet can either be empty or non-empty Set<Tag>.
        Set<Tag> tagSet = ParserUtil.parseTags(parseGenericTagsForEdit(genericTagsSet));

        // Checks for tag of type PRIORITY and adds it to created tagSet.
        if (priorityTagSet.isPresent()) {
            Tag priorityTag = ParserUtil.parsePriorityTag(argMultimap.getValue(PREFIX_PRIORITY_TAG).get());
            tagSet.add(priorityTag);
        }

        // Checks for tag of type APPLICATION_STATUS and adds it to created tagSet.
        if (applicationStatusSet.isPresent()) {
            Tag applicationStatusTag = ParserUtil
                    .parseApplicationStatusTag(argMultimap.getValue(PREFIX_APPLICATION_STATUS_TAG).get());
            tagSet.add(applicationStatusTag);
        }

        return Optional.of(tagSet);
    }

    /**
     * Parses {@code Collection<String> genericTags} into a {@code Collection<String>} if {@code genericTags} is
     * non-empty.
     * If {@code genericTags} contain only one element which is an empty string, it will be parsed into a
     * {@code Collection<String>} containing zero tags.
     */
    private Collection<String> parseGenericTagsForEdit(Collection<String> genericTags) {
        assert genericTags != null;

        return genericTags.size() == 1 && genericTags.contains("") ? Collections.emptySet() : genericTags;
    }

}
