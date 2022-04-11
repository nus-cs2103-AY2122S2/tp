package seedu.address.ui;

import java.io.IOException;
import java.util.Comparator;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import seedu.address.commons.util.Colors;
import seedu.address.model.tag.Tag;



public class TagBox extends HBox {
    private static final String FXML = "/view/TagBox.fxml";

    @FXML
    private FlowPane tags;

    /**
     * Contains Set of Tags arranged in order of
     * Priority, Application Status, General Tag
     * @param tagSet
     */
    public TagBox(Set<Tag> tagSet) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(FXML));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tagSet.stream()
            .sorted(Comparator.comparing(tag -> tag.tagName)).sorted(Comparator.comparing(tag -> tag.tagType))
            .forEach(tag -> tags.getChildren().add(chooseColor(tag)));
    }

    /**
     * Chooses the appropriate color for the corresponding tag
     * @param tag
     * @return Label with correct color
     */
    public Label chooseColor(Tag tag) {
        Label returnTag = new Label(tag.tagName);
        //@@author Th-429B-reused
        //Reused from https://github.com/Th-429B/tp
        //with minor modifications
        switch (tag.tagType) {
        case PRIORITY:
            switch (tag.tagName.toUpperCase()) {
            case "HIGH":
                returnTag.setStyle(Colors.RED);
                break;
            case "MEDIUM":
                returnTag.setStyle(Colors.ORANGE);
                break;
            case "LOW":
                returnTag.setStyle(Colors.YELLOW_ORANGE);
                break;
            default:
                break;
            }
            break;
        case APPLICATION_STATUS:
            switch (tag.tagName.toUpperCase()) {
            case "NOT_APPLIED":
                returnTag.setStyle(Colors.BLUE);
                break;
            case "APPLIED":
                returnTag.setStyle(Colors.TEAL);
                break;
            case "INTERVIEWED":
                returnTag.setStyle(Colors.YELLOW);
                break;
            case "REJECTED":
                returnTag.setStyle(Colors.PURPLE);
                break;
            case "ACCEPTED":
                returnTag.setStyle(Colors.LIGHT_GREEN);
                break;
            default:
                break;
            }
            break;
        default:
            returnTag.setStyle(Colors.GREY);
        }
        //@@author
        return returnTag;
    }
}
