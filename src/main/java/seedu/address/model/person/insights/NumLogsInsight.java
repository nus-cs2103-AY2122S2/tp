package seedu.address.model.person.insights;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.insights.Insights.Insight;


/**
 * This class encapsulates an insight that reflects the number of logs
 * a person has.
 */
public class NumLogsInsight extends Insight implements Comparable<NumLogsInsight> {

    private int number;

    protected NumLogsInsight() {
        // dummy
        this.number = -1;
    }

    private NumLogsInsight(int number) {
        this.number = number;
        super.markInitialized();
    }


    @Override
    public NumLogsInsight getInsight(Person person, Model model) {
        requireAllNonNull(person, model);
        return new NumLogsInsight(person.getLogs().size());
    }

    @Override
    public String getAsString() {
        assert(super.isInitialized());
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
