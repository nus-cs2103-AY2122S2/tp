package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.classgroup.ClassGroup;
import seedu.address.model.student.Student;
import seedu.address.model.tamodule.TaModule;

/**
 * Unmodifiable view of an address book.
 */
public interface ReadOnlyTAssist {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Student> getStudentList();

    ObservableList<TaModule> getModuleList();

    ObservableList<ClassGroup> getClassGroupList();

    ObservableList<Assessment> getAssessmentList();

}
