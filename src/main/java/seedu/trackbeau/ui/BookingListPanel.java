package seedu.trackbeau.ui;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.trackbeau.model.booking.Booking;

public class BookingListPanel extends UiPart<Region> {
    private static final String FXML = "BookingListPanel.fxml";

    @FXML
    private ListView<Booking> bookingListView;

    public BookingListPanel() {
        super(FXML);
    }
}
