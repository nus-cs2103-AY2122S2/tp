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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.DeleteFieldCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.label.Label;
import seedu.address.model.person.Address;
import seedu.address.model.person.EditPersonDescriptor;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Pronoun;
import seedu.address.model.tag.Tag;

public class DeleteFieldCommandParser {
    /**
     * Parses the user input to delete desired fields.
     *
     * @param args User input.
     * @param personToDeleteField Person being edited.
     * @return Command to delete fields of a person.
     * @throws ParseException For invalid user input.
     */
    public DeleteFieldCommand parse(String args, Person personToDeleteField) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_COMPANY, PREFIX_JOBTITLE,
                        PREFIX_PRONOUN, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteFieldCommand.MESSAGE_USAGE));
        }

        if (argMultimap.isEmpty()) {
            throw new ParseException(DeleteFieldCommand.MESSAGE_NO_PROVIDED_FIELD + "\n"
                    + DeleteFieldCommand.MESSAGE_USAGE);
        }

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            throw new ParseException(DeleteFieldCommand.MESSAGE_DELETE_NAME_FAILURE);
        }

        EditPersonDescriptor deleteFieldDescriptor = new EditPersonDescriptor(personToDeleteField);

        if (argMultimap.getValue(PREFIX_COMPANY).isPresent()) {
            deleteFieldDescriptor.setCompany(null);
        }

        if (argMultimap.getValue(PREFIX_JOBTITLE).isPresent()) {
            deleteFieldDescriptor.setJobTitle(null);
        }

        parseNumbersForDelete(argMultimap.getAllValues(PREFIX_PHONE), deleteFieldDescriptor);
        parseEmailsForDelete(argMultimap.getAllValues(PREFIX_EMAIL), deleteFieldDescriptor);
        parseAddressesForDelete(argMultimap.getAllValues(PREFIX_ADDRESS), deleteFieldDescriptor);
        parseTagsForDelete(argMultimap.getAllValues(PREFIX_TAG), deleteFieldDescriptor);
        parsePronounsForDelete(argMultimap.getAllValues(PREFIX_PRONOUN), deleteFieldDescriptor);

        return new DeleteFieldCommand(deleteFieldDescriptor, personToDeleteField);
    }

    void parseNumbersForDelete(Collection<String> numbersToBeDeleted,
            EditPersonDescriptor deleteFieldDescriptor) throws ParseException {
        Map<Label, Phone> numbers = new HashMap<>(deleteFieldDescriptor.getNumbers().orElse(new HashMap<>()));
        requireNonNull(numbersToBeDeleted);

        // delete all if no label provided
        if (CollectionUtil.hasEmptyString(numbersToBeDeleted)) {
            deleteFieldDescriptor.setNumbers(new HashMap<>());
        } else { // delete each number with label provided
            for (String label : numbersToBeDeleted) {
                if (!Label.isValidLabel(label)) {
                    throw new ParseException(Label.MESSAGE_CONSTRAINTS);
                }
                numbers.remove(new Label(label, false));
            }
            deleteFieldDescriptor.setNumbers(numbers);
        }

    }

    void parseEmailsForDelete(Collection<String> emailsToBeDeleted,
                               EditPersonDescriptor deleteFieldDescriptor) throws ParseException {
        Map<Label, Email> emails = new HashMap<>(deleteFieldDescriptor.getEmails().orElse(new HashMap<>()));
        requireNonNull(emailsToBeDeleted);

        // delete all if no label provided
        if (CollectionUtil.hasEmptyString(emailsToBeDeleted)) {
            deleteFieldDescriptor.setEmails(new HashMap<>());
        } else { // delete each email with label provided
            for (String label : emailsToBeDeleted) {
                if (!Label.isValidLabel(label)) {
                    throw new ParseException(Label.MESSAGE_CONSTRAINTS);
                }
                emails.remove(new Label(label, false));
            }
            deleteFieldDescriptor.setEmails(emails);
        }
    }

    void parseAddressesForDelete(Collection<String> addressesToBeDeleted,
                              EditPersonDescriptor deleteFieldDescriptor) throws ParseException {
        Map<Label, Address> addresses = new HashMap<>(deleteFieldDescriptor.getAddresses().orElse(new HashMap<>()));
        requireNonNull(addressesToBeDeleted);

        // delete all if no label provided
        if (CollectionUtil.hasEmptyString(addressesToBeDeleted)) {
            deleteFieldDescriptor.setAddresses(new HashMap<>());
        } else { // delete each address with label provided
            for (String label : addressesToBeDeleted) {
                if (!Label.isValidLabel(label)) {
                    throw new ParseException(Label.MESSAGE_CONSTRAINTS);
                }
                addresses.remove(new Label(label, false));
            }
            deleteFieldDescriptor.setAddresses(addresses);
        }
    }

    void parseTagsForDelete(Collection<String> tagsToBeDeleted,
                                 EditPersonDescriptor deleteFieldDescriptor) {
        Set<Tag> tags = new HashSet<>(deleteFieldDescriptor.getTags().orElse(new HashSet<>()));
        requireNonNull(tagsToBeDeleted);

        // delete all if no value provided
        if (CollectionUtil.hasEmptyString(tagsToBeDeleted)) {
            deleteFieldDescriptor.setTags(new HashSet<>());
        } else { // delete each tag with value provided
            tagsToBeDeleted.forEach(tag -> tags.remove(new Tag(tag)));
            deleteFieldDescriptor.setTags(tags);
        }
    }

    void parsePronounsForDelete(Collection<String> pronounsToBeDeleted,
                            EditPersonDescriptor deleteFieldDescriptor) {
        Set<Pronoun> pronouns = new HashSet<>(deleteFieldDescriptor.getPronouns().orElse(new HashSet<>()));
        requireNonNull(pronounsToBeDeleted);

        // delete all if no value provided
        if (CollectionUtil.hasEmptyString(pronounsToBeDeleted)) {
            deleteFieldDescriptor.setPronouns(new HashSet<>());
        } else { // delete each pronoun with value provided
            pronounsToBeDeleted.forEach(pronoun -> pronouns.remove(new Pronoun(pronoun)));
            deleteFieldDescriptor.setPronouns(pronouns);
        }
    }
}
