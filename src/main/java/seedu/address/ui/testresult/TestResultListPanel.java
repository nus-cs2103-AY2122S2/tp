package seedu.address.ui.testresult;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.testresult.TestResult;
import seedu.address.ui.UiPart;

/**
 * Panel containing the list of persons.
 */
public class TestResultListPanel extends UiPart<Region> {
    private static final String SCREEN_TITLE = "Tests";
    private static final String FXML = "testresult/TestResultListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TestResultListPanel.class);

    @javafx.fxml.FXML
    private ListView<TestResult> testResultListView;

    @javafx.fxml.FXML
    private Label screenTitle;
    /**
     * Creates a {@code TestResultListPanel} with the given {@code ObservableList}.
     */
    public TestResultListPanel(ObservableList<TestResult> testResultList) {
        super(FXML);
        screenTitle.setText(SCREEN_TITLE);
        testResultListView.setItems(testResultList);
        testResultListView.setCellFactory(listView -> new TestResultListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code TestResult} using a {@code TestResultCard}.
     */
    class TestResultListViewCell extends ListCell<TestResult> {
        @Override
        protected void updateItem(TestResult testResult, boolean empty) {
            super.updateItem(testResult, empty);

            if (empty || testResult == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TestResultCard(testResult, getIndex() + 1).getRoot());
            }
        }
    }

}
