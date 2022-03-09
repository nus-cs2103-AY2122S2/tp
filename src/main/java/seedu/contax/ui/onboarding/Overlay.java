package seedu.contax.ui.onboarding;

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
        showAll();
        topOverlay.setStyle("-fx-background-color: rgb(0, 0, 0, 0.5)");
        bottomOverlay.setStyle("-fx-background-color: rgb(0, 0, 0, 0)");
        topOverlay.layoutXProperty().bind(boundX);
        topOverlay.layoutYProperty().bind(boundY);
        topOverlay.minHeightProperty().bind(height);
        topOverlay.minWidthProperty().bind(width);
        topOverlay.maxHeightProperty().bind(height);
        topOverlay.maxWidthProperty().bind(width);

    }

    /**
     * Show only the given regio
     * @param boundX DoubleProperty to bind layoutX to
     * @param boundY DoubleProperty to bind layoutY to
     * @param height ReadOnlyDoubleProperty for height of window
     * @param width ReadOnlyDoubleProperty for width o  window
     */
    public void showOnly(DoubleProperty boundX, DoubleProperty boundY,
                         ReadOnlyDoubleProperty height, ReadOnlyDoubleProperty width,
                         ReadOnlyDoubleProperty parentHeight, ReadOnlyDoubleProperty parentWidth) {
        showAll();
        topOverlay.setStyle("-fx-background-color: rgb(0, 0, 0, 0.5)");
        bottomOverlay.setStyle("-fx-background-color: rgb(0, 0, 0, 0.5)");
        topOverlay.layoutXProperty().bind(boundX);
        topOverlay.layoutYProperty().bind(new SimpleDoubleProperty(0));
        topOverlay.minHeightProperty().bind(boundY);
        topOverlay.minWidthProperty().bind(parentWidth);
        topOverlay.maxHeightProperty().bind(boundY);
        topOverlay.maxWidthProperty().bind(parentWidth);


        bottomOverlay.layoutXProperty().bind(boundX);
        bottomOverlay.layoutYProperty().bind(boundY.add(height));
        bottomOverlay.minHeightProperty().bind(parentHeight.subtract(boundY.add(height)));
        bottomOverlay.minWidthProperty().bind(parentWidth);
        bottomOverlay.maxHeightProperty().bind(parentHeight.subtract(boundY.add(height)));
        bottomOverlay.maxWidthProperty().bind(parentWidth);
    }

    /**
     * Show all overlays
     */
    private void showAll() {
        topOverlay.setVisible(true);
        bottomOverlay.setVisible(true);
    }

    /**
     * Hide all overlays
     */
    private void hideAll() {
        topOverlay.setVisible(false);
        bottomOverlay.setVisible(false);
    }
}
