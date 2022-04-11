package seedu.address.testutil;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.model.label.Label;
import seedu.address.model.person.Address;
import seedu.address.model.person.Company;
import seedu.address.model.person.EditPersonDescriptor;
import seedu.address.model.person.Email;
import seedu.address.model.person.JobTitle;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Pronoun;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditPersonDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Person person) {
        descriptor = new EditPersonDescriptor();

        descriptor.setName(person.getName());
        descriptor.setCompany(person.getCompany().orElse(null));
        descriptor.setJobTitle(person.getJobTitle().orElse(null));
        descriptor.setNumbers(person.getNumbers());
        descriptor.setEmails(person.getEmails());
        descriptor.setAddresses(person.getAddresses());
        descriptor.setPronouns(person.getPronouns());
        descriptor.setTags(person.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Company} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withCompany(String company) {
        descriptor.setCompany(new Company(company));
        return this;
    }

    /**
     * Sets the {@code JobTitle} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withJobTitle(String jobTitle) {
        descriptor.setJobTitle(new JobTitle(jobTitle));
        return this;
    }


    /**
     * Sets the numbers of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withNumbers(String... numbers) {
        Map<Label, Phone> parsedNumbers = Arrays.stream(numbers)
                .map(phoneLabelPair -> phoneLabelPair.split(" l/"))
                .collect(Collectors.toMap(phoneLabelPair -> phoneLabelPair.length == 1
                                ? new Label(phoneLabelPair[0], true)
                                : new Label(phoneLabelPair[1], false),
                    phoneLabelPair -> new Phone(phoneLabelPair[0])));

        descriptor.setNumbers(parsedNumbers);
        return this;
    }

    /**
     * Sets the emails of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withEmails(String... emails) {
        Map<Label, Email> parsedEmails = Arrays.stream(emails)
                .map(emailLabelPair -> emailLabelPair.split(" l/"))
                .collect(Collectors.toMap(emailLabelPair -> emailLabelPair.length == 1
                                ? new Label(emailLabelPair[0], true)
                                : new Label(emailLabelPair[1], false),
                    emailLabelPair -> new Email(emailLabelPair[0])));

        descriptor.setEmails(parsedEmails);
        return this;
    }

    /**
     * Sets the addresses of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withAddresses(String... addresses) {
        Map<Label, Address> parsedAddresses = Arrays.stream(addresses)
                .map(addressLabelPair -> addressLabelPair.split(" l/"))
                .collect(Collectors.toMap(addressLabelPair -> addressLabelPair.length == 1
                                ? new Label(addressLabelPair[0], true)
                                : new Label(addressLabelPair[1], false),
                    addressLabelPair -> new Address(addressLabelPair[0])));

        descriptor.setAddresses(parsedAddresses);
        return this;
    }

    /**
     * Parses the {@code pronouns} into a {@code Set<Pronouns>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withPronouns(String... pronouns) {
        Set<Pronoun> pronounSet = Stream.of(pronouns).map(Pronoun::new).collect(Collectors.toSet());
        descriptor.setPronouns(pronounSet);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}
