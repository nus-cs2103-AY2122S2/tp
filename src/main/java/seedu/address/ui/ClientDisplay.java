package seedu.address.ui;

import java.util.Map;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import seedu.address.model.client.Client;
import seedu.address.model.policy.Policy;

/**
 * An UI component that displays information of select {@code Client} in the right panel
 */
public class ClientDisplay extends UiPart<Region> {

    private static final String FXML = "ClientDisplayPanel.fxml";
    private static final int PREFERENCE_CELL_HEIGHT = 48;
    private static final int POLICY_CELL_HEIGHT = 114;

    private final Client client;

    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label birthday;
    @FXML
    private Label lastContacted;
    @FXML
    private Label email;
    @FXML
    private Label preferencesHeader;
    @FXML
    private ListView<Map.Entry<String, String>> preferences;
    @FXML
    private StackPane preferencesContainer;
    @FXML
    private ListView<Policy> policyListView;
    @FXML
    private Label note;
    @FXML
    private VBox clientDisplayCard;

    /**
     * Creates a {@code ClientDisplay} with the given {@code Client} to display.
     */
    public ClientDisplay(Client client) {
        super(FXML);
        assert client != null;
        this.client = client;
        name.setText(client.getName().fullName);
        phone.setText(client.getPhone().value);
        email.setText(client.getEmail().value);
        address.setText(client.getAddress().value);
        if (client.getBirthday() != null) {
            birthday.setText(client.getBirthday().toString());
        } else {
            birthday.setText("-");
        }
        if (client.getLastContacted() != null) {
            lastContacted.setText("Last contacted: " + client.getLastContacted().toString());
        } else {
            lastContacted.setText("Last contacted: -");
        }
        ObservableList<Map.Entry<String, String>> preferenceList = client.getPreferenceMap().asObservableList();
        preferences.setItems(preferenceList);
        preferences.setCellFactory(listView -> new PreferenceListViewCell());
        preferences.setMinHeight(preferenceList.size() * PREFERENCE_CELL_HEIGHT);
        int labelIndex = clientDisplayCard.getChildren().indexOf(preferencesHeader);
        clientDisplayCard.getChildren().add(labelIndex + 1, preferences);

        ObservableList<Policy> policyList = client.getPolicies().asUnmodifiableObservableList();
        policyListView.setItems(policyList);
        policyListView.setCellFactory(listView -> new PolicyListViewCell());
        policyListView.setMinHeight(policyList.size() * POLICY_CELL_HEIGHT);
        clientDisplayCard.getChildren().add(policyListView);

        note.setText(client.getNote().value);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Policy} using a {@code PolicyCard}.
     */
    class PreferenceListViewCell extends ListCell<Map.Entry<String, String>> {
        @Override
        protected void updateItem(Map.Entry<String, String> preference, boolean empty) {
            super.updateItem(preference, empty);

            if (empty || preference == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PreferenceRow(preference).getRoot());
            }
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Policy} using a {@code PolicyCard}.
     */
    class PolicyListViewCell extends ListCell<Policy> {
        @Override
        protected void updateItem(Policy policy, boolean empty) {
            super.updateItem(policy, empty);

            if (empty || policy == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PolicyCard(policy, getIndex() + 1).getRoot());
            }
        }
    }
}

