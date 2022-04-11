package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;

import seedu.address.model.Model;

/**
 * A command to indicate that the window to export to CSV files should be open.
 */
public class ExportToCsvCommand extends Command {

    public static final String COMMAND_WORD = "export";

    public static final String MESSAGE_SUCCESS = "Exported to CSV";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        HashMap<String, Boolean> settings = new HashMap<>();
        settings.put("exportToCsv", true);
        return new CommandResult(MESSAGE_SUCCESS, settings);
    }
}
