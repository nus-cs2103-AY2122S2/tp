package unibook.model.person;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import unibook.model.module.Module;
import unibook.model.tag.Tag;

/**
 * Represents a Professor in the UniBook.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Professor extends Person {

    private final Office office;

    /**
     * Every field must be present and not null.
     */
    public Professor(Name name, Phone phone, Email email, Set<Tag> tags, Office office, Set<Module> modules) {
        super(name, phone, email, tags, modules);
        this.office = office;
    }

    /**
     * Constructor for creating a professor from a person class with additional field office.
     *
     * @param person person to create this professor from
     * @param office office that this professor is located at
     */
    public Professor(Person person, Office office) {
        this(person.getName(), person.getPhone(), person.getEmail(),
            person.getTags(), office, person.getModules());
    }

    /**
     * Returns a new Professor Object with phone removed
     *
     * @return Professor with phone deleted
     */
    public Professor deletePhone() {
        return new Professor(getName(), new Phone(), getEmail(), getTags(), getOffice(), getModules());
    }

    /**
     * Returns a new Professor Object with email removed
     *
     * @return Professor with email deleted
     */
    public Professor deleteEmail() {
        return new Professor(getName(), getPhone(), new Email(), getTags(), getOffice(), getModules());
    }

    /**
     * Returns a new Professor Object with tag removed
     *
     * @param tagNameToDelete A String of the tag name
     * @return Professor with that specific tag deleted
     */
    public Professor deleteTag(String tagNameToDelete) {
        Set<Tag> tags = getTags();
        Set<Tag> newTags = new HashSet<>();
        for (Tag tag: tags) {
            if (!tag.tagName.equalsIgnoreCase(tagNameToDelete)) {
                newTags.add(tag);
            }
        }
        return new Professor(getName(), getPhone(), getEmail(), newTags, getOffice(), getModules());
    }

    /**
     * Returns a new Professor Object with office removed
     *
     * @return Professor with that specific office deleted
     */
    public Professor deleteOffice() {
        return new Professor(getName(), getPhone(), getEmail(), getTags(), new Office(), getModules());
    }

    /**
     * Returns the Office object that this professor has
     *
     * @return Office
     */
    public Office getOffice() {
        return this.office;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Professor)) {
            return false;
        }

        Professor otherPerson = (Professor) other;

        return super.equals(other) && otherPerson.getOffice().equals(getOffice());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getName(), getPhone(), getEmail(), getTags(), office);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
            .append("; Phone: ")
            .append(getPhone())
            .append("; Email: ")
            .append(getEmail())
            .append("; Office: ")
            .append(office);

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
