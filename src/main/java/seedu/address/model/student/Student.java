package seedu.address.model.student;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.*;

import seedu.address.commons.core.index.Index;
import seedu.address.model.lab.Lab;
import seedu.address.model.lab.LabList;
import seedu.address.model.tag.Tag;

/**
 * Represents a Student in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

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
    public Student(Name name, Email email, Set<Tag> tags,
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

    /**
     * Adds a Lab to the Student's {@code labs}.
     * The Lab must not already exist in the list.
     *
     * @param lab The Lab to add.
     */
    public void addLab(Lab lab) {
        labs.add(lab);
    }

    /**
     * Removes the lab at the specified index from the Student's {@code labs}.
     *
     * @param index The index of the lab to be removed.
     */
    public void removeLab(Index index) {
        labs.remove(index);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both Students have the same name.
     * This defines a weaker notion of equality between two Students.
     */
    public boolean isSameStudent(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
                && otherStudent.getName().equals(getName());
    }

    public ViewDetails getViewDetails() {
        return new ViewDetails(this);
    }

    /**
     * Returns true if both Students have the same identity and data fields.
     * This defines a stronger notion of equality between two Students.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Student)) {
            return false;
        }

        Student otherStudent = (Student) other;
        return otherStudent.getName().equals(getName())
                && otherStudent.getEmail().equals(getEmail())
                && otherStudent.getTags().equals(getTags())
                && otherStudent.getGithubUsername().equals(getGithubUsername())
                && otherStudent.getTelegram().equals(getTelegram())
                && otherStudent.getStudentId().equals(getStudentId());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, email, tags, githubUsername, telegram, studentId);
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
