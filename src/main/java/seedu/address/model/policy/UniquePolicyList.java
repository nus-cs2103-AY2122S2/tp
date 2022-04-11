package seedu.address.model.policy;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.client.Name;
import seedu.address.model.policy.exceptions.DuplicatePolicyException;
import seedu.address.model.policy.exceptions.EmptyPolicyListException;
import seedu.address.model.policy.exceptions.PolicyNotEditedException;
import seedu.address.model.policy.exceptions.PolicyNotFoundException;

/**
 * A list of policies that enforces uniqueness between its elements and does not allow nulls.
 * A policy is considered unique by comparing using {@code Policy#isSamePolicy(Policy)}. As such, adding and
 * updating of policies uses Policy#isSamePolicy(Policy) for equality to ensure that the policy being added or
 * updated is unique in terms of identity in the UniquePolicyList. However, the removal of a policy uses
 * Policy#equals(Object) to ensure that the policy with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Policy#isSamePolicy(Policy)
 */
public class UniquePolicyList implements Iterable<Policy> {

    private final ObservableList<Policy> internalList = FXCollections.observableArrayList();
    private final ObservableList<Policy> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent policy as the given argument.
     */
    public boolean contains(Policy toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePolicy);
    }

    /**
     * Adds a policy to the list.
     * The policy must not already exist in the list.
     */
    public void add(Policy toAdd) throws DuplicatePolicyException {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePolicyException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the policy {@code target} in the list with {@code editedPolicy}.
     * {@code target} must exist in the list.
     * The policy identity of {@code editedPolicy} must not be the same as another existing policy in the list.
     */
    public void setPolicy(Policy target, Policy editedPolicy) throws
            PolicyNotEditedException,
            DuplicatePolicyException {
        requireAllNonNull(target, editedPolicy);

        assert !internalList.isEmpty();

        int index = internalList.indexOf(target);
        assert index >= 0;

        if (target.equals(editedPolicy)) {
            throw new PolicyNotEditedException();
        }

        if (!setDoesNotCreateDuplicate(target, editedPolicy)) {
            throw new DuplicatePolicyException();
        }

        internalList.set(index, editedPolicy);
    }

    private boolean setDoesNotCreateDuplicate(Policy target, Policy editedPolicy) {
        for (Policy p : internalUnmodifiableList) {
            if (!p.equals(target) && p.isSamePolicy(editedPolicy)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Removes the equivalent policy from the list.
     * The policy must exist in the list.
     */
    public void remove(Policy toRemove) throws EmptyPolicyListException, PolicyNotFoundException {
        requireNonNull(toRemove);
        if (internalList.isEmpty()) {
            throw new EmptyPolicyListException();
        }
        if (!internalList.remove(toRemove)) {
            throw new PolicyNotFoundException();
        }
    }

    public void setPolicies(UniquePolicyList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code policies}.
     * {@code policies} must not contain duplicate policies.
     */
    public void setPolicies(List<Policy> policies) {
        requireAllNonNull(policies);
        if (!policiesAreUnique(policies)) {
            throw new DuplicatePolicyException();
        }

        internalList.setAll(policies);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Policy> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Policy> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniquePolicyList // instanceof handles nulls
                        && internalList.equals(((UniquePolicyList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code policies} contains only unique policies.
     */
    private boolean policiesAreUnique(List<Policy> policies) {
        for (int i = 0; i < policies.size() - 1; i++) {
            for (int j = i + 1; j < policies.size(); j++) {
                if (policies.get(i).isSamePolicy(policies.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns the sum total of premium amounts of all policies in the unique policy list.
     */
    public int totalPremiumSum() {
        int res = 0;
        for (Policy p : internalUnmodifiableList) {
            res += p.getPremium().getValue();
        }
        return res;
    }

    /**
     * Returns true if the {@code UniquePolicyList} contains an {@code Policy} from the given {@code company}
     */
    public boolean hasPolicyFromCompany(Name company) {
        for (Policy p : internalUnmodifiableList) {
            if (p.getCompany().equals(company)) {
                return true;
            }
        }
        return false;
    }
}
