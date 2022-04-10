package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Stream;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.ExportCsvOpenException;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.Email;
import seedu.address.model.applicant.Phone;
import seedu.address.model.interview.Interview;
import seedu.address.model.position.Position;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);
    private static final String EXPORT_CSV_FOLDER = "export_csv\\";
    private static final String APPLICANT_CSV_FILE = EXPORT_CSV_FOLDER + "applicant.csv";
    private static final String INTERVIEW_CSV_FILE = EXPORT_CSV_FOLDER + "interview.csv";
    private static final String POSITION_CSV_FILE = EXPORT_CSV_FOLDER + "position.csv";
    private static final String APPLICANT_CSV_HEADER = "Name,Phone,Email,Age,Address,Gender,Hire status,Tags";
    private static final String INTERVIEW_CSV_HEADER = "Date,Interview Status,Name,Phone,Email,Age,Address,"
            + "Gender,Hire status,Tags,Position,Description,Number of openings,Number of offers,Requirements";
    private static final String POSITION_CSV_HEADER = "Position,Description,Number of openings,Number of offers"
            + "Requirements";
    private static final String MESSAGE_CLOSE_CSV = "Please close the opened CSV before export";

    private final HireLah hireLah;
    private final UserPrefs userPrefs;
    private final FilteredList<Applicant> filteredApplicants;
    private final FilteredList<Interview> filteredInterviews;
    private final FilteredList<Position> filteredPositions;

    /**
     * Initializes a ModelManager with the given hireLah and userPrefs.
     */
    public ModelManager(ReadOnlyHireLah addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);
        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.hireLah = new HireLah(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredApplicants = new FilteredList<>(this.hireLah.getApplicantList());
        filteredInterviews = new FilteredList<>(this.hireLah.getInterviewList());
        filteredPositions = new FilteredList<>(this.hireLah.getPositionList());
    }

    public ModelManager() {
        this(new HireLah(), new UserPrefs());
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
    public Path getHireLahFilePath() {
        return userPrefs.getHireLahFilePath();
    }

    @Override
    public void setHireLahFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setHireLahFilePath(addressBookFilePath);
    }

    //=========== HireLah ================================================================================

    @Override
    public void setHireLah(ReadOnlyHireLah hireLah) {
        this.hireLah.resetData(hireLah);
    }

    @Override
    public ReadOnlyHireLah getHireLah() {
        return hireLah;
    }

    @Override
    public boolean hasApplicant(Applicant applicant) {
        requireNonNull(applicant);
        return hireLah.hasApplicant(applicant);
    }

    @Override
    public Applicant getApplicantWithEmail(Email email) {
        requireNonNull(email);
        return hireLah.getApplicantWithEmail(email);
    }

    @Override
    public Applicant getApplicantWithPhone(Phone phone) {
        requireNonNull(phone);
        return hireLah.getApplicantWithPhone(phone);
    }

    @Override
    public void deleteApplicant(Applicant target) {
        hireLah.removeApplicant(target);
    }

    @Override
    public void addApplicant(Applicant applicant) {
        hireLah.addApplicant(applicant);
        updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);
    }

    @Override
    public void setApplicant(Applicant target, Applicant editedApplicant) {
        requireAllNonNull(target, editedApplicant);

        hireLah.setApplicant(target, editedApplicant);
    }

    @Override
    public boolean hasInterview(Interview interview) {
        requireNonNull(interview);
        return hireLah.hasInterview(interview);
    }

    @Override
    public boolean hasConflictingInterview(Interview interview) {
        requireAllNonNull(interview);
        return hireLah.hasConflictingInterview(interview);
    }


    @Override
    public void deleteInterview(Interview target) {
        hireLah.removeInterview(target);
    }

    @Override
    public void addInterview(Interview interview) {
        hireLah.addInterview(interview);
        updateFilteredInterviewList(PREDICATE_SHOW_ALL_INTERVIEWS);
    }


    @Override
    public void setInterview(Interview target, Interview editedInterview) {
        requireAllNonNull(target, editedInterview);

        hireLah.setInterview(target, editedInterview);
    }

    @Override
    public boolean hasPosition(Position position) {
        requireNonNull(position);
        return hireLah.hasPosition(position);
    }

    @Override
    public void addPosition(Position position) {
        hireLah.addPosition(position);
        updateFilteredPositionList(PREDICATE_SHOW_ALL_POSITIONS);
    }

    @Override
    public void deletePosition(Position target) {
        hireLah.removePosition(target);
    }

    @Override
    public void setPosition(Position target, Position editedPosition) {
        requireAllNonNull(target, editedPosition);

        hireLah.setPosition(target, editedPosition);
    }

    @Override
    public void updateApplicant(Applicant applicantToBeUpdated, Applicant newApplicant) {
        requireAllNonNull(applicantToBeUpdated, newApplicant);

        hireLah.updateApplicant(applicantToBeUpdated, newApplicant);
    }

    @Override
    public void updatePosition(Position positionToBeUpdated, Position newPosition) {
        requireAllNonNull(positionToBeUpdated, newPosition);
        hireLah.updatePosition(positionToBeUpdated, newPosition);
    }

    //=========== Filtered Applicant List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Applicant} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Applicant> getFilteredApplicantList() {
        return filteredApplicants;
    }

    @Override
    public void updateFilteredApplicantList(Predicate<Applicant> predicate) {
        requireNonNull(predicate);
        filteredApplicants.setPredicate(predicate);
    }
    // Need to test
    @Override
    public void updateSortApplicantList(Comparator<Applicant> comparator) {
        requireNonNull(comparator);
        hireLah.sortApplicant(comparator);
        filteredApplicants.setPredicate(PREDICATE_SHOW_ALL_APPLICANTS);
    }

    @Override
    public void updateFilterAndSortApplicantList(Predicate<Applicant> predicate, Comparator<Applicant> comparator) {
        requireAllNonNull(predicate, comparator);
        hireLah.sortApplicant(comparator);
        filteredApplicants.setPredicate(predicate);
    }

    @Override
    public void exportCsvApplicant() throws FileNotFoundException, ExportCsvOpenException {
        exportCsv(APPLICANT_CSV_FILE, APPLICANT_CSV_HEADER, filteredApplicants.stream()
                .map(Applicant::convertToCsv));
    }

    //=========== Filtered Interview List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Interview} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Interview> getFilteredInterviewList() {
        return filteredInterviews;
    }

    @Override
    public void updateFilteredInterviewList(Predicate<Interview> predicate) {
        requireNonNull(predicate);
        filteredInterviews.setPredicate(predicate);
    }

    @Override
    public void updateSortInterviewList(Comparator<Interview> comparator) {
        requireNonNull(comparator);
        hireLah.sortInterview(comparator);
        filteredInterviews.setPredicate(PREDICATE_SHOW_ALL_INTERVIEWS);
    }

    @Override
    public void updateFilterAndSortInterviewList(Predicate<Interview> predicate, Comparator<Interview> comparator) {
        requireAllNonNull(predicate, comparator);
        hireLah.sortInterview(comparator);
        filteredInterviews.setPredicate(predicate);
    }

    @Override
    public ArrayList<Interview> getApplicantsInterviews(Applicant applicant) {
        return hireLah.getApplicantsInterviews(applicant);
    }

    @Override
    public ArrayList<Interview> getPositionsInterviews(Position position) {
        return hireLah.getPositionsInterview(position);
    }

    @Override
    public void exportCsvInterview() throws FileNotFoundException, ExportCsvOpenException {
        exportCsv(INTERVIEW_CSV_FILE, INTERVIEW_CSV_HEADER, filteredInterviews.stream()
                .map(Interview::convertToCsv));
    }

    @Override
    public boolean isSameApplicantPosition(Applicant applicant, Position position) {
        return hireLah.isSameApplicantPosition(applicant, position);
    }

    //=========== Filtered Position List Accessors =============================================================
    @Override
    public ObservableList<Position> getFilteredPositionList() {
        return filteredPositions;
    }

    @Override
    public void updateFilteredPositionList(Predicate<Position> predicate) {
        requireNonNull(predicate);
        filteredPositions.setPredicate(predicate);
    }

    @Override
    public void updateSortPositionList(Comparator<Position> comparator) {
        requireNonNull(comparator);
        hireLah.sortPosition(comparator);
        filteredPositions.setPredicate(PREDICATE_SHOW_ALL_POSITIONS);
    }

    @Override
    public void updateFilterAndSortPositionList(Predicate<Position> predicate, Comparator<Position> comparator) {
        requireAllNonNull(predicate, comparator);
        hireLah.sortPosition(comparator);
        filteredPositions.setPredicate(predicate);
    }

    @Override
    public void exportCsvPosition() throws FileNotFoundException, ExportCsvOpenException {
        exportCsv(POSITION_CSV_FILE, POSITION_CSV_HEADER, filteredPositions.stream()
                .map(Position::convertToCsv));
    }

    //=========== Utility methods =============================================================
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
        return hireLah.equals(other.hireLah)
                && userPrefs.equals(other.userPrefs)
                && filteredApplicants.equals(other.filteredApplicants)
                && filteredPositions.equals(other.filteredPositions)
                && filteredInterviews.equals(other.filteredInterviews);
    }

    private void exportCsv(String csvFile, String csvHeader, Stream<String> stringStream)
            throws ExportCsvOpenException {
        File csvOutputFile = new File(csvFile);
        File directory = new File(EXPORT_CSV_FOLDER);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            pw.println(csvHeader);
            stringStream
                    .forEach(pw::println);
            assert (csvOutputFile.exists());
        } catch (FileNotFoundException exception) {
            throw new ExportCsvOpenException(MESSAGE_CLOSE_CSV);
        }
    }
}
