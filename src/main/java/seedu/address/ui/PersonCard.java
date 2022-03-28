package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Field;
import seedu.address.model.person.Membership;
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

    public final Person person;

    @FXML
    private HBox cardPane;

    // Required Fields
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label personId;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane optionalFields;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane memberships;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");

        // Required fields.
        name.setText(person.getName().getValue());
        personId.setText("#" + person.getUniqueId());
        phone.setText(person.getPhone().getValue());
        address.setText(person.getAddress().getValue());
        email.setText(person.getEmail().getValue());

        // Optional fields.
        person.getFields().stream().filter((Field f) -> !f.prefix.isRequired()).forEach((Field f) -> {
            optionalFields.getChildren().add(new Label(f.getValue()));
        });

        // Tags.
        person.getTags().stream().sorted(Comparator.comparing(tag -> tag.value))
            .forEach(tag -> tags.getChildren().add(new Label(tag.value)));

        /*
        // Memberships.
        person.getMemberships().getList().stream()
                .sorted(Comparator.comparing(membership -> membership.toString()))
                .forEach(membership -> memberships.getChildren().add(new Label(membership.toString())));

         */

        Membership membership = person.getMembership();
        if (membership != null) {
            memberships.getChildren().add(new Label(membership.toString()));
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
        return id.getText().equals(card.id.getText()) && person.equals(card.person);
    }
}
