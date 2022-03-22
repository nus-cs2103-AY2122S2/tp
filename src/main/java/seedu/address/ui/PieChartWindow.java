package seedu.address.ui;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a pie chart page
 */
public class PieChartWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(PieChartWindow.class);
    private static final String FXML = "PieChartWindow.fxml";
    private ArrayList<String> faculties;
    private ArrayList<String> blocks;
    private TreeMap<String, Double> covidStatsByBlockData;
    private TreeMap<String, Double> positiveStatsByFacultyData;
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
     *
     * @param message Feedback by tracey with summary of the covid data
     */
    public PieChartWindow(String message) {
        this(new Stage());
        charts = new HBox();
        covidStatsByBlockData = new TreeMap<>();
        positiveStatsByFacultyData = new TreeMap<>();
        blocks = parseBlockLetter(message);
        faculties = parseFacultyName(message);
        execute();
    }

    /**
     * Parse the output message from SummariseCommand and gets the list of student's faculty in Tracey.
     *
     * @param message Feedback message to user from SummariseCommand
     * @return Array list containing student's faculty present in Tracey
     */
    private ArrayList<String> parseFacultyName(String message) {
        ArrayList<String> arrayList = new ArrayList<>();
        String[] splitMessage = message.split("In ");
        Stream.of(splitMessage).filter(string -> string.contains("faculty")).forEach(arrayList::add);
        return arrayList;
    }

    /**
     * Parse the output message from SummariseCommand and gets the list of student's block in Tracey.
     *
     * @param message Feedback message to user from SummariseCommand
     * @return Array list containing student's blocks present in Tracey
     */
    private ArrayList<String> parseBlockLetter(String message) {
        ArrayList<String> arrayList = new ArrayList<>();
        String[] splitMessage = message.split("In ");
        Stream.of(splitMessage).filter(string -> string.contains("block")).forEach(arrayList::add);
        return arrayList;
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
     * Collate the pie charts for each block into a H-box.
     */
    private void collateBlocksChart() {
        for (int i = 0; i < blocks.size(); i++) {
            String title = blocks.get(i).substring(0, 1); // example: A
            covidStatsByBlockData = getCovidStats(blocks, i);
            pieChart = makePieChart(covidStatsByBlockData, title);
            charts.getChildren().add(pieChart);
        }
    }

    /**
     * Create Pie Chart for hall containing Covid Positive statistics only, by Faculty.
     */
    private HBox createFacultyChartPositive() {
        HBox facChart = new HBox();
        for (int i = 0; i < faculties.size(); i++) {
            getPositiveStatsByFaculty(positiveStatsByFacultyData, faculties, i);
        }
        pieChart = makePieChart(positiveStatsByFacultyData, "Hall");
        facChart.getChildren().add(pieChart);
        facChart.setAlignment(Pos.CENTER);
        return facChart;
    }

    /**
     * Puts the parsed data from the array list of blocks in the hall
     * in Tracey into a treemap for processing of data for the pie chart.
     *
     * @param forms Array list containing students' covid status types in a block present in Tracey
     * @param index The specific form number to find the covid status types and number of students
     * @return A treemap containing key value a pair of status type and number of students
     */
    private TreeMap<String, Double> getCovidStats(ArrayList<String> forms, int index) {
        TreeMap<String, Double> treeMap = new TreeMap<>();
        String targetForm = forms.get(index);
        // spilt the form by lines
        String[] spiltTargetForm = targetForm.split(System.lineSeparator());
        // get data from the standardised forms
        for (int j = 1; j < 4; j++) {
            String[] spiltLine = spiltTargetForm[j].split(": ", 2);
            String status = spiltLine[0]; // Type of status
            int numOfStudents = Integer.parseInt(spiltLine[1].substring(0, 1));
            treeMap.put(status, (double) numOfStudents);
        }
        return treeMap;
    }

    /**
     * Puts the parsed data from the array list of faculty into a tree map
     * in Tracey into a treemap for processing of data for the pie chart.
     *
     * @param treeMap The treemap containing the faculty and number of students
     * @param form Array list containing student's faculty present in Tracey
     * @param i the specific form number to find the number of students who are positive
     */
    private void getPositiveStatsByFaculty(TreeMap<String, Double> treeMap, ArrayList<String> form, int i) {
        String targetForm = form.get(i);
        String faculty = targetForm.split(" ", 2)[0];
        String[] spiltTargetForm = targetForm.split("Covid Positive: ", 2);
        int numOfStudents = Integer.parseInt(spiltTargetForm[1].substring(0, 1));
        treeMap.put(faculty, (double) numOfStudents);
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
