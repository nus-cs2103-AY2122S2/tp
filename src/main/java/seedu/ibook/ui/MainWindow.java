package seedu.ibook.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.ibook.commons.core.LogsCenter;
import seedu.ibook.logic.Logic;
import seedu.ibook.logic.commands.CommandResult;
import seedu.ibook.logic.commands.exceptions.CommandException;
import seedu.ibook.logic.parser.exceptions.ParseException;
import seedu.ibook.model.item.Item;
import seedu.ibook.model.product.Product;
import seedu.ibook.model.product.filters.AttributeFilter;
import seedu.ibook.ui.control.ControlBox;
import seedu.ibook.ui.popup.PopupHandler;
import seedu.ibook.ui.table.ProductTable;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private final Stage primaryStage;
    private final Logic logic;

    private MenuToolbar menuToolbar;
    private CommandBox commandBox;
    private ResultWindow resultWindow;
    private ControlBox controlBox;
    private ProductTable productTable;

    private PopupHandler popupHandler;

    @FXML
    private VBox mainContent;

    /**
     * Initializes a {@code MainWindow}.
     *
     * @param primaryStage The {@code Stage} of the application.
     * @param logic The main code {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;
    }

    /**
     * Shows the stage.
     */
    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    public void handleExit() {
        primaryStage.hide();
    }

    /**
     * Gets the primary stage.
     * @return The primary stage.
     */
    Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Fills up the inner part of this window.
     */
    void fillInnerParts() {
        ObservableList<Node> children = mainContent.getChildren();

        popupHandler = new PopupHandler(this);

        menuToolbar = new MenuToolbar(this);
        children.add(menuToolbar.getRoot());

        commandBox = new CommandBox(this);
        children.add(commandBox.getRoot());

        resultWindow = new ResultWindow(this);
        children.add(resultWindow.getRoot());

        controlBox = new ControlBox(this);
        children.add(controlBox.getRoot());

        productTable = new ProductTable(this);
        children.add(productTable.getRoot());
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.ibook.logic.Logic#execute(String)
     */
    public void executeCommand(String commandText) {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultWindow.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                menuToolbar.handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            hidePopup();
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            setError(e.getMessage());
        }
    }

    /**
     * Populate the filters used for the product list.
     */
    public void populateFilters() {
        controlBox.populateFilters();
    }

    /**
     * Shows the popup window for adding product.
     */
    public void showPopupAddProduct() {
        popupHandler.showPopupAddProduct();
    }

    /**
     * Shows the popup window for updating product.
     */
    public void showPopupUpdateProduct(int index, Product product) {
        popupHandler.showPopupUpdateProduct(index, product);
    }

    /**
     * Shows the popup window for deleting product.
     */
    public void showPopupDeleteProduct(int index, Product product) {
        popupHandler.showPopupDeleteProduct(index, product);
    }

    /**
     * Shows the popup window for adding item.
     */
    public void showPopupAddItem(int index, Product product) {
        popupHandler.showPopupAddItem(index, product);
    }

    /**
     * Shows the popup window for managing item.
     */
    public void showPopupManageItem(int productIndex, int itemIndex,
                                    Product product, Item item) {
        popupHandler.showPopupManageItem(productIndex, itemIndex, product, item);
    }

    /**
     * Gets the filtered list of {@code Product} from {@code Logic}.
     *
     * @return Get a filtered list of {@code Product}.
     */
    public ObservableList<Product> getFilteredIBook() {
        return logic.getFilteredIBook();
    }

    /**
     * Gets the filtered list of {@code AttributeFilter} from {@code Logic}.
     *
     * @return Get a filtered list of {@code AttributeFilter}.
     */
    public ObservableList<AttributeFilter> getProductFilters() {
        return logic.getProductFilters();
    }

    /**
     * Removes a filter for the product list.
     */
    public void removeProductFilter(AttributeFilter filter) {
        logic.removeProductFilter(filter);
        populateFilters();
    }

    private void hidePopup() {
        popupHandler.hidePopup();
    }

    private void setError(String message) {
        if (popupHandler.isShowing()) {
            popupHandler.setFeedbackToUser(message);
        } else {
            resultWindow.setFeedbackToUser(message);
        }
    }
}
