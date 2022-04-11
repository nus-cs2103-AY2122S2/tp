package seedu.ibook.ui.control;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import seedu.ibook.model.product.filters.AttributeFilter;
import seedu.ibook.ui.MainWindow;
import seedu.ibook.ui.UiComponent;

public class ControlBox extends UiComponent<HBox> {

    private static final String FXML = "control/ControlBox.fxml";

    @FXML
    private FlowPane filterBox;

    private final ArrayList<Filter> filters = new ArrayList<>();

    /**
     * Creates a {@code ControlBox}.
     *
     * @param mainWindow The {@code MainWindow} that this component resides on.
     */
    public ControlBox(MainWindow mainWindow) {
        super(FXML, mainWindow);
    }

    /**
     * Populate the filters used for the product list.
     */
    public void populateFilters() {
        ObservableList<AttributeFilter> filterList = getMainWindow().getProductFilters();
        filterBox.getChildren().clear();
        filters.clear();
        for (AttributeFilter filter: filterList) {
            Filter filterButton = new Filter(filter, getMainWindow());
            filterBox.getChildren().add(filterButton.getRoot());
            filters.add(filterButton);
        }
    }

    /**
     * Handles the add filter button clicked event.
     */
    @FXML
    private void handleAddFiltersProductClicked() {
        getMainWindow().showPopupAddProduct();
    }

    /**
     * Handles the add product button clicked event.
     */
    @FXML
    private void handleAddProductClicked() {
        getMainWindow().showPopupAddProduct();
    }
}
