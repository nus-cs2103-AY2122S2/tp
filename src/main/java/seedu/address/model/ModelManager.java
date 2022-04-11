package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.client.Client;
import seedu.address.model.meeting.Meeting;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Client> filteredClients;
    private final FilteredList<Meeting> filteredMeetings;
    private final SortedList<Client> sortedClients;
    private boolean isShowAllMeetings = false;
    private Client displayedClient;
    private boolean isSorted = false;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredClients = new FilteredList<>(this.addressBook.getClientList());
        filteredClients.setPredicate(PREDICATE_SHOW_ALL_CLIENTS);
        filteredMeetings = new FilteredList<>(this.addressBook.getMeetingList());
        sortedClients = new SortedList<>(this.addressBook.getClientList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
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
    public boolean hasClient(Client client) {
        requireNonNull(client);
        return addressBook.hasClient(client);
    }

    @Override
    public boolean hasNoClients() {
        return filteredClients.isEmpty() && filteredClients.getPredicate().equals(PREDICATE_SHOW_ALL_CLIENTS);
    }

    @Override
    public void deleteClient(Client target) {
        addressBook.removeClient(target);
    }

    @Override
    public void addClient(Client client) {
        addressBook.addClient(client);
        updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
    }

    @Override
    public void setClient(Client target, Client editedClient) {
        requireAllNonNull(target, editedClient);

        addressBook.setClient(target, editedClient);
    }

    @Override
    public boolean isOverlapping(Meeting meeting) {
        requireNonNull(meeting);
        return addressBook.isOverlapping(meeting);
    }

    @Override
    public boolean isOverlappingExcept(Meeting meeting, Meeting exceptedMeeting) {
        requireAllNonNull(meeting, exceptedMeeting);
        return addressBook.isOverlappingExcept(meeting, exceptedMeeting);
    }

    @Override
    public void addMeeting(Meeting meeting) {
        addressBook.addMeeting(meeting);
        updateFilteredMeetingList(PREDICATE_SHOW_ALL_MEETINGS, isShowAllMeetings);
    }

    @Override
    public boolean isShowAllMeetings() {
        return isShowAllMeetings;
    }

    @Override
    public void deleteMeeting(Meeting target) {
        addressBook.removeMeeting(target);
    }

    @Override
    public void setMeeting(Meeting target, Meeting editedMeeting) {
        addressBook.setMeeting(target, editedMeeting);
    }

    @Override
    public void sortMeetings() {
        addressBook.sortMeetings();
    }


    @Override
    public void closeMeeting(Meeting target) {
        requireNonNull(target);

        Client client = target.getClient();
        Meeting closedMeeting = target.closeMeeting();
        Client updatedClient = closedMeeting.getClient();
        assert updatedClient.isSameClient(client);

        setClient(client, updatedClient);
        updateDisplayedClient(updatedClient);
        addressBook.setMeeting(target, closedMeeting);
    }

    @Override
    public Client getDisplayedClient() {
        return displayedClient;
    }

    @Override
    public void updateDisplayedClient(Client client) {
        displayedClient = client;
    }

    @Override
    public boolean isSorted() {
        return isSorted;
    }

    @Override
    public void setIsSorted(boolean isSorted) {
        this.isSorted = isSorted;
    }

    //=========== Filtered Client List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Client} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Client> getFilteredClientList() {
        return filteredClients;
    }

    @Override
    public ObservableList<Client> getClientList() {
        return isSorted ? sortedClients : filteredClients;
    }

    @Override
    public void updateFilteredClientList(Predicate<Client> predicate) {
        requireNonNull(predicate);
        filteredClients.setPredicate(predicate);
    }

    @Override
    public void updateSortedClientList(Comparator<Client> comparator) {
        requireNonNull(comparator);
        sortedClients.setComparator(comparator);
    }

    //=========== Filtered Meeting List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Meeting}.
     */
    @Override
    public ObservableList<Meeting> getFilteredMeetingList() {
        return filteredMeetings;
    }

    @Override
    public void updateFilteredMeetingList(Predicate<Meeting> predicate, boolean isShowAll) {
        requireNonNull(predicate);
        this.isShowAllMeetings = isShowAll;
        filteredMeetings.setPredicate(predicate);
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
                && filteredClients.equals(other.filteredClients)
                && sortedClients.equals(other.sortedClients);
    }

}
