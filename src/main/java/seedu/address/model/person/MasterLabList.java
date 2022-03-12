package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.commons.core.index.Index;
import seedu.address.model.person.exceptions.LabNotFoundException;
import seedu.address.model.person.lab.Lab;
import seedu.address.model.person.lab.LabList;

public class MasterLabList extends LabList {

    /**
     * Returns the index of the Lab with {@code labNumber}
     *
     * @param labNumber The lab number of the Lab you want to find.
     */
    public Index indexOf(String labNumber) {
        requireNonNull(labNumber);
        Lab toIndex = new Lab(labNumber);
        if (!super.asUnmodifiableObservableList().contains(toIndex)) {
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
