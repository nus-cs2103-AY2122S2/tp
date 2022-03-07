package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.model.person.exceptions.DuplicateLabException;
import seedu.address.model.person.exceptions.LabNotFoundException;
import seedu.address.model.person.lab.Lab;

public class MasterLabList {
    private final ArrayList<Lab> masterList = new ArrayList<>();

    /**
     * Returns true if the list contains an equivalent lab as the given argument.
     */
    public boolean contains(Lab toCheck) {
        requireNonNull(toCheck);
        return masterList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a lab to the list.
     * The person must not already exist in the list.
     */
    public void add(Lab toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateLabException();
        }
        masterList.add(toAdd);
    }

    /**
     * Removes the equivalent lab from the list.
     * The lab must exist in the list.
     */
    public void remove(Lab toRemove) {
        requireNonNull(toRemove);
        if (!masterList.remove(toRemove)) {
            throw new LabNotFoundException(toRemove.labNumber);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MasterLabList // instanceof handles nulls
                && masterList.equals(((MasterLabList) other).masterList));
    }

    @Override
    public int hashCode() {
        return masterList.hashCode();
    }
}
