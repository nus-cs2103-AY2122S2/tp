package seedu.address.logic.commands;
import static seedu.address.logic.parser.CliSyntax.PREFIX_THEME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class ThemeCommand extends Command {

    public static final String COMMAND_WORD = "theme";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Changes the theme of MyGM.\n"
            + "Parameters: " + PREFIX_THEME + "THEME\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_THEME + "light";

    public static final String MESSAGE_EDIT_THEME_DARK = "Changed to dark mode successfully";

    public static final String MESSAGE_EDIT_THEME_LIGHT = "Changed to light mode successfully";

    public static final String MESSAGE_EDIT_THEME_INVALID =
            "Invalid theme. Currently only supports dark and light mode.";

    private final boolean toDark;

    private final boolean toLight;

    /**
     * Creates a new ThemeCommand.
     *
     * @param toDark Whether the theme is to become dark.
     * @param toLight Whether the theme is to become light.
     */
    public ThemeCommand(boolean toDark, boolean toLight) {
        this.toDark = toDark;
        this.toLight = toLight;
    }

    /**
     * Executes the command and returns the feedback.
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult
     * @throws CommandException
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (toDark) {
            return new CommandResult(MESSAGE_EDIT_THEME_DARK, false, false, true, false);
        } else {
            return new CommandResult(MESSAGE_EDIT_THEME_LIGHT, false, false, false, true);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ThemeCommand)) {
            return false;
        }

        ThemeCommand t = (ThemeCommand) other;

        return this.toDark == t.toDark && this.toLight == t.toLight;
    }
}
