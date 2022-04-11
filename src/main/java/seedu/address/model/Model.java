package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.commandhistory.CommandHistoryEntry;
import seedu.address.model.image.ImageDetailsList;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluates to false */
    Predicate<Person> PREDICATE_HIDE_ALL_PERSONS = unused -> false;

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
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Returns the user prefs' contact images file path.
     */
    Path getContactImagesFilePath();

    /**
     * Sets the user prefs' contact images file path.
     */
    void setContactImagesFilePath(Path contactImagesFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /**
     * Returns the AddressBook
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Returns true if a person with the same identity as {@code tag} exists in the address book.
     */
    boolean hasTag(Tag tag);

    /**
     * Adds the given tag
     * {@code tag} must not already exist in the address book.
     */
    void addTag(Tag tag);

    /**
     * Replaces the given tag {@code target} with {@code editedTag}.
     * {@code target} must exist in the address book.
     * The tag identity of {@code editedTag} must not be the same as another existing tag in the address book.
     */
    void setTag(Tag target, Tag editedTag);

    /**
     * Deletes the given tag.
     * The tag must exist in the address book.
     */
    void deleteTag(Tag target);

    /**
     * Adds the given tag to the {@code ActivatedTagList}
     * {@code tag} must already exist in the address book.
     */
    void addActivatedTag(Tag tag);

    /**
     * Returns an unmodifiable view of the filtered person list
     */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns an unmodifiable view of the activated tag list
     */
    ObservableList<Tag> getActivatedTagList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    void sortFilteredPersonListByName();

    void sortFilteredPersonListByAddress();

    void sortFilteredPersonListByDeadlineList();

    void sortFilteredPersonListByEmail();

    void sortFilteredPersonListByPhone();

    void sortFilteredPersonListByFavourite();

    void sortFilteredPersonListByHighImportance();

    SortedList<Person> getSortedPersonList();

    /**
     * Clears the {@code tags} within the {@code ActivatedTagList}
     */
    void clearActivatedTagList();

    /** Returns the contact that is being viewed in detail */
    ObservableList<Person> getDetailedContactView();

    /**
     * Sets the given {@code Person} as the contact to be viewed in detail.
     * @param person person to be viewed
     */
    void setDetailedContactView(Person person);

    /** Removes the contact that is being viewed in detail */
    void clearDetailedContactView();

    /**
     * Gets the {@code Person} in detailed view.
     */
    Person getDetailedContactViewPerson();

    /**
     * Updates the images to be displayed.
     */
    void setImagesToView(ImageDetailsList images);

    /**
     * Gets the images to be displayed.
     */
    ImageDetailsList getImagesToView();

    /**
     * Updates the commandText history
     */
    void updateCommandHistory(String commandText);

    /**
     * Retrieves the i-th latest command history
     * @param i the number of commands we should backstep to retrieve
     * @return the command text
     */
    CommandHistoryEntry getCommandHistory(int i);

}
