package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICATION_STATUS_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOBTITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.application.JobTitle;
import seedu.address.model.application.JobTitleContainsKeywordsPredicate;
import seedu.address.model.application.Name;
import seedu.address.model.application.NameContainsKeywordsPredicate;
import seedu.address.model.application.TagContainsKeywordsPredicate;
import seedu.address.model.tag.ApplicationStatusTagType;
import seedu.address.model.tag.PriorityTagType;
import seedu.address.model.tag.Tag;



/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_JOBTITLE, PREFIX_TAG,
                        PREFIX_PRIORITY_TAG, PREFIX_APPLICATION_STATUS_TAG);

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String keyword = checkNotEmptyName(argMultimap.getValue(PREFIX_NAME).get());
            return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(keyword.split(" "))));
        }
        if (argMultimap.getValue(PREFIX_JOBTITLE).isPresent()) {
            String keyword = checkNotEmptyJobTitle(argMultimap.getValue(PREFIX_JOBTITLE).get());
            return new FindCommand(new JobTitleContainsKeywordsPredicate(Arrays.asList(keyword.split(" "))));
        }
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            Collection<String> keywords = checkNotEmptyTags(argMultimap.getAllValues(PREFIX_TAG));
            return new FindCommand(new TagContainsKeywordsPredicate(new ArrayList<>(keywords)));
        }
        if (argMultimap.getValue(PREFIX_PRIORITY_TAG).isPresent()) {
            String keyword = checkNotEmptyPriorityTag(argMultimap.getValue(PREFIX_PRIORITY_TAG).get());
            return new FindCommand(new TagContainsKeywordsPredicate(Arrays.asList(keyword)));
        }
        if (argMultimap.getValue(PREFIX_APPLICATION_STATUS_TAG).isPresent()) {
            String keyword = checkNotEmptyApplicationStatusTag(
                    argMultimap.getValue(PREFIX_APPLICATION_STATUS_TAG).get());
            return new FindCommand(new TagContainsKeywordsPredicate(Arrays.asList(keyword)));
        }

        throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    /**
     * Checks whether the given input is in the correct format or non-empty.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @return Original input.
     * @throws ParseException if the given {@code name} is invalid.
     */
    public String checkNotEmptyName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return trimmedName;
    }

    /**
     * Checks whether the given input is in the correct format or non-empty.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @return Original input.
     * @throws ParseException if the given {@code jobTitle} is invalid.
     */
    public String checkNotEmptyJobTitle(String jobTitle) throws ParseException {
        requireNonNull(jobTitle);
        String trimmedJobTitle = jobTitle.trim();
        if (!JobTitle.isValidJobTitle(trimmedJobTitle)) {
            throw new ParseException(JobTitle.MESSAGE_CONSTRAINTS);
        }
        return trimmedJobTitle;
    }

    /**
     * Checks whether the given input is in the correct format or non-empty.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @return Original input.
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public String checkNotEmptyApplicationStatusTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim().toUpperCase();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        if (!ApplicationStatusTagType.contains(trimmedTag)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddCommand.MESSAGE_APPLICATION_STATUS_TAG));
        }
        return trimmedTag;
    }

    /**
     * Checks whether the given input is in the correct format or non-empty.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @return Original input.
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public String checkNotEmptyPriorityTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim().toUpperCase();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        if (!PriorityTagType.contains(trimmedTag)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddCommand.MESSAGE_PRIORITY_TAG));
        }
        return trimmedTag;
    }

    /**
     * Checks whether the given input is in the correct format or non-empty.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @return Original input.
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public Collection<String> checkNotEmptyTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        if (tags.isEmpty() || (tags.size() == 1 && tags.iterator().next().isEmpty())) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        for (String tagName : tags) {
            if (!Tag.isValidTagName(tagName)) {
                throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
            }
        }
        return tags;
    }
}
