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
    @FXML
    private Label withLabel;
    @FXML
    private Label priority;


    /**
     * Creates a {@code AppointmentCard} with the given {@code Appointment} and index to display.
     */
    public AppointmentCard(Appointment appointmentModel, int displayedIndex) {
        super(FXML);
        this.appointmentModel = appointmentModel;
        this.displayedIndex = displayedIndex;

        id.setText(displayedIndex + ". ");
        name.setText(appointmentModel.getName().name);
        switch (appointmentModel.getPriority()) {
        case HIGH:
            priority.setText("High");
            priority.setStyle(
                    "-fx-background-color: lightpink; "
                    + "-fx-text-fill: black;"
                    + "-fx-padding: 5 20;"
                    + "-fx-background-radius: 50"
            );
            break;
        case MEDIUM:
            priority.setText("Medium");
            priority.setStyle(
                    "-fx-background-color: lightyellow; "
                    + "-fx-text-fill: black;"
                    + "-fx-padding: 5 20;"
                    + "-fx-background-radius: 50"
            );
            break;
        case LOW:
            priority.setText("Low");
            priority.setStyle(
                    "-fx-background-color: lightgreen; "
                    + "-fx-text-fill: black;"
                    + "-fx-padding: 5 20;"
                    + "-fx-background-radius: 50"
            );
            break;
        default:
            priority.setText("");
        }


        LocalDateTime startDateTime = appointmentModel.getStartDateTime().value;
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

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppointmentCard // instanceof handles nulls
                && appointmentModel.equals(((AppointmentCard) other).appointmentModel)
                && displayedIndex == ((AppointmentCard) other).displayedIndex);
    }
}
