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
    public MonthlyCustomerChartWindow(Stage root) {
        super(FXML, root);
        //code referenced from https://docs.oracle.com/javafx/2/charts/line-chart.htm
        root.setTitle("Customer Gained This Year By Month");
        root.setWidth(500);
        root.setHeight(500);
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Month");
        //creating the chart
        lineChart = new LineChart<Number, Number>(xAxis, yAxis);
        lineChart.setTitle("Customer Gained This Year By Month");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Customers registered into the Salon");
        Scene scene = new Scene(lineChart, 800, 600);
        lineChart.getData().add(series);
        root.setScene(scene);
    }

    /**
     * Creates a new MonthlyCustomerChartWindow.
     */
    public MonthlyCustomerChartWindow() {
        this(new Stage());
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
