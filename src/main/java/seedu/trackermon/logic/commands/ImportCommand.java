package seedu.trackermon.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.trackermon.model.Model;
import seedu.trackermon.storage.JsonFileManager;


/**
 * Lists all shows in Trackermon to the user.
 */
public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_SUCCESS = "Imported data file.";
    public static final String MESSAGE_FAIL = "Data file import failed.";
    public static final String MESSAGE_ABORT = "Import data aborted.";
    public static final String MESSAGE_FAIL_WRONG_FILETYPE = "Incorrect file type selected. "
            + "Only JSON(.json) files can be imported into Trackermon!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        JsonFileManager jfm = new JsonFileManager();

        int result = jfm.importFile(model.getShowListFilePath());

        if (result == JsonFileManager.SUCCESS) {
            return new CommandResult(MESSAGE_SUCCESS, false, false, true);
        } else if (result == JsonFileManager.CANCEL) {
            return new CommandResult(MESSAGE_ABORT);
        }

        return new CommandResult(MESSAGE_FAIL);
    }
}
