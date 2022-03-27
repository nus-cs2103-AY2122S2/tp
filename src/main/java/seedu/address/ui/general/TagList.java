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
    private final List<Tag> tagList;

    @FXML
    private Label heading;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code TagList}.
     */
    public TagList(List<Tag> tagList) {
        super(FXML);
        this.tagList = tagList;
        heading.setText("Current tags: ");
        tagList.forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    public List<Tag> getTagList() {
        return this.tagList;
    }
}
