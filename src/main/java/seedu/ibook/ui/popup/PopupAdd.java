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
public class PopupAdd extends Popup {

    private static final String FXML = "popup/PopupAdd.fxml";

    @FXML
    private TextField name;
    @FXML
    private TextField category;
    @FXML
    private TextField price;
    @FXML
    private TextArea description;

    /**
     * Initializes a new {@code popupAdd} window.
     *
     * @param mainWindow The {@code MainWindow} that this component resides on.
     */
    public PopupAdd(MainWindow mainWindow) {
        super(FXML, mainWindow);
    }

    @FXML
    private void handleCreateProduct() {
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
