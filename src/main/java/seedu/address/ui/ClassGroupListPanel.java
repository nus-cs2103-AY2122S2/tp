package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.ClassGroup;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class ClassGroupListPanel {
    private static final String FXML = "ClassListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ClassGroupListPanel.class);

    @FXML
    private ListView<ClassGroup> classGroupListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    // public ClassGroupListPanel(ObservableList<ClassGroup> classGroupList) {
    //     super(FXML);
    //     classGroupListView.setItems(classGroupList);
    //     classGroupListView.setCellFactory(listView -> new ClassGroupListViewCell());
    // }

    public ClassGroupListPanel() {
        
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class ClassGroupListViewCell extends ListCell<ClassGroup> {
        @Override
        protected void updateItem(ClassGroup classGroup, boolean empty) {
            super.updateItem(classGroup, empty);

            if (empty || classGroup == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ClassGroupCard(classGroup, getIndex() + 1).getRoot());
            }
        }
    }

}
