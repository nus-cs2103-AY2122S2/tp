package unibook.model;

import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import unibook.commons.core.GuiSettings;
import unibook.model.module.Module;
import unibook.model.module.ModuleCode;
import unibook.model.module.group.Group;
import unibook.model.person.Person;
import unibook.model.person.Professor;
import unibook.model.person.Student;


/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Module> PREDICATE_SHOW_ALL_MODULES = unused -> true;

    Predicate<Person> PREDICATE_SHOW_ALL_PROFESSORS = p -> p instanceof Professor;
    Predicate<Person> PREDICATE_SHOW_ALL_STUDENTS = p -> p instanceof Student;

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' UniBook file path.
     */
    Path getUniBookFilePath();

    /**
     * Sets the user prefs' UniBook file path.
     */
    void setUniBookFilePath(Path uniBookFilePath);

    /**
     * Returns the UniBook
     */
    ReadOnlyUniBook getUniBook();

    /**
     * Replaces UniBook data with the data in {@code uniBook}.
     */
    void setUniBook(ReadOnlyUniBook uniBook);

    /**
     * Returns true if a person with the same identity as {@code person} exists in the UniBook.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the UniBook.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the UniBook.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the UniBook.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the UniBook.
     */
    void setPerson(Person target, Person editedPerson);

    //=========== Groups =====================================================================================
    void addGroup(Group group);

    Group removeGroup(ModuleCode moduleCode, Group group);

    List<Group> getShowingGroupList();

    /**
     * Returns an unmodifiable view of the filtered person list
     */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    void addPersonToTheirGroups(Person person);

    boolean hasModule(Module module);

    boolean hasModule(ModuleCode moduleCode);

    boolean hasModuleAndGroup(ModuleCode moduleCode, Group group);

    void deleteByModuleCode(ModuleCode moduleCode);

    void deleteModule(Module module);

    void addPersonToTheirModules(Person person);

    /**
     * Adds the given module.
     * {@code module} must not already exist in the UniBook.
     */
    void addModule(Module module);

    void setModule(Module target, Module editedModule);

    boolean isModuleExist(Set<ModuleCode> moduleCodeSet);

    void deleteModuleAndPersons(ModuleCode moduleCode);

    void deleteProfModule(ModuleCode moduleCode);

    /**
     * Finds the corresponding module to the module code.
     *
     * @return Module belonging to the ModuleCode
     */
    Module getModuleByCode(ModuleCode moduleCode);

    ObservableList<Module> getFilteredModuleList();

    void updateFilteredModuleList(Predicate<Module> predicate);

}
