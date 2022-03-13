package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.UniquePersonList;
import seedu.address.model.interview.Interview;
import seedu.address.model.interview.UniqueInterviewList;
import seedu.address.model.position.Position;
import seedu.address.model.position.UniquePositionList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueInterviewList interviews;
    private final UniquePositionList positions;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        interviews = new UniqueInterviewList();
        positions = new UniquePositionList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the applicant list with {@code applicants}.
     * {@code applicants} must not contain duplicate applicants.
     */
    public void setPersons(List<Applicant> applicants) {
        this.persons.setPersons(applicants);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// applicant-level operations

    /**
     * Returns true if a applicant with the same identity as {@code applicant} exists in the address book.
     */
    public boolean hasPerson(Applicant applicant) {
        requireNonNull(applicant);
        return persons.contains(applicant);
    }

    /**
     * Adds a applicant to the address book.
     * The applicant must not already exist in the address book.
     */
    public void addPerson(Applicant p) {
        persons.add(p);
    }

    /**
     * Replaces the given applicant {@code target} in the list with {@code editedApplicant}.
     * {@code target} must exist in the address book.
     * The applicant identity of {@code editedApplicant} must not be the same as another existing applicant
     *  in the address book.
     */
    public void setPerson(Applicant target, Applicant editedApplicant) {
        requireNonNull(editedApplicant);

        persons.setPerson(target, editedApplicant);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Applicant key) {
        persons.remove(key);
    }

    //// interview-level operations

    /**
     * Returns true if an interview that is the same as {@code interview} exists in the address book.
     */
    public boolean hasInterview(Interview i) {
        requireNonNull(i);
        return interviews.contains(i);
    }

    /**
     * Adds an interview to the address book.
     * The interview must not already exist in the address book.
     */
    public void addInterview(Interview i) {
        interviews.add(i);
    }

    //// position-level operations

    /**
     * Returns true if a position that is the same as {@code position} exists in the address book.
     */
    public boolean hasPosition(Position position) {
        requireNonNull(position);
        return positions.contains(position);
    }

    /**
     * Adds a position to the address book.
     * The position must not already exist in the address book.
     */
    public void addPosition(Position position) {
        positions.add(position);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePosition(Position key) {
        positions.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Applicant> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Position> getPositionList() {
        return positions.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
