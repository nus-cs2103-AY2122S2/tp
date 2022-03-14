package seedu.contax.ui.onboarding;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.contax.ui.UiPart;

public class OnboardingInstruction extends UiPart<Region> {

    private static final String FXML = "onboarding/OnboardingInstruction.fxml";

    @FXML
    private Label instruction;

    /**
     * Creates a label to contain instructions for the onboarding guide
     */
    public OnboardingInstruction() {
        super(FXML);
    }

    public void show() {
        instruction.setVisible(true);
    }

    public void hide() {
        instruction.setVisible(false);
    }

    public String getText() {
        return instruction.getText();
    }
    public void setText(String text) {
        instruction.setText(text);
    }

    /**
     * Translate and bind the instructionLabel to the given DoubleProperty x and y
     * @param x x to translate to
     * @param y y to translate to
     */
    public void translate(DoubleProperty x, DoubleProperty y) {
        instruction.setManaged(false);
        instruction.layoutXProperty().bind(x);
        instruction.layoutYProperty().bind(y);
    }

    /**
     * Translate and bind the instructionLabel to the given DoubleBinding x and y
     * @param x x to translate to
     * @param y y to translate to
     */
    public void translate(DoubleBinding x, DoubleBinding y) {
        instruction.setManaged(false);
        instruction.layoutXProperty().bind(x);
        instruction.layoutYProperty().bind(y);
    }

    /**
     * Sets the instructionLabel to the center and binds it
     * @param height height of the window
     * @param width width of the window
     */
    public void setCenter(ReadOnlyDoubleProperty height, ReadOnlyDoubleProperty width) {
        instruction.setManaged(false);
        instruction.layoutXProperty().bind(width.subtract(instruction.getWidth()).divide(2));
        instruction.layoutYProperty().bind(height.subtract(instruction.getHeight()).divide(2));
    }

    /**
     * Sets the size of instructionLabel relative to the window
     * @param height relative height
     * @param width relative width
     * @param heightProperty height of window
     * @param widthProperty width of window
     */
    public void setSize(double height, double width, ReadOnlyDoubleProperty heightProperty,
                        ReadOnlyDoubleProperty widthProperty) {

        instruction.maxHeightProperty().bind(heightProperty.multiply(height));
        instruction.minHeightProperty().bind(heightProperty.multiply(height));
        instruction.prefHeightProperty().bind(heightProperty.multiply(height));
        instruction.maxWidthProperty().bind(widthProperty.multiply(width));
        instruction.minWidthProperty().bind(widthProperty.multiply(width));
        instruction.prefWidthProperty().bind(widthProperty.multiply(width));
    }
}
