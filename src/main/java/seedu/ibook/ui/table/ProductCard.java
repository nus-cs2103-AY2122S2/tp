package seedu.ibook.ui.table;

import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import seedu.ibook.model.product.Product;
import seedu.ibook.ui.MainWindow;
import seedu.ibook.ui.UiComponent;
import seedu.ibook.ui.table.item.ItemTable;

/**
 * A card showing the details of a {@code Product}.
 */
public class ProductCard extends UiComponent<HBox> {

    private static final String FXML = "table/ProductCard.fxml";

    private final int index;
    private final Product product;

    private final EventExpand eventExpand = new EventExpand();

    private boolean isShowingItems;

    private RotateTransition rotateOpen;
    private RotateTransition rotateClose;

    @FXML
    private Button expandButton;
    @FXML
    private Label indexLabel;
    @FXML
    private Label name;
    @FXML
    private Label category;
    @FXML
    private Label price;
    @FXML
    private Label description;

    @FXML
    private VBox itemTableContainer;

    /**
     * Initializes a {@code ProductCard}.
     *
     * @param index Index of the {@code Product} in the filtered list.
     * @param product The {@code Product}.
     * @param mainWindow The Main Window that this component resides on.
     */
    ProductCard(int index, Product product, MainWindow mainWindow) {
        super(FXML, mainWindow);
        this.index = index;
        this.product = product;
        this.isShowingItems = false;
        initRotate();
        populateField();
    }

    private void initRotate() {
        Duration time = Duration.millis(150);

        rotateOpen = new RotateTransition(time, expandButton);
        rotateOpen.setByAngle(90);
        rotateOpen.setCycleCount(1);
        rotateOpen.setOnFinished(eventExpand);

        rotateClose = new RotateTransition(time, expandButton);
        rotateClose.setByAngle(-90);
        rotateClose.setCycleCount(1);
        rotateClose.setOnFinished(eventExpand);
    }

    private void populateField() {
        indexLabel.setText(String.valueOf(index));
        name.setText(product.getName().toString());
        category.setText(product.getCategory().toString());
        price.setText(product.getPrice().toString());
        description.setText(product.getDescription().toString());
    }

    @FXML
    private void handleExpand() {
        if (isShowingItems) {
            isShowingItems = false;
            rotateClose.play();
        } else {
            isShowingItems = true;
            rotateOpen.play();
        }
    }

    @FXML
    private void handlePopupUpdateProduct() {
        getMainWindow().showPopupUpdateProduct(index, product);
    }

    @FXML
    private void handlePopupDeleteProduct() {
        getMainWindow().showPopupDeleteProduct(index, product);
    }

    @FXML
    private void handlePopupAddItem() {
        getMainWindow().showPopupAddItem(index, product);
    }

    private class EventExpand implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            if (isShowingItems) {
                ItemTable itemTable = new ItemTable(getMainWindow(), product, index);
                itemTableContainer.getChildren().add(itemTable.getRoot());
            } else {
                itemTableContainer.getChildren().clear();
            }
        }
    }

}
