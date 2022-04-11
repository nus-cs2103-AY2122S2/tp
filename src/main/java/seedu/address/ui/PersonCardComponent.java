package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCardComponent extends UiPart<Region> {

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
    private FlowPane educations;
    @FXML
    private FlowPane internships;
    @FXML
    private FlowPane modules;
    @FXML
    private FlowPane ccas;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCardComponent(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);

        person.getEducations().stream()
                .sorted(Comparator.comparing(edu -> edu.tagName))
                .forEach(edu -> educations.getChildren().add(new Label(edu.tagName)));
        person.getInternships().stream()
                .sorted(Comparator.comparing(intern -> intern.tagName))
                .forEach(intern -> internships.getChildren().add(new Label(intern.tagName)));
        person.getModules().stream()
                .sorted(Comparator.comparing(module -> module.tagName))
                .forEach(module -> modules.getChildren().add(new Label(module.tagName)));
        person.getCcas().stream()
                .sorted(Comparator.comparing(cca -> cca.tagName))
                .forEach(cca -> ccas.getChildren().add(new Label(cca.tagName)));

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCardComponent)) {
            return false;
        }

        // state check
        PersonCardComponent card = (PersonCardComponent) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}
