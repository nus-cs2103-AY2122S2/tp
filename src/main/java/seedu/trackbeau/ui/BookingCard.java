package seedu.trackbeau.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.trackbeau.model.booking.Booking;

import java.time.format.DateTimeFormatter;

/**
 * An UI component that displays information of a {@code Booking}.
 */
public class BookingCard extends UiPart<Region> {

    private static final String FXML = "BookingListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on addressbook level 4</a>
     */

    public final Booking booking;
    public final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy 'at' h:mm a");

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label service;
    @FXML
    private Label startTime;

    /**
     * Creates a {@code BookingCard} with the given {@code Booking} and index to display.
     */
    public BookingCard(Booking booking, int displayedIndex) {
        super(FXML);
        this.booking = booking;
        id.setText(displayedIndex + ". ");
        name.setText(booking.getCustomerName().fullName);
        phone.setText("Phone Number: " + booking.getCustomerPhone().value);
        service.setText("Service: " + booking.getServiceName().fullName);
        startTime.setText("Start Time: " + booking.getBookingDateTime().value.format(formatter));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BookingCard)) {
            return false;
        }

        // state check
        BookingCard card = (BookingCard) other;
        return id.getText().equals(card.id.getText())
                && booking.equals(card.booking);
    }
}
