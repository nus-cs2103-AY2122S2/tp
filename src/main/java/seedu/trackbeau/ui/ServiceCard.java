package seedu.trackbeau.ui;

import java.text.NumberFormat;
import java.time.Duration;
import java.util.Locale;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.trackbeau.model.service.Service;

/**
 * An UI component that displays information of a {@code Customer}.
 */
public class ServiceCard extends UiPart<Region> {

    private static final String FXML = "ServiceListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on Addressbook level 4</a>
     */

    public final Service service;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label price;
    @FXML
    private Label duration;


    /**
     * Creates a {@code ServiceCard} with the given {@code Service} and index to display.
     */
    public ServiceCard(Service service, int displayedIndex) {
        super(FXML);
        this.service = service;
        id.setText(displayedIndex + ". ");
        name.setText(service.getName().fullName);
        Locale locale = new Locale("en", "SG");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        String priceString = formatter.format(service.getPrice().value);
        price.setText("Price: " + priceString);
        Duration durationFormatter = Duration.ofMinutes(service.getDuration().value);
        int hours = durationFormatter.toHoursPart();
        int minutes = durationFormatter.toMinutesPart();
        String displayDuration = "Duration: ";
        if (hours > 0) {
            displayDuration += (hours + "hr");
        }
        if (minutes > 0) {
            displayDuration += (" " + minutes + "mins");
        }
        duration.setText(displayDuration);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ServiceCard)) {
            return false;
        }

        // state check
        ServiceCard card = (ServiceCard) other;
        return id.getText().equals(card.id.getText())
                && service.equals(card.service);
    }
}
