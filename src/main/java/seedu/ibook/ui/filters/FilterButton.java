package seedu.ibook.ui.filters;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import seedu.ibook.model.product.filters.AttributeFilter;
import seedu.ibook.ui.MainWindow;
import seedu.ibook.ui.UiComponent;

public class FilterButton extends UiComponent<HBox> {
    private static final String FXML = "Filters/FilterButton.fxml";

    private final AttributeFilter filter;

    @FXML
    private Label type;
    @FXML
    private Label value;

    /**
     * Initializes a {@code FilterButton}.
     *
     * @param filter The {@code AttributeFilter}.
     * @param mainWindow The Main Window that this component resides on.
     */
    FilterButton(AttributeFilter filter, MainWindow mainWindow) {
        super(FXML, mainWindow);
        this.filter = filter;
        populateFields();
    }

    @FXML
    private void populateFields() {
        type.setText(filter.getType());
        value.setText(filter.getValue());
    }

    @FXML
    private void handleRemove() {
        getMainWindow().removeProductFilter(filter);
    }
}
