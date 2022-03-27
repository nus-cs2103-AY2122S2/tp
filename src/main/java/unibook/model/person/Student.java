package unibook.model.person;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.Set;

import unibook.logic.commands.EditCommand;
import unibook.logic.commands.EditCommand.EditGroupDescriptor;
import unibook.logic.commands.exceptions.CommandException;
import unibook.model.module.Module;
import unibook.model.module.group.Group;
import unibook.model.tag.Tag;

/**
 * Represents a Student in the UniBook.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student extends Person {

    private final Set<Group> groups;

    /**
     * Every field must be present and not null.
     */
    public Student(Name name, Phone phone, Email email, Set<Tag> tags, Set<Module> modules, Set<Group> groups) {
        super(name, phone, email, tags, modules);
        requireNonNull(groups);
        this.groups = groups;
    }

    /**
     * Another constructor to create a student from a given person and set of groups.
     *
     * @param person person to create student from.
     * @param groups list of groups student is in.
     */
    public Student(Person person, Set<Group> groups) {
        this(person.getName(), person.getPhone(), person.getEmail(),
            person.getTags(), person.getModules(), groups);
    }


    /**
     * Returns true only if the other student has all the same fields.
     *
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Student)) {
            return false;
        }

        Student otherPerson = (Student) other;

        return super.equals(other);
    }

    /**
     * Returns the groups this student is in.
     *
     * @return
     */
    public Set<Group> getGroups() {
        return groups;
    }


    /**
     * Edits the information of the group in the respective module
     */
    public void editGroupByMod(Module module, EditGroupDescriptor editGroupDescriptor) throws CommandException {
        for (Group g : groups) {
            if (g.getModule().equals(module)) {
                Group group = g;
                groups.remove(g);
                Group newGroup = EditCommand.createEditedGroup(g, editGroupDescriptor);
                groups.add(newGroup);
            }
        }
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

        if (!groups.isEmpty()) {
            builder.append("; Groups: ");
            groups.forEach(builder::append);
        }
        return builder.toString();
    }
}
