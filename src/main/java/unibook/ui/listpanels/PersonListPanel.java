package unibook.ui.listpanels;

import static unibook.ui.util.CustomListChangeListeners.addIndexedListChangeListener;
import static unibook.ui.util.CustomPaneListFiller.fillPaneFromList;

import java.util.function.BiFunction;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import unibook.commons.core.LogsCenter;
import unibook.model.module.Module;
import unibook.model.person.Person;
import unibook.model.person.Professor;
import unibook.model.person.Student;
import unibook.model.person.exceptions.PersonNoSubtypeException;
import unibook.ui.MainWindow;
import unibook.ui.UiPart;
import unibook.ui.cards.ModuleAndGroupMiniCard;
import unibook.ui.cards.ProfessorCard;
import unibook.ui.cards.StudentCard;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "listpanels/PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);
    private ObservableList<Person> personList;
    private ObservableList<Module> moduleList;
    private MainWindow mainWindow;

    @FXML
    private VBox personListView;
    @FXML
    private VBox moduleAndGroupListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Person> personList, ObservableList<Module> moduleList,
                           MainWindow mainWindow) {
        super(FXML);
        logger.info("Instantiating person list");
        this.mainWindow = mainWindow;
        this.personList = personList;
        this.moduleList = moduleList;

        //functon for creating a person card
        BiFunction<Person, Integer, Node> cardCreator = new BiFunction<Person, Integer, Node>() {
            @Override
            public Node apply(Person person, Integer index) {
                if (person instanceof Student) {
                    return new StudentCard((Student) person, index + 1).getRoot();
                } else if (person instanceof Professor) {
                    return new ProfessorCard((Professor) person, index + 1).getRoot();
                } else {
                    //Since a person must always be a Student or Professor, this
                    //should never run
                    throw new PersonNoSubtypeException();
                }
            }
        };


        //set up the basic vbox list
        fillPaneFromList(personListView, this.personList, cardCreator);

        //set up listener for changes to underlying personlist
        addIndexedListChangeListener(personListView, this.personList, cardCreator);

        //fill in the moduleandgroup pane
        fillPaneFromList(moduleAndGroupListView, this.moduleList, (module, index) ->
                new ModuleAndGroupMiniCard(module, index, mainWindow).getRoot());

        //set up list event listener pane
        addIndexedListChangeListener(moduleAndGroupListView, this.moduleList, (module, index) ->
                new ModuleAndGroupMiniCard(module, index, mainWindow).getRoot());
    }
}
