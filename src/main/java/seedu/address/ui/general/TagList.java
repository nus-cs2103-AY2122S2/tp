package seedu.address.ui.general;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.model.tag.Tag;
import seedu.address.ui.UiPart;

public class TagList extends UiPart<Region> {
    private static final String FXML = "TagList.fxml";

    @FXML
    private Label heading;
    @FXML
    private FlowPane tagsInPane;

    /**
     * Creates a {@code TagList}.
     */
    public TagList() {
        super(FXML);
    }

    public void setTagList(List<Tag> tagList) {
        heading.setText("Current tags: ");
        tagsInPane.getChildren().clear();
        int i = 1;
        for (Tag tag : tagList) {
            tagsInPane.getChildren().add(new Label(i + ". " + tag.tagName));
            i++;
        }
    }
}
