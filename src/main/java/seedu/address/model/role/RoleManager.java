package seedu.address.model.role;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/**
 * Represents the list of all roles tagged to a Company found in the address book.
 */
public class RoleManager {

    private static final Predicate<Role> PREDICATE_SHOW_ALL_ROLES = unused -> true;

    private ObservableList<Role> roleList;
    private FilteredList<Role> filteredRoleList;

    /**
     * Initializes a <code>RoleManager</code> with an empty list of <code>Role</code>
     */
    public RoleManager() {
        roleList = FXCollections.observableArrayList();
        filteredRoleList = roleList.filtered(PREDICATE_SHOW_ALL_ROLES);
    }

    /**
     * Obtains the list of roles tagged to a Company
     *
     * @return <code>ObservableList</code> representing the list of roles
     */
    public ObservableList<Role> getRoles() {
        return this.roleList;
    }

    /**
     * Obtains the filtered list of roles tagged to a Company
     *
     * @return <code>FilteredList</code> representing the list of roles
     */
    public ObservableList<Role> getFilteredRoles() {
        return filteredRoleList;
    }

    /**
     * Obtains the unmodifiable set of roles tagged to a <code>Company</code>
     *
     * @return <code>Set</code> representing the set of roles
     */
    public Set<Role> getSetRoles() {
        Set<Role> roleSet = new HashSet<Role>(this.filteredRoleList);
        return Collections.unmodifiableSet(roleSet);
    }

    /**
     * Filters the list of roles tagged to a <code>Company</code> given a predicate
     */
    public void filterRoles(Predicate<Role> predicate) {
        filteredRoleList.setPredicate(predicate);
    }

    /**
     * Obtains the number of roles
     *
     * @return Integer representing the number of roles
     */
    public int countRoles() {
        return this.roleList.size();
    }

    /**
     * Add <code>Role</code> to list of roles tagged to a company
     * @param role Role to be added to the list of roles
     */
    public void addRole(Role role) {
        this.roleList.add(role);
    }

    /**
     * Checks if this role exists in the roleList
     * @param role role to check if it is duplicated
     * @return Boolean representing if this role is already stored
     */
    public boolean hasRole(Role role) {
        requireNonNull(role);
        return roleList.contains(role);
    }

    /**
     * Deletes <code>Role</code> given the index within the <code>Company</code>
     * @param index of <code>Role</code> to be deleted
     */
    public void deleteRole(int index) {
        this.roleList.remove(index);

    }
}
