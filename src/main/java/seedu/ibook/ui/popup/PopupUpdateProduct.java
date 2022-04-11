package seedu.ibook.ui.popup;

import static seedu.ibook.commons.util.StringUtil.escapeCharacter;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import seedu.ibook.logic.commands.product.UpdateCommand;
import seedu.ibook.logic.parser.CliSyntax;
import seedu.ibook.model.product.Product;
import seedu.ibook.ui.MainWindow;

/**
 * Popup responsible to update product.
 */
public class PopupUpdateProduct extends Popup {

    private static final String FXML = "popup/PopupUpdateProduct.fxml";

    private int index;

    @FXML
    private TextField name;
    @FXML
    private TextField category;
    @FXML
    private TextField price;
    @FXML
    private TextField discountRate;
    @FXML
    private TextField discountStart;
    @FXML
    private TextArea description;

    /**
     * Initializes a new {@code PopupUpdateProduct} window.
     *
     * @param mainWindow The {@code MainWindow} that this component resides on.
     */
    public PopupUpdateProduct(MainWindow mainWindow) {
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
        name.setText(escapeCharacter(product.getName().toString()));
        category.setText(escapeCharacter(product.getCategory().toString()));
        price.setText(product.getPrice().toString());
        discountRate.setText(product.getDiscountRate().toString());
        discountStart.setText(product.getDiscountStart().toString());
        description.setText(escapeCharacter(product.getDescription().toString()));
    }

    private boolean isValid() {
        if (name.getText().matches(ILLEGAL_REGEX)
                || category.getText().matches(ILLEGAL_REGEX)
                || price.getText().matches(ILLEGAL_REGEX)
                || discountRate.getText().matches(ILLEGAL_REGEX)
                || discountStart.getText().matches(ILLEGAL_REGEX)
                || description.getText().matches(ILLEGAL_REGEX)) {
            setFeedbackToUser(MESSAGE_CONSTRAINTS);
            return false;
        }
        return true;
    }

    @FXML
    private void handleUpdateProduct() {
        replaceLineBreak(description);
        if (!isValid()) {
            return;
        }
        String commandText = UpdateCommand.COMMAND_WORD
                + " " + index
                + " " + CliSyntax.PREFIX_NAME.getPrefix()
                + name.getText()
                + " " + CliSyntax.PREFIX_CATEGORY.getPrefix()
                + category.getText()
                + " " + CliSyntax.PREFIX_PRICE.getPrefix()
                + price.getText()
                + " " + CliSyntax.PREFIX_DISCOUNT_RATE.getPrefix()
                + discountRate.getText()
                + " " + CliSyntax.PREFIX_DISCOUNT_START.getPrefix()
                + discountStart.getText()
                + " " + CliSyntax.PREFIX_DESCRIPTION.getPrefix()
                + description.getText();

        execute(commandText);
    }

}
