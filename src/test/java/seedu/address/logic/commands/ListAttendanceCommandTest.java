package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALEX;
import static seedu.address.testutil.TypicalStudents.BERNARD;
import static seedu.address.testutil.TypicalTutorials.T01;
import static seedu.address.testutil.TypicalTutorials.T02;
import static seedu.address.testutil.TypicalTutorials.TG01;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Displayable;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.assessment.AssessmentName;
import seedu.address.model.assessment.Score;
import seedu.address.model.assessment.StudentResult;
import seedu.address.model.attendance.Attendance;
import seedu.address.model.attendance.Comment;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.NusNetId;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Student;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.model.tutorial.TutorialName;

public class ListAttendanceCommandTest {

    private int week = 10;
    private int validWeek = 13;
    private int invalidWeek = 14;
    private int studentRelatedWeek = -1;

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        assertThrows(NullPointerException.class, (
            ) -> new ListAttendanceCommand(T01.getTutorialName(), ALEX.getStudentId(), week).execute(null));
    }

    @Test
    public void equals() {
        ListAttendanceCommand alexCommand =
                new ListAttendanceCommand(null, ALEX.getStudentId(), studentRelatedWeek);
        ListAttendanceCommand bernardCommand =
                new ListAttendanceCommand(null, BERNARD.getStudentId(), studentRelatedWeek);
        ListAttendanceCommand t01Command = new ListAttendanceCommand(T01.getTutorialName(), null, week);
        ListAttendanceCommand t02Command = new ListAttendanceCommand(T02.getTutorialName(), null, week);

        // same object -> returns true
        assertTrue(alexCommand.equals(alexCommand));
        assertTrue(t01Command.equals(t01Command));

        // same values -> returns true
        ListAttendanceCommand alexT01CommandCopy =
                new ListAttendanceCommand(null, ALEX.getStudentId(), studentRelatedWeek);
        assertTrue(alexCommand.equals(alexT01CommandCopy));
        ListAttendanceCommand t01CommandCopy = new ListAttendanceCommand(T01.getTutorialName(), null, week);
        assertTrue(t01Command.equals(t01CommandCopy));

        // different types -> returns false
        assertFalse(alexCommand.equals(1));
        assertFalse(t01Command.equals(1));

        // null -> returns false
        assertFalse(alexCommand.equals(null));
        assertFalse(t01Command.equals(null));

        // different values -> returns false
        assertFalse(alexCommand.equals(bernardCommand));
        assertFalse(alexCommand.equals(t01Command));
        assertFalse(t01Command.equals(t02Command));
    }

    @Test
    public void execute_validStudentInput_success() throws CommandException {
        String expectedMessage = String.format(ListAttendanceCommand.MESSAGE_SUCCESS_STUDENT, ALEX.getName());
        ListAttendanceCommand command =
                new ListAttendanceCommand(null, ALEX.getStudentId(), studentRelatedWeek);
        ModelStubAcceptingStudentId model = new ModelStubAcceptingStudentId();
        CommandResult result = command.execute(model);
        assertEquals(expectedMessage, result.getFeedbackToUser());
    }

    @Test
    public void execute_validTutorialNameWeekInput_success() throws CommandException {
        String expectedMessage = String.format(ListAttendanceCommand.MESSAGE_SUCCESS, T01.getTutorialName(), validWeek);
        ListAttendanceCommand command =
                new ListAttendanceCommand(T01.getTutorialName(), null, validWeek);
        ModelStubAcceptingTutorialName model = new ModelStubAcceptingTutorialName();
        CommandResult result = command.execute(model);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(validWeek, result.getAttendanceWeek());
    }

    @Test
    public void execute_invalidStudentInput_failure() {
        String expectedMessage = GradeCommand.MESSAGE_STUDENT_NOT_FOUND;
        ListAttendanceCommand command =
                new ListAttendanceCommand(null, BERNARD.getStudentId(), studentRelatedWeek);
        ModelStubAcceptingStudentId model = new ModelStubAcceptingStudentId();
        assertThrows(CommandException.class, expectedMessage, () -> command.execute(model));
    }

    @Test
    public void execute_invalidTutorialNameInput_failure() {
        String expectedMessage = Messages.MESSAGE_TUTORIAL_NOT_FOUND;
        ListAttendanceCommand command =
                new ListAttendanceCommand(T02.getTutorialName(), null, validWeek);
        ModelStubAcceptingTutorialName model = new ModelStubAcceptingTutorialName();
        assertThrows(CommandException.class, expectedMessage, () ->command.execute(model));
    }

    @Test
    public void execute_invalidWeekInput_failure() {
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_TUTORIAL_WEEKS, T01.getWeeks());
        ListAttendanceCommand command =
                new ListAttendanceCommand(T01.getTutorialName(), null, invalidWeek);
        ModelStubAcceptingTutorialName model = new ModelStubAcceptingTutorialName();
        assertThrows(CommandException.class, expectedMessage, () -> command.execute(model));
    }

    /**
     * A default model stub that have all of the methods failing.
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
        public void addComment(Tutorial tutorial, Name name, Comment toAdd) {
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
        public boolean hasPersonWithEmail(Email email) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPersonWithPhone(Phone email) {
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
     * A Model stub that contains a last shown list.
     */
    private class ModelStubAcceptingStudentId extends ModelStub {

        @Override
        public boolean hasStudentWithId(NusNetId toAddStudentId) {
            return toAddStudentId.equals(ALEX.getStudentId());
        }

        @Override
        public Student getStudentWithId(NusNetId id) {
            return ALEX;
        }

        @Override
        public Tutorial getTutorialWithName(TutorialName name) {
            return TG01;
        }

        @Override
        public void updateFilteredAttendanceList(Tutorial tutorial, Name studentName) {
            return;
        }

    }

    /**
     * A model stub that accepts tutorial name.
     */
    private class ModelStubAcceptingTutorialName extends ModelStub {
        @Override
        public ReadOnlyAddressBook getAddressBook() {
            AddressBook ab = new AddressBook();
            ab.addTutorial(T01);
            return ab;
        }

        @Override
        public void updateFilteredAttendanceList(Tutorial tutorial, Name studentName) {
            return;
        }

    }

}
