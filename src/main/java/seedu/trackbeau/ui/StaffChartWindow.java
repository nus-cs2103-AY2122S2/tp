package seedu.trackbeau.ui;

import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;
import seedu.trackbeau.commons.core.LogsCenter;

public class StaffChartWindow extends UiPart<Stage> {
    //referenced from https://docs.oracle.com/javafx/2/charts/pie-chart.htm
    private static final Logger logger = LogsCenter.getLogger(StaffChartWindow.class);
    private static final String FXML = "PieChartWindow.fxml";

    @FXML
    private PieChart pieChart;
    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public StaffChartWindow(Stage root) {
        super(FXML, root);
        Scene scene = new Scene(new Group());
        root.setTitle("Staff Preference Chart");
        root.setWidth(500);
        root.setHeight(500);

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(new PieChart.Data("Jane", 13),
                        new PieChart.Data("John", 25),
                        new PieChart.Data("Jason", 10),
                        new PieChart.Data("Alice", 22),
                        new PieChart.Data("Jay", 30));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Favorite Staffs Amongst Customers"); (
                (Group) scene.getRoot()).getChildren().add(chart);
        root.setScene(scene);
    }

    /**
     * Creates a new HelpWindow.
     */
    public StaffChartWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
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
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

}
