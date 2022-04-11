package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLATFORM_NAME_FLAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SOCIAL_MEDIA;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.edit.EditPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.socialmedia.SocialMedia;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    private static final Prefix[] ALL_EDIT_PARSER_PREFIXES =
            new Prefix[]{PREFIX_EMAIL, PREFIX_INDEX, PREFIX_NAME, PREFIX_PHONE,
                PREFIX_PLATFORM_NAME_FLAG, PREFIX_SOCIAL_MEDIA, PREFIX_TAG};

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE,
                PREFIX_EMAIL, PREFIX_SOCIAL_MEDIA, PREFIX_TAG, PREFIX_PLATFORM_NAME_FLAG, PREFIX_INDEX);

        Target target;

        try {
            target = ParserUtil.parseTarget(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        if (!argMultimap.atLeastOnePrefix(ALL_EDIT_PARSER_PREFIXES)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();

        if (argMultimap.noOtherPrefixes(PREFIX_INDEX, PREFIX_PLATFORM_NAME_FLAG, PREFIX_SOCIAL_MEDIA)) {
            return new EditSocialMediaCommandParser().parse(args);
        }

        if (!argMultimap.atLeastOnePrefix(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_SOCIAL_MEDIA, PREFIX_TAG)
                || argMultimap.doesPrefixesExist(PREFIX_PLATFORM_NAME_FLAG, PREFIX_INDEX)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditPersonCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }

        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);
        parseSocialsForEdit(argMultimap.getAllValues(PREFIX_SOCIAL_MEDIA)).ifPresent(editPersonDescriptor::setSocials);

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditPersonCommand.MESSAGE_NOT_EDITED);
        }

        return new EditPersonCommand(target, editPersonDescriptor);
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

    /**
     * Parses {@code Collection<String> socials} into a {@code Set<SocialMedia>} if {@code socials} is non-empty.
     * If {@code socials} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<SocialMedia>} containing zero socialMedias.
     */
    private Optional<List<SocialMedia>> parseSocialsForEdit(Collection<String> socials) throws ParseException {
        assert socials != null;

        if (socials.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> socialsSet = socials.size() == 1 && socials.contains("") ? Collections.emptySet() : socials;
        return Optional.of(ParserUtil.parseSocialMedias(socialsSet));
    }

}
