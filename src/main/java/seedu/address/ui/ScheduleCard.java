package seedu.address.ui;

import java.util.stream.Stream;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.schedule.Schedule;

/**
 * A UI component that displays information of a {@code Schedule}.
 */
public class ScheduleCard extends UiPart<Region> {
    private static final String FXML = "ScheduleListCard.fxml";

    public final Schedule schedule;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label description;
    @FXML
    private FlowPane dateTimes;

    /**
     * Creates a {@code ScheduleCard} with the given {@code Person} and index to display.
     */
    public ScheduleCard(Schedule schedule, int displayedIndex) {
        super(FXML);
        this.schedule = schedule;
        id.setText(displayedIndex + ". ");
        name.setText(schedule.getScheduleName().toString());
        description.setText(schedule.getScheduleDescription().toString());
        Stream.of(schedule.getScheduleDateTime())
                .forEach(dateTime -> dateTimes.getChildren().add(new Label(dateTime.toString())));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ScheduleCard)) {
            return false;
        }

        // state check
        ScheduleCard card = (ScheduleCard) other;
        return id.getText().equals(card.id.getText())
                && schedule.equals(card.schedule);
    }
}
