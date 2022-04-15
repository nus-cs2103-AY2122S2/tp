package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.strategy.Player;
import seedu.address.model.task.Task;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final TaskBook taskBook;
    private final StrategyBoard strategyBoard;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Task> filteredTasks;
    private final FilteredList<Player> filteredPlayers;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyTaskBook taskBook,
                        ReadOnlyStrategyBoard strategyBoard, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, taskBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.taskBook = new TaskBook(taskBook);
        this.strategyBoard = new StrategyBoard(strategyBoard);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredTasks = new FilteredList<>(this.taskBook.getTaskList());
        filteredPlayers = new FilteredList<>(this.strategyBoard.getPlayerList());
    }

    public ModelManager() {
        this(new AddressBook(), new TaskBook(), new StrategyBoard(), new UserPrefs());
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
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    @Override
    public Path getTaskBookFilePath() {
        return userPrefs.getTaskBookFilePath();
    }

    @Override
    public void setTaskBookFilePath(Path taskBookFilePath) {
        requireNonNull(taskBookFilePath);
        userPrefs.setTaskBookFilePath(taskBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    @Override
    public ObservableList<Person> getUnfilteredPersonList() {
        return addressBook.getPersonList();
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
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

    //=========== Task Book ================================================================================

    @Override
    public void setTaskBook(ReadOnlyTaskBook taskBook) {
        this.taskBook.resetData(taskBook);
    }

    @Override
    public ReadOnlyTaskBook getTaskBook() {
        return taskBook;
    }

    @Override
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return taskBook.hasTask(task);
    }

    @Override
    public void deleteTask(Task target) {
        taskBook.removeTask(target);
    }

    @Override
    public void addTask(Task task) {
        taskBook.addTask(task);
        updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
    }

    @Override
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);

        taskBook.setTask(target, editedTask);
    }

    @Override
    public ObservableList<Task> getUnfilteredTaskList() {
        return taskBook.getTaskList();
    }

    //=========== Filtered Task List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Task} backed by the internal list of
     * {@code versionedTaskBook}
     */
    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return filteredTasks;
    }

    @Override
    public void updateFilteredTaskList(Predicate<Task> predicate) {
        requireNonNull(predicate);
        filteredTasks.setPredicate(predicate);
    }

    //=========== Strategy Board ================================================================================

    @Override
    public void setStrategyBoard(ReadOnlyStrategyBoard strategyBoard) {
        this.strategyBoard.resetData(strategyBoard);
    }

    @Override
    public ReadOnlyStrategyBoard getStrategyBoard() {
        return strategyBoard;
    }

    @Override
    public boolean hasPlayer(Player player) {
        requireNonNull(player);
        return strategyBoard.hasPlayer(player);
    }

    @Override
    public void deletePlayer(Player target) {
        strategyBoard.removePlayer(target);
    }

    @Override
    public void addPlayer(Player player) {
        strategyBoard.addPlayer(player);
        updateFilteredPlayerList(PREDICATE_SHOW_ALL_PLAYERS);
    }

    @Override
    public void setPlayer(Player target, Player editedPlayer) {
        requireAllNonNull(target, editedPlayer);

        strategyBoard.setPlayer(target, editedPlayer);
    }

    //=========== Filtered Player List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Player} backed by the internal list of
     * {@code versionedStrategyBoard}
     */
    @Override
    public ObservableList<Player> getFilteredPlayerList() {
        return filteredPlayers;
    }

    @Override
    public void updateFilteredPlayerList(Predicate<Player> predicate) {
        requireNonNull(predicate);
        filteredPlayers.setPredicate(predicate);
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
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons)
                && taskBook.equals(other.taskBook)
                && filteredTasks.equals(other.filteredTasks)
                && strategyBoard.equals(other.strategyBoard)
                && filteredPlayers.equals(other.filteredPlayers);
    }

}
