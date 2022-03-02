package seedu.address.model.person;

import seedu.address.model.tag.Tag;

import java.util.Objects;
import java.util.Set;

public class Student extends Person {

    private final Group group;

    public Student(Name name, Phone phone, Email email, Set<Tag> tags, Group group) {
        super(name, phone, email, tags);
        this.group = group;
    }

    public Group getGroup() {
        return this.group;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Student)) {
            return false;
        }

        Student otherPerson = (Student) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getGroup().equals(getGroup());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getName(), getPhone(), getEmail(), getTags(), group);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Group: ")
                .append(group);

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}
