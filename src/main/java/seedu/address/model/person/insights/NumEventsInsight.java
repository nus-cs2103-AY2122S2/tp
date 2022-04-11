package seedu.address.model.person.insights;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.insights.Insights.Insight;


/**
 * This class encapsulates the number of events a person participates in as an insight.
 */
public class NumEventsInsight extends Insight implements Comparable<NumEventsInsight> {

    public static final String DEFAULT_HAS_EVENT_PREFIX = "Number of events: ";

    private final int number;

    private NumEventsInsight(int number) {
        this.number = number;
        super.markInitialized();
    }

    protected NumEventsInsight() {
        this.number = -1;
    }

    /**
     * Constructs a NumEventsInsight object.
     */

    @Override
    public NumEventsInsight getInsight(Person person, Model model) {
        requireAllNonNull(person, model);
        int numberOfEvents = (int) model.getAddressBook()
                .getEventList()
                .stream()
                .filter(event -> event.hasFriendWithName(person))
                .count();
        return new NumEventsInsight(numberOfEvents);
    }

    @Override
    public String getAsString() {
        assert(super.isInitialized());
        return DEFAULT_HAS_EVENT_PREFIX + this.number;
    }

    @Override
    public int compareTo(NumEventsInsight other) {
        return this.number - other.number;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof NumEventsInsight) {
            return this.number == ((NumEventsInsight) other).number;
        }
        return false;
    }

    @Override
    public String toString() {
        return this.getAsString();
    }
}
