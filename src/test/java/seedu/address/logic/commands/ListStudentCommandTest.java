package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalTutorials.T01;
import static seedu.address.testutil.TypicalTutorials.T02;
import static seedu.address.testutil.TypicalTutorials.getTypicalAddressBook;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Displayable;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
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

public class ListStudentCommandTest {

    private static final Index INDEX_ONE = Index.fromOneBased(1);
    private static final Index INDEX_TWO = Index.fromOneBased(2);
    private static final TutorialName VALID_TUTORIAL_NAME_1 = T01.getTutorialName();
    private static final TutorialName VALID_TUTORIAL_NAME_2 = T02.getTutorialName();

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        assertThrows(NullPointerException.class, (
            ) -> new ListStudentCommand(INDEX_ONE, null).execute(null));
    }

    @Test
    public void equals() {
        ListStudentCommand firstCommand = new ListStudentCommand(INDEX_ONE, null);
        ListStudentCommand secondCommand = new ListStudentCommand(INDEX_TWO, null);
        ListStudentCommand thirdCommand = new ListStudentCommand(null, VALID_TUTORIAL_NAME_1);
        ListStudentCommand fourthCommand = new ListStudentCommand(null, VALID_TUTORIAL_NAME_2);
        ListStudentCommand allStudentCommand = new ListStudentCommand(null, null);

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));
        assertTrue(thirdCommand.equals(thirdCommand));
        assertTrue(allStudentCommand.equals(allStudentCommand));

        // same values -> returns true
        ListStudentCommand firstCommandCopy = new ListStudentCommand(INDEX_ONE, null);
        assertTrue(firstCommand.equals(firstCommandCopy));
        ListStudentCommand thirdCommandCopy = new ListStudentCommand(null, VALID_TUTORIAL_NAME_1);
        assertTrue(thirdCommand.equals(thirdCommandCopy));
        ListStudentCommand allStudentCommandCopy = new ListStudentCommand(null, null);
        assertTrue(allStudentCommand.equals(allStudentCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different values -> returns false
        assertFalse(firstCommand.equals(secondCommand));
        assertFalse(thirdCommand.equals(fourthCommand));
        assertFalse(firstCommand.equals(thirdCommand));
    }

    @Test
    public void execute_noIndexTutNameInput_success() {
        String expectedMessage = ListStudentCommand.MESSAGE_SUCCESS;
        ListStudentCommand command = new ListStudentCommand(null, null);
        expectedModel.updateFilteredStudentList(expectedModel.PREDICATE_SHOW_ALL_STUDENTS);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_indexInput_success() throws Exception {
        String expectedMessage = String.format(ListStudentCommand.MESSAGE_SUCCESS_CLASS, T01);
        ListStudentCommand command = new ListStudentCommand(INDEX_ONE, null);
        CommandResult result = command.execute(new ModelStubAcceptingIndex());
        assertEquals(expectedMessage, result.getFeedbackToUser());
    }

    @Test
    public void execute_indexInput_invalidIndexFailure() {
        String expectedMessage = Messages.MESSAGE_INVALID_TUTORIAL_DISPLAYED_INDEX;
        ListStudentCommand command = new ListStudentCommand(INDEX_TWO, null);
        assertThrows(CommandException.class, expectedMessage, () -> command.execute(new ModelStubAcceptingIndex()));
    }

    @Test
    public void execute_indexInput_displayListMismatchFailure() {
        String expectedMessage = Messages.MESSAGE_INDEX_LIST_MISMATCH + ListStudentCommand.MESSAGE_INDEX_USAGE;
        ListStudentCommand command = new ListStudentCommand(INDEX_ONE, null);
        ModelStubAcceptingIndex model = new ModelStubAcceptingIndex();
        model.updateWrongLastShownList();
        assertThrows(CommandException.class, expectedMessage, () -> command.execute(model));
    }

    @Test
    public void execute_tutNameInput_success() throws Exception {
        String expectedMessage = String.format(ListStudentCommand.MESSAGE_SUCCESS_CLASS, T01);
        ListStudentCommand command = new ListStudentCommand(null, T01.getTutorialName());
        CommandResult result = command.execute(new ModelStubAcceptingTutorialName());
        assertEquals(expectedMessage, result.getFeedbackToUser());
    }

    @Test
    public void execute_indexInput_invalidTutorialFailure() {
        String expectedMessage = Messages.MESSAGE_TUTORIAL_NOT_FOUND;
        ListStudentCommand command = new ListStudentCommand(null, T02.getTutorialName());
        assertThrows(CommandException.class, expectedMessage, (
            ) -> command.execute(new ModelStubAcceptingTutorialName()));
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
    private class ModelStubAcceptingIndex extends ModelStub {
        private ObservableList<Displayable> lastShownList = FXCollections.observableArrayList();

        ModelStubAcceptingIndex() {
            lastShownList.add(T01);
        }

        @Override
        public ObservableList<Displayable> getLastShownList() {
            return lastShownList;
        }

        @Override
        public void updateFilteredStudentList(Predicate<Person> predicate) {
            return;
        }

        public void updateWrongLastShownList() {
            ArrayList<Person> wrongList = new ArrayList<>();
            wrongList.add(BOB);
            lastShownList.setAll(wrongList);
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
        public void updateFilteredStudentList(Predicate<Person> predicate) {
            return;
        }
    }

}
