package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

public class Profile extends UiPart<Region> {

    private static final String FXML = "Profile.fxml";

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

    /**
     * Creates a {@code Profile}.
     */
    public Profile() {
        super(FXML);
    }

    /**
     * Set all the profile elements with the given person.
     *
     * @param person the person of the selected person card
     */
    public void setProfile(Person person) {
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    /**
     * Remove the tags displayed in the previous profile, since FlowPane elements seems to retain even when
     * the profile changes.
     *
     */
    public void clearPreviousTags() {
        tags.getChildren().clear();
    }
}
