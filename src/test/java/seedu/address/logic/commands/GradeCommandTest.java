package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.commands.AssessmentTestUtil.VALID_ASSESSMENT_NAME_OP1;
import static seedu.address.logic.commands.AssessmentTestUtil.VALID_ASSESSMENT_NAME_OP2;
import static seedu.address.logic.commands.AssessmentTestUtil.VALID_FULL_MARK_OP2;
import static seedu.address.logic.commands.AssessmentTestUtil.VALID_SCORE_OP1;
import static seedu.address.logic.commands.AssessmentTestUtil.VALID_SCORE_OP2;
import static seedu.address.logic.commands.StudentTestUtil.VALID_NAME_AARON;
import static seedu.address.logic.commands.StudentTestUtil.VALID_NAME_BILL;
import static seedu.address.logic.commands.StudentTestUtil.VALID_STUDENT_ID_AARON;
import static seedu.address.logic.commands.TutorialTestUtil.VALID_TUTORIAL_NAME_TG1;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAssessments.OP1;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Displayable;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.assessment.AssessmentName;
import seedu.address.model.assessment.AssessmentResults;
import seedu.address.model.assessment.FullMark;
import seedu.address.model.assessment.Score;
import seedu.address.model.assessment.StudentResult;
import seedu.address.model.assessment.exceptions.AssessmentNotFoundException;
import seedu.address.model.assessment.exceptions.DuplicateStudentResultException;
import seedu.address.model.assessment.exceptions.StudentResultNotFoundException;
import seedu.address.model.attendance.Attendance;
import seedu.address.model.attendance.Comment;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.NusNetId;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Student;
import seedu.address.model.person.exceptions.StudentNotFoundException;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.model.tutorial.TutorialName;
import seedu.address.testutil.AssessmentBuilder;
import seedu.address.testutil.StudentBuilder;
import seedu.address.testutil.StudentResultBuilder;
import seedu.address.testutil.TutorialBuilder;

