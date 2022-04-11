package seedu.address.ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import seedu.address.model.schedule.Schedule;


/**
 * Represents the control of schedule calendar.
 */
public class ScheduleCalendarPanel extends UiPart<Region> {
    private static final String FXML = "ScheduleCalendarPanel.fxml";
    private static final List<String> DAYS =
            List.of("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");

    @FXML
    private GridPane calendar;

    @FXML
    private Text todayDay;

    @FXML
    private Text todayDate;

    private ObservableList<Schedule> schedules;

    /**
     * Creates a new calendar panel.
     *
     * @param schedules List o schedules.
     */
    public ScheduleCalendarPanel(ObservableList<Schedule> schedules) {
        super(FXML);
        this.schedules = schedules;

        setTodayDay();
        setTodayDate();
        setDates();
    }

    /**
     * Updates the content of schedule calendar.
     *
     * @param schedules List of schedules.
     */
    public void update(ObservableList<Schedule> schedules) {
        this.schedules = schedules;

        setTodayDay();
        setTodayDate();
        setDates();
    }

    /**
     * Sets the day today on GUI.
     */
    public void setTodayDay() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("EEEE");
        LocalDateTime localDateTime = LocalDateTime.now();
        todayDay.setText(dateTimeFormatter.format(localDateTime));
        todayDay.setFont(new Font(45));
    }

    /**
     * Sets the date today on GUI.
     */
    public void setTodayDate() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        LocalDateTime localDateTime = LocalDateTime.now();
        todayDate.setText(dateTimeFormatter.format(localDateTime));
        todayDate.setFont(new Font(25));
    }

    /**
     * Sets the dates in the calendar.
     */
    public void setDates() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yyyy");
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd");
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
            LocalDate currentDate = LocalDate.of(currYear, currMonth, i + 1);
            boolean hasSchedule = false;
            for (Schedule schedule : this.schedules) {
                if (schedule.getScheduleDateTime().getScheduleDateTime().toLocalDate().equals(currentDate)) {
                    hasSchedule = true;
                    break;
                }
            }


            Text day = new Text();
            day.setFont(new Font(20));
            if (!hasSchedule) {
                day.setText(String.valueOf(i + 1) + "\n");
                day.setFill(Color.WHITE);
            } else {
                day.setText(String.valueOf(i + 1) + "\n" + "○");
                day.setFill(Color.RED);
            }

            StackPane cell = new StackPane(day);
            if (i + 1 == currDate) {
                cell.setBackground(new Background(new BackgroundFill(Paint.valueOf("#707070"), null, null)));
            } else {
                cell.setBackground(new Background(new BackgroundFill(Paint.valueOf("#252525"), null, null)));
            }

            calendar.add(cell, col, row);
            col++;
            if (col > 6) {
                col = 0;
                row++;
            }
        }
    }
}
