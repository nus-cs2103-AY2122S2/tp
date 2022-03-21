package seedu.address.ui;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.address.model.person.Faculty;

public class UiSummarisePieChart {

    private String summariseMessage;
    private ArrayList<String> faculties;
    private ArrayList<Double> positiveStats;

    /**
     * Constructor for this class
     *
     * @param message Feedback message to user from SummariseCommand
     */
    public UiSummarisePieChart(String message) {
        summariseMessage = message;
        faculties = parseFacultyName(summariseMessage);
        positiveStats = parsePositivePercentage(summariseMessage);
    }

    /**
     * Show the pie chart to user when the user enters SummariseCommand. The pie chart shows the
     * percentage of positive cases for each student's faculty present in Tracey. The number of
     * elements in both array list for faculty and covid positive percentage for each faculty present
     * in Tracey must be the same.
     */
    public void showPieChartWindow() {
        int numOfFaculties = faculties.size();
        int numOfStats = positiveStats.size();

        assert numOfStats == numOfFaculties;

        TreeMap<String, Double> positiveStatsByFacultyData = getPositiveStatsByFacultyData(faculties, positiveStats);
        PieChart pieChart = makePieChart(positiveStatsByFacultyData);
        renderPieChartWindow(pieChart);

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
        for (int i = 0; i < splitMessage.length - 1; i++) {
            if (splitMessage[i + 1].equals("percent")) {
                arrayList.add(Double.parseDouble(splitMessage[i].split("\\)")[1]));
            }
        }
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
     * Opens a new window and renders the pie chart.
     *
     * @param pieChart A pie chart that shows covid positive percentage for each faculty
     */
    private void renderPieChartWindow(PieChart pieChart) {
        Stage newWindow = new Stage();
        VBox vbox = new VBox(pieChart);
        Scene scene = new Scene(vbox);
        newWindow.setScene(scene);
        newWindow.show();
    }

}
