package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;

import seedu.address.model.Model;

public class ImportFromCsvCommand extends Command {

    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_SUCCESS = "Imported CSV";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        HashMap<String, Boolean> settings = new HashMap<>();
        settings.put("importFromCsv", true);
        return new CommandResult(MESSAGE_SUCCESS, settings);
    }
}
