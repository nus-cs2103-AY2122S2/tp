package seedu.ibook.ui.filters;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import seedu.ibook.model.product.filters.AttributeFilter;
import seedu.ibook.ui.MainWindow;
import seedu.ibook.ui.UiComponent;

public class FilterList extends UiComponent<HBox> {

    private static final String FXML = "Filters/FilterList.fxml";

    @FXML
    private HBox filters;

    /**
     * Initializes a {@code FilterList}.
     *
     * @param mainWindow The Main Window that this component resides on.
     */
    public FilterList(MainWindow mainWindow) {
        super(FXML, mainWindow);
        populateFilters();
    }

    /**
     * Populate the filters used for the product list.
     */
    public void populateFilters() {
        ObservableList<AttributeFilter> filterList = getMainWindow().getProductFilters();
        filters.getChildren().clear();
        for (AttributeFilter filter: filterList) {
            FilterButton filterButton = new FilterButton(filter, getMainWindow());
            filters.getChildren().add(filterButton.getRoot());
        }
        if (filterList.size() > 0) {
            Button clearButton = new Button("Clear all");
            clearButton.setOnAction((event) -> getMainWindow().clearProductFilters());
            filters.getChildren().add(clearButton);
        }
    }
}
