package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.insights.PersonInsight;

/**
 * Panel containing the list of PersonInsight's.
 */
public class PersonInsightListPanel extends UiPart<Region> {
    private static final String FXML = "PersonInsightListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonInsightListPanel.class);

    @FXML
    private ListView<PersonInsight> personInsightListView;

    /**
     * Creates a {@code PersonInsightListPanel} with the given {@code ObservableList}.
     */
    public PersonInsightListPanel(ObservableList<PersonInsight> personInsights) {
        super(FXML);
        personInsightListView.setItems(personInsights);
        personInsightListView.setCellFactory(listView -> new PersonInsightListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code PersonInsight} using a {@code PersonInsightCard}.
     */
    private class PersonInsightListViewCell extends ListCell<PersonInsight> {
        @Override
        protected void updateItem(PersonInsight insight, boolean empty) {
            super.updateItem(insight, empty);

            if (empty || insight == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonInsightCard(insight, getIndex() + 1).getRoot());
            }
        }
    }

}
