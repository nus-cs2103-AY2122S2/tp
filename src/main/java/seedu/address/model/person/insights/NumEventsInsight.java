package seedu.address.model.person.insights;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.Model;
import seedu.address.model.person.Person;


/**
 * This class encapsulates the number of events a person participates in as an insight.
 */
public class NumEventsInsight extends Insight implements Comparable<NumEventsInsight> {


    private final int number;

    public static NumEventsInsight of(Person person, Model model) {
        NumEventsInsight helper = new NumEventsInsight(); // dummy todo surely there is a better way
        return helper.getInsight(person, model);
    }

    private NumEventsInsight() {
        // dummy todo surely there is a better way
        this.number = -1;
    }

    private NumEventsInsight(int number) {
        this.number = number;
    }

    @Override
    public NumEventsInsight getInsight(Person person, Model model) {
        requireAllNonNull(person, model);
        int numberOfEvents = (int) model.getEventsList()
                .stream()
                .filter(event -> event.hasFriendWithName(person))
                .count();
        return new NumEventsInsight(numberOfEvents);
    }

    @Override
    public String getAsString() {
        return "Number of events: " + this.number;
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
