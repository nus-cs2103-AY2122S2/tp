package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ImportCsvCommand;

public class ImportCsvCommandParserTest {
    private final ImportCsvCommandParser importCsvCommandParser = new ImportCsvCommandParser();

    @Test
    public void parsePathTest_success() {
        String filePath = "data/sample-data.csv";
        ImportCsvCommand importCsvCommand = new ImportCsvCommand(Paths.get(filePath));
        assertParseSuccess(importCsvCommandParser, filePath, importCsvCommand);
    }

    @Test
    public void parsePathTest_exceptionThrown() {
        assertParseFailure(importCsvCommandParser, "", ImportCsvCommandParser.NO_PATH_MENTIONED);
    }
}
