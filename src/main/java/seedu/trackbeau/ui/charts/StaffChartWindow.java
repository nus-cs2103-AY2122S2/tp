package seedu.trackbeau.ui.charts;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;
import seedu.trackbeau.commons.core.LogsCenter;
import seedu.trackbeau.ui.UiPart;

public class StaffChartWindow extends UiPart<Stage> {
    //referenced from https://docs.oracle.com/javafx/2/charts/pie-chart.htm
    private static final Logger logger = LogsCenter.getLogger(StaffChartWindow.class);
    private static final String FXML = "PieChartWindow.fxml";

    @FXML
    private PieChart pieChart;
    /**
     * Creates a new StaffChart Window.
     *
     * @param root Stage to use as the root of the StaffChartWindow.
     */
    public StaffChartWindow(Stage root) {
        super(FXML, root);
        Scene scene = new Scene(new Group());
        root.setTitle("Staff Preference Chart");
        root.setWidth(500);
        root.setHeight(500);
        pieChart.setLabelLineLength(10);
        pieChart.setTitle("Favorite Staffs Amongst Customers"); (
                (Group) scene.getRoot()).getChildren().add(pieChart);
        root.setScene(scene);
    }

    /**
     * Creates a new StaffChartWindow.
     */
    public StaffChartWindow() {
        this(new Stage());
    }

    public PieChart getPieChart() {
        return pieChart;
    }

    /**
     * Shows the StaffChart window.
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
        logger.fine("Showing staff chart.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the StaffChart window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the StaffChart window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the StaffChart window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

}
