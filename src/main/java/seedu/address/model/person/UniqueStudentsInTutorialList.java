package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.person.Student.isStudent;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.model.tutorial.TutorialName;

/**
 * A list of students in a Tutorial.
 * Updated automatically when changes are made to the modelManager's allStudents list.
 */
public class UniqueStudentsInTutorialList {
    /**
     * This list should contain only Student objects.
     */
    private final FilteredList<Person> studentsInClass;
    private final TutorialName tutorialName;

    /**
     * Constructs a UniqueStudentsInTutorialList.
     *
     * @param allStudents the ModelManager's allStudents list, containing all Students in the addressbook.
     * @param tutName the TutorialName.
     */

    public UniqueStudentsInTutorialList(ObservableList<Person> allStudents, TutorialName tutName) {
        requireAllNonNull(allStudents, tutName);
        studentsInClass = createStudentsList(allStudents, tutName);
        tutorialName = tutName;
    }

    private FilteredList<Person> createStudentsList(ObservableList<Person> allStudents, TutorialName tutName) {
        Predicate<Person> predicateShowStudentsInTutorial = x -> isStudent(x)
                && ((Student) x).isInTutorial(tutName);
        return new FilteredList<Person>(allStudents, predicateShowStudentsInTutorial);
    }

    /**
     * Returns true if the list contains person with an equivalent student name as the argument,
     * uses {@code Person#isSamePerson(Person)}
     */
    public boolean containsStudent(Student student) {
        return studentsInClass.stream()
                .anyMatch(x -> x.isSamePerson(student));
    }

    /**
     * Returns true if the list contains a student with a matching {@code studentId}.
     */
    public boolean containsStudentWithId(NusNetId studentId) {
        for (int i = 0; i < studentsInClass.size(); i++) {
            Student student = (Student) studentsInClass.get(i);
            if (student.getStudentId().equals(studentId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the student in the list that has a matching {@code studentId}.
     * @param studentId
     * @return
     */
    public Student getStudentWithId(NusNetId studentId) {
        Student student;
        for (int i = 0; i < studentsInClass.size(); i++) {
            student = (Student) studentsInClass.get(i);
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        // execution should not reach this point
        return null;
    }

    public FilteredList<Person> getStudentsInClass() {
        return studentsInClass;
    }
}
