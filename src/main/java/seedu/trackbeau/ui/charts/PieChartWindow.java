package seedu.trackbeau.ui.charts;

import java.util.logging.Logger;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;
import seedu.trackbeau.commons.core.LogsCenter;
import seedu.trackbeau.ui.UiPart;

public class PieChartWindow extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(PieChartWindow.class);
    private static final String FXML = "PieChartWindow.fxml";
    private final String loggingValue;

    @javafx.fxml.FXML
    private PieChart pieChart;

    /**
     * Creates a new Pie Chart Window.
     *
     * @param root Stage to use as the root of the PieChartWindow.
     */
    public PieChartWindow(Stage root, String rootTitle, String chartTitle, String loggingValue) {
        super(FXML, root);
        this.loggingValue = loggingValue;
        //referenced from https://docs.oracle.com/javafx/2/charts/pie-chart.htm
        Scene scene = new Scene(new Group());
        root.setTitle(rootTitle);
        pieChart.setLabelLineLength(10);
        pieChart.setTitle(chartTitle); (
                (Group) scene.getRoot()).getChildren().add(pieChart);
        root.setScene(scene);
        //End of reference
    }

    /**
     * Creates a new PieChartWindow.
     */
    public PieChartWindow(String rootTitle, String chartTitle, String loggingValue) {
        this(new Stage(), rootTitle, chartTitle, loggingValue);
    }

    /**
     * Shows the PieChart window.
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
        logger.fine(String.format("Showing %s chart.", loggingValue));
        getRoot().show();
        getRoot().centerOnScreen();
    }


    public PieChart getPieChart() {
        return this.pieChart;
    }

    /**
     * Returns true if the PieChart window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the PieChart window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the PieChart window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}
