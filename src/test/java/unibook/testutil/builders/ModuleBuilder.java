package unibook.testutil.builders;

import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import unibook.model.module.Module;
import unibook.model.module.ModuleCode;
import unibook.model.module.ModuleKeyEvent;
import unibook.model.module.ModuleName;
import unibook.model.module.group.Group;
import unibook.model.person.Professor;
import unibook.model.person.Student;

/**
 * A utility class to help with building Module objects.
 */
public class ModuleBuilder {

    public static final String DEFAULT_CODE = "CS2107";
    public static final String DEFAULT_NAME = "Introduction to Information Security";

    private ModuleName moduleName;
    private ModuleCode moduleCode;
    private ObservableList<Professor> professors;
    private ObservableList<Student> students;
    private ObservableList<Group> groups;
    private ObservableList<ModuleKeyEvent> keyEvents;

    /**
     * Creates a {@code ModuleBuilder} with the default details.
     */
    public ModuleBuilder() {
        moduleName = new ModuleName(DEFAULT_NAME);
        moduleCode = new ModuleCode(DEFAULT_CODE);
        professors = FXCollections.observableArrayList();
        students = FXCollections.observableArrayList();
        groups = FXCollections.observableArrayList();
        keyEvents = FXCollections.observableArrayList();
    }

    /**
     * Initializes the ModuleBuilder with the data of {@code moduleToCopy}.
     */
    public ModuleBuilder(Module moduleToCopy) {
        moduleName = moduleToCopy.getModuleName();
        moduleCode = moduleToCopy.getModuleCode();
        professors = moduleToCopy.getProfessors();
        students = moduleToCopy.getStudents();
        groups = moduleToCopy.getGroups();
        keyEvents = moduleToCopy.getKeyEvents();
    }

    /**
     * Sets the {@code moduleName} of the {@code Module} that we are building.
     */
    public ModuleBuilder withModuleName(String name) {
        moduleName = new ModuleName(name);
        return this;
    }

    /**
     * Sets the {@code moduleCode} of the {@code Module} that we are building.
     */
    public ModuleBuilder withModuleCode(String code) {
        moduleCode = new ModuleCode(code);
        return this;
    }

    /**
     * Sets the {@code professors} of the {@code Module} that we are building.
     */
    public ModuleBuilder withProfessors(ObservableList<Professor> professors) {
        this.professors = professors;
        return this;
    }

    /**
     * Sets the {@code students} of the {@code Module} that we are building.
     */
    public ModuleBuilder withStudents(ObservableList<Student> students) {
        this.students = students;
        return this;
    }

    /**
     * Sets the {@code groups} of the {@code Module} that we are building.
     */
    public ModuleBuilder withGroups(Set<Group> groupName) {
        groups = FXCollections.observableArrayList(groupName);
        return this;
    }

    /**
     * Sets the {@code keyEvents} of the {@code Module} that we are building.
     */
    public ModuleBuilder withKeyEvents(Set<ModuleKeyEvent> keyEventSet) {
        keyEvents = FXCollections.observableArrayList(keyEventSet);
        return this;
    }

    public Module build() {
        return new Module(moduleName, moduleCode, professors, students, groups, keyEvents);
    }

}
