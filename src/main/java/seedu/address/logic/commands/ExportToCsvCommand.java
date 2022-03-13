package seedu.address.logic.commands;

import java.util.HashMap;

import seedu.address.model.Model;

public class ExportToCsvCommand extends Command {

    public static final String COMMAND_WORD = "export";

    public static final String MESSAGE_SUCCESS = "Exported to CSV";


    @Override
    public CommandResult execute(Model model) {
        HashMap<String, Boolean> settings = new HashMap<>();
        settings.put("exportToCsv", true);
        return new CommandResult(MESSAGE_SUCCESS, settings);
    }
}
