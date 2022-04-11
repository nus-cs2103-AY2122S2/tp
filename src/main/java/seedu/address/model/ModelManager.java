package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.module.Module;

/**
 * Represents the in-memory model of the LinkyTime data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final LinkyTime linkyTime;
    private final UserPrefs userPrefs;
    private final FilteredList<Meeting> filteredMeetings;
    private final FilteredList<Module> filteredModules;
    private Predicate<Meeting> invariantPredicate = PREDICATE_SHOW_ALL_UNCOMPLETED_MEETINGS;

    /**
     * Initializes a ModelManager with the given linkyTime and userPrefs.
     */
    public ModelManager(ReadOnlyLinkyTime linkyTime, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(linkyTime, userPrefs);

        logger.fine("Initializing with LinkyTime: " + linkyTime + " and user prefs " + userPrefs);
        this.linkyTime = new LinkyTime(linkyTime);
        this.userPrefs = new UserPrefs(userPrefs);
        this.linkyTime.sortMeetings();
        filteredMeetings = new FilteredList<>(this.linkyTime.getMeetingList());
        this.linkyTime.sortModules();
        filteredModules = new FilteredList<>(this.linkyTime.getModuleList());
        refreshFilteredMeetingList();
    }

    public ModelManager() {
        this(new LinkyTime(), new UserPrefs());
    }

    // =========== UserPrefs ===============================================================================

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
    public Path getLinkyTimeFilePath() {
        return userPrefs.getLinkyTimeFilePath();
    }

    @Override
    public void setLinkyTimeFilePath(Path linkyTimeFilePath) {
        requireNonNull(linkyTimeFilePath);
        userPrefs.setLinkyTimeFilePath(linkyTimeFilePath);
    }

    // =========== LinkyTime ===============================================================================

    @Override
    public void setLinkyTime(ReadOnlyLinkyTime linkyTime) {
        this.linkyTime.resetData(linkyTime);
    }

    @Override
    public ReadOnlyLinkyTime getLinkyTime() {
        return linkyTime;
    }

    // =========== Meeting ============================================================================

    @Override
    public boolean hasMeeting(Meeting meeting) {
        requireNonNull(meeting);
        return linkyTime.hasMeeting(meeting);
    }

    @Override
    public void deleteMeeting(Meeting target) {
        requireNonNull(target);
        linkyTime.removeMeeting(target);
        refreshFilteredMeetingList();
    }

    @Override
    public void addMeeting(Meeting meeting) {
        requireNonNull(meeting);
        linkyTime.addMeeting(meeting);
        refreshFilteredMeetingList();
    }

    @Override
    public void setMeeting(Meeting target, Meeting editedMeeting) {
        requireAllNonNull(target, editedMeeting);
        linkyTime.setMeeting(target, editedMeeting);
        refreshFilteredMeetingList();
    }

    // =========== Meeting List Accessors ====================================================

    @Override
    public ObservableList<Meeting> getFilteredMeetingList() {
        return filteredMeetings;
    }

    @Override
    public void updateFilteredMeetingList(Predicate<Meeting> predicate) {
        requireNonNull(predicate);
        // Forces the GUI to perform a complete re-render to reflect updated recurrent meeting date and times.
        // This is a temporary workaround until a coherent solution comes about.
        linkyTime.sortMeetings();

        filteredMeetings.setPredicate(m -> false);
        filteredMeetings.setPredicate(invariantPredicate.and(predicate));
    }

    @Override
    public void showCompletedMeetings(boolean showCompleted) {
        invariantPredicate = showCompleted
                ? PREDICATE_SHOW_ALL_COMPLETED_MEETINGS : PREDICATE_SHOW_ALL_UNCOMPLETED_MEETINGS;
        refreshFilteredMeetingList();
    }

    private void refreshFilteredMeetingList() {
        updateFilteredMeetingList(m -> true);
    }

    @Override
    public ObservableList<Meeting> getMeetingList() {
        return linkyTime.getMeetingList();
    }

    // =========== Module ==================================================================================

    @Override
    public boolean hasModule(Module module) {
        requireNonNull(module);
        return linkyTime.hasModule(module);
    }

    @Override
    public void addModule(Module module) {
        linkyTime.addModule(module);
        linkyTime.sortModules();
        updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
    }

    @Override
    public void setModule(Module target, Module editedModule) {
        requireAllNonNull(target, editedModule);
        linkyTime.setModule(target, editedModule);
        linkyTime.sortModules();
    }

    @Override
    public void deleteModule(Module target) {
        linkyTime.removeModule(target);
    }

    // =========== Module List Accessors ==========================================================

    @Override
    public ObservableList<Module> getFilteredModuleList() {
        return filteredModules;
    }

    @Override
    public void updateFilteredModuleList(Predicate<Module> predicate) {
        requireNonNull(predicate);
        filteredModules.setPredicate(predicate);
    }

    @Override
    public ObservableList<Module> getModuleList() {
        return linkyTime.getModuleList();
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
        final ModelManager other = (ModelManager) obj;
        return userPrefs.equals(other.userPrefs)
                && filteredMeetings.equals(other.filteredMeetings)
                && filteredModules.equals(other.filteredModules);
    }

}
