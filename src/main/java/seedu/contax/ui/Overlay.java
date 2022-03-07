package seedu.contax.ui;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public class Overlay extends UiPart<Region> {

    private static final String FXML = "Overlay.fxml";

    @FXML
    private Pane topOverlay;

    @FXML
    private Pane bottomOverlay;

    @FXML
    private Pane leftOverlay;

    @FXML
    private Pane rightOverlay;


    Overlay() {
        super(FXML);
    }

    public void init() {
        topOverlay.setStyle("-fx-background-color: rgb(0, 0, 0, 0.5)");
        bottomOverlay.setStyle("-fx-background-color: rgb(0, 0, 0, 0.5)");
//        leftOverlay.setStyle("-fx-background-color: rgb(0, 0, 0, 0.5)");
//        rightOverlay.setStyle("-fx-background-color: rgb(0, 0, 0, 0.5)");
    }

    public void cover(DoubleProperty boundX, DoubleProperty boundY, ReadOnlyDoubleProperty height, ReadOnlyDoubleProperty width) {
        bottomOverlay.layoutXProperty().bind(boundX);
        bottomOverlay.layoutYProperty().bind(boundY);
        bottomOverlay.minHeightProperty().bind(height);
        bottomOverlay.minWidthProperty().bind(width);
        bottomOverlay.maxHeightProperty().bind(height);
        bottomOverlay.maxWidthProperty().bind(width);

    }

    public void showOnly(DoubleProperty boundX, DoubleProperty boundY,
                         ReadOnlyDoubleProperty height, ReadOnlyDoubleProperty width,
                         ReadOnlyDoubleProperty parentHeight, ReadOnlyDoubleProperty parentWidth) {
        topOverlay.layoutXProperty().bind(boundX);
        topOverlay.setLayoutY(0.0);
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
}
