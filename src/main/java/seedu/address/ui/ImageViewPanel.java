package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.TilePane;
import seedu.address.commons.core.index.Index;
import seedu.address.model.image.ImageDetails;
import seedu.address.model.image.ImageDetailsList;

public class ImageViewPanel extends UiPart<Region> {
    private static final String FXML = "ImageViewPanel.fxml";

    @FXML
    private TilePane imageListView;

    /**
     * Creates a {@code ImageViewPanel} with the given {@code ImageDetailsList}.
     */
    public ImageViewPanel(ImageDetailsList images) {
        super(FXML);

        double totalWidth = this.getRoot().getWidth();
        int individualWidth = (int) totalWidth / 3;

        for (int i = 0; i < images.size(); i++) {
            Index index = Index.fromZeroBased(i);
            ImageDetails imageDetails = images.get(index.getZeroBased());
            ImageCard imageCard = new ImageCard(index.getOneBased(), imageDetails, 120, individualWidth);
            imageListView.getChildren().add(imageCard.getRoot());
        }
    }

}
