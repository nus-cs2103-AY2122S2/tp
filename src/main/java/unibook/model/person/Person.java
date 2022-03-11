package unibook.model.person;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import unibook.commons.util.CollectionUtil;
import unibook.model.module.Module;
import unibook.model.tag.Tag;

/**
 * Represents a Person in the UniBook.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Module that is person is associated with
    private final Set<Module> modules = new HashSet<>();

    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Set<Tag> tags, Set<Module> modules) {
        CollectionUtil.requireAllNonNull(name, phone, email, tags);
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.tags.addAll(tags);
        this.modules.addAll(modules);
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


    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }


    /**
     * Returns an immutable module set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Module> getModules() {
        return Collections.unmodifiableSet(modules);
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
            && otherPerson.getPhone().equals(getPhone())
            && otherPerson.getEmail().equals(getEmail())
            && otherPerson.getTags().equals(getTags())
            && otherPerson.getModules().equals(getModules());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, tags, modules);
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
