package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.userimage.UserImage;


/**
 * Controller for a view image page
 */
public class ViewImageWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(ViewImageWindow.class);
    private static final String FXML = "ViewImageWindow.fxml";

    private Logic logic;

    @FXML
    private ImageView displayArea;

    @FXML
    private TextArea description;



    /**
     * Creates a new ViewImageWindow.
     *
     * @param root Stage to use as the root of the ViewImageWindow.
     */
    public ViewImageWindow(Stage root, Logic logic) {
        super(FXML, root);
        displayArea.setFitHeight(400);
        displayArea.setFitWidth(600);
        this.logic = logic;
        description.setEditable(false);
    }

    /**
     * Creates a new ViewImageWindow.
     */
    public ViewImageWindow(Logic logic) {
        this(new Stage(), logic);
    }

    /**
     * Shows the view image window.
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
        logger.fine("Showing view image window.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the view image window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the view image window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the view image window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    public void refresh() {
        UserImage userImage = logic.getViewPersonImage();
        Image displayImage = new Image(this.getClass().getResourceAsStream("/" + userImage.getFilePath().get()));
        displayArea.setImage(displayImage);
        description.setText(userImage.getDescription());
    }
}