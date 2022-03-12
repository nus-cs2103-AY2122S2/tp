package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.model.person.exceptions.DuplicateLabException;
import seedu.address.model.person.exceptions.DuplicatePersonException;
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

    public void setLabs(MasterLabList replacement) {
        requireNonNull(replacement);
        masterList.clear();
        masterList.addAll(replacement.masterList);
    }

    /**
     * Replaces the contents of this list with {@code labs}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setLabs(List<Lab> labs) {
        requireAllNonNull(labs);
        if (!labsAreUnique(labs)) {
            throw new DuplicateLabException();
        }
        masterList.clear();
        masterList.addAll(labs);
    }

    /**
     * Returns a copy of the {@code masterList}.
     */
    public ArrayList<Lab> getMasterList() {
        ArrayList<Lab> t = new ArrayList<>();
        t.addAll(masterList);
        return t;
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

    /**
     * Returns true if {@code labs} contains only unique labs.
     */
    private boolean labsAreUnique(List<Lab> labs) {
        for (int i = 0; i < labs.size() - 1; i++) {
            for (int j = i + 1; j < labs.size(); j++) {
                if (labs.get(i).isSameLab(labs.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
