package seedu.address.ui;

import seedu.address.logic.Logic;

/**
 * The Favourite Window. Provides the basic application layout containing
 * a menu bar and space to display favourited clients in the application.
 */
public class FavouriteWindow extends SingleColumnPersonListWindow {

    /**
     * Sets up Logic instance in FavoriteWindow
     */
    public FavouriteWindow(Logic logic) {
        super(logic);
    }

    /**
     * Fills up all the placeholders of this window.
     */
    @Override
    void fillInnerParts() {
        reminderStatus.setVisible(false);
        reminderStatus.setManaged(false);
        personListPanel = new PersonListPanel(super.logic.getFavouritedPersonList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());
        setTitle("Favourites");
    }
}
