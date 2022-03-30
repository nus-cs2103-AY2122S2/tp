package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.person.InsurancePackage;

/**
 * Controller for a package page
 */
public class PackageWindow extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(PackageWindow.class);
    private static final String FXML = "PackageWindow.fxml";
    private Logic logic;

    @FXML
    private StackPane packageListPanelPlaceholder;
    @FXML
    private Label helpMessage;

    // Independent Ui parts residing in this Ui container
    private PackageListPanel packageListPanel;

    /**
     * Creates a new PackageWindow.
     *
     * @param root Stage to use as the root of the PackageWindow.
     */
    public PackageWindow(Stage root) {
        super(FXML, root);
    }

    /**
     * Creates a new PackageWindow.
     */
    public PackageWindow(Logic logic) {
        this(new Stage());
        this.logic = logic;
    }

    /**
     * Shows the package window.
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
        logger.fine("Showing package page about the insurance packages.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Fills up all the placeholders of this window.
     */
    public void fillInnerParts() {
        ObservableList<InsurancePackage> internalList =
                FXCollections.observableArrayList(logic.getAllPackages().getPackagesList());
        ObservableList<InsurancePackage> internalUnmodifiableList =
                FXCollections.unmodifiableObservableList(internalList);
        ObservableList<InsurancePackage> packageList = new FilteredList<>(internalUnmodifiableList);
        packageListPanel = new PackageListPanel(packageList);
        packageListPanelPlaceholder.getChildren().add(packageListPanel.getRoot());
    }

    /**
     * Returns true if the package window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the package window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the package window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}
