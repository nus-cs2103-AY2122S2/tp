package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GITHUB_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_INTLSTUDENT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.lab.Lab;
import seedu.address.model.lab.exceptions.DuplicateLabException;
import seedu.address.model.lab.exceptions.LabNotFoundException;
import seedu.address.model.student.Student;
import seedu.address.model.student.exceptions.DuplicateStudentException;
import seedu.address.testutil.StudentBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getStudentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateStudents_throwsDuplicateStudentException() {
        // Two students with the same identity fields
        Student editedAlice = new StudentBuilder(ALICE).withGithub(VALID_GITHUB_BOB).withTags(VALID_TAG_INTLSTUDENT)
                .build();
        List<Student> newStudents = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newStudents);

        assertThrows(DuplicateStudentException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasStudent_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasStudent(null));
    }

    @Test
    public void hasStudent_studentNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentInAddressBook_returnsTrue() {
        addressBook.addStudent(ALICE);
        assertTrue(addressBook.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addStudent(ALICE);
        Student editedAlice = new StudentBuilder(ALICE).withGithub(VALID_GITHUB_BOB).withTags(VALID_TAG_INTLSTUDENT)
                .build();
        assertTrue(addressBook.hasStudent(editedAlice));
    }

    @Test
    public void getStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getStudentList().remove(0));
    }

    @Test
    public void hasLab_nullLab_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasLab(null));
    }

    @Test
    public void hasLab_labNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasLab(new Lab("1")));
    }

    @Test
    public void hasLab_labInAddressBook_returnsTrue() {
        addressBook.addLab(new Lab("1"));
        assertTrue(addressBook.hasLab(new Lab("1")));
    }

    @Test
    public void removeLab_nullLab_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.removeLab(null));
    }

    @Test
    public void removeLab_labNotInAddressBook_throwsLabNotFoundException() {
        assertThrows(LabNotFoundException.class, () -> addressBook.removeLab(new Lab("12")));
    }

    @Test
    public void removeLab_labInAddressBook_success() {
        addressBook.addLab(new Lab("1"));
        assertDoesNotThrow(() -> addressBook.removeLab(new Lab("1")));
        assertFalse(addressBook.hasLab(new Lab("1")));
    }

    @Test
    public void addLab_nullLab_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.addLab(null));
    }

    @Test
    public void addLab_duplicateLab_throwsDuplicateLabException() {
        addressBook.addLab(new Lab("1"));
        assertThrows(DuplicateLabException.class, () -> addressBook.addLab(new Lab("1")));
    }

    /**
     * A stub ReadOnlyAddressBook whose students list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Student> students = FXCollections.observableArrayList();
        private final MasterLabList masterLabList = new MasterLabList();

        AddressBookStub(Collection<Student> students) {
            this.students.setAll(students);
        }

        @Override
        public ObservableList<Student> getStudentList() {
            return students;
        }

        @Override
        public MasterLabList getMasterLabList() {
            return masterLabList;
        }

        @Override
        public boolean isStudentListEmpty() {
            return students.isEmpty();
        }

        @Override
        public ArrayList<Lab> getLabsAsArrayList() {
            return masterLabList.getMasterList();
        }
    }

}
