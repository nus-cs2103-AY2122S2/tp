package seedu.trackermon.ui;

import java.util.Comparator;
import java.util.concurrent.Flow;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.trackermon.MainApp;
import seedu.trackermon.model.show.Show;

/**
 * An UI component that displays information of a {@code Show}.
 */
public class ShowCard extends UiPart<Region> {

    private static final String FXML = "ShowListCard.fxml";
    private static final String ICON_STAR = "/images/icon_star_30.png";
    private static final String ICON_STAR_FILLED = "/images/icon_star_filled_30.png";
    private static final int MAX_RATING = 5; // Move this max rating to Rating class
    private static final double IMAGE_SIZE = 20;

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4
     * where Trackermon is based on</a>
     */

    public final Show show;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label status;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane ratings;

    /**
     * Creates a {@code ShowCard} with the given {@code Show} and index to display.
     */
    public ShowCard(Show show, int displayedIndex) {
        super(FXML);
        this.show = show;
        id.setText(displayedIndex + ". ");
        name.setText(show.getName().fullName);

        String statusString = show.getStatus().toString();
        String statusMessage = "[" + statusString.substring(0, 1).toUpperCase()
                + statusString.substring(1, statusString.length()) + "]";
        status.setText(statusMessage);
        show.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        int curRating = (int) Math.round(Math.random() * MAX_RATING); // Remove this line when rating is done
        for (int i = 0; i < MAX_RATING; i++) { // Change MAX_RATING to Rating.MAX_RATING
            if (i <= curRating) { // Change curRating to show.getRating()
                ratings.getChildren().add(getImageNode(ICON_STAR_FILLED));
            } else {
                ratings.getChildren().add(getImageNode(ICON_STAR));
            }
        }
    }

    private ImageView getImageNode(String imagePath) {
        ImageView imageNode = new ImageView(new Image(MainApp.class.getResourceAsStream(imagePath)));
        imageNode.setFitHeight(IMAGE_SIZE);
        imageNode.setFitWidth(IMAGE_SIZE);
        return imageNode;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ShowCard)) {
            return false;
        }

        // state check
        ShowCard card = (ShowCard) other;
        return id.getText().equals(card.id.getText())
                && show.equals(card.show);
    }
}
