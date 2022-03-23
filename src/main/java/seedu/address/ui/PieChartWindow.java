package seedu.address.ui;

import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.SummariseCommand;

/**
 * Controller for a pie chart page.
 */
public class PieChartWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(PieChartWindow.class);
    private static final String FXML = "PieChartWindow.fxml";
    private TreeMap<String, Double> covidStatsByBlockData;
    private TreeMap<String, Double> positiveStatsByFacultyData;
    private TreeMap<String, TreeMap<String, Double>> covidStatsDataByBlocks;
    private PieChart pieChart;
    private Scene pieChartScene;
    private HBox charts;

    /**
     * Creates a new PieChartWindow.
     *
     * @param root Stage to use as the root of the PieChartWindow.
     */
    public PieChartWindow(Stage root) {
        super(FXML, root);
    }

    /**
     * Creates a new PieChartWindow.
     * Obtain the organised covid statuses from SummariseCommand.
     * Pie Charts by blocks and for faculties are created.
     */
    public PieChartWindow() {
        this(new Stage());
        charts = new HBox();
        covidStatsByBlockData = new TreeMap<>();
        covidStatsDataByBlocks = SummariseCommand.getCovidStatsByBlockDataList();
        positiveStatsByFacultyData = SummariseCommand.getPositiveStatsByFacultyData();
        execute();
    }

    /**
     * Organise the data into Pie Charts in a new window.
     */
    private void execute() {
        collateBlocksChart();
        HBox facultyChart = createFacultyChartPositive();
        VBox allPieCharts = new VBox(charts);
        allPieCharts.getChildren().add(facultyChart);
        pieChartScene = makePieChartScene(allPieCharts);
        this.getRoot().setScene(pieChartScene);
    }

    /**
     * Collate the pie charts for each block into a H-box by stacking the charts together.
     * Pie charts are created one at a time by blocks. ie, if there are 5 blocks, 5 pie charts are created.
     */
    private void collateBlocksChart() {
        for (Map.Entry<String, TreeMap<String, Double>> entry : covidStatsDataByBlocks.entrySet()) {
            String title = entry.getKey(); // ie. A, B, C, D, E
            covidStatsByBlockData = entry.getValue();
            pieChart = makePieChart(covidStatsByBlockData, title);
            charts.getChildren().add(pieChart);
        }
    }

    /**
     * Create Pie Chart for hall containing Covid Positive statistics only, by Faculty.
     */
    private HBox createFacultyChartPositive() {
        HBox facChart = new HBox();
        pieChart = makePieChart(positiveStatsByFacultyData, "Hall");
        facChart.getChildren().add(pieChart);
        facChart.setAlignment(Pos.CENTER);
        return facChart;
    }

    /**
     * A method to produce the pie chart that shows covid status breakdown.
     *
     * @param data Covid positive percentage for each faculty present in Tracey
     * @param title Label of this particular pie chart
     * @return A pie chart that shows covid status breakdown
     */
    private PieChart makePieChart(TreeMap<String, Double> data, String title) {
        TreeMap<String, Double> treeMap = data;
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        PieChart pieChart = new PieChart(pieChartData);
        pieChart.setTitle("Covid overview in " + title);
        for (Map.Entry<String, Double> entry : treeMap.entrySet()) {
            pieChartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }
        return pieChart;
    }

    /**
     * Creates the scene which contains the pie chart.
     *
     * @param pieCharts collated group of charts to be displayed
     * @return Scene containing the pie chart
     */
    private Scene makePieChartScene(VBox pieCharts) {
        pieChartScene = new Scene(pieCharts);
        return pieChartScene;
    }

    /**
     * Shows the pie chart window.
     *
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
        logger.fine("Showing pie chart page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the pie chart window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the pie chart window.
     */
    public void hide() {
        getRoot().hide();
    }

}
