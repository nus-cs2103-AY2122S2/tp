package seedu.contax.ui;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

import java.nio.file.Path;
import java.nio.file.Paths;

public class OnboardingInstruction extends UiPart<Region> {

    private static final String FXML = "OnboardingInstruction.fxml";

    @FXML
    private Label instruction;

    public OnboardingInstruction() {
        super(FXML);
        instruction.setStyle("-fx-background-color: rgba(25, 25, 25, 1)");
        instruction.setTextFill(Color.WHITE);
    }

    public void show() {
        instruction.setVisible(true);
    }

    public void hide() {
        instruction.setVisible(false);
    }

    public void setText(String text) {
        instruction.setText(text);
    }

    public void translate(DoubleProperty x, DoubleProperty y) {
        instruction.setManaged(false);
        instruction.layoutXProperty().bind(x);
        instruction.layoutYProperty().bind(y);
    }

    public void setCenter(ReadOnlyDoubleProperty height, ReadOnlyDoubleProperty width) {
        instruction.setManaged(false);
        instruction.layoutXProperty().bind(width.subtract(instruction.getWidth()).divide(2));
        instruction.layoutYProperty().bind(height.subtract(instruction.getHeight()).divide(2));
    }

    public void setSize(double height, double width, ReadOnlyDoubleProperty heightProperty, ReadOnlyDoubleProperty widthProperty) {

        instruction.maxHeightProperty().bind(heightProperty.multiply(height));
        instruction.minHeightProperty().bind(heightProperty.multiply(height));
        instruction.prefHeightProperty().bind(heightProperty.multiply(height));
        instruction.maxWidthProperty().bind(widthProperty.multiply(width));
        instruction.minWidthProperty().bind(widthProperty.multiply(width));
        instruction.prefWidthProperty().bind(widthProperty.multiply(width));
    }
}
