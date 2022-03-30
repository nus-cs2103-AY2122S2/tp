package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;

import seedu.address.model.Model;

/**
 * A command to indicate that the insurance packages should be displayed.
 */
public class ListPackageCommand extends Command {

    public static final String COMMAND_WORD = "listp";

    public static final String MESSAGE_SUCCESS = "Listed all packages";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        HashMap<String, Boolean> settings = new HashMap<>();
        settings.put("showPackages", true);
        return new CommandResult(MESSAGE_SUCCESS, settings);
    }
}
