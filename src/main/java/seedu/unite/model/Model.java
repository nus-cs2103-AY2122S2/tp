package seedu.unite.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.unite.commons.core.GuiSettings;
import seedu.unite.model.person.Person;
import seedu.unite.model.tag.Tag;
import seedu.unite.ui.theme.Theme;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' UNite file path.
     */
    Path getUniteFilePath();

    /**
     * Sets the user prefs' UNite file path.
     */
    void setUniteFilePath(Path uniteFilePath);

    /**
     * Replaces UNite data with the data in {@code unite}.
     */
    void setUnite(ReadOnlyUnite unite);

    /** Returns the UNite */
    ReadOnlyUnite getUnite();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the UNite.
     */
    boolean hasPerson(Person person);

    /**
     * Returns true if a tag with the same identity as {@code tag} exists in the UNite.
     */
    boolean hasTag(Tag tag);

    /**
     * Deletes the given person.
     * The person must exist in the UNite.
     */
    void deletePerson(Person target);

    /**
     * Deletes the given tag.
     * The tag must exist in the UNite.
     */
    void deleteTag(Tag target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the UNite.
     */
    void addPerson(Person person);

    /**
     * Adds the given tag.
     * {@code tag} must not already exist in the UNite.
     */
    void addTag(Tag tag);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the UNite.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the UNite.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Replaces the given tag {@code target} with {@code editedTag}.
     * {@code target} must exist in the UNite.
     * The tag identity of {@code editedTag} must not be the same as another existing tag in the UNite.
     */
    void setTag(Tag target, Tag editedTag);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the full person list */
    ObservableList<Person> getFullPersonList();

    /** Returns an unmodifiable view of the tag list */
    ObservableList<Tag> getTagList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /** Disables mouse interaction in UNite */
    void disableMouseUX();

    /** Enables mouse interaction in UNite */
    void enableMouseUX();

    /** Returns true is mouseUX is enabled */
    boolean isMouseUxEnabled();

    /** Counts the number of persons attached to the given tag */
    int countPersonsInTag(Tag tag);

    /** Change general display to show profile of the given person  */
    void showProfile(Person person);

    /** Change general display to show tag list */
    void showTagList();

    /** Change general display to show grab result */
    void showGrabResult(String grabResult);

    /** Indicate a profile is being removed */
    void removeProfile(Person person);

    /** Indicate a switch theme operation */
    void switchTheme(Theme theme);

    /** Returns true if the general display is required to show profile */
    boolean isShowProfile();

    /** Returns true if the general display is required to show tag list */
    boolean isShowTagList();

    /** Returns true if the general display is required to show grab result */
    boolean isShowGrabResult();

    /** Returns true if the general display is required to update the profile if a person is being deleted */
    boolean isRemoveProfile();

    /** Returns true if unite is required to switch theme */
    boolean isSwitchTheme();

    /** Returns the person from show profile or remove profile */
    Person getPerson();

    /** Returns the grab result */
    String getGrabResult();

    /** Returns the given theme to change to */
    Theme getTheme();
}
