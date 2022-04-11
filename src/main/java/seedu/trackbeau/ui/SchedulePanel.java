package seedu.trackbeau.ui;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.trackbeau.commons.core.LogsCenter;
import seedu.trackbeau.model.booking.Booking;

public class SchedulePanel extends UiPart<Region> {
    private static final String FXML = "Schedule.fxml";
    private static final int DAYS_IN_WEEK = 7;
    private final Logger logger = LogsCenter.getLogger(SchedulePanel.class);

    private LocalDate startOfWeek;

    @FXML
    private HBox weekView;

    /**
     * Creates a {@code Schedule} with the given {@code ObservableList}.
     */
    public SchedulePanel(ObservableList<Booking> bookingList, LocalDate date) {
        super(FXML);
        this.startOfWeek = date;
        while (startOfWeek.getDayOfWeek() != DayOfWeek.MONDAY) {
            this.startOfWeek = startOfWeek.minusDays(1);
        }
        for (int i = 0; i < DAYS_IN_WEEK; i++) {
            weekView.getChildren().add(dayView(bookingList, startOfWeek.plusDays(i)).getRoot());
        }
    }

    private ScheduleListPanel dayView(ObservableList<Booking> bookingList, LocalDate date) {
        return new ScheduleListPanel(bookingList.filtered(booking -> isSameDate(booking, date)).sorted(), date);
    }

    private boolean isSameDate(Booking booking, LocalDate date) {
        return booking.getBookingDateTime().value.toLocalDate().equals(date);
    }
}
