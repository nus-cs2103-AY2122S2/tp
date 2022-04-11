package seedu.ibook.ui.table;

import javafx.scene.layout.HBox;
import seedu.ibook.ui.MainWindow;
import seedu.ibook.ui.UiComponent;

/**
 * The state of {@code ProductTable} when it is empty.
 */
public class NotFoundProductTableState extends UiComponent<HBox> {

    private static final String FXML = "table/NotFoundProductTableState.fxml";

    /**
     * Initializes the empty state of {@code ProductTable}.
     *
     * @param mainWindow The {@code MainWindow} that this component resides on.
     */
    public NotFoundProductTableState(MainWindow mainWindow) {
        super(FXML, mainWindow);
    }
}
