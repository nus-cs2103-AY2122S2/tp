package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.label.Label;
import seedu.address.model.tag.Tag;

/* This class was refactored from EditCommand into a separate file for reuse in multiple classes by @ckcherry23.
   It was slightly modified by the team members of 'Reache' (AY2122S2-CS2103T-W12-4) to suit our product */

//@@author

/**
 * Stores the details to edit the person with. Each non-empty field value will replace the
 * corresponding field value of the person.
 */
public class EditPersonDescriptor {
    private Name name;
    private HashMap<Label, Phone> numbers;
    private HashMap<Label, Email> emails;
    private HashMap<Label, Address> addresses;
    private Company company;
    private JobTitle jobTitle;
    private HashSet<Pronoun> pronouns;
    private HashSet<Tag> tags;

    public EditPersonDescriptor() {}

    /**
     * Copy constructor.
     * A defensive copy of {@code tags} is used internally.
     */
    public EditPersonDescriptor(EditPersonDescriptor toCopy) {
        setName(toCopy.name);
        setNumbers(toCopy.numbers);
        setEmails(toCopy.emails);
        setAddresses(toCopy.addresses);
        setTags(toCopy.tags);
        setCompany(toCopy.company);
        setJobTitle(toCopy.jobTitle);
        setPronouns(toCopy.pronouns);
    }

    /**
     * Person constructor.
     */
    public EditPersonDescriptor(Person toCopy) {
        requireNonNull(toCopy);

        setName(toCopy.getName());
        setNumbers(toCopy.getNumbers());
        setEmails(toCopy.getEmails());
        setAddresses(toCopy.getAddresses());
        setTags(toCopy.getTags());
        setCompany(toCopy.getCompany().isPresent() ? toCopy.getCompany().get() : null);
        setJobTitle(toCopy.getJobTitle().isPresent() ? toCopy.getJobTitle().get() : null);
        setPronouns(toCopy.getPronouns());
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(name, numbers, emails, addresses, tags, company, pronouns, jobTitle);
    }

    //----Single data fields----
    //Company
    public void setCompany(Company company) {
        this.company = company;
    }

    public Optional<Company> getCompany() {
        return Optional.ofNullable(company);
    }

    //JobTitle
    public void setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Optional<JobTitle> getJobTitle() {
        return Optional.ofNullable(jobTitle);
    }

    //Name
    public void setName(Name name) {
        this.name = name;
    }

    public Optional<Name> getName() {
        return Optional.ofNullable(name);
    }

    //-----Multiple data fields----
    //Phone
    public void setNumbers(Map<Label, Phone> numbers) {
        this.numbers = (numbers != null) ? new HashMap<>(numbers) : null;
    }

    public Optional<Map<Label, Phone>> getNumbers() {
        return (numbers != null) ? Optional.of(Collections.unmodifiableMap(numbers)) : Optional.empty();
    }

    //Email
    public void setEmails(Map<Label, Email> emails) {
        this.emails = (emails != null) ? new HashMap<>(emails) : null;
    }

    public Optional<Map<Label, Email>> getEmails() {
        return (emails != null) ? Optional.of(Collections.unmodifiableMap(emails)) : Optional.empty();
    }

    //Address
    public void setAddresses(Map<Label, Address> addresses) {
        this.addresses = (addresses != null) ? new HashMap<>(addresses) : null;
    }

    public Optional<Map<Label, Address>> getAddresses() {
        return (addresses != null) ? Optional.of(Collections.unmodifiableMap(addresses)) : Optional.empty();
    }

    //Tags

    /**
     * Sets {@code tags} to this object's {@code tags}.
     * A defensive copy of {@code tags} is used internally.
     */
    public void setTags(Set<Tag> tags) {
        this.tags = (tags != null) ? new HashSet<>(tags) : null;
    }

    /**
     * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     * Returns {@code Optional#empty()} if {@code tags} is null.
     */
    public Optional<Set<Tag>> getTags() {
        return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
    }

    //Pronouns

    /**
     * Sets {@code pronouns} to this object's {@code pronouns}.
     * A defensive copy of {@code pronouns} is used internally.
     */
    public void setPronouns(Set<Pronoun> pronouns) {
        this.pronouns = (pronouns != null) ? new HashSet<>(pronouns) : null;
    }

    /**
     * Returns an unmodifiable pronouns set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     * Returns {@code Optional#empty()} if {@code pronouns} is null.
     */
    public Optional<Set<Pronoun>> getPronouns() {
        return (pronouns != null) ? Optional.of(Collections.unmodifiableSet(pronouns)) : Optional.empty();
    }
    //

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditPersonDescriptor)) {
            return false;
        }

        // state check
        EditPersonDescriptor e = (EditPersonDescriptor) other;

        return getName().equals(e.getName())
                && getCompany().equals(e.getCompany())
                && getJobTitle().equals(e.getJobTitle())
                && getPronouns().equals(e.getPronouns())
                && getNumbers().equals(e.getNumbers())
                && getEmails().equals(e.getEmails())
                && getAddresses().equals(e.getAddresses())
                && getTags().equals(e.getTags());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("EditPersonDescriptor: [");
        builder.append("Name: ")
                .append(getName())
                .append("; Company: ")
                .append(getCompany())
                .append("; Job Title: ")
                .append(getJobTitle());

        if (getPronouns().isPresent()) {
            Set<Pronoun> pronouns = getPronouns().get();
            builder.append("; Pronouns: ");
            pronouns.forEach(builder::append);
        }

        if (getTags().isPresent()) {
            Set<Tag> tags = getTags().get();
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        if (getNumbers().isPresent()) {
            Map<Label, Phone> numbers = getNumbers().get();
            builder.append("; Numbers: ");
            numbers.forEach((label, number) -> builder.append(number.phone).append(" l/").append(label).append(" "));
        }

        if (getAddresses().isPresent()) {
            Map<Label, Address> addresses = getAddresses().get();
            builder.append("; Addresses: ");
            addresses.forEach((label, address) -> builder.append(address.address).append(" l/")
                    .append(label).append(" "));
        }

        if (getEmails().isPresent()) {
            Map<Label, Email> emails = getEmails().get();
            builder.append("; Emails: ");
            emails.forEach((label, email) -> builder.append(email.email).append(" l/").append(label).append(" "));
        }
        builder.append("]");
        return builder.toString();
    }
}
