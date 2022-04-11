package seedu.trackbeau.ui.charts;

import java.util.logging.Logger;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import seedu.trackbeau.commons.core.LogsCenter;
import seedu.trackbeau.ui.UiPart;

public class MonthlyCustomerChartWindow extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(MonthlyCustomerChartWindow.class);
    //FXML code is referenced from https://github.com/AY2021S1-CS2103T-T09-2 CalorieGraph.fxml
    private static final String FXML = "LineChartWindow.fxml";

    @javafx.fxml.FXML
    private LineChart lineChart;

    /**
     * Creates a new Monthly Customer Chart Window.
     *
     * @param root Stage to use as the root of the Monthly Customer Chart Window.
     */
    public MonthlyCustomerChartWindow(Stage root, int maxMonthlyCustomerCount,
                                      int totalCustomerCount) {
        super(FXML, root);
        //code referenced from https://docs.oracle.com/javafx/2/charts/line-chart.htm
        root.setTitle("Customer Gained This Year By Month");
        root.setWidth(500);
        root.setHeight(500);
        lineChart = this.createBaseLineChart(maxMonthlyCustomerCount, totalCustomerCount);
        Scene scene = new Scene(lineChart, 800, 600);
        root.setScene(scene);
    }

    /**
     * Creates a new MonthlyCustomerChartWindow.
     */
    public MonthlyCustomerChartWindow(int maxMonthlyCustomerCount, int totalCustomerCount) {
        this(new Stage(), maxMonthlyCustomerCount, totalCustomerCount);
    }

    /**
     * Shows the MonthlyCustomerChart window.
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
        logger.fine("Showing monthly customer chart.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    public LineChart getLineChart() {
        return this.lineChart;
    }

    /**
     * Creates a template for monthly customer chart with dummy data.
     * @param maxMonthlyCustomerCount which is the highest count of customer gained in a month
     * @param totalCustomerCount which is the total customers saved in TrackBeau (not limited to current year)
     * @return
     */
    public LineChart createBaseLineChart(int maxMonthlyCustomerCount, int totalCustomerCount) {
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        //set 12 months on the x axis
        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(1);
        xAxis.setUpperBound(12);
        xAxis.setTickUnit(1);
        xAxis.setMinorTickVisible(false);
        xAxis.setLabel("Month");

        final NumberAxis yAxis = new NumberAxis();
        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(1);
        //+ 1 to give space for easy viewing of chart values
        yAxis.setUpperBound(maxMonthlyCustomerCount + 1);
        yAxis.setTickUnit(1);
        yAxis.setMinorTickVisible(false);

        //creating the chart
        LineChart baseLineChart = new LineChart<Number, Number>(xAxis, yAxis);
        baseLineChart.setTitle(String.format("Total Customers in TrackBeau: %d", totalCustomerCount));
        XYChart.Series series = new XYChart.Series();
        series.setName("Customers registered into the Salon");
        baseLineChart.getData().add(series);

        return baseLineChart;
    }

    /**
     * Returns true if the MonthlyCustomerChart window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the MonthlyCustomerChart window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the MonthlyCustomerChart window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}
