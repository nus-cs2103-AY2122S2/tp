package seedu.ibook.ui.table.item;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import seedu.ibook.ui.MainWindow;
import seedu.ibook.ui.UiComponent;

/**
 * The state of {@code ItemTable} when it is empty.
 */
public class EmptyItemTableState extends UiComponent<HBox> {

    private static final String FXML = "Table/Item/EmptyItemTableState.fxml";

    /**
     * Initializes the empty state of {@code ItemTable}.
     *
     * @param mainWindow The {@code MainWindow} that this component resides on.
     */
    public EmptyItemTableState(MainWindow mainWindow) {
        super(FXML, mainWindow);
    }

    /**
     * Handles the add item button clicked event.
     */
    @FXML
    private void handleAddItemClicked() {
        // TODO: Add after PR #129
    }
}
