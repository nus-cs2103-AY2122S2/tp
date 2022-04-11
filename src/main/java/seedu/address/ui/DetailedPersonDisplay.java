package seedu.address.ui;

import java.util.Comparator;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.model.person.Person;

/**
 * A UI for the display of person details on the right.
 */
public class DetailedPersonDisplay extends UiPart<Region> {
    public static final String FXML = "DetailedPersonDisplay.fxml";
    public static final String DEFAULT_EMPTY_MESSAGE = "-";

    @FXML
    private StackPane personDisplayPane;

    @FXML
    private Label nameLabel;

    @FXML
    private Label phoneLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private Label lastContactLabel;

    @FXML
    private Label memoLabel;

    @FXML
    private FlowPane tagsPane;

    public DetailedPersonDisplay() {
        super(FXML);
    }

    public void setPersonDisplay(Person person) {
        if (person != null) {
            String newName = person.getName().toString();
            nameLabel.setText(newName);

            String newPhone = person.getPhone().toString();
            phoneLabel.setText(newPhone);

            String newEmail = person.getEmail().toString();
            emailLabel.setText(newEmail);

            String newAddress = person.getAddress().toString();
            addressLabel.setText(newAddress);

            String newLastContact = person.getContactedDate().toString();
            lastContactLabel.setText(newLastContact);

            String newMemo = DEFAULT_EMPTY_MESSAGE;
            if (!person.isMemoEmpty()) {
                newMemo = person.getMemo().toString();
            }
            memoLabel.setText(newMemo);

            tagsPane.getChildren().clear();
            person.getTags().stream()
                    .sorted(Comparator.comparing(tag -> tag.tagName.toLowerCase()))
                    .forEach(tag -> tagsPane.getChildren().add(new Label(tag.toString())));
        } else {
            nameLabel.setText(DEFAULT_EMPTY_MESSAGE);
            phoneLabel.setText(DEFAULT_EMPTY_MESSAGE);
            emailLabel.setText(DEFAULT_EMPTY_MESSAGE);
            addressLabel.setText(DEFAULT_EMPTY_MESSAGE);
            memoLabel.setText(DEFAULT_EMPTY_MESSAGE);
            lastContactLabel.setText(DEFAULT_EMPTY_MESSAGE);
            tagsPane.getChildren().clear();
        }
    }

    /**
     * Returns a listener that updates the UI whenever the Person object on display is modified.
     *
     * @return a ChangeListener that reacts to any edits of a Person object.
     */
    public ChangeListener<? super Person> getListener() {
        return (ChangeListener<Person>) (observable, oldPerson, newPerson) -> setPersonDisplay(newPerson);
    }
}
