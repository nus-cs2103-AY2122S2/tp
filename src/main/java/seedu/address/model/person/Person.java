package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the hustle book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private Flag flag;
    private final Set<Tag> tags = new HashSet<>();
    private final PrevDateMet prevDateMet;
    private final Info info;
    private final Salary salary;
    private ScheduledMeeting scheduledMeeting;


    /**
     * Constructor for Person object where every field is present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Flag flag, Set<Tag> tags,
                  PrevDateMet prevDateMet, Salary salary, Info info, ScheduledMeeting scheduledMeeting) {
        requireAllNonNull(name, phone, email, address, flag, tags, prevDateMet, salary, info, scheduledMeeting);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.flag = flag;
        this.tags.addAll(tags);
        this.prevDateMet = prevDateMet;
        this.salary = salary;
        this.info = info;
        this.scheduledMeeting = scheduledMeeting;
    }

    /**
     * Constructor for Person object where every field is present and not null except scheduled meeting.
     */
    public Person(Name name, Phone phone, Email email, Address address, Flag flag, Set<Tag> tags,
                  PrevDateMet prevDateMet, Salary salary, Info info) {
        requireAllNonNull(name, phone, email, address, flag, tags, prevDateMet, salary, info);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.flag = flag;
        this.tags.addAll(tags);
        this.prevDateMet = prevDateMet;
        this.salary = salary;
        this.info = info;
        this.scheduledMeeting = new ScheduledMeeting();
    }

    /**
     * Constructor for Person object where every field is present and not null except prevDateMet, salary, info
     * and scheduled meeting.
     * Previous date met will be set to the current date as the user might meet up with the client
     * for the first time.
     * Salary will be set to the default value, "0".
     */
    public Person(Name name, Phone phone, Email email, Address address, Flag flag, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, flag, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.flag = flag;
        this.tags.addAll(tags);
        this.prevDateMet = new PrevDateMet(LocalDate.now().toString());
        this.salary = new Salary();
        this.info = new Info("No further info");
        this.scheduledMeeting = new ScheduledMeeting();
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Flag getFlag() {
        return flag;
    }

    public PrevDateMet getPrevDateMet() {
        return prevDateMet;
    }

    public Info getInfo() {
        return info;
    }
    public Salary getSalary() {
        return this.salary;
    }

    public ScheduledMeeting getScheduledMeeting() {
        return scheduledMeeting;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if current person has the same scheduled meeting.
     * @param scheduledMeeting The meeting to be compared with.
     * @return true if meeting clash.
     */
    public boolean hasSameMeeting(ScheduledMeeting scheduledMeeting) {
        if (scheduledMeeting == getScheduledMeeting()) {
            return true;
        }

        return scheduledMeeting != null
                && scheduledMeeting.hasSameMeeting(getScheduledMeeting());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        // Todo Add scheduled meeting check
        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getPrevDateMet().equals(getPrevDateMet())
                && otherPerson.getInfo().equals(getInfo())
                && otherPerson.getSalary().equals(getSalary())
                && otherPerson.getScheduledMeeting().equals(getScheduledMeeting());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags, prevDateMet, info, salary, scheduledMeeting);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ").append(getPhone())
                .append("; Email: ").append(getEmail())
                .append("; Address: ").append(getAddress())
                .append("; Flag: ").append(getFlag())
                .append("; Previous Date Met: ").append(getPrevDateMet())
                .append("; Salary: ").append(getSalary())
                .append("; Info: ").append(getInfo())
                .append(": Next Meeting: ").append(getScheduledMeeting());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
