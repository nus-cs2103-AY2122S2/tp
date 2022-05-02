package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
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
    private VBox optionalFields;
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
        personId.setText("Client ID #" + person.getUniqueId());
        phone.setText(person.getPhone().getValue());
        address.setText(person.getAddress().getValue());
        email.setText(person.getEmail().getValue());

        // Optional fields.
        person.getFields().stream().filter((Field f) -> !f.prefix.isRequired()).forEach((Field f) -> {
            String value = f.getValue();
            // Do not display blank fields. (e.g. blank remarks)
            if (!value.isBlank()) {
                optionalFields.getChildren().add(new Label(value));
            }
        });

        // Tags.
        person.getTags().stream().sorted(Comparator.comparing(tag -> tag.value))
            .forEach(tag -> tags.getChildren().add(new Label(tag.value)));

        Membership membership = person.getMembership();
        if (membership != null) {
            Label newLabel = new Label(membership.toString().toUpperCase());
            if (membership.getTier() == Membership.Tier.GOLD) {
                newLabel.setId("gold");
            } else if (membership.getTier() == Membership.Tier.SILVER) {
                newLabel.setId("silver");
            } else {
                newLabel.setId("bronze");
            }
            memberships.getChildren().add(newLabel);
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
