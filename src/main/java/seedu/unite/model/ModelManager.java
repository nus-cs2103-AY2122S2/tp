package seedu.unite.model;

import static java.util.Objects.requireNonNull;
import static seedu.unite.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.unite.commons.core.GuiSettings;
import seedu.unite.commons.core.LogsCenter;
import seedu.unite.model.person.Person;
import seedu.unite.model.tag.Tag;
import seedu.unite.ui.theme.Theme;

/**
 * Represents the in-memory model of the unite data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Unite unite;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final ObservableList<Person> fullPersonList;
    private final ObservableList<Tag> tags;
    private boolean isMouseUxEnabled;
    private boolean isShowProfile;
    private boolean isShowTagList;
    private boolean isRemoveProfile;
    private boolean isShowGrabResult;
    private boolean isSwitchTheme;
    private Person person;
    private Theme theme;
    private String grabResult;


    /**
     * Initializes a ModelManager with the given unite and userPrefs.
     */
    public ModelManager(ReadOnlyUnite unite, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(unite, userPrefs);

        logger.fine("Initializing with unite: " + unite + " and user prefs " + userPrefs);

        this.unite = new Unite(unite);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.unite.getPersonList());
        fullPersonList = this.unite.getPersonList();
        tags = this.unite.getTagList();
        isMouseUxEnabled = false;
    }

    public ModelManager() {
        this(new Unite(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
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
    public Path getUniteFilePath() {
        return userPrefs.getUniteFilePath();
    }

    @Override
    public void setUniteFilePath(Path uniteFilePath) {
        requireNonNull(uniteFilePath);
        userPrefs.setUniteFilePath(uniteFilePath);
    }

    //=========== Unite ================================================================================

    @Override
    public void setUnite(ReadOnlyUnite unite) {
        this.unite.resetData(unite);
    }

    @Override
    public ReadOnlyUnite getUnite() {
        return unite;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return unite.hasPerson(person);
    }

    @Override
    public boolean hasTag(Tag tag) {
        requireNonNull(tag);
        return unite.hasTag(tag);
    }

    @Override
    public void deletePerson(Person target) {
        unite.removePerson(target);
    }

    @Override
    public void deleteTag(Tag target) {
        unite.removeTag(target);
    }

    @Override
    public void addPerson(Person person) {
        unite.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void addTag(Tag tag) {
        unite.addTag(tag);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        unite.setPerson(target, editedPerson);
    }

    @Override
    public void setTag(Tag target, Tag editedTag) {
        requireAllNonNull(target, editedTag);

        unite.setTag(target, editedTag);
    }

    @Override
    public ObservableList<Tag> getTagList() {
        return tags;
    }

    @Override
    public void enableMouseUX() {
        isMouseUxEnabled = true;
    }

    @Override
    public void disableMouseUX() {
        isMouseUxEnabled = false;
    }

    @Override
    public boolean isMouseUxEnabled() {
        return isMouseUxEnabled;
    }

    @Override
    public void showProfile(Person person) {
        isShowProfile = true;
        isShowTagList = false;
        isRemoveProfile = false;
        isShowGrabResult = false;
        isSwitchTheme = false;
        this.person = person;
    }

    @Override
    public void showTagList() {
        isShowProfile = false;
        isShowTagList = true;
        isRemoveProfile = false;
        isShowGrabResult = false;
        isSwitchTheme = false;
    }

    @Override
    public void showGrabResult(String grabResult) {
        isShowProfile = false;
        isShowTagList = false;
        isRemoveProfile = false;
        isShowGrabResult = true;
        isSwitchTheme = false;
        this.grabResult = grabResult;
    }

    @Override
    public void removeProfile(Person person) {
        isShowProfile = false;
        isShowTagList = false;
        isRemoveProfile = true;
        isShowGrabResult = false;
        isSwitchTheme = false;
        this.person = person;
    }

    @Override
    public void switchTheme(Theme theme) {
        isShowProfile = false;
        isShowTagList = false;
        isRemoveProfile = false;
        isShowGrabResult = false;
        isSwitchTheme = true;
        this.theme = theme;
    }

    @Override
    public boolean isShowProfile() {
        return isShowProfile;
    }

    @Override
    public boolean isShowTagList() {
        return isShowTagList;
    }

    @Override
    public boolean isShowGrabResult() {
        return isShowGrabResult;
    }

    @Override
    public boolean isRemoveProfile() {
        return isRemoveProfile;
    }

    @Override
    public boolean isSwitchTheme() {
        return isSwitchTheme;
    }

    @Override
    public Person getPerson() {
        return person;
    }

    @Override
    public String getGrabResult() {
        return grabResult;
    }

    @Override
    public Theme getTheme() {
        return theme;
    }


    @Override
    public int countPersonsInTag(Tag tag) {
        int count = 0;
        for (Person p : fullPersonList) {
            Set<Tag> tagList = p.getTags();
            if (tagList.contains(tag)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public ObservableList<Person> getFullPersonList() {
        return fullPersonList;
    }
    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedUnite}
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

        return unite.equals(other.unite)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons)
                && tags.equals(other.tags);
    }

}
