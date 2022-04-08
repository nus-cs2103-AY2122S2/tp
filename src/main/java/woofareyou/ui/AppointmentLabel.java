package woofareyou.ui;

import static java.util.Objects.requireNonNull;

import javafx.scene.control.Label;
import woofareyou.model.pet.Appointment;

public class AppointmentLabel extends Label {
    private static final String LABEL = "Appointment:";
    private static final String LABEL_COLOR = "-fx-background-color: #f9c74f";

    private AppointmentLabel() {
    }

    /**
     * Create an appointment label for GUI if there is any.
     *
     * @param appointment of Pet
     * @return Appointment Label
     */
    public static AppointmentLabel createAppointmentLabel(Appointment appointment) {
        requireNonNull(appointment);
        AppointmentLabel appLabel = new AppointmentLabel();

        if (appointment.getDateTime() == null) {
            return appLabel;
        }
        appLabel.setText(LABEL);
        appLabel.setStyle(LABEL_COLOR);

        return appLabel;
    }
}
