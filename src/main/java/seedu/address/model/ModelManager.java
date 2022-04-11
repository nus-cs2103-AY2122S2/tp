package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.assessment.AssessmentName;
import seedu.address.model.assessment.AssessmentResults;
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

/**
 * Represents the in-memory model of the address book data.
 * TODO: add methods for AddressBook to support command logic
 * The AddressBook contains the tutorial list and assessment list.
 * e.g. TutorialManager to provide higher-level methods.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Tutorial> filteredTutorials;
    private final FilteredList<Assessment> filteredAssessments;
    private final FilteredList<Person> filteredPersonsByMultiplePredicate;
    private final ObservableList<Displayable> lastShownList;

    private ObservableList<Attendance> displayAttendanceList;
    private ObservableList<StudentResult> displayAssessmentResults;
    private ObservableList<Comment> displayComment;

    /**
     * A list containing all Students in the address book.
     * DO NOT MODIFY (as it is the list that {@link seedu.address.model.person.UniqueStudentsInTutorialList}
     * listens to).
     */
    private final FilteredList<Person> allStudents;
    /**
     * Used for the GUI display.
     * Can modify if needed.
     */
    private final FilteredList<Person> filteredStudents;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredTutorials = new FilteredList<>(this.addressBook.getTutorialList());
        filteredAssessments = new FilteredList<>(this.addressBook.getAssessmentList());
        filteredPersonsByMultiplePredicate = new FilteredList<>(this.addressBook.getFilteredPersonsList());
        lastShownList = FXCollections.observableArrayList();
        lastShownList.setAll(this.addressBook.getLastShownList());
        displayComment = FXCollections.observableArrayList();

        allStudents = new FilteredList<>(this.addressBook.getPersonList(), PREDICATE_SHOW_ALL_STUDENTS);
        filteredStudents = new FilteredList<>(allStudents);

    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public boolean hasPersonWithName(Name name) {
        requireNonNull(name);
        return addressBook.hasPersonWithName(name);
    }

    @Override
    public boolean hasPersonWithEmail(Email email) {
        requireNonNull(email);
        return addressBook.hasPersonWithEmail(email);
    }

    @Override
    public boolean hasPersonWithPhone(Phone phone) {
        requireNonNull(phone);
        return addressBook.hasPersonWithPhone(phone);
    }

    @Override
    public Person getPersonWithName(Name name) {
        requireNonNull(name);
        return addressBook.getPersonWithName(name);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    //=========== Assessments =============================================================
    /**
     * Returns an unmodifiable view of the list of {@code Assessment} backed by the internal list of
     * {@code AddressBook}
     */
    @Override
    public ObservableList<Assessment> getAssessmentList() {
        return addressBook.getAssessmentList();
    }

    @Override
    public ObservableList<Assessment> getFilteredAssessmentList() {
        return filteredAssessments;
    }

    @Override
    public void updateFilteredAssessmentList(Predicate<Assessment> predicate) {
        requireNonNull(predicate);
        filteredAssessments.setPredicate(predicate);
        lastShownList.setAll(filteredAssessments);
        addressBook.setLastShownList(lastShownList);
    }

    @Override
    public boolean hasAssessment(Assessment assessment) {
        requireNonNull(assessment);
        return addressBook.hasAssessment(assessment);
    }

    @Override
    public void addAssessment(Assessment toAdd) {
        requireNonNull(toAdd);
        addressBook.addAssessment(toAdd);
        updateFilteredAssessmentList(PREDICATE_SHOW_ALL_ASSESSMENTS);
    }

    @Override
    public boolean hasAssessmentWithName(AssessmentName name) {
        requireNonNull(name);
        return addressBook.hasAssessmentWithName(name);
    }

    @Override
    public Assessment getAssessmentWithName(AssessmentName name) {
        requireNonNull(name);
        return addressBook.getAssessmentWithName(name);
    }

    @Override
    public Assessment removeAssessmentWithName(AssessmentName name) {
        requireNonNull(name);
        Assessment result = addressBook.removeAssessmentWithName(name);
        updateFilteredAssessmentList(PREDICATE_SHOW_ALL_ASSESSMENTS);
        return result;
    }

    //=========== Assessment Results =============================================================
    @Override
    public ObservableList<StudentResult> getDisplayAssessmentResults() {
        requireNonNull(displayAssessmentResults);
        return displayAssessmentResults;
    }

    @Override
    public boolean hasStudentResult(Name studentName, AssessmentName assessmentName) {
        requireAllNonNull(studentName, assessmentName);
        return addressBook.hasStudentResult(studentName, assessmentName);
    }

    @Override
    public void addStudentResult(Name studentName, AssessmentName assessmentName, Score score) {
        requireAllNonNull(studentName, assessmentName, score);
        addressBook.addStudentResult(studentName, assessmentName, score);
    }

    @Override
    public void removeStudentResults(NusNetId studentId, TutorialName tutorialName) {
        requireAllNonNull(studentId, tutorialName);
        addressBook.removeStudentResults(studentId, tutorialName);
    }

    @Override
    public void setStudentResult(Name studentName, AssessmentName assessmentName, Score sc) {
        requireAllNonNull(studentName, assessmentName, sc);
        addressBook.setStudentResult(studentName, assessmentName, sc);
    }

    @Override
    public void markAttendanceForClass(Tutorial tutorial, int week) {
        requireAllNonNull(tutorial, week);
        updateFilteredAttendanceList(tutorial, null);
        addressBook.markAttendanceForClass(tutorial, week);
    }

    @Override
    public void markAttendanceForStudent(Tutorial tutorial, NusNetId studentId, int week) {
        requireAllNonNull(tutorial, studentId, week);
        addressBook.markAttendanceForStudent(tutorial, studentId, week);
    }

    @Override
    public void unmarkAttendanceForClass(Tutorial tutorial, int week) {
        requireAllNonNull(tutorial, week);
        updateFilteredAttendanceList(tutorial, null);
        addressBook.unmarkAttendanceForClass(tutorial, week);
    }

    @Override
    public void unmarkAttendanceForStudent(Tutorial tutorial, NusNetId studentId, int week) {
        requireAllNonNull(tutorial, studentId, week);
        addressBook.unmarkAttendanceForStudent(tutorial, studentId, week);
    }

    @Override
    public void updateFilteredAttendanceList(Tutorial tutorial, Name studentName) {
        requireAllNonNull(tutorial);
        ObservableList<Attendance> attendanceList = FXCollections.observableArrayList();
        tutorial.generateAttendance();
        if (studentName != null) {
            attendanceList.setAll(tutorial.getAttendanceList().getAttendancesByStudentName(studentName));
        } else {
            attendanceList.setAll(tutorial.getAttendanceList().getAttendances());
        }
        displayAttendanceList = attendanceList;
        lastShownList.setAll(displayAttendanceList);
        addressBook.setLastShownList(lastShownList);
    }

    @Override
    public ObservableList<Attendance> getFilteredAttendanceList() {
        return displayAttendanceList;
    }

    @Override
    public void updateDisplayAssessmentResults(TutorialName tutName, AssessmentName assessmentName) {
        requireAllNonNull(tutName, assessmentName);
        AssessmentResults assessmentResults = addressBook.getAssessmentResults(tutName, assessmentName);
        displayAssessmentResults = assessmentResults.asUnmodifiableStudentResultsList();
        lastShownList.setAll(displayAssessmentResults);
        addressBook.setLastShownList(lastShownList);
    }

    //=========== Tutorials =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Tutorial} backed by the internal list of
     * {@code AddressBook}
     */
    @Override
    public ObservableList<Tutorial> getFilteredTutorialList() {
        return filteredTutorials;
    }

    @Override
    public boolean hasTutorial(Tutorial tutorial) {
        requireNonNull(tutorial);
        return addressBook.hasTutorial(tutorial);
    }

    @Override
    public boolean hasTutorialWithName(TutorialName tutorialName) {
        requireNonNull(tutorialName);
        return addressBook.hasTutorialWithName(tutorialName);
    }

    @Override
    public Tutorial getTutorialWithName(TutorialName tutorialName) {
        requireNonNull(tutorialName);
        return addressBook.getTutorialWithName(tutorialName);
    }

    @Override
    public void deleteTutorial(Tutorial target) {
        addressBook.removeStudentInTutorial(target);
        addressBook.removeTutorial(target);
    }

    @Override
    public void addTutorial(Tutorial tutorial) {
        requireNonNull(tutorial);
        addressBook.addTutorial(tutorial);
        updateFilteredTutorialList(PREDICATE_SHOW_ALL_TUTORIALS);
    }

    @Override
    public void setTutorial(Tutorial target, Tutorial editedTutorial) {
        requireAllNonNull(target, editedTutorial);
        addressBook.setTutorial(target, editedTutorial);
    }

    @Override
    public void updateFilteredTutorialList(Predicate<Tutorial> predicate) {
        requireNonNull(predicate);
        filteredTutorials.setPredicate(predicate);
        lastShownList.setAll(filteredTutorials);
        addressBook.setLastShownList(lastShownList);
    }

    //=========== Students =============================================================

    @Override
    public boolean hasStudentWithName(Name studentName) {
        requireNonNull(studentName);
        return allStudents.stream().anyMatch(x -> x.getName().equals(studentName));
    }

    @Override
    public boolean hasStudentWithId(NusNetId studentId) {
        requireNonNull(studentId);
        return allStudents.stream().anyMatch(x -> ((Student) x).getStudentId().equals(studentId));
    }

    @Override
    public Student getStudentWithId(NusNetId id) {
        requireNonNull(id);
        return addressBook.getStudentWithId(id);
    }

    @Override
    public boolean tutorialHasStudentWithId(NusNetId id, TutorialName tutorialName) {
        requireAllNonNull(id, tutorialName);
        return addressBook.tutorialHasStudentWithId(id, tutorialName);
    }

    @Override
    public void addStudent(Student student) {
        requireNonNull(student);
        addressBook.addStudent(student);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
    }

    @Override
    public void addComment(Tutorial tutorial, Name name, Comment commentToAdd) {
        requireAllNonNull(tutorial, name, commentToAdd);
        addressBook.addComment(tutorial, name, commentToAdd);
        displayComment.setAll(commentToAdd);
        lastShownList.setAll(displayComment);
        addressBook.setLastShownList(lastShownList);
    }

    @Override
    public void removeComment(Tutorial tutorial, Name studentToRemoveComment) {
        requireAllNonNull(tutorial, studentToRemoveComment);
        addressBook.removeComment(tutorial, studentToRemoveComment);
        Comment commentToView = addressBook.viewComment(tutorial, studentToRemoveComment);
        displayComment.setAll(commentToView);
        lastShownList.setAll(displayComment);
        addressBook.setLastShownList(lastShownList);
    }

    @Override
    public Comment getComment(Tutorial tutorial, Name studentToViewComment) {
        requireAllNonNull(tutorial, studentToViewComment);
        Comment commentToView = addressBook.viewComment(tutorial, studentToViewComment);
        displayComment.setAll(commentToView);
        lastShownList.setAll(displayComment);
        addressBook.setLastShownList(lastShownList);
        return commentToView;
    }

    @Override
    public ObservableList<Comment> getCommentList() {
        return displayComment;
    }

    @Override
    public FilteredList<Person> getAllStudentsList() {
        return allStudents;
    }

    @Override
    public FilteredList<Person> getFilteredStudentList() {
        return filteredStudents;
    }

    @Override
    public void updateFilteredStudentList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredStudents.setPredicate(predicate);
        lastShownList.setAll(filteredStudents);
        addressBook.setLastShownList(lastShownList);
    }

    /**
     * Removes a {@code student} from the address book
     */
    public void removeStudent(Student student) {
        requireNonNull(student);
        addressBook.removeStudent(student);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
    }

    @Override
    public TutorialName getTutorialNameOfStudent(Name studentName) {
        requireNonNull(studentName);
        return addressBook.getTutorialNameOfStudent(studentName);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code AddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
        lastShownList.setAll(filteredPersons);
        addressBook.setLastShownList(lastShownList);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

    //=========== Filtered Person Multiple Predicate List Accessors ==================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code AddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonsMultiPredList() {
        return filteredPersonsByMultiplePredicate;
    }

    @Override
    public void setFilteredPersonsMultiPredList(List<Person> persons) {
        requireNonNull(persons);
        addressBook.setFilteredPersons(persons);
        lastShownList.setAll(filteredPersonsByMultiplePredicate);
        addressBook.setLastShownList(lastShownList);
    }

    @Override
    public ObservableList<Displayable> getLastShownList() {
        return lastShownList;
    }
}

