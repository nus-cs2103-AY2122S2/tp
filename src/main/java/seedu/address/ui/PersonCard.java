package seedu.address.ui;

import static javafx.application.Application.launch;

import java.util.Comparator;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
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
    private Label notes;
    @FXML
    private Label deadlines;
    @FXML
    private FlowPane tags;
    @FXML
    private Canvas starCanvas;
    @FXML
    private ImageView flagImageView;
    private final Image highImportanceFlag = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/red_flag.png")));
    private final Image notHighImportanceFlag = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/white_flag.png")));

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
        notes.setText(person.getNotes().listFormat());
        email.setText(person.getEmail().value);
        deadlines.setText(person.getDeadlines().listFormat());
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        if (person.isFavourite()) {
            starCanvas.setVisible(true);
            drawStarShape(starCanvas.getGraphicsContext2D());
        }

        // Red flag if importance, otherwise empty plain flag
        if (person.hasHighImportance()) {
            flagImageView.setImage(highImportanceFlag);
        } else {
            flagImageView.setImage(notHighImportanceFlag);
        }
        flagImageView.setFitHeight(20);
        flagImageView.setFitWidth(20);
    }

    private void drawStarShape(GraphicsContext gc) {
        //@@author takufunkai-reused
        //Reused from https://zetcode.com/gui/javafx/canvas/
        // with minor modifications to the points and fill, for suitable colour and size.

        double[] xpoints = {1, 7, 9, 11, 17, 13, 14, 9, 4, 5};
        double[] ypoints = {7, 6.5, 1, 6.5, 7, 10, 15, 12, 15, 10};

        gc.setFill(Color.YELLOW);
        gc.fillPolygon(xpoints, ypoints, xpoints.length);

        gc.setStroke(Color.BLACK);
        gc.strokePolygon(xpoints, ypoints, xpoints.length);

        //@@author
    }

    public static void main(String[] args) {
        launch(args);
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
