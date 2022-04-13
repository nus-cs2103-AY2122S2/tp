package seedu.tinner.ui;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.tinner.model.company.Company;
import seedu.tinner.model.role.Role;

/**
 * An UI component that displays information of a {@code Company}.
 */
public class CompanyCard extends UiPart<Region> {

    private static final String FXML = "CompanyCard.fxml";
    private static final int STAR_ICON_DIMENSIONS = 15;
    private static final Image STAR_ICON = new Image("/images/star.png", true);

    private static final String BLACK_BG = "-fx-background-color: black;";
    private static final String WHITE_BG = "-fx-background-color: white; -fx-text-fill: black;";
    private static final String GREEN_BG = "-fx-background-color: #36bf00";
    private static final String PURPLE_BG = "-fx-background-color: #b114c9;";
    private static final String ORANGE_BG = "-fx-background-color: #e88700;";
    private static final String RED_BG = "-fx-background-color: #d62700;";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on CompanyList level 4</a>
     */

    public final Company company;

    private RoleListPanel roleListPanel;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private ImageView frame;
    @FXML
    private FlowPane roleTags;
    @FXML
    private StackPane roleListPanelPlaceholder;

    /**
     * Creates a {@code CompanyCard} with the given {@code Company} and index to display.
     */
    public CompanyCard(Company company, int displayedIndex) {
        super(FXML);
        this.company = company;
        id.setText(displayedIndex + ". ");
        name.setText(company.getName().value);

        String phoneField = company.getPhone().value;
        String addressField = company.getAddress().value;
        String emailField = company.getEmail().value;
        boolean isFavourited = company.getFavouriteStatus().value;

        phone.setText(phoneField);
        address.setText(addressField);
        address.setWrapText(true);
        email.setText(emailField);
        email.setWrapText(true);
        setStarIcon();

        phone.setManaged(!phoneField.isEmpty());
        address.setManaged(!addressField.isEmpty());
        email.setManaged(!emailField.isEmpty());
        frame.setVisible(isFavourited);

        ObservableList<Role> roleList = company.getRoleManager().getFilteredRoleList();

        setRoleTags(roleList);
        setRoleListPanelPlaceholder(roleList);
        updateView(roleList);

        roleList.addListener((ListChangeListener<Role>) change -> updateView(roleList));
    }

    /**
     * Updates <code>roleListPanelPlaceholder</code> to reflect the contents of the current <code>roleList</code>
     */
    public void setRoleListPanelPlaceholder(ObservableList<Role> roleList) {
        roleListPanel = new RoleListPanel(roleList);
        roleListPanelPlaceholder.getChildren().add(roleListPanel.getRoot());
    }

    /**
     * Populates <code>roleTags</code> with names of roles tagged to the company represented by this
     * <code>CompanyCard</code> and color code the labels accordingly to the statuses.
     */
    public void setRoleTags(ObservableList<Role> roleList) {
        roleTags.getChildren().clear();

        roleList.forEach(roleTag -> {
            Label label = new Label(roleTag.getName().value);
            setRoleTagColor(label, roleTag.getStatus().value);
            roleTags.getChildren().add(label);

        });
    }

    private void setRoleTagColor(Label label, String status) {
        switch (status) {
        case "applying":
            label.setStyle(WHITE_BG);
            break;
        case "offered":
            label.setStyle(GREEN_BG);
            break;
        case "pending":
            label.setStyle(ORANGE_BG);
            break;
        case "rejected":
            label.setStyle(RED_BG);
            break;
        case "complete":
            label.setStyle(BLACK_BG);
            break;
        default:
            label.setStyle(PURPLE_BG);
        }
    }

    /**
     * Initialises the star icon for an instance of a company card
     */
    public void setStarIcon() {
        frame.setImage(STAR_ICON);
        frame.setFitHeight(STAR_ICON_DIMENSIONS);
        frame.setFitWidth(STAR_ICON_DIMENSIONS);
    }

    /**
     * Updates the <code>roleListPanelPlaceholder</code> and <code>roleTags</code> with the given
     * <code>roleList</code>
     */
    public void updateView(ObservableList<Role> roleList) {
        boolean roleListIsNotEmpty = !roleList.isEmpty();

        setRoleTags(roleList);
        roleTags.setManaged(roleListIsNotEmpty);
        roleListPanelPlaceholder.setManaged(roleListIsNotEmpty);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CompanyCard)) {
            return false;
        }

        // state check
        CompanyCard card = (CompanyCard) other;
        return id.getText().equals(card.id.getText())
                && company.equals(card.company);
    }
}
