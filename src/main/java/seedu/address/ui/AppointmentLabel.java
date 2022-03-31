package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javafx.scene.control.Label;
import seedu.address.model.pet.Appointment;

public class AppointmentLabel {
    private static final String APPOINTMENT_AFTER_TODAY_STYLE = "-fx-background-color: #c4c4c4";
    private static final String APPOINTMENT_BEFORE_TODAY_STYLE = "-fx-background-color: #ff595e";
    private static final String APPOINTMENT_TODAY_STYLE = "-fx-background-color: #90be6d";
    private static final String LABEL = "Appointment:";
    private static final LocalDate NOW = LocalDate.now();

    private AppointmentLabel() {}

    /**
     * Create an appointment label for GUI if there is any. If appointment date is not the current date,
     * label will be grey, else it will be green.
     * @param appointment Pet appointment
     * @return Label with appropriate color
     */
    public static Label createAppointmentLabel(Appointment appointment) {
        requireNonNull(appointment);


        Label appLabel = new Label();

        if (appointment.getDateTime() == null) {
            return appLabel;
        } else {
            LocalDateTime appDateTime = appointment.getDateTime();
            LocalDate appDate = appDateTime.toLocalDate();
            appLabel.setText(LABEL);

            if (appDate.equals(NOW)) {
                appLabel.setStyle(APPOINTMENT_TODAY_STYLE);
            } else if (appDate.isBefore(NOW)) {
                appLabel.setStyle(APPOINTMENT_BEFORE_TODAY_STYLE);
            } else {
                appLabel.setStyle(APPOINTMENT_AFTER_TODAY_STYLE);
            }
        }
        return appLabel;
    }
}
