package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.address.logic.commands.ImportCsvCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ImportCsvCommandParser implements Parser<ImportCsvCommand> {

    public static final String NO_PATH_MENTIONED = "Path to read the CSV file not specified";

    @Override
    public ImportCsvCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args);

        String preamble = argMultimap.getPreamble().trim();

        if (preamble.isEmpty()) {
            throw new ParseException(NO_PATH_MENTIONED);
        }

        Path path = Paths.get(preamble);
        return new ImportCsvCommand(path);
    }
}
