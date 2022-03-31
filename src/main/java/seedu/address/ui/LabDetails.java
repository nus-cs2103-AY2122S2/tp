package seedu.address.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import seedu.address.model.lab.Lab;

public class LabDetails extends HBox {

    private final Lab lab;

    /**
     * Constructor for {@code LabDetails} with the specified {@code Lab}
     */
    public LabDetails(Lab lab) {
        this.lab = lab;
        this.getChildren().addAll(new LabLabel(lab), new Label(lab.getDetails()));
        setAlignment(Pos.CENTER_LEFT);
    }
}
