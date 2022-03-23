package seedu.contax.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.contax.model.appointment.Appointment;
import seedu.contax.model.appointment.AppointmentSlot;
import seedu.contax.model.chrono.ScheduleItem;

public class CompositeScheduleItemList {

    private final ObservableList<Appointment> backingAppointmentList;
    private final ObservableList<AppointmentSlot> backingSlotList;
    private final ObservableList<ScheduleItem> scheduleItemList;
    private final ObservableList<ScheduleItem> unmodifiableScheduleItemList;

    public CompositeScheduleItemList(ObservableList<Appointment> backingAppointmentList,
                                     ObservableList<AppointmentSlot> backingSlotList) {
        requireNonNull(backingAppointmentList);
        requireNonNull(backingSlotList);
        this.backingAppointmentList = backingAppointmentList;
        this.backingSlotList = backingSlotList;
        this.scheduleItemList = FXCollections.observableArrayList();
        this.unmodifiableScheduleItemList = FXCollections.unmodifiableObservableList(scheduleItemList);

        attachListeners();
    }

    public ObservableList<ScheduleItem> getUnmodifiableList() {
        return this.unmodifiableScheduleItemList;
    }

    private void attachListeners() {
        this.backingAppointmentList.addListener((InvalidationListener) (change) -> {
            this.onChangeListener();
        });
        this.backingSlotList.addListener((InvalidationListener) (change) -> {
            this.onChangeListener();
        });
    }

    private void onChangeListener() {
        // Merge sorted lists
        int apptListIdx = 0;
        int slotListIdx = 0;
        ArrayList<ScheduleItem> mergedList =
                new ArrayList<>(backingAppointmentList.size() + backingSlotList.size());

        while (apptListIdx < backingAppointmentList.size() && slotListIdx < backingSlotList.size()) {
            if (backingAppointmentList.get(apptListIdx).compareTo(backingSlotList.get(slotListIdx)) < 0) {
                mergedList.add(backingAppointmentList.get(apptListIdx));
                apptListIdx++;
            } else {
                mergedList.add(backingSlotList.get(slotListIdx));
                slotListIdx++;
            }
        }
        while (apptListIdx < backingAppointmentList.size()) {
            mergedList.add(backingAppointmentList.get(apptListIdx));
            apptListIdx++;
        }
        while (slotListIdx < backingSlotList.size()) {
            mergedList.add(backingSlotList.get(slotListIdx));
            slotListIdx++;
        }

        scheduleItemList.setAll(mergedList);
    }
}
