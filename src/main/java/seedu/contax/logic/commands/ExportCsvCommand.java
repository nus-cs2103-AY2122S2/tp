package seedu.contax.logic.commands;

import seedu.contax.model.Model;
import seedu.contax.storage.CsvManager;

/**
 * Exports current address book manually to data/addressbook.csv
 */
public class ExportCsvCommand extends Command {
    public static final String COMMAND_WORD = "exportcsv";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exports contacts to CSV file"
            + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_SUCCESS = "Exported successfully";
    public static final String MESSAGE_FAILURE = "Export failed. Please try again";
    private static final String EXPORT_FILEPATH = "data/addressbook.csv";

    private String filePath;

    public ExportCsvCommand() {
        this.filePath = EXPORT_FILEPATH;
    }

    public ExportCsvCommand(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public CommandResult execute(Model model) {
        CsvManager csvManager = new CsvManager(model);
        boolean exportCsv = csvManager.exportCsv(filePath);
        if (exportCsv) {
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            return new CommandResult(MESSAGE_FAILURE);
        }
    }
}
