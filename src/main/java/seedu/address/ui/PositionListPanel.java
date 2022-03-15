package seedu.address.ui;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.position.Description;
import seedu.address.model.position.Position;
import seedu.address.model.position.PositionName;
import seedu.address.model.position.PositionOpenings;
import seedu.address.model.position.Requirement;

/**
 * Panel containing the list of positions.
 */
public class PositionListPanel extends UiPart<Region> {
    private static final String FXML = "PositionListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PositionListPanel.class);

    @FXML
    private ListView<Position> positionListView;

    /**
     * Creates a {@code PositionListPanel} with the given {@code ObservableList}.
     */
    public PositionListPanel(ObservableList<Position> positionList) {
        super(FXML);

        Set<Requirement> reqs1 = new HashSet<>();
        reqs1.add(new Requirement("Java"));
        reqs1.add(new Requirement("C++"));

        Position tempPos1 = new Position(
                new PositionName("Senior Software Developer"),
                new Description("The highest paying job in the company\n"
                        + "More than 5 years experience"),
                new PositionOpenings("3"),
                reqs1);

        Set<Requirement> reqs2 = new HashSet<>();
        reqs2.add(new Requirement("Source Academy"));
        reqs2.add(new Requirement("C"));

        Position tempPos2 = new Position(
                new PositionName("Useless IT Intern"),
                new Description("Need to hire to fill the quota"),
                new PositionOpenings("1"),
                reqs2);

        Set<Requirement> reqs3 = new HashSet<>();
        reqs3.add(new Requirement("Sweep Floor"));
        reqs3.add(new Requirement("Wipe Window"));
        reqs3.add(new Requirement("Wash Toilet"));

        Position tempPos3 = new Position(
                new PositionName("Janitor"),
                new Description("Arguably the most important job"),
                new PositionOpenings("0"),
                reqs3);

        Position tempPos4 = new Position(
                new PositionName("Admin Officer"),
                new Description("Degree or Postgraduate holder with Major in Information Technology, "
                        + "Computer Science, or other similar focus, and a cumulative GPA of 3.5 and above"),
                new PositionOpenings("1"),
                new HashSet<>());

        List<Position> tempList = new ArrayList<>();
        tempList.add(tempPos1);
        tempList.add(tempPos2);
        tempList.add(tempPos3);
        tempList.add(tempPos4);
        ObservableList<Position> posList = FXCollections.observableList(tempList);

        positionListView.setItems(posList); // TO REPLACE WITH REAL LIST
        positionListView.setCellFactory(listView -> new PositionListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Position} using a {@code PositionCard}.
     */
    class PositionListViewCell extends ListCell<Position> {
        @Override
        protected void updateItem(Position position, boolean empty) {
            super.updateItem(position, empty);

            if (empty || position == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PositionCard(position, getIndex() + 1).getRoot());
            }
        }
    }
}
