package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;
import seedu.address.model.person.insights.PersonInsight;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonInsightCard extends UiPart<Region> {

    private static final String FXML = "PersonInsightCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    private final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label numLogs;
    @FXML
    private Label numEvents;
    @FXML
    private Label lastEvent;


    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonInsightCard(PersonInsight personInsight, int displayedIndex) {
        super(FXML);
        this.person = personInsight.getPerson();
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        numLogs.setText(personInsight.getNumLogsInsightAsString());
        numEvents.setText(personInsight.getNumEventsInsightAsString());
        lastEvent.setText(personInsight.getLastEventInsightAsString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonInsightCard)) {
            return false;
        }

        // state check
        PersonInsightCard card = (PersonInsightCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}
