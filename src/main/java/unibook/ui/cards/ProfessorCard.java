package unibook.ui.cards;

import java.util.Comparator;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import unibook.commons.core.LogsCenter;
import unibook.model.person.Professor;
import unibook.ui.UiPart;

/**
 * A UI component that displays information of a {@code Professor}.
 */
public class ProfessorCard extends UiPart<Region> {
    private static final String FXML = "cards/ProfessorCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on UniBook level 4</a>
     */

    public final Professor professor;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label office;
    @FXML
    private FlowPane tags;

    private final Logger logger = LogsCenter.getLogger(ProfessorCard.class);

    /**
     * Creates a {@code ProfessorCard} with the given {@code Professor} and index to display.
     */
    public ProfessorCard(Professor professor, int displayedIndex) {
        super(FXML);
        logger.info(String.format("Instantiating professor card with index %s", displayedIndex));
        this.professor = professor;
        id.setText(displayedIndex + ". ");
        name.setText(professor.getName().fullName);
        if (professor.getPhone().value.isEmpty()) {
            logger.info("Professor has no phone number, making phone label invisible");
            //no phone number, dont show phone field
            phone.setVisible(false);
            phone.setManaged(false);
        } else {
            phone.setText("Phone: " + professor.getPhone().value);
        }

        if (professor.getEmail().value.isEmpty()) {
            logger.info("Professor has no email, making email label invisible");
            //no email, dont show email field
            email.setManaged(false);
            email.setVisible(false);
        } else {
            email.setText("Email: " + professor.getEmail().value);
        }

        if (professor.getOffice().value.isEmpty()) {
            logger.info("Professor has no office, making office label invisible");
            //no office, dont show office field
            office.setManaged(false);
            office.setVisible(false);
        } else {
            office.setText("Office: " + professor.getOffice().value);
        }

        professor.getTags().stream()
            .sorted(Comparator.comparing(tag -> tag.tagName))
            .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ProfessorCard)) {
            return false;
        }

        // state check
        ProfessorCard card = (ProfessorCard) other;
        return id.getText().equals(card.id.getText())
            && professor.equals(card.professor);
    }
}
