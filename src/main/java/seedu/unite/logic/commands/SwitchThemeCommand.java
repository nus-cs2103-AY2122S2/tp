package seedu.unite.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.unite.logic.commands.exceptions.CommandException;
import seedu.unite.model.Model;
import seedu.unite.ui.theme.Theme;

/**
 * Switch the current theme to either light or dark.
 */
public class SwitchThemeCommand extends Command {
    public static final String COMMAND_WORD = "theme";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Switch the current theme. "
            + "Parameters: "
            + "THEME (either dark or light).\n"
            + "Example: " + COMMAND_WORD + " "
            + "light";

    public static final String MESSAGE_SWITCH_THEME_SUCCESS = "Switched to %1$s";

    private static final Logger logger = Logger.getLogger(SwitchThemeCommand.class.getName());

    private final Theme theme;

    public SwitchThemeCommand(Theme theme) {
        this.theme = theme;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        logger.log(Level.INFO, this.theme.toString());

        model.switchTheme(theme);
        return new CommandResult(String.format(MESSAGE_SWITCH_THEME_SUCCESS, theme));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || other instanceof SwitchThemeCommand && ((SwitchThemeCommand) other).theme.equals(theme);
    }
}
