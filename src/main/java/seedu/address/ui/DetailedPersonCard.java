package seedu.address.ui;

import java.util.Comparator;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import seedu.address.commons.core.index.Index;
import seedu.address.model.image.ImageDetails;
import seedu.address.model.image.ImageDetailsList;
import seedu.address.model.person.Person;

/**
 * A UI component that shows a full, detailed view of a Person
 */
public class DetailedPersonCard extends UiPart<Region> {
    private static final String FXML = "DetailedPersonCard.fxml";
    private static final int MAXIMUM_IMAGES_TO_DISPLAY = 6;

    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private VBox notesPane;
    @FXML
    private Label notesTitle;
    @FXML
    private Label notes;
    @FXML
    private VBox deadlinesPane;
    @FXML
    private Label deadlinesTitle;
    @FXML
    private Label deadlines;
    @FXML
    private FlowPane tags;
    @FXML
    private Canvas starCanvas;
    @FXML
    private ImageView flagImageView;
    @FXML
    private TilePane imageListView;
    @FXML
    private Label moreImages;

    private final Image highImportanceFlag = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/red_flag.png")));
    private final Image notHighImportanceFlag = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/white_flag.png")));

    /**
     * Creates a {@code DetailedPersonCard} with the given {@code Person} to display
     * @param person the person to display
     */
    public DetailedPersonCard(Person person) {
        super(FXML);

        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        notes.setText(person.getNotes().listFormat());
        deadlines.setText(person.getDeadlines().listFormat());
        deadlinesTitle.setText("Deadlines");
        notesTitle.setText("Notes");

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

        setImageListView(person.getImageDetailsList());
    }

    private void setImageListView(ImageDetailsList images) {
        double totalWidth = this.getRoot().getWidth();
        int individualWidth = (int) totalWidth / 3;

        for (int i = 0; i < Math.min(images.size(), MAXIMUM_IMAGES_TO_DISPLAY); i++) {
            Index index = Index.fromZeroBased(i);
            ImageDetails imageDetails = images.get(index.getZeroBased());
            ImageCard imageCard = new ImageCard(index.getOneBased(), imageDetails, 120, individualWidth);
            imageListView.getChildren().add(imageCard.getRoot());
        }

        if (images.size() > MAXIMUM_IMAGES_TO_DISPLAY) {
            moreImages.setText(String.format("... and %d more images. Use the images command to see everything.",
                            images.size() - MAXIMUM_IMAGES_TO_DISPLAY));
            moreImages.setVisible(true);
        }
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
}
