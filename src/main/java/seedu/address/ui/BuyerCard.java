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
public class BuyerCard extends UiPart<Region> {

    private static final String FXML = "BuyerListCard.fxml";

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
    public BuyerCard(Buyer buyer, int displayedIndex) {
        super(FXML);
        this.client = buyer;
        id.setText(displayedIndex + ". ");
        name.setText(buyer.getName().fullName);
        phone.setText(buyer.getPhone().value);
        appointment.setText(buyer.getAppointment().getAppointmentDetail());
        buyer.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        String houseType = buyer.getPropertyToBuy().getHouse().getHouseTypeToString();
        String houseLocation = buyer.getPropertyToBuy().getHouse().getLocationToString();
        String houseLowerPrice = buyer.getPropertyToBuy().getPriceRange().getLowerToString();
        String houseUpperPrice = buyer.getPropertyToBuy().getPriceRange().getUpperToString();
        if (buyer.getPropertyToBuy() instanceof NullPropertyToBuy) {
            propertyType.setText("no property added yet");
            propertyLocation.setText("-");
            propertyLowerPrice.setText("");
        } else {
            propertyType.setText(houseType);
            propertyLocation.setText(houseLocation);
            propertyLowerPrice.setText(houseLowerPrice);
            propertyUpperPrice.setText(houseUpperPrice);
        }

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BuyerCard)) {
            return false;
        }

        // state check
        BuyerCard card = (BuyerCard) other;
        return id.getText().equals(card.id.getText())
                && client.equals(card.client);
    }
}
