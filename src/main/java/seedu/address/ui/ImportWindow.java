package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

import java.util.logging.Logger;

public class ImportWindow extends UiPart<Stage>{

    private static final String FXML = "ImportWindow.fxml";
    private static final Logger logger = LogsCenter.getLogger(ImportWindow.class);

    @FXML
    private Label importFile;

    public ImportWindow(Stage root) {
        super(FXML, root);
    }

    public ImportWindow() {
        this(new Stage());
    }


    public void show() {
        logger.fine("Showing email list page.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    public boolean isShowing() {
        return getRoot().isShowing();
    }

    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the email window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}
