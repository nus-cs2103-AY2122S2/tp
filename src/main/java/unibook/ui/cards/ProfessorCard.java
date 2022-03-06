package unibook.ui.cards;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import unibook.model.person.Professor;
import unibook.ui.UiPart;

/**
 * A UI component that displays information of a {@code Professor}.
 */
public class ProfessorCard extends UiPart<Region> {
    private static final String FXML = "cards/ProfessorListCard.fxml";

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

    /**
     * Creates a {@code ProfessorCard} with the given {@code Professor} and index to display.
     */
    public ProfessorCard(Professor professor, int displayedIndex) {
        super(FXML);
        this.professor = professor;
        id.setText(displayedIndex + ". ");
        name.setText(professor.getName().fullName);
        phone.setText(professor.getPhone().value);
        email.setText(professor.getEmail().value);
        office.setText(professor.getOffice().value);
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
