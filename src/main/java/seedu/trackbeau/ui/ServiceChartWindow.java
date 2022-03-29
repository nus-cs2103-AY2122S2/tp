package seedu.trackbeau.ui;

import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;
import seedu.trackbeau.commons.core.LogsCenter;

public class ServiceChartWindow extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(ServiceChartWindow.class);
    private static final String FXML = "PieChartWindow.fxml";

    @javafx.fxml.FXML
    private PieChart pieChart;
    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public ServiceChartWindow(Stage root) {
        super(FXML, root);
        //referenced from https://docs.oracle.com/javafx/2/charts/pie-chart.htm
        Scene scene = new Scene(new Group());
        root.setTitle("Service Preference Chart");
        root.setWidth(500);
        root.setHeight(500);

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Ageless Facial", 13),
                        new PieChart.Data("Eyebrow Shaping", 25),
                        new PieChart.Data("Student Facial", 10),
                        new PieChart.Data("Bioluminescence Facial", 22),
                        new PieChart.Data("Eye Treatment", 30));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Favorite Services Amongst Customers"); (
                (Group) scene.getRoot()).getChildren().add(chart);
        root.setScene(scene);
    }

    /**
     * Creates a new HelpWindow.
     */
    public ServiceChartWindow() {
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
        logger.fine("Showing service chart.");
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
