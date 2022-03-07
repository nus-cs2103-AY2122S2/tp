package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class Table extends UiPart<VBox> {

    private static final String FXML = "Table.fxml";

    Table() {
        super(FXML);
        populateField();
    }

    void populateField() {
        ObservableList<Node> children = getRoot().getChildren();
        children.add(new TableHeader().getRoot());
        children.add(new TableContent().getRoot());
    }
}
