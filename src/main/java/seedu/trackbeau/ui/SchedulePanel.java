package seedu.trackbeau.ui;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.trackbeau.commons.core.LogsCenter;
import seedu.trackbeau.model.booking.Booking;

public class SchedulePanel extends UiPart<Region> {
    private static final String FXML = "Schedule.fxml";
    private final Logger logger = LogsCenter.getLogger(SchedulePanel.class);

    private LocalDate startOfWeek;

    @FXML
    private ListView<Booking> monListView;
    @FXML
    private ListView<Booking> tueListView;
    @FXML
    private ListView<Booking> wedListView;
    @FXML
    private ListView<Booking> thuListView;
    @FXML
    private ListView<Booking> friListView;
    @FXML
    private ListView<Booking> satListView;
    @FXML
    private ListView<Booking> sunListView;

    /**
     * Creates a {@code Schedule} with the given {@code ObservableList}.
     */
    public SchedulePanel(ObservableList<Booking> bookingList, LocalDate date) {
        super(FXML);
        this.startOfWeek = date;
        while (startOfWeek.getDayOfWeek() != DayOfWeek.MONDAY) {
            this.startOfWeek = startOfWeek.minusDays(1);
        }
        monListView.setItems(bookingList.filtered(booking -> isSameDate(booking, startOfWeek)));
        monListView.setCellFactory(listView -> new BookingListViewCell());
        tueListView.setItems(bookingList.filtered(booking -> isSameDate(booking, startOfWeek.plusDays(1))));
        tueListView.setCellFactory(listView -> new BookingListViewCell());
        wedListView.setItems(bookingList.filtered(booking -> isSameDate(booking, startOfWeek.plusDays(2))));
        wedListView.setCellFactory(listView -> new BookingListViewCell());
        thuListView.setItems(bookingList.filtered(booking -> isSameDate(booking, startOfWeek.plusDays(3))));
        thuListView.setCellFactory(listView -> new BookingListViewCell());
        friListView.setItems(bookingList.filtered(booking -> isSameDate(booking, startOfWeek.plusDays(4))));
        friListView.setCellFactory(listView -> new BookingListViewCell());
        satListView.setItems(bookingList.filtered(booking -> isSameDate(booking, startOfWeek.plusDays(5))));
        sunListView.setCellFactory(listView -> new BookingListViewCell());
        sunListView.setItems(bookingList.filtered(booking -> isSameDate(booking, startOfWeek.plusDays(6))));
        sunListView.setCellFactory(listView -> new BookingListViewCell());
    }

    public boolean isSameDate(Booking booking, LocalDate date) {
        return booking.getBookingDateTime().value.toLocalDate().equals(date);
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
