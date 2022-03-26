package seedu.contax.ui.onboarding;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import seedu.contax.ui.UiPart;

public class Overlay extends UiPart<Region> {

    private static final String FXML = "onboarding/Overlay.fxml";

    @FXML
    private Pane topOverlay;

    @FXML
    private Pane bottomOverlay;

    public enum ShowOverlay {
        BOTH,
        TOP,
        BOTTOM,
    }

    Overlay() {
        super(FXML);
    }

    /**
     * Cast over the given bounds and dimensions
     * @param boundX DoubleProperty to bind layoutX to
     * @param boundY DoubleProperty to bind layoutY to
     * @param height ReadOnlyDoubleProperty for height of window
     * @param width ReadOnlyDoubleProperty for width o  window
     */
    public void cover(DoubleProperty boundX, DoubleProperty boundY,
                      ReadOnlyDoubleProperty height, ReadOnlyDoubleProperty width) {

        showOverlay(topOverlay);
        hideOverlay(bottomOverlay);

        setLayout(topOverlay, boundX, boundY);

        setHeight(topOverlay, height);
        setWidth(topOverlay, width);
    }

    /**
     * Show only the given region
     * @param boundX DoubleProperty to bind layoutX to
     * @param boundY DoubleProperty to bind layoutY to
     * @param height ReadOnlyDoubleProperty for height of window
     * @param width ReadOnlyDoubleProperty for width o  window
     * @param parentHeight ReadOnlyDoubleProperty for height of parent window
     * @param parentWidth ReadOnlyDoubleProperty for height of parent window
     * @param option overlay option
     */
    public void showOnly(DoubleProperty boundX, DoubleProperty boundY,
                         ReadOnlyDoubleProperty height, ReadOnlyDoubleProperty width,
                         ReadOnlyDoubleProperty parentHeight, ReadOnlyDoubleProperty parentWidth,
                         ShowOverlay option) {

        setLayout(topOverlay, boundX, new SimpleDoubleProperty(0));
        setLayout(bottomOverlay, boundX, boundY.add(height));

        setHeight(topOverlay, boundY);
        setWidth(topOverlay, parentWidth);

        setHeight(bottomOverlay, parentHeight.subtract(boundY.add(height)));
        setWidth(bottomOverlay, parentWidth);

        processOverlayOption(option);
    }

    /**
     * Processes the given overlay option, which determines how the overlay will
     * be shown
     * @param option Overlay option
     */
    private void processOverlayOption(ShowOverlay option) {
        switch (option) {
        case BOTH:
            showOverlay(topOverlay);
            showOverlay(bottomOverlay);
            break;
        case BOTTOM:
            hideOverlay(topOverlay);
            showOverlay(bottomOverlay);
            break;
        case TOP:
            showOverlay(topOverlay);
            hideOverlay(bottomOverlay);
            break;
        default:
            break;
        }
    }

    /**
     * Sets width of given overlay with the given ReadOnlyDoubleProperty.
     * @param overlay overlay to set.
     * @param property property to bind with.
     */
    private void setWidth(Pane overlay, ReadOnlyDoubleProperty property) {
        overlay.minWidthProperty().bind(property);
        overlay.maxWidthProperty().bind(property);
    }

    /**
     * Sets height of given overlay with the given ReadOnlyDoubleProperty.
     * @param overlay overlay to set.
     * @param property property to bind with.
     */
    private void setHeight(Pane overlay, ReadOnlyDoubleProperty property) {
        overlay.minHeightProperty().bind(property);
        overlay.maxHeightProperty().bind(property);
    }

    /**
     * Sets height of given overlay with the given DoubleBinding.
     * @param overlay overlay to set.
     * @param property property to bind with.
     */
    private void setHeight(Pane overlay, DoubleBinding property) {
        overlay.minHeightProperty().bind(property);
        overlay.maxHeightProperty().bind(property);
    }

    /**
     * Sets the given overlay with the given x and y ReadOnlyDoubleProperty.
     * @param overlay overlay to set.
     * @param x property to bind x with
     * @param y property to bind y with
     */
    private void setLayout(Pane overlay, ReadOnlyDoubleProperty x, ReadOnlyDoubleProperty y) {
        overlay.layoutXProperty().bind(x);
        overlay.layoutYProperty().bind(y);
    }

    /**
     * Sets the given overlay with the given x ReadOnlyDoublePropery and y DoubleBinding.
     * @param overlay overlay to set.
     * @param x property to bind x with
     * @param y property to bind y with
     */
    private void setLayout(Pane overlay, ReadOnlyDoubleProperty x, DoubleBinding y) {
        overlay.layoutXProperty().bind(x);
        overlay.layoutYProperty().bind(y);
    }

    /**
     * Shows the given overlay
     * @param overlay
     */
    private void showOverlay(Pane overlay) {
        overlay.setVisible(true);
        overlay.setStyle("-fx-background-color: rgb(0, 0, 0, 0.5)");
    }

    /**
     * Hides the given overlay
     * @param overlay
     */
    private void hideOverlay(Pane overlay) {
        overlay.setVisible(false);
        overlay.setStyle("-fx-background-color: rgb(0, 0, 0, 0)");
    }

}
