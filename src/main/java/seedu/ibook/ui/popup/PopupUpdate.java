package seedu.ibook.ui.popup;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import seedu.ibook.logic.commands.UpdateCommand;
import seedu.ibook.logic.parser.CliSyntax;
import seedu.ibook.model.product.Product;
import seedu.ibook.ui.MainWindow;

/**
 * Popup responsible to update product.
 */
public class PopupUpdate extends Popup {

    private static final String FXML = "popup/PopupUpdate.fxml";

    private int index;

    @FXML
    private TextField name;
    @FXML
    private TextField category;
    @FXML
    private TextField price;
    @FXML
    private TextArea description;

    /**
     * Initializes a new {@code popupUpdate} window.
     *
     * @param mainWindow The {@code MainWindow} that this component resides on.
     */
    public PopupUpdate(MainWindow mainWindow) {
        super(FXML, mainWindow);
    }

    /**
     * Shows the popup window.
     *
     * @param index Index of the product.
     * @param product The product to be updated.
     */
    public void show(int index, Product product) {
        super.show();
        this.index = index;
        name.setText(product.getName().toString());
        category.setText(product.getCategory().toString());
        price.setText(product.getPrice().toString());
        description.setText(product.getDescription().toString());
    }

    @FXML
    private void handleUpdateProduct() {
        String commandText = UpdateCommand.COMMAND_WORD
                + " " + index
                + " " + CliSyntax.PREFIX_NAME.getPrefix()
                + name.getText()
                + " " + CliSyntax.PREFIX_CATEGORY.getPrefix()
                + category.getText()
                + " " + CliSyntax.PREFIX_PRICE.getPrefix()
                + price.getText()
                + " " + CliSyntax.PREFIX_DESCRIPTION.getPrefix()
                + description.getText();

        execute(commandText);
    }

}
