package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.client.Client;
import seedu.address.model.property.NullPropertyToBuy;

/**
 * An UI component that displays information of a {@code client}.
 */
public class ClientCard extends UiPart<Region> {

    private static final String FXML = "ClientListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Client client;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private FlowPane tags;
    @FXML
    private Label appointment;
    @FXML
    private Label propertyType;
    @FXML
    private Label propertyLocation;
    @FXML
    private Label propertyLowerPrice;
    @FXML
    private Label propertyUpperPrice;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public ClientCard(Buyer client, int displayedIndex) {
        super(FXML);
        this.client = client;
        id.setText(displayedIndex + ". ");
        name.setText(client.getName().fullName);
        phone.setText(client.getPhone().value);
        appointment.setText(client.getAppointment().getAppointmentDetail());
        client.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        if (client.getDesiredProperty() instanceof NullPropertyToBuy) {
            System.out.println("The NullPropertyToBuy is accessed");

        } else {
            propertyType.setText(client.getDesiredProperty().getHouse().getHouseTypeToString());
            propertyLocation.setText(client.getDesiredProperty().getHouse().getLocationToString());
            propertyLowerPrice.setText(client.getDesiredProperty().getPriceRange().getLowerToString());
            propertyUpperPrice.setText(client.getDesiredProperty().getPriceRange().getUpperToString());
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClientCard)) {
            return false;
        }

        // state check
        ClientCard card = (ClientCard) other;
        return id.getText().equals(card.id.getText())
                && client.equals(card.client);
    }
}
