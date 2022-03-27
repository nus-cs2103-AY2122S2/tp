package seedu.contax.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

/**
 * Creates a List Panel containing the list of type T.
 *
 * @param <T> The type of model that this ListPanel will display.
 */
public class ListPanel <T> extends UiPart<Region> {
    private static final String FXML = "ListPanel.fxml";

    @FXML
    private ListView<T> listView;

    private final CardFactory<T> cardFactory;

    /**
     * Creates a {@code ListPanel} that displays the data in the supplied {@code ObservableList}.
     *
     * @param dataSource The list of models that will be displayed.
     * @param cardFactory A factory for supplying the ListView with {@code RecyclableCards}.
     */
    public ListPanel(ObservableList<T> dataSource, CardFactory<T> cardFactory) {
        super(FXML);
        this.cardFactory = cardFactory;
        listView.setItems(dataSource);
        listView.setCellFactory(listView -> new CustomListCell());
    }

    /**
     * Handles the customization of the look and feel of each cell in the list.
     */
    class CustomListCell extends ListCell<T> {

        private final RecyclableCard<T> cellCard;

        /**
         * Constructs a new instance of {@code CustomListCell}.
         */
        CustomListCell() {
            cellCard = cardFactory.createCard();
        }

        @Override
        protected void updateItem(T model, boolean empty) {
            super.updateItem(model, empty);

            if (empty || model == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(cellCard.updateModel(model, getIndex() + 1));
            }
        }
    }
}
