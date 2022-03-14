package seedu.trackbeau.ui;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.trackbeau.model.customer.Customer;

public class StatisticsPanel extends UiPart<Region> {
    private static final String FXML = "StatisticsPanel.fxml";

    @FXML
    private ListView<Customer> statisticsView;

    public StatisticsPanel() {
        super(FXML);
    }
}
