package seedu.address.model.tamodule;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.tamodule.exceptions.DuplicateModuleException;
import seedu.address.model.tamodule.exceptions.ModuleNotFoundException;

/**
 * A list of modules that enforces uniqueness between its elements and does not allow nulls.
 * A module is considered unique by comparing using {@code TaModule#isSameModule(TaModule)}. As such, adding and
 * updating of modules uses TaModule#isSameModule(TaModule) for equality so as to ensure that the module being added
 * or updated is unique in terms of identity in the UniqueModuleList. However, the removal of a module uses
 * TaModule#equals(Object) so as to ensure that the module with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see TaModule#isSameModule(TaModule)
 */
public class UniqueModuleList implements Iterable<TaModule> {

    private final ObservableList<TaModule> internalList = FXCollections.observableArrayList();
    private final ObservableList<TaModule> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent module as the given argument.
     */
    public boolean contains(TaModule toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameModule);
    }

    /**
     * Adds a module to the list.
     * The module must not already exist in the list.
     */
    public void add(TaModule toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateModuleException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the module {@code target} in the list with {@code editedTaModule}.
     * {@code target} must exist in the list.
     * The module identity of {@code editedTaModule} must not be the same as another existing module in the list.
     */
    public void setModule(TaModule target, TaModule editedTaModule) {
        requireAllNonNull(target, editedTaModule);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ModuleNotFoundException();
        }

        if (!target.isSameModule(editedTaModule) && contains(editedTaModule)) {
            throw new DuplicateModuleException();
        }

        internalList.set(index, editedTaModule);
    }

    /**
     * Removes the equivalent module from the list.
     * The module must exist in the list.
     */
    public void remove(TaModule toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ModuleNotFoundException();
        }
    }

    public void setModules(UniqueModuleList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code taModules}.
     * {@code taModules} must not contain duplicate modules.
     */
    public void setModules(List<TaModule> taModules) {
        requireAllNonNull(taModules);
        if (!modulesAreUnique(taModules)) {
            throw new DuplicateModuleException();
        }

        internalList.setAll(taModules);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<TaModule> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<TaModule> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueModuleList // instanceof handles nulls
                        && internalList.equals(((UniqueModuleList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code taModules} contains only unique modules.
     */
    private boolean modulesAreUnique(List<TaModule> taModules) {
        for (int i = 0; i < taModules.size() - 1; i++) {
            for (int j = i + 1; j < taModules.size(); j++) {
                if (taModules.get(i).isSameModule(taModules.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
