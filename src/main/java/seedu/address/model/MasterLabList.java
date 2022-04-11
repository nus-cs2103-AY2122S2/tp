package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.commons.core.index.Index;
import seedu.address.model.lab.Lab;
import seedu.address.model.lab.LabList;
import seedu.address.model.lab.exceptions.LabNotFoundException;

public class MasterLabList extends LabList {

    /**
     * Removes the given Lab from the MasterLabList and returns the index of the given Lab.
     *
     * @param lab The Lab that is to be removed
     * @return An Index object specifying where the lab is in the list.
     * @throws LabNotFoundException If the MasterLabList does not contain the given Lab.
     */
    public Index removeLab(Lab lab) throws LabNotFoundException {
        requireNonNull(lab);
        if (!contains(lab)) {
            throw new LabNotFoundException(lab.labNumber);
        }
        Index toRemove = indexOf(lab);
        remove(toRemove);
        return toRemove;
    }

    /**
     * Returns the index of the Lab with {@code labNumber}
     *
     * @param toIndex The lab number of the Lab to search for.
     */
    private Index indexOf(Lab toIndex) throws LabNotFoundException {
        requireNonNull(toIndex);

        if (!contains(toIndex)) {
            throw new LabNotFoundException(toIndex.labNumber);
        }

        return Index.fromZeroBased(super.asUnmodifiableObservableList().indexOf(toIndex));
    }

    /**
     * Returns a copy of the {@code masterList}.
     */
    public ArrayList<Lab> getMasterList() {
        ArrayList<Lab> t = new ArrayList<>();
        t.addAll(super.asUnmodifiableObservableList());
        return t;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MasterLabList // instanceof handles nulls
                && super.equals(other));
    }
}
