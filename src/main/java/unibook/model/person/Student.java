package unibook.model.person;

import java.util.Objects;
import java.util.Set;

import unibook.model.module.Module;
import unibook.model.tag.Tag;

/**
 * Represents a Student in the UniBook.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student extends Person {

    /**
     * Every field must be present and not null.
     */
    public Student(Name name, Phone phone, Email email, Set<Tag> tags, Set<Module> modules) {
        super(name, phone, email, tags, modules);
    }

    /**
     * Another constructor to create a student from a given person.
     *
     * @param person person to create student from
     */
    public Student(Person person) {
        this(person.getName(), person.getPhone(), person.getEmail(),
            person.getTags(), person.getModules());
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
            && otherPerson.getModules().equals(getModules());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getName(), getPhone(), getEmail(), getTags());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
            .append("; Phone: ")
            .append(getPhone())
            .append("; Email: ")
            .append(getEmail());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        Set<Module> modules = getModules();
        if (!modules.isEmpty()) {
            builder.append("; Modules: ");
            modules.forEach(builder::append);
        }
        return builder.toString();
    }
}
