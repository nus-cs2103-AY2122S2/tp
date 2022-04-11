package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.ModuleNotFoundException;

public class UniqueModuleList implements Iterable<Mod> {


    private ObservableList<Mod> internalList = FXCollections.observableArrayList();

    public UniqueModuleList() {
    }

    /**
     * Constructs a UniqueModuleList with a given UniqueModuleList to copy from.
     *
     * @param other the UniqueModuleList to copy from.
     */
    public UniqueModuleList(UniqueModuleList other) {
        requireNonNull(other);
        this.internalList.clear();

        for (Mod m : other) {
            this.internalList.add(new Mod(m.getMod(), m.getDefaultGroup()));
        }
    }

    /**
     * Returns true if the list contains a module with the same code as the given argument.
     */
    public boolean contains(Mod mod) {
        requireNonNull(mod);
        return internalList.stream().anyMatch(x -> x.value.equalsIgnoreCase(mod.value));
    }

    /**
     * Retrieves the module.
     * @param mod The module to be retrieved.
     * @return An optional module.
     */
    public Optional<Mod> retrieveMod(Mod mod) {
        requireNonNull(mod);
        String modCode = mod.value;
        return internalList.stream().filter(x -> x.value.equalsIgnoreCase(modCode)).findAny();
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(Mod mod) {
        requireNonNull(mod);
        if (contains(mod)) {
            throw new DuplicatePersonException();
        }
        internalList.add(mod);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(Mod toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ModuleNotFoundException();
        }
    }

    @Override
    public Iterator<Mod> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof UniqueModuleList); // instanceof handles nulls
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    public ObservableList<Mod> getInternalList() {
        return internalList;
    }

    /**
     * Resets the module list to the given module list.
     * @param ml The module list to reset to.
     */
    public void resetData(UniqueModuleList ml) {
        this.internalList.clear();
        for (Mod m : ml) {
            this.internalList.add(m);
        }
    }
}
