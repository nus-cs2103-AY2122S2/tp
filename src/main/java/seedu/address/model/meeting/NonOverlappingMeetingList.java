package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.client.Client;
import seedu.address.model.meeting.exceptions.MeetingNotFoundException;
import seedu.address.model.meeting.exceptions.OverlappingMeetingsException;


/**
 * A list of clients that enforces uniqueness between its elements and does not allow nulls.
 * A client is considered unique by comparing using {@code Client#isSameClient(Client)}. As such, adding and updating of
 * clients uses Client#isSameClient(Client) for equality so as to ensure that the client being added or updated is
 * unique in terms of identity in the UniqueClientList. However, the removal of a client uses Client#equals(Object) so
 * as to ensure that the client with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Meeting#isOverlapping(Meeting)
 */
public class NonOverlappingMeetingList implements Iterable<Meeting> {

    private final ObservableList<Meeting> internalList = FXCollections.observableArrayList();
    private final ObservableList<Meeting> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains any overlapping meetings.
     */
    public boolean overlaps(Meeting toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isOverlapping);
    }

    /**
     * Returns true if the list contains any overlapping meetings except for the specified meeting.
     */
    public boolean overlapsExcept(Meeting toCheck, Meeting exceptedMeeting) {
        requireAllNonNull(toCheck, exceptedMeeting);
        return internalList.stream().filter(x -> !x.equals(exceptedMeeting)).anyMatch(toCheck::isOverlapping);
    }

    /**
     * Adds a meeting to the list.
     * The meeting must not be overlapping with other meetings in the list.
     */
    public void add(Meeting toAdd) {
        requireNonNull(toAdd);
        if (overlaps(toAdd)) {
            throw new OverlappingMeetingsException();
        }
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent meeting from the list.
     * The meeting must exist in the list.
     */
    public void remove(Meeting toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new MeetingNotFoundException();
        }
    }

    /**
     * Sorts the meetings in ascending order based on starting meeting time.
     */
    public void sortAscending() {
        List<Meeting> sortedMeetings = new ArrayList<>(internalList);
        Comparator<Meeting> comparatorDateTime = Comparator
                .comparing((Meeting meeting) -> meeting.getStartDateTime().toLocalDate());

        sortedMeetings.sort(comparatorDateTime);
        setMeetings(sortedMeetings);
    }

    /**
     * Update meetings with the specified client to a new client.
     */
    public void setClient(Client target, Client editedClient) {
        requireAllNonNull(target, editedClient);

        for (int i = 0; i < internalList.size(); i++) {
            Meeting meeting = internalList.get(i);
            if (meeting.getClient().isSameClient(target)) {
                Meeting newMeeting = meeting.updateClient(editedClient);
                internalList.set(i, newMeeting);
            }
        }
    }

    /**
     * Replaces the given meeting {@code target} with {@code editedMeeting}.
     * {@code target} must exist in the address book.
     * The meeting timing of {@code editedMeeting} must not be overlapping with
     * another existing meeting in the address book.
     */
    public void setMeeting(Meeting target, Meeting editedMeeting) {
        requireAllNonNull(target, editedMeeting);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new MeetingNotFoundException();
        }

        if (!target.isOverlapping(editedMeeting) && overlaps(editedMeeting)) {
            throw new OverlappingMeetingsException();
        }

        internalList.set(index, editedMeeting);
    }

    /**
     * Replaces the contents of this list with a replacement.
     * {@code replacement} must not contain overlapping meetings.
     */
    public void setMeetings(NonOverlappingMeetingList replacement) {
        requireNonNull(replacement);

        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code meetings}.
     * {@code meetings} must not contain overlapping meetings.
     */
    public void setMeetings(List<Meeting> meetings) {
        requireAllNonNull(meetings);
        if (!meetingsAreNonOverlapping(meetings)) {
            throw new OverlappingMeetingsException();
        }

        internalList.setAll(meetings);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Meeting> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Meeting> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NonOverlappingMeetingList // instanceof handles nulls
                        && internalList.equals(((NonOverlappingMeetingList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code meetings} contains non-overlapping meetings.
     */
    private boolean meetingsAreNonOverlapping(List<Meeting> meetings) {
        for (int i = 0; i < meetings.size() - 1; i++) {
            for (int j = i + 1; j < meetings.size(); j++) {
                if (meetings.get(i).isOverlapping(meetings.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
