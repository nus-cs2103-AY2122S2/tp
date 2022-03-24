package seedu.address.ui;

import java.util.ArrayList;
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
    private ArrayList<UserImage> userImages;
    private int index;

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
        this.logic = logic;
        index = 0;
        displayArea.setFitHeight(400);
        displayArea.setFitWidth(600);
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

    public void setup() {
        userImages = new ArrayList<>(logic.getViewImageSet());
        refresh(userImages.get(index));
    }

    /**
     * Changes the image being displayed in the window
     * @param userImage image to switch to
     */
    public void refresh(UserImage userImage) {
        UserImage newImage = userImage;
        System.out.println(System.getProperty("java.class.path"));
        Image displayImage = new Image(
                this.getClass().getResourceAsStream("/" + newImage.getFilePath().get()));
        displayArea.setImage(displayImage);
        description.setText(newImage.getDescription());
    }

    /**
     * Change image to the next image if not last image
     */
    @FXML
    public void onNextClick() {
        if (index + 1 != userImages.size()) {
            index++;
            refresh(userImages.get(index));
        }
    }

    /**
     * Change image to the previous image if not first image
     */
    @FXML
    public void onPreviousClick() {
        if (index != 0) {
            index--;
            refresh(userImages.get(index));
        }
    }
}
