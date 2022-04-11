package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;

/**
 * The Favourite Window. Provides the basic application layout containing
 * a menu bar and space to display favourited clients in the application.
 */
public class SingleColumnPersonListWindow extends UiPart<Stage> {

    private static final String FXML = "SingleColumnPersonListWindow.fxml";

    @FXML
    protected Label reminderStatus;
    @FXML
    protected StackPane personListPanelPlaceholder;
    @FXML
    protected Stage stage;

    // Independent Ui parts residing in this Ui container
    protected PersonListPanel personListPanel;

    protected Logic logic;

    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Sets up Logic instance in FavoriteWindow
     */
    public SingleColumnPersonListWindow(Logic logic) {
        super(FXML);
        this.logic = logic;
        setTitle("Window");
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
     * Sets the title of this Window.
     */
    protected void setTitle(String title) {
        stage.setTitle(title);
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        logger.fine("The person panel has data of favourited clients now!");
        reminderStatus.setVisible(false);
        reminderStatus.setManaged(false);
        personListPanel = new PersonListPanel(logic.getFavouritedPersonList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());
    }
}
