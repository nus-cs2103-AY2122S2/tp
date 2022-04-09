package seedu.address.ui;

import java.util.logging.Logger;

import javafx.beans.value.ObservableObjectValue;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Panel containing {@code ContactDetail} of a {@Person}.
 */
public class ContactDetailPanel extends UiPart<Region> {
    private static final String FXML = "ContactDetailPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ContactDetailPanel.class);
    private final ObservableObjectValue<Person> observableSelectedPerson;

    @FXML
    private ScrollPane panel;

    @FXML
    private VBox emptyRhsPanelPlaceHolder;

    /**
     * Creates a {@code RightHandSidePanel} with the given {@code ObservableList}.
     */
    public ContactDetailPanel(ObservableObjectValue<Person> observableObjectValue) {
        super(FXML);
        observableSelectedPerson = observableObjectValue;
        observableSelectedPerson.addListener(this::handleSelectionChange);
    }

    private void handleSelectionChange(ObservableValue<? extends Person> ov, Person oldPerson, Person newPerson) {
        if (newPerson != null) {
            ContactDetailCard contactDetailCard = new ContactDetailCard(newPerson);
            setPanel(contactDetailCard.getRoot());
        } else {
            setPanel(null);
        }
    }

    public void setPanel(Node childPanel) {
        if (childPanel != null) {
            logger.info(String.format("Setting RHS panel to: %s", childPanel));
            panel.setContent(childPanel);
        } else {
            logger.info("Clearing RHS panel");
            panel.setContent(null);
        }
    }
}
