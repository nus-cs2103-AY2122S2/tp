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
    private Label telegram;
    @FXML
    private Label matriculation;
    @FXML
    private Label course;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code Profile}.
     */
    public Profile(Person person) {
        super(FXML);
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        email.setText(person.getEmail().value);
        telegram.setText(person.getTelegram().id);
        matriculation.setText(person.getMatricCard().value);
        course.setText(person.getCourse().value);
        address.setText(person.getAddress().value);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }
}
