package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.ui.UiManager;

/**
 * Switch the current theme to either light or dark.
 */
public class SwitchThemeCommand extends Command {
    public static final String COMMAND_WORD = "switch_theme";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Switch the current theme. "
            + "Parameters: "
            + "THEME (either dark or light).\n"
            + "Example: " + COMMAND_WORD + " "
            + "light";

    public static final String MESSAGE_SUCCESS = "Switched theme to %1$s";

    private final Stage stage = UiManager.getMainWindow().getPrimaryStage();

    private final String theme;

    public SwitchThemeCommand(String theme) {
        this.theme = theme;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (theme.equals("dark")) {
            switchDarkTheme();
        } else {
            switchLightTheme();
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, theme));
    }

    /**
     * Switch to light theme.
     */
    @FXML
    public void switchLightTheme() {
        String lightThemeUrl = requireNonNull(getClass().getResource("/view/LightTheme.css"))
                .toExternalForm();
        this.stage.getScene().getStylesheets().clear();
        this.stage.getScene().getStylesheets().add(lightThemeUrl);
    }

    /**
     * Switch to dark theme.
     */
    @FXML
    public void switchDarkTheme() {
        String darkThemeUrl = requireNonNull(getClass().getResource("/view/DarkTheme.css"))
                .toExternalForm();
        this.stage.getScene().getStylesheets().clear();
        this.stage.getScene().getStylesheets().add(darkThemeUrl);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SwitchThemeCommand // instanceof handles nulls
                && theme.equals(((SwitchThemeCommand) other).theme)); // state check
    }
}
