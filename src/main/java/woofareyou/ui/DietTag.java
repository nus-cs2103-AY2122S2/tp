package woofareyou.ui;

import javafx.scene.control.Label;

/**
 * A tag for the diet of the pet, if any.
 */
public class DietTag extends Label {
    private static final String LABEL = "Diet: ";

    /**
     * Creates a new pet diet tag.
     *
     * @param dietInfo the diet of the pet to be displayed.
     */
    public DietTag(String dietInfo) {
        super(LABEL + dietInfo);
    }
}
