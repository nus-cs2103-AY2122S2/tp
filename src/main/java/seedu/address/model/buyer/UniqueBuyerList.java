package seedu.address.model.buyer;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.util.ComparatorUtil;
import seedu.address.model.client.Client;
import seedu.address.model.client.exceptions.ClientNotFoundException;
import seedu.address.model.client.exceptions.DuplicateClientException;

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
public class UniqueBuyerList implements Iterable<Buyer> {

    private final ObservableList<Buyer> internalList = FXCollections.observableArrayList();
    private final ObservableList<Buyer> internalUnmodifiableList =
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
    public void add(Buyer toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateClientException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the client {@code target} in the list with {@code editedclient}.
     * {@code target} must exist in the list.
     * The client identity of {@code editedclient} must not be the same as another existing client in the list.
     */
    public void setBuyer(Buyer target, Buyer editedClient) {
        requireAllNonNull(target, editedClient);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ClientNotFoundException();
        }

        if (!target.isSameclient(editedClient) && contains(editedClient)) {
            throw new DuplicateClientException();
        }

        internalList.set(index, editedClient);
    }

    /**
     * Removes the equivalent client from the list.
     * The client must exist in the list.
     */
    public void remove(Buyer toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ClientNotFoundException();
        }
    }

    public void setBuyers(UniqueBuyerList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code clients}.
     * {@code clients} must not contain duplicate clients.
     */
    public void setBuyers(List<Buyer> buyers) {
        requireAllNonNull(buyers);
        if (!clientsAreUnique(buyers)) {
            throw new DuplicateClientException();
        }

        internalList.setAll(buyers);
    }

    /**
     * Sorts the buyer according to {@code comparator} and in {@code order} order.
     */
    public void sortBuyers(String comparator, String order) {
        if (comparator.equals("name")) {
            if (order.equals("asc")) {
                internalList.sort(ComparatorUtil.NAME_COMPARATOR_ASC);
            } else {
                internalList.sort(ComparatorUtil.NAME_COMPARATOR_DESC);
            }
        } else {
            if (order.equals("asc")) {
                internalList.sort(ComparatorUtil.TIME_COMPARATOR_ASC);
            } else {
                internalList.sort(ComparatorUtil.TIME_COMAPRATOR_DESC);
            }
        }
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Buyer> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Buyer> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueBuyerList // instanceof handles nulls
                && internalList.equals(((UniqueBuyerList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code clients} contains only unique clients.
     */
    private boolean clientsAreUnique(List<Buyer> buyers) {
        for (int i = 0; i < buyers.size() - 1; i++) {
            for (int j = i + 1; j < buyers.size(); j++) {
                if (buyers.get(i).isSameclient(buyers.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
