package seedu.contax.model;

import static seedu.contax.commons.util.AppUtil.checkArgument;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.contax.model.appointment.AppointmentSlot;
import seedu.contax.model.chrono.TimeRange;

/**
 * Generates a list of slots from the backing Schedule between the specified time range.
 */
public class AppointmentSlotList {

    private TimeRange filteredRange;
    private int minimumDuration;
    private final Schedule backingSchedule;
    private final ObservableList<AppointmentSlot> resultList;
    private final ObservableList<AppointmentSlot> readOnlyResultList;
    private final InvalidationListener sourceChangeListener;

    /**
     * Constructs an {@code AppointmentSlotList}.
     *
     * @param backingSchedule The backing Schedule that slots should be generated for.
     */
    public AppointmentSlotList(Schedule backingSchedule) {
        this.backingSchedule = backingSchedule;
        this.filteredRange = null;
        this.resultList = FXCollections.observableArrayList();
        this.readOnlyResultList = FXCollections.unmodifiableObservableList(this.resultList);
        this.sourceChangeListener = (unused) -> {
            updateResultList();
        };
    }

    /**
     * Updates the time range within which slots should be generated for.
     *
     * @param newRange The new {@code TimeRange} for which slots should be generated for. This is a nullable
     *                 field, where the result list is cleared if null is supplied.
     * @param minimumDuration The minimum duration for an AppointmentSlot to be considered valid.
     */
    public void updateFilteredRange(TimeRange newRange, int minimumDuration) {
        this.filteredRange = newRange;
        if (newRange == null) {
            this.resultList.clear();
            detachListener();
            return;
        }

        checkArgument(minimumDuration > 0, "The minimum duration should be positive");
        this.minimumDuration = minimumDuration;

        updateResultList();
        attachListener();
    }

    /**
     * Returns the generated list of AppointmentSlot objects in the specified time range, or an empty list
     * if no time range is specified.
     */
    public ObservableList<AppointmentSlot> getSlotList() {
        return this.readOnlyResultList;
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof AppointmentSlotList)) {
            return false;
        }

        // state check
        AppointmentSlotList other = (AppointmentSlotList) obj;
        return Objects.equals(filteredRange, other.filteredRange)
                && minimumDuration == other.minimumDuration;
    }

    @Override
    public int hashCode() {
        return Objects.hash(minimumDuration, filteredRange);
    }

    /**
     * Attaches an update listener to the backing schedule.
     */
    private void attachListener() {
        backingSchedule.getAppointmentList().addListener(sourceChangeListener);
    }

    /**
     * Detaches the update listener from the backing schedule.
     */
    private void detachListener() {
        backingSchedule.getAppointmentList().removeListener(sourceChangeListener);
    }

    /**
     * Updates the result list with the generated list of slots.
     */
    private void updateResultList() {
        List<TimeRange> slotsFound = backingSchedule.findSlotsBetweenAppointments(
                filteredRange.getStartDateTime(), filteredRange.getEndDateTime(), minimumDuration);
        this.resultList.setAll(slotsFound.stream()
                .map(AppointmentSlot::new)
                .collect(Collectors.toList()));
    }
}
