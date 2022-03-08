package seedu.contax.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.contax.model.appointment.Appointment;
import seedu.contax.model.person.Person;

/**
 * An UI component that displays the information in an {@code Appointment}.
 */
public class AppointmentCard extends UiPart<Region> {

    private static final String FXML = "AppointmentListCard.fxml";
    private static final String DATE_FORMAT = "dd LLL yyyy";
    private static final String TIME_FORMAT = "hh:mm a";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_FORMAT);

    public final int displayedIndex;
    public final Appointment appointmentModel;

    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label startDate;
    @FXML
    private Label startTime;
    @FXML
    private Label endDate;
    @FXML
    private Label endTime;
    @FXML
    private Label personName;
    @FXML
    private Label personAddress;

    /**
     * Creates a {@code AppointmentCard} with the given {@code Appointment} and index to display.
     */
    public AppointmentCard(Appointment appointmentModel, int displayedIndex) {
        super(FXML);
        this.appointmentModel = appointmentModel;
        this.displayedIndex = displayedIndex;

        id.setText(displayedIndex + ". ");
        name.setText(appointmentModel.getName().name);

        LocalDateTime startDateTime = appointmentModel.getStartDateTime().value;
        LocalDateTime endDateTime = appointmentModel.getEndDateTime();
        startDate.setText(startDateTime.format(DATE_FORMATTER));
        startTime.setText(startDateTime.format(TIME_FORMATTER));
        endDate.setText(endDateTime.format(DATE_FORMATTER));
        endTime.setText(endDateTime.format(TIME_FORMATTER));

        if (appointmentModel.getPerson() != null) {
            Person p = appointmentModel.getPerson();
            personName.setText(p.getName().fullName);
            personAddress.setText(p.getAddress().value);
        } else {
            personName.setText("");
            personAddress.setText("");
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppointmentCard // instanceof handles nulls
                && appointmentModel.equals(((AppointmentCard) other).appointmentModel)
                && displayedIndex == ((AppointmentCard) other).displayedIndex);
    }
}
