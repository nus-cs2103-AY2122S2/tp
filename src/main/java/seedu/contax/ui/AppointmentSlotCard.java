package seedu.contax.ui;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.contax.model.appointment.AppointmentSlot;

/**
 * An UI component that displays the information in an {@code Appointment}.
 */
public class AppointmentSlotCard extends UiPart<Region> {

    private static final String FXML = "AppointmentSlotListCard.fxml";
    private static final String DATE_FORMAT = "dd LLL yyyy";
    private static final String TIME_FORMAT = "hh:mm a";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_FORMAT);

    public final AppointmentSlot appointmentSlotModel;

    @FXML
    private Label duration;
    @FXML
    private Label startDate;
    @FXML
    private Label startTime;
    @FXML
    private Label endDate;
    @FXML
    private Label endTime;

    /**
     * Creates a {@code AppointmentCard} with the given {@code Appointment} and index to display.
     */
    public AppointmentSlotCard(AppointmentSlot appointmentSlotModel) {
        super(FXML);
        this.appointmentSlotModel = appointmentSlotModel;

        LocalDateTime startDateTime = appointmentSlotModel.getStartDateTime();
        LocalDateTime endDateTime = appointmentSlotModel.getEndDateTime();
        startDate.setText(startDateTime.format(DATE_FORMATTER));
        startTime.setText(startDateTime.format(TIME_FORMATTER));
        endDate.setText(endDateTime.format(DATE_FORMATTER));
        endTime.setText(endDateTime.format(TIME_FORMATTER));

        long minutes = Duration.between(startDateTime, endDateTime).toMinutes();
        duration.setText(getReadableDuration(minutes));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppointmentSlotCard // instanceof handles nulls
                && appointmentSlotModel.equals(((AppointmentSlotCard) other).appointmentSlotModel));
    }

    private String getReadableDuration(long minutes) {
        long computedHours = minutes / 60;
        long computedMinutes = minutes % 60;

        String displayDuration = "";
        if (computedHours > 0) {
            displayDuration = computedHours + " Hours ";
        }
        if (computedHours > 0 && computedMinutes > 0) {
            displayDuration += "and ";
        }
        if (computedMinutes > 0) {
            displayDuration += computedMinutes + " Minutes";
        }
        return displayDuration;
    }
}
