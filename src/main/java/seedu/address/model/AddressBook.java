package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.UniqueApplicantList;
import seedu.address.model.interview.Interview;
import seedu.address.model.interview.UniqueInterviewList;
import seedu.address.model.position.Position;
import seedu.address.model.position.UniquePositionList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueApplicantList applicants;
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
        applicants = new UniqueApplicantList();
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
    public void setApplicants(List<Applicant> applicants) {
        this.applicants.setApplicants(applicants);
    }

    /**
     * Replaces the contents of the position list with {@code positions}.
     * {@code positions} must not contain duplicate positions.
     */
    public void setPositions(List<Position> positions) {
        this.positions.setPositions(positions);
    }

    /**
     * Replaces the contents of the interview list with {@code interviews}.
     * {@code interviews} must not contain duplicate interviews.
     */
    public void setInterviews(List<Interview> interviews) {
        this.interviews.setInterviews(interviews);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setApplicants(newData.getApplicantList());
        setPositions(newData.getPositionList());
        setInterviews(newData.getInterviewList());

    }

    //// applicant-level operations

    /**
     * Returns true if a applicant with the same identity as {@code applicant} exists in the address book.
     */
    public boolean hasApplicant(Applicant applicant) {
        requireNonNull(applicant);
        return applicants.contains(applicant);
    }

    /**
     * Adds a applicant to the address book.
     * The applicant must not already exist in the address book.
     */
    public void addApplicant(Applicant p) {
        applicants.add(p);
    }

    /**
     * Replaces the given applicant {@code target} in the list with {@code editedApplicant}.
     * {@code target} must exist in the address book.
     * The applicant identity of {@code editedApplicant} must not be the same as another existing applicant
     *  in the address book.
     */
    public void setApplicant(Applicant target, Applicant editedApplicant) {
        requireNonNull(editedApplicant);

        applicants.setApplicant(target, editedApplicant);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeApplicant(Applicant key) {
        applicants.remove(key);
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
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeInterview(Interview key) {
        interviews.remove(key);
    }

    /**
     * Adds an interview to the address book.
     * The interview must not already exist in the address book.
     */
    public void addInterview(Interview i) {
        interviews.add(i);
    }

    /**
     * Replaces the given interview {@code target} with {@code editedInterview}.
     * {@code target} must exist in HireLah.
     * The interview identity of {@code editedInterview} must not be the same as another existing interview
     * in HireLah.
     */
    public void setInterview(Interview target, Interview editedInterview) {
        requireNonNull(editedInterview);

        interviews.setInterview(target, editedInterview);
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

    /**
     * Replaces the given position {@code target} in the list with {@code editedPosition}.
     * {@code target} must exist in the address book.
     * The position identity of {@code editedPosition} must not be the same as another existing position
     *  in the address book.
     */
    public void setPosition(Position target, Position editedPosition) {
        requireNonNull(editedPosition);

        positions.setPosition(target, editedPosition);
    }

    //// util methods

    @Override
    public String toString() {
        return applicants.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Applicant> getApplicantList() {
        return applicants.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Interview> getInterviewList() {
        return interviews.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Position> getPositionList() {
        return positions.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && applicants.equals(((AddressBook) other).applicants));
    }

    @Override
    public int hashCode() {
        return applicants.hashCode();
    }
}
