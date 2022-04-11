package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.EVE;
import static seedu.address.testutil.TypicalStudents.FIONA;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
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
import seedu.address.testutil.TutorialBuilder;

public class UnmarkAttendanceCommandTest {

    @Test
    public void constructor_nullTutorialName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new UnmarkAttendanceCommand(
                null, FIONA.getStudentId(),
                5, false));
    }

    @Test
    public void execute_unmarkIndividualAttendanceAcceptedByModel_markSuccessful() throws Exception {
        ModelStubAcceptingAttendanceUnmarked modelStub = new ModelStubAcceptingAttendanceUnmarked();
        Tutorial validTutorial = new TutorialBuilder().withTutorialName("T01").withVenue("LT13")
                .withDay("Monday").withTime("13:00").withWeeks(6).build();
        validTutorial.setStudentsList(
                new FilteredList<>(FXCollections.observableArrayList(modelStub.allStudents), null));
        modelStub.tutorialsAdded.add(validTutorial);

        // Manually marks attendance for FIONA's first week
        validTutorial.generateAttendance();
        validTutorial.getAttendanceList()
                .getAttendancesByStudentName(FIONA.getName()).get(0).getAttendanceList().set(0, 1);

        assertTrue(arrayListComparison(validTutorial.getAttendanceList()
                .getAttendancesByStudentName(
                        FIONA.getName()).get(0).getAttendanceList(), new int[] {1, 0, 0, 0, 0, 0}));

        CommandResult commandResult = new UnmarkAttendanceCommand(validTutorial.getTutorialName(),
                FIONA.getStudentId(), 1, false).execute(modelStub);

        // Unmarks attendance for FIONA's first week
        assertEquals(String.format(
                UnmarkAttendanceCommand.MESSAGE_SUCCESS, FIONA.getStudentId(), 1),
                commandResult.getFeedbackToUser());
        assertTrue(arrayListComparison(validTutorial.getAttendanceList()
                .getAttendancesByStudentName(
                        FIONA.getName()).get(0).getAttendanceList(), new int[] {0, 0, 0, 0, 0, 0}));
    }

    @Test
    public void execute_unmarkMultipleAttendanceAcceptedByModel_markSuccessful() throws Exception {
        ModelStubAcceptingAttendanceUnmarked modelStub = new ModelStubAcceptingAttendanceUnmarked();
        Tutorial validTutorial = new TutorialBuilder().withTutorialName("T01").withVenue("LT13")
                .withDay("Monday").withTime("13:00").withWeeks(6).build();
        validTutorial.setStudentsList(
                new FilteredList<>(FXCollections.observableArrayList(modelStub.allStudents), null));
        modelStub.tutorialsAdded.add(validTutorial);

        // Manually marks attendance for entire class first week
        validTutorial.generateAttendance();
        validTutorial.getAttendanceList()
                .getAttendancesByStudentName(FIONA.getName()).get(0).getAttendanceList().set(1, 1);
        validTutorial.getAttendanceList()
                .getAttendancesByStudentName(EVE.getName()).get(0).getAttendanceList().set(1, 1);

        assertTrue(arrayListComparison(validTutorial.getAttendanceList()
                .getAttendancesByStudentName(
                        FIONA.getName()).get(0).getAttendanceList(), new int[] {0, 1, 0, 0, 0, 0}));
        assertTrue(arrayListComparison(validTutorial
                .getAttendanceList().getAttendancesByStudentName(
                        EVE.getName()).get(0).getAttendanceList(), new int[] {0, 1, 0, 0, 0, 0}));

        CommandResult commandResult = new UnmarkAttendanceCommand(validTutorial.getTutorialName(),
                null, 2, true).execute(modelStub);

        // Unmarks attendance for entire class first week
        assertEquals(String.format(
                UnmarkAttendanceCommand.MESSAGE_MULTIPLE_SUCCESS, 2), commandResult.getFeedbackToUser());
        assertTrue(arrayListComparison(validTutorial.getAttendanceList()
                .getAttendancesByStudentName(
                        FIONA.getName()).get(0).getAttendanceList(), new int[] {0, 0, 0, 0, 0, 0}));
        assertTrue(arrayListComparison(validTutorial.getAttendanceList()
                .getAttendancesByStudentName(
                        EVE.getName()).get(0).getAttendanceList(), new int[] {0, 0, 0, 0, 0, 0}));

    }

    @Test
    public void equals() {
        Tutorial tutorialA = new TutorialBuilder().withTutorialName("A").build();
        Tutorial tutorialB = new TutorialBuilder().withTutorialName("B").build();
        NusNetId studentIdA = new NusNetId("e0123456");
        NusNetId studentIdB = new NusNetId("e6543210");
        UnmarkAttendanceCommand unmarkAttendanceCommand1 = new UnmarkAttendanceCommand(
                tutorialA.getTutorialName(), studentIdA, 1, false);
        UnmarkAttendanceCommand unmarkAttendanceCommand2 = new UnmarkAttendanceCommand(
                tutorialA.getTutorialName(), studentIdA, 1, false);
        UnmarkAttendanceCommand unmarkAttendanceCommand3 = new UnmarkAttendanceCommand(
                tutorialB.getTutorialName(), studentIdA, 1, false);
        UnmarkAttendanceCommand unmarkAttendanceCommand4 = new UnmarkAttendanceCommand(
                tutorialA.getTutorialName(), studentIdB, 1, false);
        UnmarkAttendanceCommand unmarkAttendanceCommand5 = new UnmarkAttendanceCommand(
                tutorialA.getTutorialName(), studentIdA, 2, false);
        UnmarkAttendanceCommand unmarkAttendanceCommand6 = new UnmarkAttendanceCommand(
                tutorialA.getTutorialName(), studentIdA, 1, true);
        UnmarkAttendanceCommand unmarkAttendanceCommand7 = new UnmarkAttendanceCommand(
                tutorialA.getTutorialName(), null, 1, false);
        UnmarkAttendanceCommand unmarkAttendanceCommand8 = new UnmarkAttendanceCommand(
                tutorialA.getTutorialName(), null, 1, true);

        // same object -> returns true
        assertTrue(unmarkAttendanceCommand1.equals(unmarkAttendanceCommand1));

        // same values -> returns true
        assertTrue(unmarkAttendanceCommand1.equals(unmarkAttendanceCommand2));

        // different types -> returns false
        assertFalse(unmarkAttendanceCommand1.equals(1));

        // different tutorial name -> returns false
        assertFalse(unmarkAttendanceCommand1.equals(unmarkAttendanceCommand3));

        // different student id -> returns false
        assertFalse(unmarkAttendanceCommand1.equals(unmarkAttendanceCommand4));

        // different week -> returns false
        assertFalse(unmarkAttendanceCommand1.equals(unmarkAttendanceCommand5));

        // different multiple attendance argument -> returns false
        assertFalse(unmarkAttendanceCommand1.equals(unmarkAttendanceCommand6));
        assertFalse(unmarkAttendanceCommand7.equals(unmarkAttendanceCommand8));
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
     * A Model stub that contains a single tutorial class.
     */
    private class ModelStubWithAttendance extends ModelStub {
        private final Tutorial tutorial;

        ModelStubWithAttendance(Tutorial tutorial) {
            requireNonNull(tutorial);
            this.tutorial = tutorial;
        }

        @Override
        public boolean hasTutorialWithName(TutorialName tutorialName) {
            requireNonNull(tutorial);
            return this.tutorial.isSameTutorialName(tutorialName);
        }
    }

    /**
     * A model stub that always accept the attendance being unmarked.
     */
    private class ModelStubAcceptingAttendanceUnmarked extends ModelStub {
        public final ArrayList<Tutorial> tutorialsAdded = new ArrayList<>();
        final ArrayList<Person> allStudents = new ArrayList<Person>() {
            {
                add(FIONA);
                add(EVE);
            }
        };

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }

        @Override
        public boolean hasTutorialWithName(TutorialName tutorialName) {
            requireNonNull(tutorialName);
            return tutorialsAdded.stream().anyMatch(x -> x.isSameTutorialName(tutorialName));
        }

        @Override
        public Tutorial getTutorialWithName(TutorialName tutorialName) {
            requireNonNull(tutorialName);
            for (int i = 0; i < tutorialsAdded.size(); i++) {
                if (tutorialsAdded.get(i).getTutorialName().equals(tutorialName)) {
                    return tutorialsAdded.get(i);
                }
            }
            return null;
        }

        @Override
        public void updateFilteredAttendanceList(Tutorial tutorial, Name studentName) {
            return;
        }

        @Override
        public FilteredList<Person> getAllStudentsList() {
            ObservableList<Person> studentObservableList = FXCollections.observableArrayList(allStudents);
            return new FilteredList<>(studentObservableList);
        }

        @Override
        public void unmarkAttendanceForStudent(Tutorial tutorial, NusNetId studentId, int week) {
            requireAllNonNull(tutorial, studentId, week);
            tutorial.unmarkStudentAttendance(studentId, week);
        }

        @Override
        public void unmarkAttendanceForClass(Tutorial tutorial, int week) {
            requireAllNonNull(tutorial, week);
            tutorial.unmarkAllAttendance(week);
        }
    }

    private boolean arrayListComparison(ArrayList<Integer> arr, int[] arr2) {
        boolean isSame = true;
        for (int i = 0; i < arr.size(); i++) {
            if (!(arr.get(i) == arr2[i])) {
                isSame = false;
                break;
            }
        }
        return isSame;
    }
}
