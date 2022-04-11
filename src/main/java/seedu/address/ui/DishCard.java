package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.dish.Dish;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class DishCard extends UiPart<Region> {

    private static final String FXML = "DishListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Dish dish;

    @FXML
    private HBox dishCardPane;
    @FXML
    private Label dishName;
    @FXML
    private Label dishId;
    @FXML
    private Label dishPrice;

    /**
     * Creates a {@code DishCode} with the given {@code Dish} and index to display.
     */
    public DishCard(Dish dish, int displayedIndex) {
        super(FXML);
        this.dish = dish;
        dishId.setText(displayedIndex + ". ");
        dishName.setText(dish.getName().fullName);
        dishPrice.setText("$" + dish.getPrice().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DishCard)) {
            return false;
        }

        // state check
        DishCard card = (DishCard) other;
        return dishId.getText().equals(card.dishId.getText())
                && dish.equals(card.dish);
    }
}

