package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

/**
 * The general display window at the right-hand side of the application, responsible for displaying detailed
 * information, such as person's profile, information related to add tag and add person.
 */
public class GeneralDisplay extends UiPart<Region> {
    private static final String FXML = "GeneralDisplay.fxml";

    @FXML
    private StackPane profileDisplayPlaceholder;

    /**
     * Creates a {@code GeneralDisplay}.
     */
    public GeneralDisplay() {
        super(FXML);
    }

    /**
     * Set the general display to show profile.
     */
    public void setProfile(Profile profile) {
        profileDisplayPlaceholder.getChildren().clear();
        profileDisplayPlaceholder.getChildren().add(profile.getRoot());
    }
}
