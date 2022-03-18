package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
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
    private StackPane personListPanelPlaceholder;

    /**
     * Sets up Logic instance in FavoriteWindow
     */
    public FavouriteWindow(Logic logic) {
        super(FXML);
        this.logic = logic;
    }

    /**
     * Calls methods to fill in all parts of the window and displays window
     */
    public void show() {
        logger.fine("Showing Favourite Window.");
        fillInnerParts();
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the favourites window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the favourites window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the favourites window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    public PersonListPanel getPersonListPanel() {
        return personListPanel;
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        personListPanel = new PersonListPanel(logic.getFavouritedPersonList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());
    }
}
