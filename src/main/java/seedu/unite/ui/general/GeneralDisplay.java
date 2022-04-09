package seedu.unite.ui.general;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.unite.logic.Logic;
import seedu.unite.model.person.Person;
import seedu.unite.model.tag.Tag;
import seedu.unite.ui.UiManager;
import seedu.unite.ui.UiPart;

/**
 * The general display window at the right-hand side of the application, responsible for displaying detailed
 * information, such as person's profile, information related to add tag and add person.
 */
public class GeneralDisplay extends UiPart<Region> {
    private static final String FXML = "GeneralDisplay.fxml";
    private final Profile profile;
    private final TagList tagList;
    private final GrabResult grabResult;

    @FXML
    private StackPane profileDisplayPlaceholder;
    @FXML
    private StackPane tagListPlaceholder;
    @FXML
    private StackPane grabResultPlaceholder;

    /**
     * Creates a {@code GeneralDisplay}.
     */
    public GeneralDisplay(Logic logic, UiManager uiManager) {
        super(FXML);
        profile = new Profile(logic, uiManager);
        tagList = new TagList(logic, uiManager);
        grabResult = new GrabResult();
        tagListPlaceholder.setVisible(false);
        profileDisplayPlaceholder.setVisible(false);
        grabResultPlaceholder.setVisible(false);
    }

    /**
     * Set the general display to show profile of specified person if person is not null.
     * If person is null, it means the DeleteCommand was invoked on the currently shown person.
     */
    public void setProfile(Person person) {
        this.profile.setPerson(person);
        profileDisplayPlaceholder.setVisible(true);
        tagListPlaceholder.setVisible(false);
        grabResultPlaceholder.setVisible(false);
        resetProfile();
        profileDisplayPlaceholder.getChildren().add(profile.getRoot());
    }

    /**
     * Set the general display to show all tags.
     */
    public void setTagList(ObservableList<Tag> tags) {
        this.tagList.setTagList(tags);
        tagListPlaceholder.setVisible(true);
        profileDisplayPlaceholder.setVisible(false);
        grabResultPlaceholder.setVisible(false);
        resetTagList();
        tagListPlaceholder.getChildren().add(tagList.getTagListView());
    }

    /**
     * Set the general display to show the result from GrabCommand.
     */
    public void setGrabResult(String result) {
        this.grabResult.setResult(result);
        tagListPlaceholder.setVisible(false);
        profileDisplayPlaceholder.setVisible(false);
        grabResultPlaceholder.setVisible(true);
        resetGrabResult();
        grabResultPlaceholder.getChildren().add(grabResult.getRoot());
    }

    public Profile getProfile() {
        return profile;
    }

    public TagList getTagList() {
        return tagList;
    }

    public StackPane getProfileDisplayPlaceholder() {
        return profileDisplayPlaceholder;
    }

    public StackPane getTagListPlaceholder() {
        return tagListPlaceholder;
    }

    public void resetProfile() {
        profileDisplayPlaceholder.getChildren().clear();
    }

    public void resetTagList() {
        tagListPlaceholder.getChildren().clear();
    }

    public void resetGrabResult() {
        grabResultPlaceholder.getChildren().clear();
    }
}
