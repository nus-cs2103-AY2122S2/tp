package seedu.contax.ui.appointment.cardfactory;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.contax.model.appointment.Appointment;
import seedu.contax.model.person.Person;
import seedu.contax.ui.UiPart;

/**
 * Displays the information in an {@code Appointment}.
 * This class is deliberately maintained as package private to prevent unintended access.
 */
class AppointmentCard extends UiPart<Region> {

    private static final String FXML = "AppointmentListCard.fxml";
    private static final String DATE_FORMAT = "dd LLL yyyy";
    private static final String TIME_FORMAT = "hh:mm a";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_FORMAT);

    private int displayedIndex;
    private Appointment appointmentModel;

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
    @FXML
    private Label withLabel;

    /**
     * Creates a new empty instance of {@code AppointmentCard}.
     */
    AppointmentCard() {
        super(FXML);
    }

    /**
     * Updates the data in this {@code AppointmentCard} with the given {@code Appointment} and index to
     * display.
     *
     * @param appointmentModel The Appointment to display in this card.
     * @param displayedIndex The index to display for this card.
     */
    void updateModel(Appointment appointmentModel, int displayedIndex) {
        requireNonNull(appointmentModel);
        if (!appointmentModel.equals(this.appointmentModel)) {
            this.appointmentModel = appointmentModel;
            name.setText(appointmentModel.getName().name);

            LocalDateTime startDateTime = appointmentModel.getStartDateTime();
            LocalDateTime endDateTime = appointmentModel.getEndDateTime();
            startDate.setText(startDateTime.format(DATE_FORMATTER));
            startTime.setText(startDateTime.format(TIME_FORMATTER));
            endDate.setText(endDateTime.format(DATE_FORMATTER));
            endTime.setText(endDateTime.format(TIME_FORMATTER));

            if (appointmentModel.getPerson() != null) {
                withLabel.setVisible(true);
                Person p = appointmentModel.getPerson();
                personName.setText(p.getName().fullName);
                personAddress.setText(p.getAddress().value);
            } else {
                withLabel.setVisible(false);
                personName.setText("");
                personAddress.setText("");
            }
        }

        if (displayedIndex != this.displayedIndex) {
            this.displayedIndex = displayedIndex;
            id.setText(displayedIndex + ". ");
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
