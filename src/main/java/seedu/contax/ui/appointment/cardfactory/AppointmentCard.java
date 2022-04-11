package seedu.contax.ui.appointment.cardfactory;

import static java.util.Objects.requireNonNull;
import static seedu.contax.commons.core.DateTimeFormats.DATE_DISPLAY_FORMAT;
import static seedu.contax.commons.core.DateTimeFormats.TIME_12H_DISPLAY_FORMAT;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.contax.model.appointment.Appointment;
import seedu.contax.model.appointment.Priority;
import seedu.contax.model.person.Person;
import seedu.contax.ui.UiPart;

/**
 * Displays the information in an {@code Appointment}.
 * This class is deliberately maintained as package private to prevent unintended access.
 */
class AppointmentCard extends UiPart<Region> {

    private static final String FXML = "AppointmentListCard.fxml";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_DISPLAY_FORMAT);
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_12H_DISPLAY_FORMAT);

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
    @FXML
    private Label priority;

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

            Person person = appointmentModel.getPerson();
            boolean hasPerson = person != null;
            withLabel.setVisible(hasPerson);
            personName.setText(hasPerson ? person.getName().fullName : "");
            personAddress.setText(hasPerson ? person.getAddress().value : "");

            updatePriorityStyle(appointmentModel.getPriority());
        }

        if (displayedIndex != this.displayedIndex) {
            this.displayedIndex = displayedIndex;
            id.setText(displayedIndex + ". ");
        }
    }

    /** Updates the displayed priority label to match the supplied {@code Priority}. */
    private void updatePriorityStyle(Priority modelPriority) {
        priority.getStyleClass().remove("high");
        priority.getStyleClass().remove("medium");
        priority.getStyleClass().remove("low");

        if (modelPriority == null) {
            priority.setText("");
            return;
        }

        priority.setText(modelPriority.toString());
        switch (appointmentModel.getPriority()) {
        case HIGH:
            priority.getStyleClass().add("high");
            break;
        case MEDIUM:
            priority.getStyleClass().add("medium");
            break;
        case LOW:
            priority.getStyleClass().add("low");
            break;
        default:
            // Unknown priority styling, use default style.
            break;
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
