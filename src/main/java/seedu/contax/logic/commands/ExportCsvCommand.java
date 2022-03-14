package seedu.contax.logic.commands;

import seedu.contax.model.Model;
import seedu.contax.storage.CsvManager;

public class ExportCsvCommand extends Command {
    public static final String COMMAND_WORD = "exportcsv";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exports contacts to CSV file"
            + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_SUCCESS = "Exported successfully";
    public static final String MESSAGE_FAILURE = "Export failed. Please try again";

    @Override
    public CommandResult execute(Model model) {
        CsvManager csvManager = new CsvManager(model);
        boolean exportCsv = csvManager.exportCsv();
        if (exportCsv) {
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            return new CommandResult(MESSAGE_FAILURE);
        }
    }
}
