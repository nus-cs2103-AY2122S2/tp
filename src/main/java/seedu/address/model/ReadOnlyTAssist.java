package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.classgroup.ClassGroup;
import seedu.address.model.student.Student;
import seedu.address.model.tamodule.TaModule;

/**
 * Unmodifiable view of an TAssist.
 */
public interface ReadOnlyTAssist {

    /**
     * Returns an unmodifiable view of the student list.
     * This list will not contain any duplicate students.
     */
    ObservableList<Student> getStudentList();

    /**
     * Returns an unmodifiable view of the module list.
     * This list will not contain any duplicate modules.
     */
    ObservableList<TaModule> getModuleList();

    /**
     * Returns an unmodifiable view of the class group list.
     * This list will not contain any duplicate class groups.
     */
    ObservableList<ClassGroup> getClassGroupList();

    /**
     * Returns an unmodifiable view of the assessment list.
     * This list will not contain any duplicate assessments.
     */
    ObservableList<Assessment> getAssessmentList();

}
