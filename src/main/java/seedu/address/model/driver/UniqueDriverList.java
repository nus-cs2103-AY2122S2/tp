package seedu.address.model.driver;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.driver.exception.DriverNotFoundException;
import seedu.address.model.driver.exception.DuplicateDriverException;

public class UniqueDriverList implements Iterable<Driver> {
    private final ObservableList<Driver> internalList = FXCollections.observableArrayList();
    private final ObservableList<Driver> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent driver as the given argument.
     */
    public boolean contains(Driver toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameDriver);
    }


    /**
     * Returns true if the list contains an equivalent customer as the given argument.
     */
    public boolean containsExcludeCurrentDriver(Driver toRemove, Driver toCheck) {
        requireNonNull(toRemove);
        requireNonNull(toCheck);
        return internalList.stream().filter(x -> !x.isCurrentDriver(toRemove)).anyMatch(toCheck::isSameDriver);
    }

    /**
     * Adds a driver to the list.
     * The driver must not already exist in the list.
     */
    public void add(Driver toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateDriverException();
        }
        internalList.add(toAdd);
    }

    public void setDriver(Driver target, Driver editedDriver) {
        requireAllNonNull(target, editedDriver);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new DriverNotFoundException();
        }

        if (!target.isSameDriver(editedDriver) && contains(editedDriver)) {
            throw new DuplicateDriverException();
        }

        internalList.set(index, editedDriver);
    }

    public void setDrivers(UniqueDriverList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    public void setDrivers(List<Driver> drivers) {
        requireAllNonNull(drivers);
        if (!driversAreUnique(drivers)) {
            throw new DuplicateDriverException();
        }

        internalList.setAll(drivers);
    }

    /**
     * Removes the equivalent driver from the list.
     * The driver must exist in the list.
     */
    public void remove(Driver toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new DriverNotFoundException();
        }
    }
    public ObservableList<Driver> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Driver> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueDriverList // instanceof handles nulls
                && internalList.equals(((UniqueDriverList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    private boolean driversAreUnique(List<Driver> drivers) {
        for (int i = 0; i < drivers.size() - 1; i++) {
            for (int j = i + 1; j < drivers.size(); j++) {
                if (drivers.get(i).isSameDriver(drivers.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
