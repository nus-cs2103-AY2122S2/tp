package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.commons.core.index.Index;
import seedu.address.model.person.exceptions.DuplicateLabException;
import seedu.address.model.person.exceptions.LabNotFoundException;
import seedu.address.model.person.lab.Lab;

public class MasterLabList {
    private final ArrayList<Lab> masterList = new ArrayList<>();

    /**
     * Returns true if the list contains an equivalent lab as the given argument.
     *
     * @param toCheck The Lab to be checked.
     */
    public boolean contains(Lab toCheck) {
        requireNonNull(toCheck);
        return masterList.stream().anyMatch(toCheck::isSameLab);
    }

    /**
     * Adds a lab to the list.
     * The person must not already exist in the list.
     *
     * @param toAdd The Lab that you want to add.
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
     *
     * @param toRemove Lab you want to remove from the list.
     */
    public void remove(Lab toRemove) {
        requireNonNull(toRemove);
        if (!masterList.remove(toRemove)) {
            throw new LabNotFoundException(toRemove.labNumber);
        }
    }

    /**
     * Returns the index of the Lab with {@code labNumber}
     *
     * @param labNumber The lab number of the Lab you want to find.
     */
    public Index indexOf(String labNumber) {
        requireNonNull(labNumber);
        Lab toIndex = new Lab(labNumber);
        if (!masterList.contains(toIndex)) {
            throw new LabNotFoundException(toIndex.labNumber);
        }
        return Index.fromZeroBased(masterList.indexOf(toIndex));
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
