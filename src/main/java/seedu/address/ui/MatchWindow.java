package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Pair;
import seedu.address.logic.Logic;
import seedu.address.model.person.Person;

public class MatchWindow extends UiPart<Stage> {

    private static final String FXML = "MatchWindow.fxml";
    private static final Logger logger = LogsCenter.getLogger(MatchWindow.class);

    private Logic logic;

    @FXML
    private ListView<Pair<Person>> matchListView;

    /**
     * Creates a new MatchWindow.
     *
     * @param root Stage to use as the root of the MatchWindow.
     */
    public MatchWindow(Logic logic, Stage root) {
        super(FXML, root);
        this.logic = logic;
    }

    /**
     * Creates a new HelpWindow.
     */
    public MatchWindow(Logic logic) {
        this(logic, new Stage());
    }

    /**
     * Shows the match window.
     */
    public void show() {
        logger.fine("Showing match page about the application.");
        matchListView.setItems(FXCollections.observableArrayList(logic.getMatchList()));
        matchListView.setCellFactory(listView -> new MatchWindow.MatchListViewCell());
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class MatchListViewCell extends ListCell<Pair<Person>> {
        @Override
        protected void updateItem(Pair<Person> match, boolean empty) {
            super.updateItem(match, empty);

            if (empty || match == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new DoublePersonCard(match.getFirst(), match.getSecond()).getRoot());
            }
        }
    }

}
