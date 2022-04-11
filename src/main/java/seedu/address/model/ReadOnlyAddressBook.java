package seedu.address.model;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import seedu.address.model.lab.Lab;
import seedu.address.model.student.Student;

/**
 * Unmodifiable view of a TAddress book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the student list.
     * This list will not contain any duplicate students.
     */
    ObservableList<Student> getStudentList();

    /**
     * Returns a MasterLabList containing all the Labs.
     * This list will not contain any duplicate Labs.
     */
    MasterLabList getMasterLabList();

    /**
     * Returns whether the ReadOnlyAddressBook's UniqueStudentList is empty.
     *
     * @return true is the ReadOnlyAddressBook's UniqueStudentList is empty and false otherwise.
     */
    boolean isStudentListEmpty();

    /**
     * Returns the list of labs in the ReadOnlyAddressBook as an ArrayList.
     */
    ArrayList<Lab> getLabsAsArrayList();
}
