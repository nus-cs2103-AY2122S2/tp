package seedu.address.ui;

import java.util.Comparator;

import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.MainApp;
import seedu.address.model.person.GithubUsername;
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
    @FXML
    private VBox personCard;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Hyperlink githubUsername;
    @FXML
    private Label email;
    @FXML
    private FlowPane teams;
    @FXML
    private FlowPane skillSet;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        if (person.isPotentialTeammate()) {
            // Add highlight class to potential teammates
            personCard.getStyleClass().add("highlight");
        }
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        email.setText(person.getEmail().value);

        GithubUsername username = person.getGithubUsername();
        githubUsername.setText(username.getGithubHandle());
        HostServices hs = MainApp.getHS();
        githubUsername.setOnAction(e -> {
            hs.showDocument(username.getGithubProfileLink());
        });

        person.getTeams().stream()
            .sorted(Comparator.comparing(team -> team.teamName))
            .forEach(team -> teams.getChildren().add(new Label(team.teamName)));
        person.getSkillSet().getSkillSetInStream()
            .sorted(Comparator.comparing(skill -> skill.skillName))
            .forEach(skill -> {
                Label skillLabel = new Label(skill.skillName);
                if (skill.skillProficiency > 80) {
                    skillLabel.setStyle("-fx-background-color: #00ff00");
                } else if (skill.skillProficiency > 50) {
                    skillLabel.setStyle("-fx-background-color: #33cc33");
                } else if (skill.skillProficiency > 20) {
                    skillLabel.setStyle("-fx-background-color: #248f24");
                } else {
                    skillLabel.setStyle("-fx-background-color: #006622");
                }

                skillSet.getChildren().add(skillLabel);
            });
    }

    public void mouseOver() {

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
