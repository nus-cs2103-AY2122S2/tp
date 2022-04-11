package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

public class ImageWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "ImageWindow.fxml";

    @FXML
    private ImageView imageView;

    /**
     * Creates a new ImageWindow.
     *
     * @param root Stage to use as the root of the ImageWindow.
     */
    public ImageWindow(Stage root, Image image) {
        super(FXML, root);
        this.imageView.setImage(image);
    }

    /**
     * Creates a new ImageWindow.
     */
    public ImageWindow(Image image) {
        this(new Stage(), image);
    }

    /**
     * Shows the image window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing full image.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the image window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the image window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the image window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

}
