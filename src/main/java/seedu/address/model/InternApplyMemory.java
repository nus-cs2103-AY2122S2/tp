package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.application.Application;
import seedu.address.model.application.UniqueApplicationList;

/**
 * Wraps all data at the memory level
 * Duplicates are not allowed (by .isSameApplication comparison)
 */
public class InternApplyMemory implements ReadOnlyInternApplyMemory {

    private final UniqueApplicationList applications;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        applications = new UniqueApplicationList();
    }

    public InternApplyMemory() {}

    /**
     * Creates an InternApplyMemory using the Applications in the {@code toBeCopied}
     */
    public InternApplyMemory(ReadOnlyInternApplyMemory toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations
    /**
     * Replaces the order of the application list with {@code c} and order by {@code orderBy}.
     * */
    public void sortApplications(Comparator<Application> c, String orderBy) {
        requireNonNull(c);
        requireNonNull(orderBy);
        this.applications.sort(c, orderBy);
    }

    /**
     * Replaces the contents of the application list with {@code applications}.
     * {@code applications} must not contain duplicate applications.
     */
    public void setApplications(List<Application> applications) {
        this.applications.setApplications(applications);
    }

    /**
     * Resets the existing data of this {@code InternApplyMemory} with {@code newData}.
     */
    public void resetData(ReadOnlyInternApplyMemory newData) {
        requireNonNull(newData);

        setApplications(newData.getApplicationList());
    }

    //// application-level operations

    /**
     * Returns true if an application with the same identity as {@code application} exists in InternApply's memory.
     */
    public boolean hasApplication(Application application) {
        requireNonNull(application);
        return applications.contains(application);
    }

    /**
     * Adds an application to InternApply's memory.
     * The application must not already exist in InternApply's memory.
     */
    public void addApplication(Application p) {
        applications.add(p);
    }

    /**
     * Replaces the given application {@code target} in the list with {@code editedApplication}.
     * {@code target} must exist in InternApply's memory.
     * The application identity of {@code editedApplication} must not be the same as another existing application in
     * the address book.
     */
    public void setApplication(Application target, Application editedApplication) {
        requireNonNull(editedApplication);

        applications.setApplication(target, editedApplication);
    }

    /**
     * Removes {@code key} from this {@code InternApplyMemory}.
     * {@code key} must exist in InternApply's memory.
     */
    public void removeApplication(Application key) {
        applications.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return applications.asUnmodifiableObservableList().size() + " applications";
        // TODO: refine later
    }

    @Override
    public ObservableList<Application> getApplicationList() {
        return applications.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InternApplyMemory // instanceof handles nulls
                && applications.equals(((InternApplyMemory) other).applications));
    }

    @Override
    public int hashCode() {
        return applications.hashCode();
    }
}
