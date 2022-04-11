package seedu.address.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;


/**
 * Represents the control of player aggregated data.
 */
public class PlayerStatisticsPanel extends UiPart<Region> {
    private static final String FXML = "PlayerStatisticsPanel.fxml";
    private static final List<Tag> positionTags =
            List.of(new Tag("PG"), new Tag("SG"), new Tag("SF"),
                    new Tag("PF"), new Tag("C"));

    @FXML
    private PieChart playerStatisticsPieChart;

    private ObservableList<Person> persons;

    /**
     * Creates a new panel.
     *
     * @param persons
     */
    public PlayerStatisticsPanel(ObservableList<Person> persons) {
        super(FXML);
        this.persons = persons;
        populatePieChart();
    }

    /**
     * Updates the pie chart.
     *
     * @param persons
     */
    public void update(ObservableList<Person> persons) {
        this.persons = persons;
        populatePieChart();

        for (Node node : playerStatisticsPieChart.lookupAll(".chart-legend-item")) {
            if (node instanceof Label) {
                Label labelNode = (Label) node;
                labelNode.setWrapText(true);
                labelNode.setManaged(true);
                labelNode.setPrefWidth(100);
            }
        }
    }

    // Reused from https://github.com/AY1920S2-CS2103T-W12-3/
    // main/blob/master/src/main/java/seedu/address/ui/WalletStatisticsPanel.java with modifications.
    private void populatePieChart() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        Map<Tag, Integer> positions = new HashMap<>();
        for (Tag tag : positionTags) {
            positions.put(tag, 0);
        }
        int numOfUnclassifiedPlayers = 0;

        for (Person p : this.persons) {
            boolean isClassified = false;

            for (Tag tag : positionTags) {
                if (p.getTags().contains(tag)) {
                    positions.replace(tag, positions.get(tag) + 1);
                    isClassified = true;
                }
            }

            if (!isClassified) {
                numOfUnclassifiedPlayers++;
            }
        }

        for (Tag tag : positionTags) {
            PieChart.Data tempData = new PieChart.Data(tag.tagName, positions.get(tag));
            playerStatisticsPieChart.layout();
            pieChartData.add(tempData);
        }

        PieChart.Data tempData = new PieChart.Data("Unclassified", numOfUnclassifiedPlayers);
        playerStatisticsPieChart.layout();
        pieChartData.add(tempData);

        pieChartData.forEach(data -> {
            System.out.println(data); });

        pieChartData.forEach(data -> data.nameProperty().bind(Bindings.concat(data.getName(),
                String.format(" - %d", (int) data.getPieValue()))));
        playerStatisticsPieChart.setData(pieChartData);

    }
}
