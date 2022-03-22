package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;

/**
 * The Statistics Window. Provides the basic application layout containing
 * a menu bar and space to display data in a pie chart.
 */
public class StatisticsWindow extends UiPart<Stage> {
    private static final String FXML = "StatisticsWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Logic logic;

    @FXML
    private PieChart pieChart;

    /**
     * Sets up Logic instance in StatisticsWindow
     */
    public StatisticsWindow(Logic logic) {
        super(FXML);
        this.logic = logic;
    }

    /**
     * Calls methods to fill in data into pie chart and displays window
     */
    public void show() {
        logger.fine("Showing Statistics Window.");
        fillPieChart();
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the Statistics window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the Statistics window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the Statistics window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Fills up data into pie chart.
     */
    void fillPieChart() {

    }
}
