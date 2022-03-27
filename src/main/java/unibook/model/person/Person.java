package unibook.model.person;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import unibook.commons.util.CollectionUtil;
import unibook.model.module.Module;
import unibook.model.module.ModuleCode;
import unibook.model.person.exceptions.PersonNoSubtypeException;
import unibook.model.tag.Tag;

/**
 * Represents a Person in the UniBook. Class is abstract as only its subtypes {@code Student} and {@code Professor}
 * should be instantiable.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public abstract class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    // Data fields
    private final Set<Tag> tags = new HashSet<>();
    private Set<Module> modules = new HashSet<>();

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

    /**
     * Empty constructor of a null person.
     */
    public Person() {
        this.name = null;
        this.phone = null;
        this.email = null;
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

    abstract public Person deletePhone();

    abstract public Person deleteEmail();

    abstract public Person deleteTag(String tag);


    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }


    /**
     * Returns a modifiable module set.
     */
    public Set<Module> getModules() {
        return modules;
    }

    /**
     * Sets peron's modules
     */
    public void setModules(Set<Module> modules) {
        this.modules = modules;
    }

    /**
     * Deletes a module from the set of modules the person has by checking through each module
     * in the set and removing it from the set if the module code matches.
     *
     * @param other
     */
    public void removeModule(ModuleCode other) {
        Module toRemove = null; // necessary to prevent concurrent modification exception
        for (Module module : modules) {
            if (module.hasModuleCode(other)) {
                toRemove = module;
                break;
            }
        }
        modules.remove(toRemove);
    }

    /**
     * Returns true if this person object only has 1 module which has the same module code as
     * the one provided
     *
     * @param moduleCode
     * @return
     */
    public boolean onlyHasModule(ModuleCode moduleCode) {
        if (modules.size() != 1) {
            return false;
        }
        for (Module module : modules) {
            if (module.hasModuleCode(moduleCode)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds this person to all the modules that they are associated with, into the
     * correct personnel list (professor/student) in module depending on the runtime type
     * of this person.
     */
    public void addPersonToAllTheirModules() throws PersonNoSubtypeException {
        for (Module module : this.getModules()) {
            if (this instanceof Student) {
                module.addStudent((Student) this);
            } else if (this instanceof Professor) {
                module.addProfessor((Professor) this);
            } else {
                throw new PersonNoSubtypeException();
            }
        }
    }

    public void removePersonFromAllTheirModules() {
        for (Module module : this.getModules()) {
            module.removePerson(this);
        }
    }

    /**
     * Returns a mutable module set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Module> getModulesModifiable() {
        return modules;
    }

    /**
     * Returns true if the specified Module exists in the person's
     * module list, otherwise returns false.
     */
    public boolean hasModule(ModuleCode moduleCode) {
        for (Module m : modules) {
            if (m.hasModuleCode(moduleCode)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a module to the module set
     */
    public void addModule(Module module) {
        modules.add(module);
    }

    /**
     * returns true if both persons contain the exact same fields.
     */
    public boolean isSamePerson(Person otherPerson) {
        return otherPerson.equals(this);
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * Checks that the module codes of the modules associated with the persons are equivalent.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Set<ModuleCode> moduleCodes = getModules().stream().map(Module::getModuleCode).collect(Collectors.toSet());
        Set<ModuleCode> otherModuleCodes = ((Person) other).getModules().stream().map(Module::getModuleCode)
            .collect(Collectors.toSet());

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
            && otherPerson.getPhone().equals(getPhone())
            && otherPerson.getEmail().equals(getEmail())
            && otherPerson.getTags().equals(getTags())
            && otherModuleCodes.equals(moduleCodes);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, tags);
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
