package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.lab.Lab;
import seedu.address.model.person.lab.LabList;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Email email;
    private final GithubUsername githubUsername;
    private final Telegram telegram;
    private final StudentId studentId;
    private final LabList labs;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Email email, Set<Tag> tags,
                  GithubUsername githubUsername, Telegram telegram, StudentId studentId) {
        requireAllNonNull(name, email, tags, githubUsername, studentId);
        this.name = name;
        this.email = email;
        this.tags.addAll(tags);
        this.githubUsername = githubUsername;
        this.telegram = telegram;
        this.studentId = studentId;
        this.labs = new LabList();
    }

    public Name getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public GithubUsername getGithubUsername() {
        return githubUsername;
    }

    public Telegram getTelegram() {
        return telegram;
    }

    public StudentId getStudentId() {
        return studentId;
    }

    public LabList getLabs() {
        return labs;
    }

    public void setLabs(LabList ll) {
        labs.setLabs(ll);
    }

    public void addLab(Lab lab) {
        labs.add(lab);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both students have the same name.
     * This defines a weaker notion of equality between two students.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
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
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getGithubUsername().equals(getGithubUsername())
                && otherPerson.getTelegram().equals(getTelegram())
                && otherPerson.getStudentId().equals(getStudentId())
                && otherPerson.getLabs().equals(getLabs());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, email, tags, githubUsername, telegram, studentId, labs);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Email: ")
                .append(getEmail())
                .append("; Github: ")
                .append(getGithubUsername())
                .append("; Telegram: ")
                .append(getTelegram())
                .append("; Student ID: ")
                .append(getStudentId());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}
