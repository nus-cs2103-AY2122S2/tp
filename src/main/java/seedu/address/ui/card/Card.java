package seedu.address.ui.card;

import javafx.scene.layout.Region;
import seedu.address.ui.UiPart;

/**
 * Card showing information of a Student or Lesson when displayed on a list
 */
public abstract class Card extends UiPart<Region> {
    /**
     * Creates a {@code Card}.
     *
     * @param fxml Provided FXML file.
     */
    public Card(String fxml) {
        super(fxml);
    }
}
