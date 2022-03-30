package seedu.ibook.ui.popup;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import seedu.ibook.logic.commands.AddCommand;
import seedu.ibook.logic.parser.CliSyntax;
import seedu.ibook.ui.MainWindow;

/**
 * Popup responsible to add product.
 */
public class PopupAddProduct extends Popup {

    private static final String FXML = "popup/PopupAddProduct.fxml";

    @FXML
    private TextField name;
    @FXML
    private TextField category;
    @FXML
    private TextField price;
    @FXML
    private TextArea description;

    /**
     * Initializes a new {@code PopupAdd} window.
     *
     * @param mainWindow The {@code MainWindow} that this component resides on.
     */
    public PopupAddProduct(MainWindow mainWindow) {
        super(FXML, mainWindow);
    }

    /**
     * Shows the popup window.
     */
    @Override
    public void show() {
        super.show();
        name.setText("");
        category.setText("");
        price.setText("");
        description.setText("");
    }

    @FXML
    private void handleAddProduct() {
        String commandText = AddCommand.COMMAND_WORD
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
