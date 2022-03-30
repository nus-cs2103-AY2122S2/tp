package seedu.address.ui;

import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
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
    private TreeMap<String, Integer> covidStatsByBlockData;
    private TreeMap<String, Integer> positiveStatsByFacultyData;
    private TreeMap<String, TreeMap<String, Integer>> covidStatsDataByBlocks;
    private PieChart pieChart;
    private Scene chartScene;
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
        covidStatsDataByBlocks = SummariseCommand.getCovidStatsByBlockDataList();
        covidStatsByBlockData = new TreeMap<>();
        positiveStatsByFacultyData = SummariseCommand.getPositiveStatsByFacultyData();
        execute();
    }

    /**
     * Organises the data into Pie Charts in a new window.
     */
    private void execute() {
        collateBlocksChart();
        HBox facultyChart = createFacultyChartPositive();
        VBox allPieCharts = new VBox(charts);
        allPieCharts.getChildren().add(facultyChart);
        chartScene = makeChartScene(allPieCharts);
        this.getRoot().setScene(chartScene);
    }

    /**
     * Collates the pie charts for each block into a H-box by stacking the charts together.
     * Pie charts are created one at a time by blocks. ie, if there are 5 blocks, 5 pie charts are created.
     */
    private void collateBlocksChart() {
        for (Map.Entry<String, TreeMap<String, Integer>> entry : covidStatsDataByBlocks.entrySet()) {
            String title = entry.getKey(); // ie. A, B, C, D, E
            covidStatsByBlockData = entry.getValue();
            if (!covidStatsByBlockData.isEmpty()) {
                pieChart = makePieChart(covidStatsByBlockData, title);
            }
            charts.getChildren().add(pieChart);
        }
    }

    /**
     * Creates Bar Chart for hall containing Covid Positive statistics only, by Faculty.
     */
    private HBox createFacultyChartPositive() {
        if (positiveStatsByFacultyData.isEmpty()) {
            // No positive students to create chart
            return new HBox();
        }
        HBox facChart = new HBox();
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Covid Positive by Faculty");
        xAxis.setLabel("Faculty");
        yAxis.setLabel("Number of Students");
        barChart.setBarGap(50);
        barChart.setCategoryGap(150);

        for (Map.Entry<String, Integer> entry : positiveStatsByFacultyData.entrySet()) {
            XYChart.Series<String, Number> fac = new XYChart.Series<>();
            fac.setName(entry.getKey());
            fac.getData().add(new XYChart.Data<>("", entry.getValue()));
            barChart.getData().add(fac);
        }
        facChart.getChildren().add(barChart);
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
    private PieChart makePieChart(TreeMap<String, Integer> data, String title) {
        TreeMap<String, Integer> treeMap = data;
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        PieChart pieChart = new PieChart(pieChartData);
        pieChart.setTitle("Covid overview in " + title);
        for (Map.Entry<String, Integer> entry : treeMap.entrySet()) {
            PieChart.Data statusType = new PieChart.Data(entry.getKey(), entry.getValue());
            statusType.setName(String.format("%s (%.0f)", statusType.getName(),
                    statusType.pieValueProperty().getValue()));
            pieChartData.add(statusType);
        }
        return pieChart;
    }

    /**
     * Creates the scene which contains the pie chart.
     *
     * @param pieCharts collated group of charts to be displayed
     * @return Scene containing the pie chart
     */
    private Scene makeChartScene(VBox pieCharts) {
        chartScene = new Scene(pieCharts);
        return chartScene;
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
