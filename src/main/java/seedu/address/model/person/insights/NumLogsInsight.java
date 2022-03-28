package seedu.address.model.person.insights;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.Model;
import seedu.address.model.person.Person;


/**
 * This class encapsulates an insight that reflects the number of logs
 * a person has.
 */
public class NumLogsInsight extends Insight implements Comparable<NumLogsInsight> {
    private int number;

    public static NumLogsInsight of(Person person, Model model) {
        NumLogsInsight helper = new NumLogsInsight(); // dummy todo surely there is a better way
        return helper.getInsight(person, model);
    }

    private NumLogsInsight() {
        // dummy todo surely there is a better way
        this.number = -1;
    }
    private NumLogsInsight(int number) {
        this.number = number;
    }

    @Override
    public NumLogsInsight getInsight(Person person, Model model) {
        requireAllNonNull(person, model);
        return new NumLogsInsight(person.getLogs().size());
    }

    @Override
    public String getAsString() {
        return "Number of logs: " + this.number;
    }

    @Override
    public int compareTo(NumLogsInsight other) {
        return this.number - other.number;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof NumLogsInsight) {
            return this.number == ((NumLogsInsight) other).number;
        }
        return false;
    }

    @Override
    public String toString() {
        return this.getAsString();
    }
}