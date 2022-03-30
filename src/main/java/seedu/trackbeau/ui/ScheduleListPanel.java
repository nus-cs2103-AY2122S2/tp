package seedu.trackbeau.ui;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.trackbeau.commons.core.LogsCenter;
import seedu.trackbeau.model.booking.Booking;



/**
 * Panel containing the list of bookings.
 */
public class ScheduleListPanel extends UiPart<Region> {
    private static final String FXML = "ScheduleListPanel.fxml";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd-MM");
    private final Logger logger = LogsCenter.getLogger(ScheduleListPanel.class);

    @FXML
    private ListView<Booking> bookingListView;

    @FXML
    private Label displayDate;

    @FXML
    private Label bookingCount;

    /**
     * Creates a {@code BookingListPanel} with the given {@code ObservableList} and {@code LocalDate}.
     */
    public ScheduleListPanel(ObservableList<Booking> bookingList, LocalDate date) {
        super(FXML);
        bookingListView.setItems(bookingList);
        bookingListView.setCellFactory(listView -> new BookingListViewCell());
        displayDate.setText(formatter.format(date));
        if (isWeekend(date)) {
            displayDate.getStyleClass().add("weekendLabel");
        } else {
            displayDate.getStyleClass().add("weekdayLabel");
        }
        bookingCount.setText(bookingList.size() + " booking(s)");
    }

    private boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Booking} using a {@code BookingCard}.
     */
    class BookingListViewCell extends ListCell<Booking> {
        @Override
        protected void updateItem(Booking booking, boolean empty) {
            super.updateItem(booking, empty);

            if (empty || booking == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new BookingCard(booking, getIndex() + 1).getRoot());
            }
        }
    }
}
