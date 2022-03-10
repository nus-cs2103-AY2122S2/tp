package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Address;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Card containing the addresses associated with a person.
 */
public class AddressesCard extends UiPart<Region> {
    private static final String FXML = "AddressesCard.fxml";
    private final Logger logger = LogsCenter.getLogger(AddressesCard.class);

    @FXML
    private VBox addressesContainer;

    /**
     * Creates an {@code AddressesCard} with the given {@code Map} of addresses and their respective labels.
     */
    public AddressesCard(Map<String, Address> addresses) {
        super(FXML);
        for (Map.Entry<String, Address> address : addresses.entrySet()) {
            addressesContainer.getChildren().add(new InfoBox(address.getKey(), address.getValue().address).getRoot());
        }
    }
}
