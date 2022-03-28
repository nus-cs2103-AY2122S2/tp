package seedu.address.model.person.insights;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.model.Model;
import seedu.address.model.event.DateTime;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;

public class MostRecentEventInsight extends Insight implements Comparable<MostRecentEventInsight> {

    // data field
    private final DateTime dateTime;
    private final boolean hasAtLeastOneEvent;


    public static MostRecentEventInsight of(Person person, Model model) {
        MostRecentEventInsight helper = new MostRecentEventInsight(); // dummy todo surely there is a better way
        return helper.getInsight(person, model);
    }

    private MostRecentEventInsight() {
        // dummy todo surely there is a better way
        this.dateTime = null;
        this.hasAtLeastOneEvent = false;
    }

    private MostRecentEventInsight(boolean hasAtLeastOneEvent) {
        assert(!hasAtLeastOneEvent);
        this.hasAtLeastOneEvent = false;
        this.dateTime = null;
    }
    private MostRecentEventInsight(DateTime dateTime) {
        requireNonNull(dateTime);
        this.dateTime = dateTime;
        this.hasAtLeastOneEvent = true;
    }

    @Override
    public MostRecentEventInsight getInsight(Person person, Model model) {
        requireAllNonNull(person, model);
        List<Event> eventsWithPerson = model.getEventsList()
                .stream()
                .filter(event -> event.hasFriendWithName(person))
                .collect(Collectors.toList());
        if (eventsWithPerson.size() > 0) {
            eventsWithPerson.sort(Event::compareTo);
            DateTime dateTime = eventsWithPerson.get(eventsWithPerson.size() - 1).getDateTime();
            return new MostRecentEventInsight(dateTime);
        }
        return new MostRecentEventInsight(false);
    }

    @Override
    public String getAsString() {
        if (!this.hasAtLeastOneEvent) {
            return "Never had an event!";
        }
        assert (this.dateTime != null);
        return "Most recent event: " +  this.dateTime.toString();
    }

    @Override
    public int compareTo(MostRecentEventInsight other) {
        return this.dateTime.compareTo(other.dateTime);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof MostRecentEventInsight) {
            MostRecentEventInsight otherInsight = (MostRecentEventInsight) other;
            if ((otherInsight.dateTime == null) ^ (this.dateTime == null)) {
                return false;
            }
            return this.dateTime.equals(((MostRecentEventInsight) other).dateTime);
        }
        return false;
    }

    @Override
    public String toString() {
        return this.getAsString();
    }
}
