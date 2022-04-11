package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.order.Details;
import seedu.address.model.order.Order;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Order}.
 */
public class OrderCard extends UiPart<Region> {

    private static final String FXML = "OrderListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Order order;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label remark;
    @FXML
    private FlowPane details;
    @FXML
    private Label deliveryDateTime;
    @FXML
    private Label collectionType;
    @FXML
    private Label complete;

    /**
     * Creates a {@code OrderCard} with the given {@code Order}, {@code Person} and index to display.
     */
    public OrderCard(Order order, int displayedIndex, Person person) {
        super(FXML);
        this.order = order;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        complete.setText(order.getComplete().toString());
        remark.setText(order.getRemark().value);
        deliveryDateTime.setText(order.getDeliveryDateTime().toString());
        collectionType.setText(order.getCollectionType().getValue());

        order.getDetails().stream()
                        .map(Details::toString)
                        .forEach(detail -> details.getChildren().add(new Label(detail)));
    }

    /**
     * Creates a {@code OrderCard} with the given {@code Order} and index to display.
     */
    public OrderCard(Order order, int displayedIndex) {
        super(FXML);
        this.order = order;
        id.setText(displayedIndex + ". ");
        name.setText("Null");
        phone.setText("Null");
        address.setText("Null");
        remark.setText(order.getRemark().value);
        deliveryDateTime.setText(order.getDeliveryDateTime().toString());
        collectionType.setText(order.getCollectionType().getValue());
        complete.setText(order.getComplete().toString());
        order.getDetails().stream()
                .map(Details::toString)
                .forEach(detail -> details.getChildren().add(new Label(detail)));
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof OrderCard)) {
            return false;
        }

        // state check
        OrderCard card = (OrderCard) other;
        return id.getText().equals(card.id.getText())
                && order.equals(card.order);
    }
}
