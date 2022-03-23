package seedu.address.ui;

import static javafx.collections.FXCollections.observableArrayList;

import java.util.logging.Logger;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
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
    private int numberOfPersonsInNorth;
    private int numberOfPersonsInSouth;
    private int numberOfPersonsInEast;
    private int numberOfPersonsInWest;
    private int numberOfPersonsInCentral;

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
     * Closes the Statistics window.
     */
    public void close() {
        getRoot().close();
    }

    /**
     * Fills up data into pie chart.
     */
    public void fillPieChart() {
        numberOfPersonsInNorth = logic.getPersonsBasedOnRegion("North");
        numberOfPersonsInSouth= logic.getPersonsBasedOnRegion("South");
        numberOfPersonsInEast = logic.getPersonsBasedOnRegion("East");
        numberOfPersonsInWest = logic.getPersonsBasedOnRegion("West");
        numberOfPersonsInCentral = logic.getPersonsBasedOnRegion("Central");

        ObservableList<PieChart.Data> pieChartData = observableArrayList(
                new PieChart.Data("North", numberOfPersonsInNorth),
                new PieChart.Data("South", numberOfPersonsInSouth),
                new PieChart.Data("East", numberOfPersonsInEast),
                new PieChart.Data("West", numberOfPersonsInWest),
                new PieChart.Data("Central", numberOfPersonsInCentral)
        );

        pieChartData.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(
                                data.getName(), ": ", data.pieValueProperty()
                        )
                ));
        pieChart.getData().addAll(pieChartData);
    }
}
