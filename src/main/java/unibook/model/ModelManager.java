package unibook.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import unibook.commons.core.GuiSettings;
import unibook.commons.core.LogsCenter;
import unibook.commons.util.CollectionUtil;
import unibook.model.module.Module;
import unibook.model.module.ModuleCode;
import unibook.model.module.group.Group;
import unibook.model.person.Person;
import unibook.model.person.Student;
import unibook.model.person.exceptions.PersonNoSubtypeException;
import unibook.ui.Ui;
import unibook.ui.UiManager;


/**
 * Represents the in-memory model of the unibook data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final UniBook uniBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Module> filteredModules;
    private Ui ui;

    /**
     * Initializes a ModelManager with the given uniBook and userPrefs.
     */
    public ModelManager(ReadOnlyUniBook uniBook, ReadOnlyUserPrefs userPrefs) {
        CollectionUtil.requireAllNonNull(uniBook, userPrefs);

        logger.fine("Initializing with UniBook: " + uniBook + " and user prefs " + userPrefs);

        this.uniBook = new UniBook(uniBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.uniBook.getPersonList());
        filteredModules = new FilteredList<>(this.uniBook.getModuleList());
    }

    public ModelManager() {
        this(new UniBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getUniBookFilePath() {
        return userPrefs.getUniBookFilePath();
    }

    @Override
    public void setUniBookFilePath(Path uniBookFilePath) {
        requireNonNull(uniBookFilePath);
        userPrefs.setUniBookFilePath(uniBookFilePath);
    }

    @Override
    public ReadOnlyUniBook getUniBook() {
        return uniBook;
    }

    //=========== UniBook ================================================================================
    @Override
    public void setUniBook(ReadOnlyUniBook uniBook) {
        this.uniBook.resetData(uniBook);
    }

    //=========== Persons ================================================================================
    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return uniBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        uniBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        uniBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    public void setPerson(Person target, Person editedPerson) {
        CollectionUtil.requireAllNonNull(target, editedPerson);

        uniBook.setPerson(target, editedPerson);
    }

    @Override
    public void addPersonToTheirModules(Person person) {
        try {
            uniBook.addPersonToAllTheirModules(person);
        } catch (PersonNoSubtypeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addPersonToTheirGroups(Person person) {
        try {
            uniBook.addStudentToAllTheirGroups((Student) person);
        } catch (PersonNoSubtypeException e) {
            e.printStackTrace();
        }
    }

    //=========== Modules ================================================================================
    @Override
    public boolean hasModule(Module module) {
        requireNonNull(module);
        return uniBook.hasModule(module);
    }

    @Override
    public boolean hasModule(ModuleCode moduleCode) {
        requireNonNull(moduleCode);
        return uniBook.hasModule(moduleCode);
    }

    @Override
    public void deleteByModuleCode(ModuleCode moduleCode) {
        uniBook.removeByModuleCode(moduleCode);
    }

    @Override
    public void deleteModule(Module module) {
        uniBook.removeModule(module);
    }

    @Override
    public void removeModuleFromAllPersons(ModuleCode moduleCode) {
        uniBook.removeModuleFromAllPersons(moduleCode);
    }

    @Override
    public void removePersonFromAllModules(Person person) {
        uniBook.removePersonFromModules(person);
    }


    @Override
    public void addModule(Module module) {
        uniBook.addModule(module);
        updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
    }

    @Override
    public void setModule(Module target, Module editedModule) {
        CollectionUtil.requireAllNonNull(target, editedModule);

        uniBook.setModule(target, editedModule);
    }

    @Override
    public boolean isModuleExist(Set<ModuleCode> moduleCodeSet) {
        for (ModuleCode moduleCode : moduleCodeSet) {
            if (!uniBook.hasModule(moduleCode)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Module getModuleByCode(ModuleCode moduleCode) {
        return uniBook.getModuleByCode(moduleCode);
    }

    //=========== Groups =====================================================================================
    @Override
    public void addGroup(Group group) {
        uniBook.addGroupToModule(group);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedUniBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    //=========== Filtered Module List Accessors =============================================================

    @Override
    public ObservableList<Module> getFilteredModuleList() {
        logger.info("Retrieving filtered module list...");
        return filteredModules;
    }

    @Override
    public void updateFilteredModuleList(Predicate<Module> predicate) {
        logger.info("Updating filtered module list...");
        requireNonNull(predicate);
        filteredModules.setPredicate(predicate);
    }

    public UiManager getUi() {
        return (UiManager) this.ui;
    }

    public void setUi(Ui ui) {
        this.ui = ui;
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return uniBook.equals(other.uniBook)
            && userPrefs.equals(other.userPrefs)
            && filteredPersons.equals(other.filteredPersons)
            && filteredModules.equals(other.filteredModules);
    }

}
