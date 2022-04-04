package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import seedu.address.model.person.Log;
import seedu.address.model.person.Person;

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

    private final Person person;

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
    private FlowPane tags;
    @FXML
    private FlowPane logs;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        String phoneNumText = person.getPhone().value == null ? "" : person.getPhone().value;
        phoneNumText = "Phone number: " + phoneNumText;
        phone.setText(phoneNumText);
        String addressText = person.getAddress().value == null ? "" : person.getAddress().value;
        addressText = "Address :";
        address.setText(addressText);
        String emailText = person.getEmail().value == null ? "" : person.getEmail().value;
        emailText = "Email: " + emailText;
        email.setText(emailText);
        Text tagsText = new Text("Tags: ");
        tagsText.setFill(Color.WHITE);
        tagsText.setFont(new Font("Segoe UI", 13));
        tags.getChildren().add(tagsText);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        logs.setHgap(4);
        Text logsText = new Text("Logs: ");
        logsText.setFill(Color.WHITE);
        logsText.setFont(new Font("Segoe UI", 13));
        logs.getChildren().add(logsText);
        int index = 1;
        for (Log log: person.getLogs()) {
            logs.getChildren().add(new Label(index + ". " + log.getTitle()));
            index++;
        }
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
