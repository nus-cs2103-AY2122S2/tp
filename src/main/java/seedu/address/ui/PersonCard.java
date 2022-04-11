package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;
import seedu.address.model.person.Status;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label status;
    @FXML
    private Label classCode;
    @FXML
    private FlowPane activities;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        status.setText(person.getStatus().value);
        if (person.getStatus().value.equals(Status.POSITIVE)) {
            status.setStyle("-fx-text-fill: #FF0000 !important; -fx-label-padding: 20; "
                    + "-fx-border-color: #FF0000 !important; -fx-border-width: 4; -fx-border-radius: 7");
        } else if (person.getStatus().value.equals(Status.NEGATIVE)) {
            status.setStyle("-fx-text-fill: #00FF00 !important; -fx-label-padding: 20; "
                    + "-fx-border-color: #00FF00 !important; -fx-border-width: 4; -fx-border-radius: 7");
        } else if (person.getStatus().value.equals(Status.CLOSE_CONTACT)) {
            status.setStyle("-fx-text-fill: orange !important; -fx-label-padding: 20; "
                    + "-fx-border-color: orange !important; -fx-border-width: 4; -fx-border-radius: 7");
        }
        classCode.setText(person.getClassCode().value);
        person.getActivities().stream()
                .sorted(Comparator.comparing(activity -> activity.activityName))
                .forEach(activity -> activities.getChildren().add(new Label(activity.activityName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        PersonCard card = (PersonCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}
