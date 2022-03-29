package seedu.address.ui;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.tag.Tag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the control of schedule calendar.
 */
public class ScheduleCalendarPanel extends UiPart<Region> {
    private static final String FXML = "ScheduleCalendarPanel.fxml";

    @FXML
    private GridPane gridPane;

    private ObservableList<Schedule> schedules;

    public ScheduleCalendarPanel(ObservableList<Schedule> schedules) {
        super(FXML);
        this.schedules = schedules;
    }

    public void update(ObservableList<Schedule> schedules) {

    }
}