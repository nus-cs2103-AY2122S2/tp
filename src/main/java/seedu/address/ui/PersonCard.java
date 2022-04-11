package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";
    public final Person person;
    // Credits to flaticon.com for the below two images
    private final Image favouriteImage = new Image(this.getClass().getResourceAsStream("/images/favourite.png"));
    private final Image blacklistImage = new Image(this.getClass().getResourceAsStream("/images/blacklist.png"));
    private final Image placeholderImage = new Image(this.getClass().getResourceAsStream("/images/placeholder.png"));

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

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
    private FlowPane modules;
    @FXML
    private ImageView statusImage;
    @FXML
    private Label comment;

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
        comment.setText(person.getComment().value);

        // Get the image to display
        Image imageToDisplay = getImageToDisplay(person.getStatus().value);
        // Then set the image
        statusImage.setImage(imageToDisplay);

        person.getModules().stream()
                .sorted(Comparator.comparing(module -> module.moduleName))
                .forEach(module -> modules.getChildren().add(new Label(module.moduleName)));
    }

    private Image getImageToDisplay(String statusText) {
        switch (statusText) {
        case "blacklist":
            return blacklistImage;
        case "favourite":
            return favouriteImage;
        default:
            return placeholderImage;
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
