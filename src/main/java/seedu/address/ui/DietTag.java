package seedu.address.ui;

import javafx.scene.control.Label;

public class DietTag {
    private static final String LABEL = "Diet: ";

    public DietTag() {}

    /**
     * Creates a diet tag for GUI.
     *
     * @param diet the diet of the pet.
     * @return a tag containing the diet of the pet, if any.
     */
    public static Label createDietLabel(String diet) {
        Label dietLabel = new Label();
        if (diet.isEmpty()) {
            return dietLabel;
        }
        dietLabel.setText(LABEL + diet);
        return dietLabel;
    }
}
