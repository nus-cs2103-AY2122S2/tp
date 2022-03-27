package seedu.address.ui.general;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.commons.core.index.Index;
import seedu.address.model.person.Person;
import seedu.address.ui.UiManager;
import seedu.address.ui.UiPart;

public class Profile extends UiPart<Region> {

    private static final String FXML = "Profile.fxml";
    private Index index;

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
        this.index = Index.fromZeroBased(UiManager.getMainWindow().getPersonListPanel()
                .getPersonListView().getItems().indexOf(person));

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

    public Index getIndex() {
        return this.index;
    }
}
