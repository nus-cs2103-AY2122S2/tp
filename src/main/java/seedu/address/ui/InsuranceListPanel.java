package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.insurance.Insurance;

/**
 * Panel containing the list of persons.
 */
public class InsuranceListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(InsuranceListPanel.class);

    @FXML
    private ListView<Insurance> personListView;

    /**
     * Creates a {@code InsuranceListPanel} with the given {@code ObservableList}.
     */
    public InsuranceListPanel(ObservableList<Insurance> personList, MainWindow main) {
        super(FXML);
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new InsuranceListViewCell(main));
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Insurance} using a {@code InsuranceCard}.
     */
    class InsuranceListViewCell extends ListCell<Insurance> {
        private MainWindow main;

        InsuranceListViewCell(MainWindow main) {
            super();
            this.main = main;
        }

        @Override
        protected void updateItem(Insurance insurance, boolean empty) {
            super.updateItem(insurance, empty);

            if (empty || insurance == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new InsuranceCard(insurance, getIndex() + 1, main).getRoot());
            }
        }
    }

}
