package unibook.ui.listpanels;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import unibook.commons.core.LogsCenter;
import unibook.model.person.Person;
import unibook.model.person.Professor;
import unibook.model.person.Student;
import unibook.model.person.exceptions.PersonNoSubtypeException;
import unibook.ui.UiPart;
import unibook.ui.cards.PersonCard;
import unibook.ui.cards.ProfessorCard;
import unibook.ui.cards.StudentCard;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "listpanels/PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Person> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Person> personList) {
        super(FXML);
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                if (person instanceof Student) {
                    setGraphic(new StudentCard((Student) person, getIndex() + 1).getRoot());
                } else if (person instanceof Professor) {
                    setGraphic(new ProfessorCard((Professor) person, getIndex() + 1).getRoot());
                } else {
                    //This else condition should be impossible to execute
                    throw new PersonNoSubtypeException();
                }
            }
        }
    }

}
