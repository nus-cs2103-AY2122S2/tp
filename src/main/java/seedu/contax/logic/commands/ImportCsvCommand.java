package seedu.contax.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_FILE;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_TAG;

import java.io.IOException;

import seedu.contax.logic.commands.exceptions.CommandException;
import seedu.contax.model.IndexedCsvFile;
import seedu.contax.model.Model;
import seedu.contax.storage.CsvManager;

public class ImportCsvCommand extends Command {
    public static final String COMMAND_WORD = "importcsv";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Imports contacts from CSV file"
            + "Parameters: "
            + PREFIX_FILE + "FILEPATH "
            + "[" + PREFIX_NAME + "NAME_POSITION]"
            + "[" + PREFIX_PHONE + "PHONE_POSITION]"
            + "[" + PREFIX_EMAIL + "EMAIL_POSITION]"
            + "[" + PREFIX_ADDRESS + "ADDRESS_POSITION]"
            + "[" + PREFIX_TAG + "TAG_POSITION]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_FILE + "/data/file.csv";
    public static final String MESSAGE_NO_FILE_FOUND = "File not found: %s";
    public static final String MESSAGE_SUCCESS = "Imported successfully";
    public static final String MESSAGE_SKIPPED_LINES = "Lines skipped (either bad formatting or duplicates): %s";

    private final IndexedCsvFile toImport;

    /**
     * Creates an ImportCsvCommand to import with specified {@code params}
     */
    public ImportCsvCommand(IndexedCsvFile params) {
        requireNonNull(params);
        this.toImport = params;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        //process file
        try {
            CsvManager manager = new CsvManager(model);
            return manager.importCsv(toImport);
        } catch (IOException e) {
            throw new CommandException(String.format(MESSAGE_NO_FILE_FOUND, toImport.getFilePath()));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ImportCsvCommand // instanceof handles nulls
                && toImport.equals(((ImportCsvCommand) other).toImport));
    }
}
