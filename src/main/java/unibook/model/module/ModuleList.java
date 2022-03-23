package unibook.model.module;

import static java.util.Objects.requireNonNull;
import static unibook.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import unibook.model.module.exceptions.DuplicateModuleException;
import unibook.model.module.exceptions.ModuleNotFoundException;
import unibook.model.person.Person;

/**
 * Represents a list of Modules in the UniBook.
 */
public class ModuleList implements Iterable<Module> {
    private final ObservableList<Module> internalList = FXCollections.observableArrayList();
    private final ObservableList<Module> internalUnmodifiableList =
        FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent module as the given argument.
     */
    public boolean contains(Module toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameModule);
    }

    /**
     * Returns true if the list contains a module with given module code.
     *
     * @param moduleCode code to search for
     * @return boolean variable indicating presence of this module with the moduleCode
     */
    public boolean contains(ModuleCode moduleCode) {
        requireNonNull(moduleCode);
        return internalList.stream().anyMatch(module -> module.hasModuleCode(moduleCode));
    }

    /**
     * Adds a Module to the list.
     * The Module must not already exist in the list.
     */
    public void add(Module toAdd) {
        requireNonNull(toAdd);

        if (contains(toAdd)) {
            throw new DuplicateModuleException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the module {@code target} in the list with {@code editedModule}.
     * {@code target} must exist in the list.
     * The module name and code of {@code editedModule} must not be the same as another existing module in the list.
     */
    public void setModule(Module target, Module editedModule) {
        requireAllNonNull(target, editedModule);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ModuleNotFoundException(target);
        }

        if (!target.isSameModule(editedModule) && contains(editedModule)) {
            throw new DuplicateModuleException();
        }

        internalList.set(index, editedModule);
    }

    /**
     * Removes the equivalent module from the list.
     * The module must exist in the list.
     */
    public void remove(Module toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ModuleNotFoundException(toRemove);
        }
    }

    /**
     * Removes a module from ModuleList if it matches toRemove.
     *
     * @param toRemove
     */
    public void removeByModuleCode(ModuleCode toRemove) {
        requireNonNull(toRemove);
        Module remove = null; // necessary to prevent concurrent modification exception
        for (Module module : internalList) {
            if (module.hasModuleCode(toRemove)) {
                remove = module;
            }
        }
        internalList.remove(remove);
    }

    /**
     * Remove person from every module in ModuleList if present
     *
     * @param person
     */
    public void removePersonFromModule(Person person) {
        requireNonNull(person);
        for (Module module : internalList) {
            module.removePerson(person);
        }
    }

    public void setModules(ModuleList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code modules}.
     * {@code modules} must not contain duplicate modules.
     */
    public void setModules(List<Module> modules) {
        requireAllNonNull(modules);
        if (!modulesAreUnique(modules)) {
            throw new DuplicateModuleException();
        }

        internalList.setAll(modules);
    }


    /**
     * Returns the module thats equivalent to the given module object.
     *
     * @param searchModule
     * @return module object
     * @throws ModuleNotFoundException
     */
    public Module getModule(Module searchModule) throws ModuleNotFoundException {
        requireNonNull(searchModule);
        for (Module module : internalList) {
            if (module.equals(searchModule)) {
                return module;
            }
        }
        throw new ModuleNotFoundException(searchModule);
    }

    /**
     * Returns the module with given moduleCode if it exists in module list.
     * If not, throws ModuleNotExistException.
     *
     * @param moduleCode moudleCode of intended module.
     * @return the module with matching moduleCode.
     */
    public Module getModuleByCode(ModuleCode moduleCode) {
        requireAllNonNull(moduleCode);
        for (Module module : internalList) {
            if (module.hasModuleCode(moduleCode)) {
                return module;
            }
        }
        throw new ModuleNotFoundException(moduleCode);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Module> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Module> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ModuleList) // instanceof handles nulls
            && internalList.equals(((ModuleList) other).internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code modules} contains only unique modules.
     */
    private boolean modulesAreUnique(List<Module> modules) {
        for (int i = 0; i < modules.size() - 1; i++) {
            for (int j = i + 1; j < modules.size(); j++) {
                if (modules.get(i).isSameModule(modules.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
