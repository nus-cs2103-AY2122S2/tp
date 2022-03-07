package seedu.contax.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import seedu.contax.logic.commands.exceptions.CommandException;
import seedu.contax.model.ImportCsv;
import seedu.contax.model.Model;

public class ImportCsvCommand extends Command {
    public static final String COMMAND_WORD = "importcsv";
    public static final String MESSAGE_USAGE = "to be entered";
    public static final String MESSAGE_NO_FILE_FOUND = "file not found";


    private final ImportCsv toImport;

    /**
     * Creates an ImportCsvCommand to import with specified {@code params}
     */
    public ImportCsvCommand(ImportCsv params) {
        requireNonNull(params);
        this.toImport = params;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        //firstly process file
        try {
            String line = "";
            BufferedReader importedCsv = new BufferedReader(new FileReader(toImport.getFilePath()));
            while ((line = importedCsv.readLine()) != null) {
                String[] importedPerson = line.split(",");
            }
            return new CommandResult("imported success");
        } catch (IOException e) {
            throw new CommandException(MESSAGE_NO_FILE_FOUND);
        }

        //use params to read the file

        //load the content into the current list
    }
}
