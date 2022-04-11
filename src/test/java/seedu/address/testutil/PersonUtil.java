package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOBTITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRONOUN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Map;
import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.label.Label;
import seedu.address.model.person.Address;
import seedu.address.model.person.EditPersonDescriptor;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Pronoun;
import seedu.address.model.tag.Tag;


/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();

        sb.append(PREFIX_NAME).append(person.getName().fullName).append(" ");

        person.getCompany().map(c -> sb.append(PREFIX_COMPANY).append(c.company).append(" "));

        person.getJobTitle().map(j -> sb.append(PREFIX_JOBTITLE).append(j.jobTitle).append(" "));

        person.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        person.getPronouns().stream().forEach(
            s -> sb.append(PREFIX_PRONOUN + s.pronoun + " ")
        );
        person.getAddresses().forEach((
            label, address) -> sb.append(PREFIX_ADDRESS + address.address + " l/" + label + " ")
        );
        person.getEmails().forEach((
            label, email) -> sb.append(PREFIX_EMAIL + email.email + " l/" + label + " ")
        );
        person.getNumbers().forEach((
            label, number) -> sb.append(PREFIX_PHONE + number.phone + " l/" + label + " ")
        );

        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getCompany().ifPresent(company -> sb.append(PREFIX_COMPANY).append(company.company).append(" "));
        descriptor.getJobTitle().ifPresent(jobTitle -> sb.append(PREFIX_JOBTITLE)
                .append(jobTitle.jobTitle).append(" "));

        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG + " ");
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }

        if (descriptor.getPronouns().isPresent()) {
            Set<Pronoun> pronouns = descriptor.getPronouns().get();
            if (pronouns.isEmpty()) {
                sb.append(PREFIX_PRONOUN + " ");
            } else {
                pronouns.forEach(s -> sb.append(PREFIX_PRONOUN).append(s.pronoun).append(" "));
            }
        }

        if (descriptor.getNumbers().isPresent()) {
            Map<Label, Phone> numbers = descriptor.getNumbers().get();
            if (numbers.isEmpty()) {
                sb.append(PREFIX_PHONE + " ");
            } else {
                numbers.forEach((label, number) -> sb.append(PREFIX_PHONE + number.phone + " l/" + label + " "));
            }
        }

        if (descriptor.getEmails().isPresent()) {
            Map<Label, Email> emails = descriptor.getEmails().get();
            if (emails.isEmpty()) {
                sb.append(PREFIX_EMAIL + " ");
            } else {
                emails.forEach((label, email) -> sb.append(PREFIX_EMAIL + email.email + " l/" + label + " "));
            }
        }

        if (descriptor.getAddresses().isPresent()) {
            Map<Label, Address> addresses = descriptor.getAddresses().get();
            if (addresses.isEmpty()) {
                sb.append(PREFIX_ADDRESS + " ");
            } else {
                addresses.forEach((label, address) -> sb.append(PREFIX_ADDRESS + address.address
                        + " l/" + label + " "));
            }
        }

        return sb.toString();
    }
}
