package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.AddressBook;
import seedu.address.model.common.Description;
import seedu.address.model.person.FriendName;
import seedu.address.model.person.Person;

/**
 * Represents an Event in Amigos.
 * Guarantees: fields are present and not null, field values are validated, immutable. (except for friendNames)
 */
public class Event {

    // Identity fields
    private final EventName name;
    private final DateTime dateTime;

    // Data fields
    private final Description description;
    private final HashSet<FriendName> friendNames = new HashSet<>(); // mutable to allow for easier updating

    /**
     * Constructor for event.
     *
     * @param name        name of event
     * @param description description of event
     * @param dateTime    date and time of event
     * @param friendNames     set of friend's Names linked with the event.
     */
    public Event(EventName name, DateTime dateTime, Description description, Set<FriendName> friendNames) {
        requireAllNonNull(name, dateTime, description, friendNames);
        this.name = name;
        this.dateTime = dateTime;
        this.description = description;
        this.friendNames.addAll(friendNames);
    }

    public EventName getName() {
        return name;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public Description getDescription() {
        return description;
    }

    /**
     * Returns true if the given {@code substring} is contained within this event's name.
     * Case-insensitive.
     *
     * @param substring Substring to search for in this event's name.
     * @return True if given substring is contained within this event's name.
     */
    public boolean hasNameSubstring(String substring) {
        requireNonNull(substring);
        return getName().containsIgnoreCase(substring);
    }

    /**
     * Returns true if the given {@code substring} is contained within this event's friend names.
     * Case-insensitive.
     *
     * @param substring Substring to search for in this event's friend names.
     * @return True if the given substring is contained within this event's friend names.
     */
    public boolean hasFriendNameSubstring(String substring) {
        requireNonNull(substring);
        return getFriendNames().stream().anyMatch(name -> name.containsIgnoreCase(substring));
    }

    /**
     * Returns true if the given {@code date} matches this event's date.
     *
     * @param date Date to check this event's date against.
     * @return True if given date matches this event's date.
     */
    public boolean isOnDate(LocalDate date) {
        requireNonNull(date);
        return getDateTime().hasSameDate(date);
    }

    /**
     * Changes the given FriendName from {@code original} to {@code replacement} if
     * it is present in this Event's set of friend names.
     *
     * @param original Friend name to be changed.
     * @param replacement Name to change the friend name to.
     */
    public void changeFriendNameIfPresent(FriendName original, FriendName replacement) {
        if (getFriendNames().contains(original)) {
            friendNames.remove(original);
            friendNames.add(replacement);
        }
    }

    /**
     * Removes the given FriendName {@code toRemove} from the set of friend names in Event if present.
     *
     * @param toRemove Name of friend to remove from the Event if present.
     */
    public void removeFriendNameIfPresent(FriendName toRemove) {
        if (getFriendNames().contains(toRemove)) {
            friendNames.remove(toRemove);
        }
    }

    /**
     * Returns an immutable person set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<FriendName> getFriendNames() {
        return Collections.unmodifiableSet(friendNames);
    }

    /**
     * Returns true if all friend names correspond to actual Friends in the AddressBook.
     *
     * @return true if all friend names correspond to actual Friends in the AddressBook.
     */
    public boolean areFriendNamesValid(AddressBook addressBook) {
        // There ought to be a better way of doing this - search AddressBook by name perhaps?
        // worth thinking about - how to enforce search specifically by name, rather than relying
        // on the ::hasPerson(Person) method. todo implement search by name (specifically name objects match)
        // todo change this to take in a ReadOnlyAddressBook

        for (FriendName name : getFriendNames()) {
            Person beingLookedFor = new Person(name);
            if (!addressBook.hasPerson(beingLookedFor)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns true if both events have the same name and date.
     * This defines a weaker notion of equality between two events and is used to
     * prevent duplication.
     *
     * @param otherEvent The other event to compare to.
     * @return True if both events have the same name and date.
     */
    public boolean isSameEvent(Event otherEvent) {
        if (otherEvent == this) {
            return true;
        }

        return otherEvent != null
                && otherEvent.getName().equals(getName())
                && otherEvent.getDateTime().hasSameDate(getDateTime());
    }

    /**
     * Returns true if both events have the exact same fields.
     * This defines a stronger notion of equality between two events.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Event)) {
            return false;
        }

        Event otherEvent = (Event) other;
        return otherEvent.getName().equals(getName())
                && otherEvent.getDateTime().equals(getDateTime())
                && otherEvent.getDescription().equals(getDescription())
                && otherEvent.getFriendNames().equals(getFriendNames());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, dateTime, description, friendNames);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; DateTime: ")
                .append(getDateTime())
                .append("; Description: ")
                .append(getDescription());
        Set<FriendName> friendNames = getFriendNames();
        if (!friendNames.isEmpty()) {
            builder.append("; Friends: ");
            friendNames.forEach(name -> builder.append(name).append(" "));
        }
        return builder.toString();
    }
}
