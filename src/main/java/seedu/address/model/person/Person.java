package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.StringBuilderUtil;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the class group.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final StudentNumber studentNumber;
    private final Email email;

    // Data fields
    private Group group;
    private final Mod mod;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, StudentNumber studentNumber, Email email, Mod mod, Group group, Set<Tag> tags) {
        requireAllNonNull(name, studentNumber, email, mod, group, tags);
        this.name = name;
        this.studentNumber = studentNumber;
        this.email = email;
        this.mod = mod;
        this.group = group;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public StudentNumber getStudentNumber() {
        return studentNumber;
    }

    public Email getEmail() {
        return email;
    }

    public Mod getMod() {
        return mod;
    }

    public Group getGroup() {
        return group;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Sets the group allocation for the {@code Person}.
     * @param groupTitle the String with the desired group title.
     */
    public void setGroup(String groupTitle) {
        this.group = new Group(groupTitle);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == null) {
            return false;
        }

        if (otherPerson == this) {
            return true;
        }

        boolean isSameStudentNumber = otherPerson.getStudentNumber().equals(getStudentNumber());
        boolean isSameEmail = otherPerson.getEmail().equals(getEmail());

        return isSameEmail || isSameStudentNumber;
    }

    /**
     * Checks if the current person is identical to person, p - while ignoring any tags.
     * @param p the person being compared to.
     * @return the boolean value, true if persons are identical.
     */
    public boolean isSamePersonAgnosticToTags(Person p) {
        return p.getStudentNumber().equals(this.getStudentNumber())
                && p.getEmail().equals(this.getEmail())
                && p.getGroup().equals(this.getGroup())
                && p.getMod().equals(this.getMod());
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

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getStudentNumber().equals(getStudentNumber())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getGroup().equals(getGroup())
                && otherPerson.getMod().equals(getMod())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, studentNumber, email, mod, group, tags);
    }

    @Override
    public String toString() {
        final StringBuilderUtil stringBuilderUtil = StringBuilderUtil.getInstance();
        stringBuilderUtil.appendAll(getName(), "; StudentNumber: ", getStudentNumber(),
                "; Email: ", getEmail(),
                "; Group: ", getGroup(),
                "; Mod: ", getMod());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            stringBuilderUtil.append("; Tags: ");
            tags.forEach(stringBuilderUtil::append);
        }
        return stringBuilderUtil.getFormattedOutput();
    }

}
