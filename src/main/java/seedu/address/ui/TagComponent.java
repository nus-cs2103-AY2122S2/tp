package seedu.address.ui;

import javafx.scene.control.Label;
import seedu.address.model.person.Cca;
import seedu.address.model.person.Education;
import seedu.address.model.person.Internship;
import seedu.address.model.person.Module;
import seedu.address.model.tag.Tag;

public class TagComponent {


    private final Tag tag;

    public TagComponent(Tag tag) {
        this.tag = tag;
    }

    public Label getTagLabel() {
        Label l = new Label(tag.tagName);
        if (tag instanceof Internship) {
            l.setStyle("-fx-padding:2;"
                    + "-fx-border-width: 0;"
                    + "-fx-border-color: green;"
                    + "-fx-border-radius: 2;"
                    + "-fx-background-color: red;"
                    + "-fx-border-insets: 2;"
                    + "-fx-text-fill: white;"
                    + "-fx-font-size: 12;"
                    + "-fx-line-spacing: 5;");
        }
        if (tag instanceof Education) {
            l.setStyle("-fx-padding:2;"
                    + "-fx-border-width: 0;"
                    + "-fx-border-color: green;"
                    + "-fx-border-radius: 2;"
                    + "-fx-background-color: yellow;"
                    + "-fx-border-insets: 2;"
                    + "-fx-text-fill: black;"
                    + "-fx-font-size: 12;"
                    + "-fx-line-spacing: 5;");
        }
        if (tag instanceof Module) {
            l.setStyle("-fx-padding:2;"
                    + "-fx-border-width: 0;"
                    + "-fx-border-color: green;"
                    + "-fx-border-radius: 2;"
                    + "-fx-background-color: blue;"
                    + "-fx-border-insets: 2;"
                    + "-fx-text-fill: white;"
                    + "-fx-font-size: 12;"
                    + "-fx-line-spacing: 5;");
        }
        if (tag instanceof Cca) {
            l.setStyle("-fx-padding:2;"
                    + "-fx-border-width: 0;"
                    + "-fx-border-color: green;"
                    + "-fx-border-radius: 2;"
                    + "-fx-background-color: orange;"
                    + "-fx-border-insets: 2;"
                    + "-fx-text-fill: black;"
                    + "-fx-font-size: 12;"
                    + "-fx-line-spacing: -2;");
        }

        return l;
    }

}
