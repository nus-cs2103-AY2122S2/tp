package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.insurance.Insurance;


/**
 * An UI component that displays information of a {@code Insurance}.
 */
public class InsuranceDetailCard extends UiPart<Region> {

    private static final String FXML = "InsuranceDetailCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Insurance insurance;

    @FXML
    private HBox cardPane;
    @FXML
    private Label title;
    @FXML
    private Label id;
    @FXML
    private Label price;

    /**
     * Creates a {@code InsuranceCode} with the given {@code Insurance} and index to display.
     */
    public InsuranceDetailCard(Insurance insurance, int displayedIndex) {
        super(FXML);
        this.insurance = insurance;
        id.setText(displayedIndex + ". ");
        title.setText(insurance.getTitle().toString());
        price.setText("Price: " + insurance.getPrice().toString());

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InsuranceDetailCard)) {
            return false;
        }

        // state check
        InsuranceDetailCard card = (InsuranceDetailCard) other;
        return id.getText().equals(card.id.getText())
                && insurance.equals(card.insurance);
    }
}
