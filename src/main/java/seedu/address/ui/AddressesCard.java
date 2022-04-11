package seedu.address.ui;

import java.util.Map;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.label.Label;
import seedu.address.model.person.Address;

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
    public AddressesCard(Map<Label, Address> addresses) {
        super(FXML);
        for (Map.Entry<Label, Address> address : addresses.entrySet()) {
            addressesContainer.getChildren().add(new InfoBox(address.getKey().label, address.getValue().address)
                    .getRoot());
        }
    }
}
