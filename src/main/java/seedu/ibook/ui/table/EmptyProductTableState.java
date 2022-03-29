package seedu.ibook.ui.table;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import seedu.ibook.ui.MainWindow;
import seedu.ibook.ui.UiComponent;

/**
 * The state of {@code ProductTable} when it is empty.
 */
public class EmptyProductTableState extends UiComponent<HBox> {

    private static final String FXML = "table/EmptyProductTableState.fxml";

    /**
     * Initializes the empty state of {@code ProductTable}.
     *
     * @param mainWindow The {@code MainWindow} that this component resides on.
     */
    public EmptyProductTableState(MainWindow mainWindow) {
        super(FXML, mainWindow);
    }

    /**
     * Handles the add product button clicked event.
     */
    @FXML
    private void handleAddProductClicked() {
        getMainWindow().showPopupAddProduct();
    }
}
