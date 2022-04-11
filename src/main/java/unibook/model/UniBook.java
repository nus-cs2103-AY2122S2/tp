package unibook.model;

import static java.util.Objects.requireNonNull;
import static unibook.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import unibook.model.module.Module;
import unibook.model.module.ModuleCode;
import unibook.model.module.ModuleKeyEvent;
import unibook.model.module.ModuleList;
import unibook.model.module.group.Group;
import unibook.model.person.Email;
import unibook.model.person.Person;
import unibook.model.person.Phone;
import unibook.model.person.Professor;
import unibook.model.person.Student;
import unibook.model.person.UniquePersonList;
import unibook.model.person.exceptions.PersonNoSubtypeException;

/**
 * Wraps all data at the uni-book level
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
     * Creates a UniBook using the data in {@code toBeCopied}
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

    /**
     * Returns true if a person with the same identity as {@code person} exists in the UniBook.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Returns true if unibook contains a person with given phone number.
     */
    public boolean phoneNumberBeingUsed(Phone phone) {
        requireNonNull(phone);
        return persons.phoneNumberBeingUsed(phone);
    }

    /**
     * Returns index of person in unibook contains a person with given phone number.
     */
    public int getIdxOfPhoneNumberBeingUsed(Phone phone) {
        requireNonNull(phone);
        return persons.getIdxOfPhoneNumberBeingUsed(phone);
    }

    /**
     * Returns index of person in unibook contains a person with given email.
     */
    public int getIdxOfEmailBeingUsed(Email email) {
        requireNonNull(email);
        return persons.getIdxOfEmailBeingUsed(email);
    }

    /**
     * Returns true if unibook contains a person with given email.
     */
    public boolean emailBeingUsed(Email email) {
        requireNonNull(email);
        return persons.emailBeingUsed(email);
    }

    /**
     * Adds a person to UniBook.
     * The person must not already exist in the UniBook.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Adds this person to all the modules that they are associated with, into the
     * correct personnel list (professor/student) in module depending on the runtime type
     * of this person.
     *
     * @param p person whos modules to add them to
     */
    public void addPersonToAllTheirModules(Person p) {
        requireNonNull(p);
        for (Module personsModule : p.getModules()) {
            Module module = modules.getModule(personsModule);
            if (p instanceof Student) {
                module.addStudent((Student) p);
            } else if (p instanceof Professor) {
                module.addProfessor((Professor) p);
            } else {
                throw new PersonNoSubtypeException();
            }
        }
    }

    /**
     * Adds a student to all their module groups.
     * Adds a student to all of the groups they are associated with.
     *
     * @param s
     */
    public void addStudentToAllTheirGroups(Student s) {
        requireNonNull(s);
        for (Group group : s.getGroups()) {
            group.addMember(s);
        }
    }


    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the UniBook.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the UniBook.
     */
    public void setPerson(int idx, Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(idx, target, editedPerson);
    }


    //// module-level operations

    /**
     * Returns true if an equivalent module to the one given exists in unibook.
     */
    public boolean hasModule(Module module) {
        requireNonNull(module);
        return modules.contains(module);
    }

    /**
     * Returns true if a module with the given moduleCode exists in unibook.
     *
     * @param moduleCode moduleCode to check for
     * @return boolean variable indicating presence of module with given moduleCode
     */
    public boolean hasModule(ModuleCode moduleCode) {
        requireNonNull(moduleCode);
        return modules.contains(moduleCode);
    }

    /**
     * Returns true is the group exists in the module associated with the module code provided
     *
     * @param moduleCode
     * @param group
     * @return
     */
    public boolean hasModuleAndGroup(ModuleCode moduleCode, Group group) {
        Module module = modules.getModuleByCode(moduleCode);
        return module.hasGroup(group);
    }

    /**
     * Returns module with given code that is in unibook.
     *
     * @param moduleCode
     * @return module with given code.
     */
    public Module getModuleByCode(ModuleCode moduleCode) {
        requireNonNull(moduleCode);
        return modules.getModuleByCode(moduleCode);
    }

    /**
     * Adds a Module to UniBook.
     * The Module must not already exist in the UniBook.
     */
    public void addModule(Module m) {
        modules.add(m);
    }

    /**
     * Adds a group to a module of UniBook. Assumes that the module associated with the
     * group is already inside the group object.
     *
     * @param g
     */
    public void addGroupToModule(Group g) {
        Module moduleOfGroup = g.getModule();
        requireNonNull(moduleOfGroup);
        moduleOfGroup.addGroup(g);
    }

    /**
     * Adds a key event to a module of UniBook.
     *
     * @param k
     */
    public void addKeyEventToModule(ModuleKeyEvent k) {
        Module moduleOfEvent = k.getModule();
        requireNonNull(moduleOfEvent);
        moduleOfEvent.addKeyEvent(k);
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
        removeModuleFromAllPersons(key.getModuleCode());
        removeGroupFromAllStudents(key.getModuleCode());
    }

    /**
     * Remove module by matching the module code, also removes modules from all students and all groups from students
     * relating to that module
     *
     * @param key
     */
    public void removeByModuleCode(ModuleCode key) {
        modules.removeByModuleCode(key);
        removeModuleFromAllPersons(key);
        removeGroupFromAllStudents(key);
    }

    public void removeGroupFromAllStudents(ModuleCode moduleCode) {
        persons.removeGroupFromAllStudents(moduleCode);
    }

    /**
     * Removes person {@code key} from this {@code UniBook}.
     * {@code key} must exist in the UniBook.
     * Removes all references to the person from associated modules.
     */
    public void removePerson(Person key) {
        persons.remove(key);
        removePersonFromModules(key);
    }

    /**
     * Removes Student {@code key} from this {@code UniBook}.
     * {@code key} must exist in the UniBook.
     * Removes all references to the student from associated modules and groups.
     */
    public void removePerson(Student key) {
        persons.remove(key);
        removePersonFromModules(key);
        removeStudentFromGroups(key);
    }

    /**
     * Gets a Group in Unibook with given modulecode (indicating module it is in) and groupName.
     */
    public Group getGroupByModuleCodeAndGroupName(ModuleCode moduleCode, String groupName) {
        requireAllNonNull(moduleCode, groupName);
        Module moduleOfGroup = getModuleByCode(moduleCode);
        Group group = moduleOfGroup.getGroupByName(groupName);
        return group;
    }

    /**
     * Deletes the module associated with the module code provided, as well as all persons
     * that are associated with the module, only if the only this module is the only module
     * that they are associated with
     * @param moduleCode
     */
    public void deleteModuleAndPersons(ModuleCode moduleCode) {
        Module moduleToBeDeleted = modules.getModuleByCode(moduleCode);
        List<Student> studentsAssociatedToModule = moduleToBeDeleted.getStudents();
        List<Professor> profsAssociatedToModule = moduleToBeDeleted.getProfessors();
        List<Student> studentsToBeDeleted = new ArrayList<>();
        List<Professor> profsToBeDeleted = new ArrayList<>();
        for (Student student : studentsAssociatedToModule) {
            if (student.onlyHasModule(moduleCode)) {
                studentsToBeDeleted.add(student);
            }
        }
        for (Professor professor : profsAssociatedToModule) {
            if (professor.onlyHasModule(moduleCode)) {
                profsToBeDeleted.add(professor);
            }
        }
        persons.deleteStudentsAndProfs(studentsToBeDeleted, profsToBeDeleted);
        modules.removeByModuleCode(moduleCode);
    }

    /**
     * Delete all professors associated to the module that matches with the module code provided
     * @param moduleCode
     */
    public void deleteProfModule(ModuleCode moduleCode) {
        Module moduleToBeDeleted = modules.getModuleByCode(moduleCode);
        List<Professor> profsToBeDeleted = moduleToBeDeleted.getProfessors();
        persons.deleteProfs(profsToBeDeleted);
    }

    public void deleteGroupFromAllPersons(ModuleCode moduleCode, Group group) {
        persons.deleteGroupFromAllPersons(moduleCode, group);
    }

    /**
     * Delete all groups that match the group name provided from all persons
     *
     * @param groupName
     */
    public ObservableList<Group> getGroupsWithGroupName(String groupName) {
        requireNonNull(groupName);
        ObservableList<Group> groups = FXCollections.observableArrayList();
        for (Module m : modules) {
            Group g = m.getGroupByName(groupName);
            if (g != null) {
                groups.add(m.getGroupByName(groupName));
            }
        }

        return groups;
    }

    /**
     * Removes module from all persons that are associated with the module.
     *
     * @param moduleCode
     */
    public void removeModuleFromAllPersons(ModuleCode moduleCode) {
        persons.removeModuleFromAllPersons(moduleCode);
    }

    /**
     * Removes person from all moduels that are associated with the module.
     *
     * @param person
     */
    public void removePersonFromModules(Person person) {
        modules.removePersonFromModule(person);
    }

    /**
     * Removes the student from all the groups they are in.
     *
     * @param student
     */
    public void removeStudentFromGroups(Student student) {
        for (Group group : student.getGroups()) {
            group.removeMember(student);
        }
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons" + "||"
            + modules.asUnmodifiableObservableList().size() + " modules";
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
