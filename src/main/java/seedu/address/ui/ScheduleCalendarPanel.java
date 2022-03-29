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
import javafx.scene.text.Text;
import seedu.address.model.person.Person;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.tag.Tag;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the control of schedule calendar.
 */
public class ScheduleCalendarPanel extends UiPart<Region> {
    private static final String FXML = "ScheduleCalendarPanel.fxml";
    private static final List<String> DAYS = List.of("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");

    @FXML
    private GridPane calendar;

    @FXML
    private Text month;

    private ObservableList<Schedule> schedules;

    public ScheduleCalendarPanel(ObservableList<Schedule> schedules) {
        super(FXML);
        this.schedules = schedules;

        setMonth();
        setDates();
    }

    public void update(ObservableList<Schedule> schedules) {
        this.schedules = schedules;

        setMonth();
        setDates();

    }

    public void setMonth() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        LocalDateTime localDateTime = LocalDateTime.now();
        month.setText(dateTimeFormatter.format(localDateTime));
    }

    public void setDates() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yyyy");
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("DD");
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("EEEE");
        System.out.println(dayFormatter.format(localDateTime));
        int initialColumn = DAYS.indexOf(dayFormatter.format(localDateTime));
        int currYear = Integer.parseInt(yearFormatter.format(localDateTime));
        int currMonth = Integer.parseInt(monthFormatter.format(localDateTime));
        int currDate = Integer.parseInt(dateFormatter.format(localDateTime));
        int numOfDays = YearMonth.of(currYear, currMonth).lengthOfMonth();
        int row = 1;
        int col = initialColumn;
        for (int i = 0; i < numOfDays; i++) {
            Text day = new Text();
            day.setText(String.valueOf(i + 1));
            calendar.add(day, col, row);
            col++;
            if (col > 6) {
                col = 0;
                row++;
            }
        }
    }
}