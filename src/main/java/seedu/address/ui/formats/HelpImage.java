package seedu.address.ui.formats;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import seedu.address.ui.UiPart;

public class HelpImage extends UiPart<Region> {
    private static final String FXML = "HelpImage.fxml";

    @FXML
    private ImageView helpImage;

    public HelpImage(Image guideImage){
        super(FXML);
        helpImage.setImage(guideImage);
    }

}
