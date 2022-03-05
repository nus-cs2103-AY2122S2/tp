package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

public class PersonInfoPanel extends UiPart<Region> {
    private static final String FXML = "PersonInfoPanel.fxml";

    public final Person person;

    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label address;
    @FXML
    private FlowPane tags;
    @FXML
    private ListView<Person> assignedLessonList;

    /**
     * Creates a {@code PersonInfoPanel} filled with information with the given details of {@code Person}
     *
     * @param person Fills the {@code PersonInfoPanel} with the given details of {@code Person}
     */
    public PersonInfoPanel(Person person) {
        super(FXML);
        this.person = person;
        setPersonDetails(person);
    }

    private void setPersonDetails(Person person) {
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        // TODO: Add function to list assigned classes after implementing assigned lesson list in Person
    }
}
