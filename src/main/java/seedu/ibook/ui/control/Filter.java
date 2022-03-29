package seedu.ibook.ui.control;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import seedu.ibook.model.product.filters.AttributeFilter;
import seedu.ibook.ui.MainWindow;
import seedu.ibook.ui.UiComponent;

public class Filter extends UiComponent<HBox> {

    private static final String FXML = "control/Filter.fxml";

    private final AttributeFilter filter;

    @FXML
    private Label type;

    @FXML
    private Label value;

    /**
     * Initializes a {@code Filter}.
     *
     * @param filter The {@code AttributeFilter}.
     * @param mainWindow The Main Window that this component resides on.
     */
    Filter(AttributeFilter filter, MainWindow mainWindow) {
        super(FXML, mainWindow);
        this.filter = filter;
        populateFields();
    }

    @FXML
    private void populateFields() {
        type.setText(filter.getType().toUpperCase());
        value.setText(filter.getValue());
    }

    @FXML
    private void handleRemove() {
        getMainWindow().removeProductFilter(filter);
    }
}
