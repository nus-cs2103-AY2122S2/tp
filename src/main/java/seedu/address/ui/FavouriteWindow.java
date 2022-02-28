package seedu.address.ui;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;

/**
 * The Favourite Window. Provides the basic application layout containing
 * a menu bar and space to display favourited clients in the application.
 */
public class FavouriteWindow extends UiPart<Stage> {
    private static final String FXML = "FavouriteWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private PersonListPanel personListPanel;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane personListPanelPlaceholder;

    /**
     * Sets up Logic instance in FavoriteWindow
     */
    public FavouriteWindow(Logic logic) {
        super(FXML);
        this.logic = logic;
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(getRoot().getWidth(), getRoot().getHeight(),
                (int) getRoot().getX(), (int) getRoot().getY());
        logic.setGuiSettings(guiSettings);
        getRoot().hide();
    }

    public void show() {
        logger.fine("Showing Favourite Window.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    public PersonListPanel getPersonListPanel() {
        return personListPanel;
    }
}
