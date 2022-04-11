package seedu.address.ui;

import java.util.Map;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.label.Label;
import seedu.address.model.person.Phone;

/**
 * Card containing the numbers associated with a person.
 */
public class NumbersCard extends UiPart<Region> {
    private static final String FXML = "NumbersCard.fxml";
    private final Logger logger = LogsCenter.getLogger(NumbersCard.class);

    @FXML
    private VBox numbersContainer;

    /**
     * Creates a {@code NumbersCard} with the given {@code Map} of numbers and their respective labels.
     */
    public NumbersCard(Map<Label, Phone> numbers) {
        super(FXML);
        for (Map.Entry<Label, Phone> number : numbers.entrySet()) {
            numbersContainer.getChildren().add(new InfoBox(number.getKey().label, number.getValue().phone).getRoot());
        }
    }
}
