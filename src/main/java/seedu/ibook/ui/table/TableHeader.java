package seedu.ibook.ui.table;

import javafx.scene.layout.HBox;
import seedu.ibook.ui.MainWindow;
import seedu.ibook.ui.UiComponent;

/**
 * Header of the Table
 */
public class TableHeader extends UiComponent<HBox> {

    private static final String FXML = "Table/TableHeader.fxml";

    /**
     * Initializes a {@code TableHeader}.
     *
     * @param mainWindow The {@code MainWindow} that this component resides on.
     */
    TableHeader(MainWindow mainWindow) {
        super(FXML, mainWindow);
    }
}
