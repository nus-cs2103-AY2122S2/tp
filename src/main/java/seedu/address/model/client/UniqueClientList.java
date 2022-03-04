package seedu.address.model.client;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.client.exceptions.DuplicateclientException;
import seedu.address.model.client.exceptions.ClientNotFoundException;

/**
 * A list of clients that enforces uniqueness between its elements and does not allow nulls.
 * A client is considered unique by comparing using {@code client#isSameclient(client)}. As such, adding and updating of
 * clients uses client#isSameclient(client) for equality so as to ensure that the client being added or updated is
 * unique in terms of identity in the UniqueclientList. However, the removal of a client uses client#equals(Object) so
 * as to ensure that the client with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Client#isSameclient(Client)
 */
public class UniqueClientList implements Iterable<Client> {

    private final ObservableList<Client> internalList = FXCollections.observableArrayList();
    private final ObservableList<Client> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent client as the given argument.
     */
    public boolean contains(Client toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameclient);
    }

    /**
     * Adds a client to the list.
     * The client must not already exist in the list.
     */
    public void add(Client toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateclientException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the client {@code target} in the list with {@code editedclient}.
     * {@code target} must exist in the list.
     * The client identity of {@code editedclient} must not be the same as another existing client in the list.
     */
    public void setclient(Client target, Client editedClient) {
        requireAllNonNull(target, editedClient);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ClientNotFoundException();
        }

        if (!target.isSameclient(editedClient) && contains(editedClient)) {
            throw new DuplicateclientException();
        }

        internalList.set(index, editedClient);
    }

    /**
     * Removes the equivalent client from the list.
     * The client must exist in the list.
     */
    public void remove(Client toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ClientNotFoundException();
        }
    }

    public void setclients(UniqueClientList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code clients}.
     * {@code clients} must not contain duplicate clients.
     */
    public void setclients(List<Client> clients) {
        requireAllNonNull(clients);
        if (!clientsAreUnique(clients)) {
            throw new DuplicateclientException();
        }

        internalList.setAll(clients);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Client> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Client> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueClientList // instanceof handles nulls
                        && internalList.equals(((UniqueClientList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code clients} contains only unique clients.
     */
    private boolean clientsAreUnique(List<Client> clients) {
        for (int i = 0; i < clients.size() - 1; i++) {
            for (int j = i + 1; j < clients.size(); j++) {
                if (clients.get(i).isSameclient(clients.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
