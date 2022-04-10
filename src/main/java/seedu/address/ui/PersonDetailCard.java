package seedu.address.ui;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.model.person.Person;
import seedu.address.model.record.Record;

/**
 * A UI component that displays information of a {@code Person}.
 */
public class PersonDetailCard extends UiPart<Region> {

    private static final String FXML = "PersonDetailCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;
    private RecordUnderPersonPanel recordUnderPersonPanel;

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
    private Label record;
    @FXML
    private FlowPane tags;
    @FXML
    private StackPane recordListPanelPlaceholder;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonDetailCard(Person person, ObservableList<Record> records, int displayedIndex, MainWindow main) {
        super(FXML);


        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);

        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));


        List<Record> newRecords = new ArrayList<>();
        String clientName = person.getName().fullName.toString();
        for (int i = 0; i < records.size(); i++) {
            String recordName = records.get(i).getClientID().toString();

            if (recordName.equals(clientName)) {
                newRecords.add(records.get(i));
            }
        }

        ObservableList<Record> observableList = FXCollections.observableList(newRecords);
        recordUnderPersonPanel = new RecordUnderPersonPanel(observableList);
        recordListPanelPlaceholder.getChildren().add(recordUnderPersonPanel.getRoot());
        record.setText("Insurance:");


    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonDetailCard)) {
            return false;
        }

        // state check
        PersonDetailCard card = (PersonDetailCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}
