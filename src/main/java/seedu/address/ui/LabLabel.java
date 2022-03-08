package seedu.address.ui;


import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import seedu.address.model.person.exceptions.InvalidLabStatusException;
import seedu.address.model.person.lab.Lab;

public class LabLabel extends Label {

    private static final Background RED = new Background(
            new BackgroundFill(Color.RED, new CornerRadii(0), new Insets(0)));
    private static final Background GREEN = new Background(
            new BackgroundFill(Color.GREEN, new CornerRadii(0), new Insets(0)));
    private static final Background YELLOW = new Background(
            new BackgroundFill(Color.YELLOW, new CornerRadii(0), new Insets(0)));

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
            this.setBackground(RED);
            break;
        case SUBMITTED:
            this.setBackground(YELLOW);
            break;
        case GRADED:
            this.setBackground(GREEN);
            break;
        default:
            throw new InvalidLabStatusException();
        }
    }
}
