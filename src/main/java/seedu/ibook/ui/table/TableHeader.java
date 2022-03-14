package seedu.ibook.ui.table;

import javafx.scene.layout.HBox;
import seedu.ibook.ui.UiPart;

/**
 * Header of the Table
 */
public class TableHeader extends UiPart<HBox> {

    private static final String FXML = "Table/TableHeader.fxml";

    /**
     * Initializes a {@code TableHeader}.
     */
    TableHeader() {
        super(FXML);
    }
}
