package unibook.model.module;

import static java.util.Objects.requireNonNull;
import static unibook.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import unibook.model.module.exceptions.DuplicateGroupException;
import unibook.model.module.exceptions.DuplicateKeyEventException;
import unibook.model.module.exceptions.GroupNotFoundException;
import unibook.model.module.group.Group;
import unibook.model.person.Person;
import unibook.model.person.Professor;
import unibook.model.person.Student;
import unibook.model.person.exceptions.DuplicatePersonException;

/**
 * Represents a Module in the UniBook.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Module {

    // Identity fields
    private final ModuleName moduleName;
    private final ModuleCode moduleCode;


    // Data fields
    private final ObservableList<Professor> professors;
    private final ObservableList<Student> students;
    private final ObservableList<Group> groups;
    private final ObservableList<ModuleKeyEvent> keyEvents;


    /**
     * Constructor for a Module, assuming no students and no professor initially.
     *
     * @param moduleName
     * @param moduleCode
     */
    public Module(ModuleName moduleName, ModuleCode moduleCode) {
        requireAllNonNull(moduleName, moduleCode);
        this.moduleName = moduleName;
        this.moduleCode = moduleCode;
        this.professors = FXCollections.observableArrayList();
        this.students = FXCollections.observableArrayList();
        this.groups = FXCollections.observableArrayList();
        this.keyEvents = FXCollections.observableArrayList();
    }

    /**
     * Contstructor for a Module, with a given name, code and groups.
     *
     * @param moduleName
     * @param moduleCode
     * @param groups
     */
    public Module(ModuleName moduleName, ModuleCode moduleCode, ObservableList<Group> groups) {
        requireAllNonNull(moduleName, moduleCode, groups);
        this.moduleName = moduleName;
        this.moduleCode = moduleCode;
        this.groups = groups;
        this.students = FXCollections.observableArrayList();
        this.professors = FXCollections.observableArrayList();
        this.keyEvents = FXCollections.observableArrayList();
    }

    /**
     * Constructor for a Module, using a pre-existing list of key dates.
     *
     * @param moduleName
     * @param moduleCode
     * @param keyEvents
     */
    public Module(ObservableList<ModuleKeyEvent> keyEvents, ModuleName moduleName, ModuleCode moduleCode) {
        requireAllNonNull(moduleName, moduleCode, keyEvents);
        this.moduleName = moduleName;
        this.moduleCode = moduleCode;
        this.groups = FXCollections.observableArrayList();
        this.students = FXCollections.observableArrayList();
        this.professors = FXCollections.observableArrayList();
        this.keyEvents = keyEvents;
    }

    /**
     * Constructor for a Module, using a pre-existing list of students, professors and groups.
     *
     * @param moduleName
     * @param moduleCode
     * @param professors
     * @param students
     */
    public Module(ModuleName moduleName, ModuleCode moduleCode,
                  ObservableList<Professor> professors, ObservableList<Student> students,
                  ObservableList<Group> groups) {
        requireAllNonNull(moduleName, moduleCode, professors, students, groups);
        this.moduleName = moduleName;
        this.moduleCode = moduleCode;
        this.professors = professors;
        this.students = students;
        this.groups = groups;
        this.keyEvents = FXCollections.observableArrayList();
    }

    /**
     * Constructor for a Module, using a pre-existing list of students, professors and groups and key dates.
     *
     * @param moduleName
     * @param moduleCode
     * @param professors
     * @param students
     * @param keyEvents
     */
    public Module(ModuleName moduleName, ModuleCode moduleCode,
                  ObservableList<Professor> professors, ObservableList<Student> students,
                  ObservableList<Group> groups, ObservableList<ModuleKeyEvent> keyEvents) {
        requireAllNonNull(moduleName, moduleCode, professors, students, groups);
        this.moduleName = moduleName;
        this.moduleCode = moduleCode;
        this.professors = professors;
        this.students = students;
        this.groups = groups;
        this.keyEvents = keyEvents;
    }

    /**
     * Empty constructor for a null module.
     */
    public Module() {
        this.moduleName = null;
        this.moduleCode = null;
        this.professors = null;
        this.students = null;
        this.groups = null;
        this.keyEvents = null;
    }

    /**
     * Returns the module name.
     *
     * @return the name of the module.
     */
    public ModuleName getModuleName() {
        return moduleName;
    }

    /**
     * Returns the module code.
     *
     * @return the code of the module.
     */
    public ModuleCode getModuleCode() {
        return moduleCode;
    }

    /**
     * Returns the current list of professors.
     *
     * @return The observable list containing all professor objects.
     */
    public ObservableList<Professor> getProfessors() {
        return professors;
    }

    /**
     * Returns the current list of students.
     *
     * @return The observable list containing all student objects.
     */
    public ObservableList<Student> getStudents() {
        return students;
    }

    /**
     * Returns the current list of groups.
     *
     * @return The observable list containing all the group objects.
     */
    public ObservableList<Group> getGroups() {
        return groups;
    }


    /**
     * Returns the group in the group list of this module that has the given unique name.
     *
     * @param grpName of the group to get.
     * @return group that has the exact given grpname.
     */
    public Group getGroupByName(String grpName) {
        requireNonNull(grpName);
        for (Group grp : groups) {
            if (grp.getGroupName().equals(grpName)) {
                return grp;
            }
        }
        throw new GroupNotFoundException();
    }

    /**
     * Adds a student {@code s} to the list of the students.
     *
     * @param s
     */
    public void addStudent(Student s) {
        requireNonNull(s);
        if (students.contains(s)) {
            throw new DuplicatePersonException();
        }
        students.add(s);
    }

    /**
     * Adds a professor {@code p} to the list of the professors.
     *
     * @param p
     */
    public void addProfessor(Professor p) {
        requireNonNull(p);
        if (professors.contains(p)) {
            throw new DuplicatePersonException();
        }
        professors.add(p);
    }

    /**
     * Adds a group {@code g} to the list of groups.
     *
     * @param g
     */
    public void addGroup(Group g) {
        requireNonNull(g);
        if (groups.contains(g)) {
            throw new DuplicateGroupException();
        }
        groups.add(g);
    }

    /**
     * Adds key event to the correct module.
     *
     * @param k ModuleKeyEvent
     */
    public void addKeyEvent(ModuleKeyEvent k) {
        requireNonNull(k);
        if (keyEvents.contains(k)) {
            throw new DuplicateKeyEventException();
        }
        keyEvents.add(k);
    }

    /**
     * Adds a list of key events to the correct module.
     *
     * @param keyEventsList ArrayList of ModuleKeyEvent
     */
    public void addKeyEventList(ArrayList<ModuleKeyEvent> keyEventsList) {
        requireNonNull(keyEventsList);
        for (ModuleKeyEvent moduleKeyEvent : keyEventsList) {
            if (keyEvents.contains(moduleKeyEvent)) {
                throw new DuplicateKeyEventException();
            }
            keyEvents.add(moduleKeyEvent);
        }
    }

    /**
     * Returns true if both modules have the same name and code.
     * This defines a weaker notion of equality between two modules.
     */
    public boolean isSameModule(Module otherModule) {
        if (otherModule == this) {
            return true;
        }

        return otherModule != null
            && otherModule.getModuleCode().equals(getModuleCode())
            && otherModule.getModuleName().equals(getModuleName());
    }

    /**
     * Returns true if the module code this module has is equal to the given module code.
     *
     * @param otherCode other module code to check equality to
     * @return boolean variable indicating if the module codes are equal
     */
    public boolean hasModuleCode(ModuleCode otherCode) {
        return otherCode != null
            && otherCode.equals(moduleCode);
    }

    /**
     * Returns true if this module has contains the group with the given group name.
     *
     * @param groupName group name to check if in this module
     * @return boolean variable indicating if the group name exists in this module
     */
    public boolean hasGroupName(String groupName) {
        for (Group group : groups) {
            if (group.getGroupName().equals(groupName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns rue if this module contains the key event with the given event name and datetime.
     *
     * @param keyEvent key event name to check
     * @param dateTime datetime of key event to check
     * @return boolean variable indicating if the key event exists in this module
     */
    public boolean hasEvent(ModuleKeyEvent.KeyEventType keyEvent, LocalDateTime dateTime) {
        for (ModuleKeyEvent moduleKeyEvent : keyEvents) {
            if (moduleKeyEvent.getKeyEventType().equals(keyEvent)
                    && moduleKeyEvent.getKeyEventTiming().equals(dateTime)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Remove a person from students or professors list depending on whether person is a student or professor
     * and if present
     *
     * @param person
     */
    public void removePerson(Person person) {
        if (person instanceof Student) {
            students.remove(person);
        } else {
            professors.remove(person);
        }
    }


    /**
     * Remove a group from the list of groups under this module.
     */
    public void removeGroup(Group group) {
        if (!groups.contains(group)) {
            throw new GroupNotFoundException();
        }
        groups.remove(group);
    }

    public ObservableList<ModuleKeyEvent> getKeyEvents() {
        return this.keyEvents;
    }

    /**
     * Returns true if both modules have the same identity and data fields.
     * This defines a stronger notion of equality between two modules.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Module)) {
            return false;
        }

        Module otherModule = (Module) other;

        return otherModule.getModuleName().equals(getModuleName())
            && otherModule.getModuleCode().equals(getModuleCode())
            && otherModule.getProfessors().equals(getProfessors())
            && otherModule.getStudents().equals(getStudents())
            && otherModule.getGroups().equals(getGroups())
            && otherModule.getKeyEvents().equals(getKeyEvents());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(moduleName, moduleCode);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getModuleCode())
            .append("; Name: ")
            .append(getModuleName())
            .append("; Module Code: ")
            .append(getModuleCode())
            .append("; Professors: ")
            .append(getProfessors())
            .append("; Groups: ")
            .append(getGroups())
            .append("; Key Events: ")
            .append(getKeyEvents());
        return builder.toString();
    }
}
