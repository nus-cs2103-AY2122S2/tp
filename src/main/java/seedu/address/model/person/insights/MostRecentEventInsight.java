package seedu.address.model.person.insights;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.model.Model;
import seedu.address.model.event.DateTime;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;
import seedu.address.model.person.insights.Insights.Insight;

/**
 * This class encapsulates the insight of the most recent event.
 */
public class MostRecentEventInsight extends Insight implements Comparable<MostRecentEventInsight> {


    // constants
    public static final String DEFAULT_NO_EVENT_MESSAGE = "Most recent event: Never had an event (yet)!";
    public static final String DEFAULT_HAS_EVENT_PREFIX = "Most recent event: ";

    // data field
    private final DateTime dateTime;
    private final boolean hasAtLeastOneEvent;

    private MostRecentEventInsight(boolean hasAtLeastOneEvent) {
        assert(!hasAtLeastOneEvent);
        this.hasAtLeastOneEvent = false;
        this.dateTime = null;
        super.markInitialized();
    }

    private MostRecentEventInsight(DateTime dateTime) {
        requireNonNull(dateTime);
        this.dateTime = dateTime;
        this.hasAtLeastOneEvent = true;
        super.markInitialized();
    }

    protected MostRecentEventInsight() {
        this.dateTime = null;
        this.hasAtLeastOneEvent = false;
    };


    @Override
    public MostRecentEventInsight getInsight(Person person, Model model) {

        // sanity check
        requireAllNonNull(person, model);

        // get event
        List<Event> eventsWithPerson = model.getAddressBook()
                .getEventList()
                .stream()
                .filter(event -> event.hasFriendWithName(person))
                .filter(event -> event.getDateTime().isBeforeNow()) // only past events
                .collect(Collectors.toList());

        // if have past event, update that
        if (eventsWithPerson.size() > 0) {
            eventsWithPerson.sort(Event::compareTo);
            DateTime dateTime = eventsWithPerson.get(eventsWithPerson.size() - 1).getDateTime();
            assert (dateTime != null);
            return new MostRecentEventInsight(dateTime);
        }
        return new MostRecentEventInsight(false);
    }

    @Override
    public String getAsString() {
        assert(super.isInitialized());
        if (!this.hasAtLeastOneEvent) {
            return DEFAULT_NO_EVENT_MESSAGE;
        }
        assert (this.dateTime != null);
        return "Most recent event: " + this.dateTime.toString();
    }

    @Override
    public int compareTo(MostRecentEventInsight other) {
        if (this.dateTime == null && other.dateTime != null) {
            return -1;
        } else if (other.dateTime == null && this.dateTime != null) {
            return 1;
        } else if (other.dateTime == null && this.dateTime == null) {
            return 0;
        }
        return this.dateTime.compareTo(other.dateTime);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof MostRecentEventInsight) {
            MostRecentEventInsight otherInsight = (MostRecentEventInsight) other;
            if ((otherInsight.dateTime == null) && (this.dateTime == null)) {
                return true;
            } else if ((otherInsight.dateTime == null) ^ (this.dateTime == null)) {
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
