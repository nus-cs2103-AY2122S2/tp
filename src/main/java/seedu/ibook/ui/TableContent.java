package seedu.ibook.ui;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import seedu.ibook.model.product.Product;
import seedu.ibook.ui.popup.PopupDelete;
import seedu.ibook.ui.popup.PopupUpdate;

public class TableContent extends UiPart<ScrollPane> {

    private static final String FXML = "TableContent.fxml";

    private final ObservableList<Product> filteredIBook;

    private final PopupUpdate popupUpdate;
    private final PopupDelete popupDelete;

    @FXML
    private VBox content;

    TableContent(ObservableList<Product> filteredIBook,
                 PopupUpdate popupUpdate,
                 PopupDelete popupDelete) {
        super(FXML);
        this.filteredIBook = filteredIBook;
        this.filteredIBook.addListener(new Listener());
        this.popupUpdate = popupUpdate;
        this.popupDelete = popupDelete;
        populateField();
    }

    void populateField() {
        content.getChildren().clear();
        for (int i = 0; i < filteredIBook.size(); i++) {
            Product product = filteredIBook.get(i);
            ProductCard productCard = new ProductCard(
                    i + 1, product, popupUpdate, popupDelete);
            content.getChildren().add(productCard.getRoot());
        }
    }

    private class Listener implements ListChangeListener<Product> {
        @Override
        public void onChanged(Change<? extends Product> c) {
            populateField();
        }
    }

}
