package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user search input.
 */
public class SearchBar extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "SearchBar.fxml";

    @FXML
    private TextField searchTextField;

    /**
     * Creates a {@code SearchBar}.
     */
    public SearchBar() {
        super(FXML);
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        searchTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleSearchEntered() {
        String commandText = searchTextField.getText();
    }

    /**
     * Sets the search bar style to use the default style.
     */
    private void setStyleToDefault() {
        searchTextField.getStyleClass().remove(ERROR_STYLE_CLASS);
    }
}
