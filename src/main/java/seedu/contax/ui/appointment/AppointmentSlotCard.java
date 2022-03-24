package seedu.contax.ui.appointment;

import static java.util.Objects.requireNonNull;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.contax.model.appointment.AppointmentSlot;
import seedu.contax.ui.UiPart;

/**
 * An UI component that displays the information in an {@code AppointmentSlot}.
 */
public class AppointmentSlotCard extends UiPart<Region> {

    private static final String FXML = "AppointmentSlotListCard.fxml";
    private static final String DATE_FORMAT = "dd LLL yyyy";
    private static final String TIME_FORMAT = "hh:mm a";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_FORMAT);

    private AppointmentSlot appointmentSlotModel;

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
     * Creates an empty instance of {@code AppointmentSlotCard}.
     */
    public AppointmentSlotCard() {
        super(FXML);
    }

    /**
     * Updates the data in this {@code AppointmentSlotCard} with the given {@code AppointmentSlot} to display.
     *
     * @param appointmentSlotModel The AppointmentSlot model to display in this card.
     */
    public void updateModel(AppointmentSlot appointmentSlotModel) {
        requireNonNull(appointmentSlotModel);
        if (appointmentSlotModel.equals(this.appointmentSlotModel)) {
            return;
        }

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

    /**
     * Translates a numeric minutes into a human-readable format.
     *
     * @param minutes The number of minutes to translate.
     * @return A string representing the supplied number of minutes.
     */
    private String getReadableDuration(long minutes) {
        long computedHours = minutes / 60;
        long computedMinutes = minutes % 60;

        if (computedHours > 240) {
            return "More than 10 days";
        }

        String displayDuration = "";
        if (computedHours > 0) {
            displayDuration = computedHours + (computedHours > 1 ? " Hours " : " Hour ");
        }
        if (computedHours > 0 && computedMinutes > 0) {
            displayDuration += "and ";
        }
        if (computedMinutes > 0) {
            displayDuration += computedMinutes + (computedMinutes > 1 ? " Minutes" : " Minute");
        }
        return displayDuration;
    }
}
