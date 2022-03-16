package seedu.ibook.ui.table;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import seedu.ibook.ui.MainWindow;
import seedu.ibook.ui.UiComponent;

/**
 * The table that is containing {@code TableContent} and {@code TableHeader}
 */
public class Table extends UiComponent<VBox> {

    private static final String FXML = "Table/Table.fxml";

    private TableHeader tableHeader;
    private TableContent tableContent;

    /**
     * Initializes a {@code Table}.
     *
     * @param mainWindow The {@code MainWindow} that this component resides on.
     */
    public Table(MainWindow mainWindow) {
        super(FXML, mainWindow);
        populateField();
    }

    private void populateField() {
        ObservableList<Node> children = getRoot().getChildren();

        tableHeader = new TableHeader(getMainWindow());
        children.add(tableHeader.getRoot());

        tableContent = new TableContent(getMainWindow());
        children.add(tableContent.getRoot());
    }
}
