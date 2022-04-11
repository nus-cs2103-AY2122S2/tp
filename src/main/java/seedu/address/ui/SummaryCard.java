package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.summarybar.SummaryBox;

/**
 * An UI component that displays information of an {@code Application}.
 */
public class SummaryCard extends UiPart<Region> {

    private static final String FXML = "SummaryListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final SummaryBox summaryBox;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label currApplications;
    @FXML
    private Label totalApplications;

    /**
     * Creates an {@code ApplicationCard} with the given {@code Application} and index to display.
     */
    public SummaryCard(SummaryBox summaryBox) {
        super(FXML);
        this.summaryBox = summaryBox;
        name.setText(summaryBox.getName());
        if (summaryBox.getCurrApplications().isEmpty()) {
            currApplications.setText("");
        } else {
            currApplications.setText(summaryBox.getCurrApplications().get() + "/");
        }
        totalApplications.setText(String.valueOf(summaryBox.getTotalApplications()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SummaryCard)) {
            return false;
        }

        // state check
        SummaryCard card = (SummaryCard) other;
        return name.getText().equals(card.name.getText())
                && summaryBox.equals(card.summaryBox);
    }
}
