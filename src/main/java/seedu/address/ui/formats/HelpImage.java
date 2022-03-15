package seedu.address.ui.formats;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import seedu.address.ui.UiPart;

/**
 * For changing the contents in the help window to show images
 */
public class HelpImage extends UiPart<Region> {
    private static final String FXML = "HelpImage.fxml";

    @FXML
    private ImageView helpImage;

    /**
     * Displays a image based on file provided
     *
     * @param guideImage image file to be displayed
     */
    public HelpImage(Image guideImage) {
        super(FXML);
        helpImage.setImage(guideImage);
    }

}