public class GradeCommandTest {
    @Test
    public void constructor_nullArguments_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
            -> new GradeCommand(null, new Name(VALID_NAME_AARON), VALID_SCORE_OP1));
    }

    @Test
    public void execute_addedScoreAcceptedByModel_addSuccessful() throws Exception {
        ModelStubWithTutorialStudentAssessment modelStub =
                new ModelStubWithTutorialStudentAssessment(VALID_NAME_AARON, VALID_STUDENT_ID_AARON,
                        VALID_ASSESSMENT_NAME_OP2, VALID_FULL_MARK_OP2, VALID_TUTORIAL_NAME_TG1);
        AssessmentName assessmentName = new AssessmentName(VALID_ASSESSMENT_NAME_OP2);
        Name studentName = new Name(VALID_NAME_AARON);
        String score = VALID_SCORE_OP2;

        StudentResult validStudentResult = new StudentResultBuilder().withName(VALID_NAME_AARON)
                .withNusNetId(VALID_STUDENT_ID_AARON).withScore(score, VALID_FULL_MARK_OP2).build();
        CommandResult commandResult =
                new GradeCommand(assessmentName, studentName, score).execute(modelStub);

        assertEquals(String.format(GradeCommand.ADD_MESSAGE_SUCCESS, score, studentName,
                        VALID_TUTORIAL_NAME_TG1, assessmentName, VALID_FULL_MARK_OP2),
                            commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validStudentResult), modelStub.results.asUnmodifiableStudentResultsList());
    }

    @Test
    public void execute_editedScoreAcceptedByModel_editsSuccessful() throws Exception {
        ModelStubWithTutorialStudentAssessment modelStub =
                new ModelStubWithTutorialStudentAssessment(VALID_NAME_AARON, VALID_STUDENT_ID_AARON,
                        VALID_ASSESSMENT_NAME_OP2, VALID_FULL_MARK_OP2, VALID_TUTORIAL_NAME_TG1);

        AssessmentName assessmentName = new AssessmentName(VALID_ASSESSMENT_NAME_OP2);
        Name studentName = new Name(VALID_NAME_AARON);
        String score = VALID_SCORE_OP2;

        // initialize the model with a student result of a different score
        modelStub.addStudentResult(studentName, assessmentName, new Score(VALID_SCORE_OP1,
                new FullMark(VALID_FULL_MARK_OP2)));

        StudentResult validStudentResult = new StudentResultBuilder().withName(VALID_NAME_AARON)
                .withNusNetId(VALID_STUDENT_ID_AARON).withScore(score, VALID_FULL_MARK_OP2).build();

        CommandResult commandResult =
                new GradeCommand(assessmentName, studentName, score).execute(modelStub);

        assertEquals(String.format(GradeCommand.EDIT_MESSAGE_SUCCESS, score, studentName,
                VALID_TUTORIAL_NAME_TG1, assessmentName, VALID_FULL_MARK_OP2), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validStudentResult), modelStub.results.asUnmodifiableStudentResultsList());
    }

    @Test
    public void equals() {
        GradeCommand gradeCommandAaronOp1 = new GradeCommand(new AssessmentName(VALID_ASSESSMENT_NAME_OP1),
                new Name(VALID_NAME_AARON), VALID_SCORE_OP1);

        GradeCommand gradeCommandBillOp2 = new GradeCommand(new AssessmentName(VALID_ASSESSMENT_NAME_OP2),
                new Name(VALID_NAME_BILL), VALID_SCORE_OP2);

        // same object -> returns true
        assertTrue(gradeCommandAaronOp1.equals(gradeCommandAaronOp1));

        // same values -> returns true
        GradeCommand gradeCommandAaronOp1Copy = new GradeCommand(new AssessmentName(VALID_ASSESSMENT_NAME_OP1),
                new Name(VALID_NAME_AARON), VALID_SCORE_OP1);
        assertTrue(gradeCommandAaronOp1.equals(gradeCommandAaronOp1Copy));

        // null -> returns false
        assertFalse(gradeCommandBillOp2.equals(null));

        // different objects -> returns false
        assertFalse(gradeCommandAaronOp1.equals(gradeCommandBillOp2));
    }


    /**
     * A default Model Stub that has all methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPersonWithEmail(Email email) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPersonWithPhone(Phone phone) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTutorial(Tutorial tutorial) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Tutorial getTutorialWithName(TutorialName tutorialName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTutorial(Tutorial tutorial) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTutorial(Tutorial tutorial) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTutorial(Tutorial target, Tutorial editedTutorial) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public FilteredList<Person> getFilteredStudentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<StudentResult> getDisplayAssessmentResults() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateDisplayAssessmentResults(TutorialName tutName, AssessmentName assessmentName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Tutorial> getFilteredTutorialList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Assessment> getAssessmentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Assessment> getFilteredAssessmentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredAssessmentList(Predicate<Assessment> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAssessment(Assessment assessment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAssessment(Assessment toAdd) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAssessmentWithName(AssessmentName name) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Assessment getAssessmentWithName(AssessmentName assessmentName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Assessment removeAssessmentWithName(AssessmentName name) {
            throw new AssertionError("This method should not be called.");
        }

        public void updateFilteredTutorialList(Predicate<Tutorial> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredStudentList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public TutorialName getTutorialNameOfStudent(Name studentName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Attendance> getFilteredAttendanceList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addComment(Tutorial tutorial, Name studentToComment, Comment toAdd) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removeComment(Tutorial tutorial, Name studentToRemoveComment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Comment getComment(Tutorial tutorial, Name studentToViewComment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Comment> getCommentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredAttendanceList(Tutorial tutorial, Name studentName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public FilteredList<Person> getAllStudentsList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addStudent(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removeStudent(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasStudentWithName(Name studentName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasStudentResult(Name studentName, AssessmentName assessmentName) {
            return false;
        }

        @Override
        public void addStudentResult(Name studentName, AssessmentName assessmentName, Score sc) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removeStudentResults(NusNetId studentId, TutorialName tutorialName) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void setStudentResult(Name studentName, AssessmentName assessmentName, Score sc) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasStudentWithId(NusNetId toAddStudentId) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void markAttendanceForClass(Tutorial tutorial, int week) {
            throw new AssertionError("This method should not be called.");

        }

        @Override
        public void markAttendanceForStudent(Tutorial tutorial, NusNetId studentId, int week) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void unmarkAttendanceForClass(Tutorial tutorial, int week) {
            throw new AssertionError("This method should not be called.");

        }

        @Override
        public void unmarkAttendanceForStudent(Tutorial tutorial, NusNetId studentId, int week) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTutorialWithName(TutorialName tutorialName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPersonWithName(Name name) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Person getPersonWithName(Name name) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonsMultiPredList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Student getStudentWithId(NusNetId id) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFilteredPersonsMultiPredList(List<Person> persons) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean tutorialHasStudentWithId(NusNetId id, TutorialName tutorialName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Displayable> getLastShownList() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A model stub that contains the tutorial, student, assessment to support the adding of score.
     */
    private class ModelStubWithTutorialStudentAssessment extends ModelStub {
        final Student student;
        final Tutorial tutorial;
        final Assessment assessment;
        final AssessmentResults results;

        ModelStubWithTutorialStudentAssessment(String studentName, String studentId,
                                               String assessmentName, String fullMark, String tutName) {
            student = new StudentBuilder().withName(studentName)
                    .withStudentId(studentId).withTutorialName(tutName).build();
            assessment = new AssessmentBuilder(OP1).withName(assessmentName)
                    .withFullMark(fullMark).build();
            results = new AssessmentResults(new AssessmentName(assessmentName));
            tutorial = new TutorialBuilder().withTutorialName(tutName).build();
        }

        @Override
        public boolean hasAssessmentWithName(AssessmentName assessmentName) {
            requireNonNull(assessmentName);
            return assessment.hasName(assessmentName);
        }

        @Override
        public boolean hasStudentWithName(Name studentName) {
            requireNonNull(studentName);
            return student.getName().equals(studentName);
        }

        @Override
        public Assessment getAssessmentWithName(AssessmentName assessmentName) {
            requireNonNull(assessmentName);
            if (hasAssessmentWithName(assessmentName)) {
                return assessment;
            } else {
                throw new AssessmentNotFoundException();
            }
        }

        @Override
        public TutorialName getTutorialNameOfStudent(Name studentName) {
            requireNonNull(studentName);
            if (hasStudentWithName(studentName)) {
                return student.getTutorialName();
            } else {
                throw new StudentNotFoundException();
            }
        }

        @Override
        public boolean hasStudentResult(Name studentName, AssessmentName assessmentName) {
            requireAllNonNull(studentName, assessmentName);
            if (!hasStudentWithName(studentName)) {
                throw new StudentNotFoundException();
            }
            if (!hasAssessmentWithName(assessmentName)) {
                throw new AssessmentNotFoundException();
            }
            return results.hasStudentResultByStudentId(student.getStudentId());
        }

        @Override
        public void addStudentResult(Name studentName, AssessmentName assessmentName, Score score) {
            if (!hasStudentWithName(studentName)) {
                throw new StudentNotFoundException();
            }
            if (!hasAssessmentWithName(assessmentName)) {
                throw new AssessmentNotFoundException();
            }
            StudentResult studentResult = new StudentResult(studentName, student.getStudentId(), score);
            if (results.contains(studentResult)) {
                throw new DuplicateStudentResultException();
            }
            results.add(studentResult);
        }

        @Override
        public void setStudentResult(Name studentName, AssessmentName assessmentName, Score score) {
            if (!hasStudentWithName(studentName)) {
                throw new StudentNotFoundException();
            }
            if (!hasAssessmentWithName(assessmentName)) {
                throw new AssessmentNotFoundException();
            }
            if (!results.hasStudentResultByStudentId(student.getStudentId())) {
                throw new StudentResultNotFoundException();
            }
            results.set(studentName, student.getStudentId(), score);
        }

        @Override
        public void updateDisplayAssessmentResults(TutorialName tutName, AssessmentName assessmentName) {
            requireAllNonNull(tutName, assessmentName);
        }
    }
}
