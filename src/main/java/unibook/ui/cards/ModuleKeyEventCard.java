package unibook.ui.cards;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import unibook.commons.core.LogsCenter;
import unibook.model.module.ModuleKeyEvent;
import unibook.ui.UiPart;

public class ModuleKeyEventCard extends UiPart<Region> {
    private static final String FXML = "cards/ModuleKeyEventCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on UniBook level 4</a>
     */

    public final ModuleKeyEvent moduleKeyEvent;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label moduleKeyEventType;
    @FXML
    private Label moduleKeyEventTime;

    private final Logger logger = LogsCenter.getLogger(ModuleKeyEventCard.class);

    /**
     * Creates a {@code ProfessorCard} with the given {@code Professor} and index to display.
     */
    public ModuleKeyEventCard(ModuleKeyEvent keyEvent, int displayedIndex) {
        super(FXML);
        logger.info(String.format("Instantiating a moduleKeyEventCard"));
        this.moduleKeyEvent = keyEvent;
        id.setText(displayedIndex + ". ");
        moduleKeyEventType.setText(this.moduleKeyEvent.getKeyEventType().toString());
        moduleKeyEventTime.setText(this.moduleKeyEvent.getKeyEventTiming().format(
            DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ProfessorCard)) {
            return false;
        }

        // state check
        ModuleKeyEventCard card = (ModuleKeyEventCard) other;
        return id.getText().equals(card.id.getText())
            && moduleKeyEvent.equals(card.moduleKeyEvent);
    }
}
