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
import javafx.scene.text.TextFlow;
import seedu.address.model.person.Log;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";
    private static final Font font = new Font("Segoe UI Semibold", 13);

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
    private TextFlow phone;
    @FXML
    private TextFlow address;
    @FXML
    private TextFlow email;
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

        Text colon1 = new Text(" : ");
        colon1.setFill(Color.WHITE);
        colon1.setFont(font);

        Text colon2 = new Text(" : ");
        colon2.setFill(Color.WHITE);
        colon2.setFont(font);


        Text phoneNumText = new Text(" : " + (person.getPhone().value == null ? "-" : person.getPhone().value));
        Text phoneLabel = new Text("Phone");
        phoneNumText.setFill(Color.WHITE);
        phoneLabel.setFill(Color.WHITE);
        phoneNumText.setFont(font);
        phoneLabel.setFont(font);
        phoneLabel.setUnderline(true);
        phone.getChildren().addAll(phoneLabel, phoneNumText);

        Text addressText = new Text(" : " + (person.getAddress().value == null ? "-" : person.getAddress().value));
        Text addressLabel = new Text("Address");
        addressLabel.setFill(Color.WHITE);
        addressText.setFill(Color.WHITE);
        addressText.setFont(font);
        addressLabel.setFont(font);
        addressLabel.setUnderline(true);
        address.getChildren().addAll(addressLabel, addressText);

        Text emailText = new Text(" : " + (person.getEmail().value == null ? "-" : person.getEmail().value));
        Text emailLabel = new Text("Email");
        emailLabel.setFill(Color.WHITE);
        emailText.setFill(Color.WHITE);
        emailText.setFont(font);
        emailLabel.setFont(font);
        emailLabel.setUnderline(true);
        email.getChildren().addAll(emailLabel, emailText);

        Text tagsText = new Text("Tags");
        tagsText.setFill(Color.WHITE);
        tagsText.setFont(font);
        tagsText.setUnderline(true);
        tags.getChildren().addAll(tagsText, colon1);
        if (person.getTags().size() == 0) {
            Text empty = new Text("-");
            empty.setFill(Color.WHITE);
            empty.setFont(font);
            tags.getChildren().add(empty);
        } else {
            person.getTags().stream()
                    .sorted(Comparator.comparing(tag -> tag.tagName))
                    .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        }
        logs.setHgap(4);
        Text logsText = new Text("Logs");
        logsText.setFill(Color.WHITE);
        logsText.setFont(font);
        logsText.setUnderline(true);
        logs.getChildren().addAll(logsText, colon2);
        if (person.getLogs().size() == 0) {
            Text empty = new Text("-");
            empty.setFill(Color.WHITE);
            empty.setFont(font);
            logs.getChildren().add(empty);
        } else {
            int index = 1;
            for (Log log : person.getLogs()) {
                logs.getChildren().add(new Label(index + ". " + log.getTitle()));
                index++;
            }
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
