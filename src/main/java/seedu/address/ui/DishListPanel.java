package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.dish.Dish;

/**
 * Panel containing the list of dishes.
 */
public class DishListPanel extends ListPanel {
    private static final String FXML = "DishListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(DishListPanel.class);

    @FXML
    private ListView<Dish> dishListView;

    /**
     * Creates a {@code DishListPanel} with the given {@code ObservableList}.
     */
    public DishListPanel(ObservableList<Dish> dishList) {
        super(FXML);
        dishListView.setItems(dishList);
        dishListView.setCellFactory(listView -> new DishListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Dish} using a {@code DishCard}.
     */
    class DishListViewCell extends ListCell<Dish> {
        @Override
        protected void updateItem(Dish dish, boolean empty) {
            super.updateItem(dish, empty);

            if (empty || dish == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new DishCard(dish, getIndex() + 1).getRoot());
            }
        }
    }

}

