package seedu.address.ui;


import javafx.scene.control.Label;
import seedu.address.model.person.exceptions.InvalidLabStatusException;
import seedu.address.model.person.lab.Lab;

public class LabLabel extends Label {

    private final Lab lab;

    /**
     * Creates a {@code LabLabel} with the given {@code Lab}.
     */
    public LabLabel(Lab lab) {
        this.lab = lab;
        this.setText("Lab " + lab.labNumber);
        setColor();
    }

    private void setColor() {
        switch (lab.labStatus) {
        case UNSUBMITTED:
            this.setStyle("-fx-background-color: red;");
            break;
        case SUBMITTED:
            this.setStyle("-fx-background-color: orange;");
            break;
        case GRADED:
            this.setStyle("-fx-background-color: green;");
            break;
        default:
            throw new InvalidLabStatusException();
        }
    }
}
