package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.patron.Patron;

/**
 * A UI component that displays information of a {@code Patron}.
 */
public class PatronCard extends UiPart<Region> {

    private static final String FXML = "PatronListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on LibTask level 4</a>
     */

    public final Patron patron;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label patronDisplayCardId;
    @FXML
    private Label patronId;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private FlowPane patronRoleTags;

    /**
     * Creates a {@code PatronCode} with the given {@code Patron} and index to display.
     */
    public PatronCard(Patron patron, int displayedIndex) {
        super(FXML);
        requireNonNull(patron);
        this.patron = patron;
        patronDisplayCardId.setText(displayedIndex + ". ");
        name.setText(patron.getName().fullName);
        patronId.setText("ID: " + patron.getId().value);
        phone.setText("Phone: " + patron.getPhone().value);
        email.setText("Email: " + patron.getEmail().value);
        patron.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> patronRoleTags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PatronCard)) {
            return false;
        }

        // state check
        PatronCard card = (PatronCard) other;
        return patronDisplayCardId.getText().equals(card.patronDisplayCardId.getText())
                && patron.equals(card.patron);
    }
}
