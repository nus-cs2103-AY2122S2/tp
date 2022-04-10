package woofareyou.ui;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javafx.scene.control.Label;
import woofareyou.model.pet.Appointment;

public class AppointmentTag extends Label {
    private static final String APPOINTMENT_TAG_PREFIX = "Appointment: ";
    private static final String APPOINTMENT_AFTER_TODAY_STYLE = "-fx-background-color: #c4c4c4";
    private static final String APPOINTMENT_BEFORE_TODAY_STYLE = "-fx-background-color: #ff595e";
    private static final String APPOINTMENT_TODAY_STYLE = "-fx-background-color: #90be6d";
    private static final LocalDate NOW = LocalDate.now();

    /**
     * Creates a new pet appointment tag.
     */
    public AppointmentTag(Appointment appointment) {
        super(getTagDetails(appointment));
        super.setStyle(getTagStyle(appointment));
    }

    /**
     * Retrieves the details of the pet's appointment to be displayed in the tag.
     *
     * @param appointment the pet's appointment.
     * @return the details of the pet's appointment.
     */
    public static String getTagDetails(Appointment appointment) {
        requireNonNull(appointment);

        return APPOINTMENT_TAG_PREFIX + appointment;
    }

    /**
     * Retrieves the style to be applied to the appointment tag.
     *
     * @param appointment the pet's appointment.
     * @return the style of the appointment tag to be applied.
     */
    private static String getTagStyle(Appointment appointment) {
        requireNonNull(appointment);
        LocalDateTime appDateTime = appointment.getDateTime();
        LocalDate appDate = appDateTime.toLocalDate();

        if (appDate.equals(NOW)) {
            return APPOINTMENT_TODAY_STYLE; // appointment is today, green tag
        } else if (appDate.isBefore(NOW)) {
            return APPOINTMENT_BEFORE_TODAY_STYLE; // appointment has already past, red tag
        } else {
            return APPOINTMENT_AFTER_TODAY_STYLE; // appointment is upcoming, grey tag
        }
    }
}
