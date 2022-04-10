package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.lab.Lab;
import seedu.address.model.student.Student;
import seedu.address.model.student.UniqueStudentList;

/**
 * Wraps all data at the TAddressbook level
 * Duplicates are not allowed (by .isSameStudent comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueStudentList students;

    private final MasterLabList labs;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        students = new UniqueStudentList();
    }

    public AddressBook() {
        labs = new MasterLabList();
    }

    /**
     * Creates a TAddressBook using the Students in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the Student list with {@code persons}.
     * {@code students} must not contain duplicate Students.
     */
    public void setStudents(List<Student> students) {
        this.students.setStudents(students);
    }

    /**
     * Replaces the contents of the Lab list with {@code labs}.
     * {@code labs} must not contain duplicate Labs.
     */
    public void setLabs(MasterLabList labs) {
        this.labs.setLabs(labs);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setStudents(newData.getStudentList());
        setLabs(newData.getMasterLabList());
    }

    //// student-level operations

    /**
     * Returns true if a student with the same identity as {@code student} exists in the TAddressBook.
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    /**
     * Adds a student to the TAddressBook.
     * The student must not already exist in the address book.
     */
    public void addStudent(Student s) {
        students.add(s);
    }

    /**
     * Replaces the given student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the TAddressBook.
     * The Student identity of {@code editedStudent} must not be the same as
     * another existing Student in the TAddressBook.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireNonNull(editedStudent);

        students.setStudent(target, editedStudent);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the TAddressBook.
     */
    public void removeStudent(Student key) {
        students.remove(key);
    }

    /**
     * Returns true if a Lab with the same identity as {@code lab} exists in the TAddressBook.
     */
    public boolean hasLab(Lab lab) {
        requireNonNull(lab);
        return labs.contains(lab);
    }

    /**
     * Adds a lab to the TAddressBook.
     * The lab must not already exist in the TAddressBook.
     *
     * @param lab The lab to be added.
     */
    public void addLab(Lab lab) {
        labs.add(lab);
        students.addLabToAll(lab);
    }

    /**
     * Removes a lab from the TAddressBook.
     * The lab must already exist in the TAddressBook.
     *
     * @param lab The lab to be removed.
     */
    public void removeLab(Lab lab) {
        Index toRemove = labs.removeLab(lab);
        students.removeLabFromAll(toRemove);
    }

    //// util methods

    @Override
    public String toString() {
        return students.asUnmodifiableObservableList().size() + " students";
        // TODO: refine later
    }

    @Override
    public ObservableList<Student> getStudentList() {
        return students.asUnmodifiableObservableList();
    }

    @Override
    public MasterLabList getMasterLabList() {
        return labs;
    }

    @Override
    public boolean isStudentListEmpty() {
        return students.isEmpty();
    }

    @Override
    public ArrayList<Lab> getLabsAsArrayList() {
        return labs.getMasterList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && students.equals(((AddressBook) other).students));
    }

    @Override
    public int hashCode() {
        return students.hashCode();
    }
}
