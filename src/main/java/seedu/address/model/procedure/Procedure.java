package seedu.address.model.procedure;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a specific procedure in a client.
 */
public class Procedure implements Comparable<Procedure> {
    private final Information information;
    private final Date date;
    private final Cost cost;
    private Completion hasCompleted;

    /**
     * Every field must be present and not null.
     */
    public Procedure(Information information, Date date, Cost cost, Completion hasCompleted) {
        requireAllNonNull(information, date, cost);
        this.information = information;
        this.date = date;
        this.cost = cost;
        this.hasCompleted = hasCompleted;
    }

    public Information getInfo() {
        return this.information;
    }

    public Date getDate() {
        return this.date;
    }

    public Cost getCost() {
        return this.cost;
    }

    public Completion getHasCompleted() {
        return this.hasCompleted;
    }

    public void setHasCompleted(Completion hasCompleted) {
        this.hasCompleted = hasCompleted;
    }

    /**
     * Returns true if both Procedures have the same fields, less the completion field.
     */
    public boolean isProcedureDuplicate(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Procedure)) {
            return false;
        }

        Procedure otherPerson = (Procedure) other;
        return otherPerson.getInfo().equals(getInfo())
            && otherPerson.getDate().equals(getDate())
            && otherPerson.getCost().equals(getCost());
    }

    /**
     * Returns true if both Procedures have the same fields.
     * This defines a stronger notion of equality between two Procedures.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Procedure)) {
            return false;
        }

        Procedure otherProc = (Procedure) other;
        return otherProc.getInfo().equals(getInfo())
                && otherProc.getDate().equals(getDate())
                && otherProc.getCost().equals(getCost())
                && otherProc.getHasCompleted().equals(getHasCompleted());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(information, date, cost, hasCompleted);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Information: ")
                .append(getInfo())
                .append("; Date: ")
                .append(getDate())
                .append("; Cost: $")
                .append(getCost())
                .append("; Completed: ")
                .append(getHasCompleted());

        return builder.toString();
    }

    @Override
    public int compareTo(Procedure otherProcedure) {
        if (this.getDate().compareTo(otherProcedure.getDate()) != 0) {
            return this.getDate().compareTo(otherProcedure.getDate());
        } else if (this.getCost().compareTo(otherProcedure.getCost()) != 0) {
            return this.getCost().compareTo(otherProcedure.getCost());
        } else {
            return this.getInfo().compareTo(otherProcedure.getInfo());
        }
    }
}

