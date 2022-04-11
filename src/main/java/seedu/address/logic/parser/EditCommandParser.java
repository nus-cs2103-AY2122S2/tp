package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOBTITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRONOUN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.label.Label;
import seedu.address.model.person.Address;
import seedu.address.model.person.EditPersonDescriptor;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Pronoun;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args, Person personToEdit) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_COMPANY,
                        PREFIX_JOBTITLE, PREFIX_PRONOUN, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        //----Single-field data----
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }

        if (argMultimap.getValue(PREFIX_COMPANY).isPresent()) {
            editPersonDescriptor.setCompany(ParserUtil.parseCompany(argMultimap.getValue(PREFIX_COMPANY).get()));
        }

        if (argMultimap.getValue(PREFIX_JOBTITLE).isPresent()) {
            editPersonDescriptor.setJobTitle(ParserUtil.parseJobTitle(argMultimap.getValue(PREFIX_JOBTITLE).get()));
        }
        //----Multi-field data-----
        parseNumbersForEdit(argMultimap.getAllValues(PREFIX_PHONE)).ifPresent(editPersonDescriptor::setNumbers);
        parseAddressesForEdit(argMultimap.getAllValues(PREFIX_ADDRESS)).ifPresent(editPersonDescriptor::setAddresses);
        parseEmailsForEdit(argMultimap.getAllValues(PREFIX_EMAIL)).ifPresent(editPersonDescriptor::setEmails);
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);
        parsePronounsForEdit(argMultimap.getAllValues(PREFIX_PRONOUN)).ifPresent(editPersonDescriptor::setPronouns);

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(editPersonDescriptor, personToEdit);
    }

    private Optional<HashMap<Label, Email>> parseEmailsForEdit(Collection<String> emails) throws ParseException {
        assert emails != null;

        if (emails.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> emailCollection = emails.size() == 1 && emails.contains("")
                ? Collections.emptySet() : emails;
        return Optional.of(ParserUtil.parseEmails(emailCollection));
    }

    private Optional<HashMap<Label, Phone>> parseNumbersForEdit(Collection<String> numbers) throws ParseException {
        assert numbers != null;

        if (numbers.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> numberCollection = numbers.size() == 1 && numbers.contains("")
                ? Collections.emptySet() : numbers;
        return Optional.of(ParserUtil.parseNumbers(numberCollection));
    }

    private Optional<HashMap<Label, Address>> parseAddressesForEdit(Collection<String> addresses)
            throws ParseException {
        assert addresses != null;

        if (addresses.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> addressCollection = addresses.size() == 1 && addresses.contains("")
                ? Collections.emptySet() : addresses;
        return Optional.of(ParserUtil.parseAddresses(addressCollection));
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
     * Parses {@code Collection<String> pronouns} into a {@code Set<Pronoun>} if {@code pronouns} is non-empty.
     * If {@code pronouns} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Pronoun>} containing zero tags.
     */
    private Optional<Set<Pronoun>> parsePronounsForEdit(Collection<String> pronouns) throws ParseException {
        assert pronouns != null;

        if (pronouns.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> pronounSet = pronouns.size() == 1 && pronouns.contains("")
                ? Collections.emptySet() : pronouns;
        return Optional.of(ParserUtil.parsePronouns(pronounSet));
    }
}
