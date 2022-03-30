package seedu.address.ui.general;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.ui.UiPart;

/**
 * The general display window at the right-hand side of the application, responsible for displaying detailed
 * information, such as person's profile, information related to add tag and add person.
 */
public class GeneralDisplay extends UiPart<Region> {
    private static final String FXML = "GeneralDisplay.fxml";
    private Profile profile;
    private TagList tagList;

    @FXML
    private StackPane profileDisplayPlaceholder;
    @FXML
    private StackPane tagListPlaceholder;

    /**
     * Creates a {@code GeneralDisplay}.
     */
    public GeneralDisplay() {
        super(FXML);
        profile = new Profile();
        tagList = new TagList();
        tagListPlaceholder.setVisible(false);
        profileDisplayPlaceholder.setVisible(false);
    }

    /**
     * Set the general display to show profile of specified person if person is not null.
     * If person is null, it means the DeleteCommand was invoked on the currently shown person.
     */
    public void setProfile(Person person) {
        this.profile.setPerson(person);
        tagListPlaceholder.setVisible(false);
        profileDisplayPlaceholder.setVisible(true);
        resetProfile();
        profileDisplayPlaceholder.getChildren().add(profile.getRoot());
    }

    /**
     * Set the general display to show all tags.
     */
    public void setTagList(List<Tag> tags) {
        this.tagList.setTagList(tags);
        tagListPlaceholder.setVisible(true);
        profileDisplayPlaceholder.setVisible(false);
        resetTagList();
        tagListPlaceholder.getChildren().add(tagList.getRoot());
    }

    public Profile getProfile() {
        return profile;
    }

    public void resetProfile() {
        profileDisplayPlaceholder.getChildren().clear();
    }

    public void resetTagList() {
        tagListPlaceholder.getChildren().clear();
    }
}
