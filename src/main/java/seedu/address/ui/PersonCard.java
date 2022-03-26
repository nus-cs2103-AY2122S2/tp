package seedu.address.ui;

import java.util.StringJoiner;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;
import seedu.address.model.property.Property;

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
    private Label property;
    @FXML
    private Label preference;
    @FXML
    private Label favourite;
    @FXML
    private Label buyer;
    @FXML
    private Label seller;

    /**
     * Creates a {@code PersonCard} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        favourite.setText(person.getFavourite().toString());
        // create the buyer/seller label, depending on the user's type
        if (person.getUserType().isBuyer()) {
            buyer.setText(person.getUserType().value);
            seller.setVisible(false);
            seller.setManaged(false);
        } else {
            seller.setText(person.getUserType().value);
            buyer.setVisible(false);
            buyer.setManaged(false);
        }

        if (!person.getFavourite().isUnfavourited()) {
            favourite.setText(person.getFavourite().toString());
        } else {
            favourite.setManaged(false);
        }

        if (person.getProperties().isEmpty()) {
            property.setManaged(false);
        } else {
            StringJoiner propertyJoiner = new StringJoiner("\n");
            person.getProperties().stream().map(Property::toString).forEach(propertyJoiner::add);
            property.setText(propertyJoiner.toString());
        }

        if (person.getPreference().isPresent()) {
            preference.setText(person.getPreference().get().toString());
        } else {
            preference.setManaged(false);
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
