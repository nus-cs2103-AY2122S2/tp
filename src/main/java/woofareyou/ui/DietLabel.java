package woofareyou.ui;

import java.util.Objects;

import javafx.scene.control.Label;
import woofareyou.model.pet.Diet;

public class DietLabel {
    private static final String LABEL = "Diet:";
    private static final String LABEL_COLOR = "-fx-background-color: #8479E1";

    public DietLabel() {}

    /**
     * Creates a diet label for GUI
     * @param diet Diet of pet
     * @return Diet label
     */
    public static Label createDietLabel(Diet diet) {
        Label dietLabel = new Label();
        if (Objects.equals(diet.value, "")) {
            return dietLabel;
        }
        dietLabel.setText(LABEL);
        dietLabel.setStyle(LABEL_COLOR);
        return dietLabel;
    }
}
