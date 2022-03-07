package unibook.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import unibook.model.module.Module;
import unibook.model.module.ModuleList;
import unibook.model.person.Person;
import unibook.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class UniBook implements ReadOnlyUniBook {

    private final UniquePersonList persons;
    private final ModuleList modules;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        modules = new ModuleList();
    }

    public UniBook() {
    }

    /**
     * Creates an UniBook using the Persons in the {@code toBeCopied}
     */
    public UniBook(ReadOnlyUniBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the person list with {@code modules}.
     * {@code modules} must not contain duplicate modules.
     */
    public void setModules(List<Module> modules) {
        this.modules.setModules(modules);
    }


    /**
     * Resets the existing data of this {@code UniBook} with {@code newData}.
     */
    public void resetData(ReadOnlyUniBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setModules(newData.getModuleList());
    }


    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the UniBook.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the UniBook.
     * The person must not already exist in the UniBook.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the UniBook.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the UniBook.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes person {@code key} from this {@code UniBook}.
     * {@code key} must exist in the UniBook.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// module-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the UniBook.
     */
    public boolean hasModule(Module module) {
        requireNonNull(module);
        return modules.contains(module);
    }

    /**
     * Adds a Module to the UniBook.
     * The Module must not already exist in the UniBook.
     */
    public void addModule(Module m) {
        modules.add(m);
    }

    /**
     * Replaces the given module {@code target} in the list with {@code editedModule}.
     * {@code target} must exist in the UniBook.
     * The module code and name of {@code editedModule} must not be the same as another existing module in the UniBook.
     */
    public void setModule(Module target, Module editedModule) {
        requireNonNull(editedModule);
        modules.setModule(target, editedModule);
    }

    /**
     * Removes module {@code key} from this {@code UniBook}.
     * {@code key} must exist in the UniBook.
     */
    public void removeModule(Module key) {
        modules.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons" + "||"
            + modules.asUnmodifiableObservableList().size() + " modules";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Module> getModuleList() {
        return modules.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof UniBook // instanceof handles nulls
            && persons.equals(((UniBook) other).persons)
            && modules.equals(((UniBook) other).modules));
    }

    @Override
    public int hashCode() {
        return persons.hashCode() + modules.hashCode();
    }
}
