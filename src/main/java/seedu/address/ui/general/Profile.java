package seedu.address.ui.general;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import seedu.address.commons.core.index.Index;
import seedu.address.model.person.Person;
import seedu.address.ui.UiManager;
import seedu.address.ui.UiPart;

public class Profile extends UiPart<Region> {

    private static final String FXML = "Profile.fxml";
    private Index index;
    private Person person;

    @FXML
    private Text name;
    @FXML
    private Text phone;
    @FXML
    private Text address;
    @FXML
    private Text email;
    @FXML
    private Text telegram;
    @FXML
    private Text matriculation;
    @FXML
    private Text course;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code Profile}.
     */
    public Profile() {
        super(FXML);
    }

    public void setPerson(Person person) {
        this.index = Index.fromZeroBased(UiManager.getMainWindow().getPersonListPanel()
                .getPersonListView().getItems().indexOf(person));
        this.person = person;
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        email.setText(person.getEmail().value);
        telegram.setText(person.getTelegram().id);
        matriculation.setText(person.getMatricCard().value);
        course.setText(person.getCourse().value);
        address.setText(person.getAddress().value);
        tags.getChildren().clear();
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    public Person getPerson() {
        return this.person;
    }

    public Index getIndex() {
        return this.index;
    }
}
