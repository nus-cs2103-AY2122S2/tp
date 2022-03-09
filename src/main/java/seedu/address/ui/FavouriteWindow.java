package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.person.Person;

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

    public void show() {
        logger.fine("Showing Favourite Window.");
        fillInnerParts();
        getRoot().show();
        getRoot().centerOnScreen();
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
