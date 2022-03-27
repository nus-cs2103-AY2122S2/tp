package seedu.address.ui.general;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.ui.UiPart;

/**
 * The general display window at the right-hand side of the application, responsible for displaying detailed
 * information, such as person's profile, information related to add tag and add person.
 */
public class GeneralDisplay extends UiPart<Region> {
    private static final String FXML = "GeneralDisplay.fxml";
    private Profile profile;

    @FXML
    private StackPane profileDisplayPlaceholder;
    @FXML
    private StackPane tagListPlaceholder;

    /**
     * Creates a {@code GeneralDisplay}.
     */
    public GeneralDisplay() {
        super(FXML);
        tagListPlaceholder.setVisible(false);
        profileDisplayPlaceholder.setVisible(false);
    }

    /**
     * Set the general display to show profile.
     */
    public void setProfile(Profile profile) {
        this.profile = profile;
        tagListPlaceholder.setVisible(false);
        profileDisplayPlaceholder.setVisible(true);
        profileDisplayPlaceholder.getChildren().clear();
        profileDisplayPlaceholder.getChildren().add(profile.getRoot());
    }

    /**
     * Refresh the profile if the person being currently displayed, and certain operation has being
     * operated on that person.
     */
    public void refreshProfile(Profile profile) {
        if (this.profile != null && this.profile.getIndex().equals(profile.getIndex())) {
            this.setProfile(profile);
        }
    }

    /**
     * Set the general display to show all tags.
     */
    public void setTagList(TagList tagList) {
        this.profile = null;
        tagListPlaceholder.getChildren().clear();
        tagListPlaceholder.setVisible(true);
        profileDisplayPlaceholder.setVisible(false);
        tagListPlaceholder.getChildren().add(tagList.getRoot());
    }
}
