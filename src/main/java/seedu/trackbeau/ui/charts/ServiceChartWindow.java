package seedu.trackbeau.ui.charts;

import java.util.logging.Logger;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;
import seedu.trackbeau.commons.core.LogsCenter;
import seedu.trackbeau.ui.UiPart;

public class ServiceChartWindow extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(ServiceChartWindow.class);
    private static final String FXML = "PieChartWindow.fxml";

    @javafx.fxml.FXML
    private PieChart pieChart;
    /**
     * Creates a new Service Chart Window.
     *
     * @param root Stage to use as the root of the ServiceChartWindow.
     */
    public ServiceChartWindow(Stage root) {
        super(FXML, root);
        //referenced from https://docs.oracle.com/javafx/2/charts/pie-chart.htm
        Scene scene = new Scene(new Group());
        root.setTitle("Service Preference Chart");
        root.setWidth(500);
        root.setHeight(500);
        pieChart.setLabelLineLength(10);
        pieChart.setTitle("Favorite Services Amongst Customers"); (
                (Group) scene.getRoot()).getChildren().add(pieChart);
        root.setScene(scene);
    }

    /**
     * Creates a new ServiceChartWindow.
     */
    public ServiceChartWindow() {
        this(new Stage());
    }

    /**
     * Shows the ServiceChart window.
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
        logger.fine("Showing service chart.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    public PieChart getPieChart() {
        return this.pieChart;
    }

    /**
     * Returns true if the ServiceChart window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the ServiceChart window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the ServiceChart window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}
