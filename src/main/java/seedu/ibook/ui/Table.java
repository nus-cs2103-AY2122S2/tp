package seedu.ibook.ui;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import seedu.ibook.model.product.Product;
import seedu.ibook.ui.popup.PopupDelete;
import seedu.ibook.ui.popup.PopupUpdate;

public class Table extends UiPart<VBox> {

    private static final String FXML = "Table.fxml";

    private TableHeader tableHeader;
    private TableContent tableContent;

    private final PopupUpdate popupUpdate;
    private final PopupDelete popupDelete;

    private final ObservableList<Product> filteredIBook;

    Table(ObservableList<Product> filteredIBook,
          PopupUpdate popupUpdate,
          PopupDelete popupDelete) {
        super(FXML);
        this.filteredIBook = filteredIBook;
        this.popupUpdate = popupUpdate;
        this.popupDelete = popupDelete;
        populateField();
    }

    void populateField() {
        ObservableList<Node> children = getRoot().getChildren();

        tableHeader = new TableHeader();
        children.add(tableHeader.getRoot());

        tableContent = new TableContent(filteredIBook, popupUpdate, popupDelete);
        children.add(tableContent.getRoot());
    }
}
