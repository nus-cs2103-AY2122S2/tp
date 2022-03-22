package seedu.address.ui;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Faculty;

/**
 * Controller for a pie chart page
 */
public class PieChartWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(PieChartWindow.class);
    private static final String FXML = "PieChartWindow.fxml";
    private ArrayList<String> faculties;
    private ArrayList<Double> positiveStats;
    private TreeMap<String, Double> positiveStatsByFacultyData;
    private PieChart pieChart;
    private VBox pieChartContainer;
    private Scene pieChartScene;

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
     */
    public PieChartWindow(String message) {
        this(new Stage());
        faculties = parseFacultyName(message);
        positiveStats = parsePositivePercentage(message);
        positiveStatsByFacultyData = getPositiveStatsByFacultyData(faculties, positiveStats);
        pieChart = makePieChart(positiveStatsByFacultyData);
        pieChartScene = makePieChartScene(pieChart);
        this.getRoot().setScene(pieChartScene);
    }

    /**
     * Parse the output message from SummariseCommand and gets the list of student's faculty in Tracey.
     *
     * @param message Feedback message to user from SummariseCommand
     * @return Array list containing student's faculty present in Tracey
     */
    private ArrayList<String> parseFacultyName(String message) {
        ArrayList<String> arrayList = new ArrayList<>();
        String[] splitMessage = message.split(" ");
        Stream.of(splitMessage).filter(string -> Faculty.isValidFaculty(string))
                .forEach(string -> arrayList.add(string));
        return arrayList;
    }

    /**
     * Parse the output message from SummariseCommand and gets the list of covid positive percentage from each
     * faculty present in Tracey.
     *
     * @param message Feedback message to user from SummariseCommand
     * @return Array list containing covid positive percentage from each faculty present in Tracey.
     */
    private ArrayList<Double> parsePositivePercentage(String message) {
        ArrayList<Double> arrayList = new ArrayList<>();
        String[] splitMessage = message.split(" ");
        String validationRegex = "[0-9]*\\.[0-9]{2}$";

        // filters the index where the split message with REGEX ')' has length of 2 i.e. [student(s, 100.00]
        Stream.of(splitMessage).filter(msg -> msg.split("\\)").length == 2)
                // filters the index where the second element is a percentage i.e. 100.00
                .filter(msg -> msg.split("\\)")[1].trim().matches(validationRegex))
                // adds each percentage to the array list
                .forEach(msg -> arrayList.add(Double.parseDouble(msg.split("\\)")[1])));

        return arrayList;
    }

    /**
     * Puts the parsed data from the two array lists of faculty and covid positive percentage of faculty present
     * in Tracey into a treemap for processing of data for the pie chart.
     *
     * @param faculties     Array list containing student's faculty present in Tracey
     * @param positiveStats Array list containing covid positive percentage from each faculty present in Tracey.
     * @return A treemap containing key value a pair of faculty and covid positive percentage.
     */
    private TreeMap<String, Double> getPositiveStatsByFacultyData(
            ArrayList<String> faculties, ArrayList<Double> positiveStats) {
        assert faculties.size() == positiveStats.size();
        int numberOfStudents = faculties.size();
        TreeMap<String, Double> treeMap = new TreeMap<>();
        for (int i = 0; i < numberOfStudents; i++) {
            treeMap.put(faculties.get(i), positiveStats.get(i));
        }
        return treeMap;
    }

    /**
     * A method to produce the pie chart that shows positive percentage by faculty.
     *
     * @param data Covid positive percentage for each faculty present in Tracey
     * @return A pie chart that shows covid positive percentage for each faculty
     */
    private PieChart makePieChart(TreeMap<String, Double> data) {
        TreeMap<String, Double> treeMap = data;
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList();
        PieChart pieChart = new PieChart(pieChartData);
        pieChart.setTitle("Covid positive percentage by faculty");
        for (Map.Entry<String, Double> entry : treeMap.entrySet()) {
            pieChartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }
        return pieChart;
    }

    /**
     * Creates the scene which contains the pie chart.
     * @param pieChart
     * @return Scene containing the pie chart
     */
    private Scene makePieChartScene(PieChart pieChart) {
        pieChartContainer = new VBox(pieChart);
        pieChartScene = new Scene(pieChartContainer);
        return pieChartScene;
    }

    /**
     * Shows the pie chart window.
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
