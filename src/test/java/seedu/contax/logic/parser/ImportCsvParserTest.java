package seedu.contax.logic.parser;

import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.contax.logic.commands.CommandTestUtil.COMMAND_CSV_ADDRESSPOSITION;
import static seedu.contax.logic.commands.CommandTestUtil.COMMAND_CSV_EMAILPOSITION;
import static seedu.contax.logic.commands.CommandTestUtil.COMMAND_CSV_INVALID_ADDRESSPOSITION;
import static seedu.contax.logic.commands.CommandTestUtil.COMMAND_CSV_INVALID_EMAILPOSITION;
import static seedu.contax.logic.commands.CommandTestUtil.COMMAND_CSV_INVALID_NAMEPOSITION;
import static seedu.contax.logic.commands.CommandTestUtil.COMMAND_CSV_INVALID_PHONEPOSITION;
import static seedu.contax.logic.commands.CommandTestUtil.COMMAND_CSV_INVALID_TAGPOSITION;
import static seedu.contax.logic.commands.CommandTestUtil.COMMAND_CSV_NAMEPOSITION;
import static seedu.contax.logic.commands.CommandTestUtil.COMMAND_CSV_NEGATIVE_ADDRESSPOSITION;
import static seedu.contax.logic.commands.CommandTestUtil.COMMAND_CSV_NEGATIVE_EMAILPOSITION;
import static seedu.contax.logic.commands.CommandTestUtil.COMMAND_CSV_NEGATIVE_NAMEPOSITION;
import static seedu.contax.logic.commands.CommandTestUtil.COMMAND_CSV_NEGATIVE_PHONEPOSITION;
import static seedu.contax.logic.commands.CommandTestUtil.COMMAND_CSV_NEGATIVE_TAGPOSITION;
import static seedu.contax.logic.commands.CommandTestUtil.COMMAND_CSV_PHONEPOSITION;
import static seedu.contax.logic.commands.CommandTestUtil.COMMAND_CSV_TAGPOSITION;
import static seedu.contax.logic.commands.CommandTestUtil.COMMAND_INVALID_BAD_EXTENSION_FILEPATH;
import static seedu.contax.logic.commands.CommandTestUtil.COMMAND_INVALID_NO_EXTENSION_FILEPATH;
import static seedu.contax.logic.commands.CommandTestUtil.COMMAND_VALID_CSV_FILEPATH;
import static seedu.contax.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.contax.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.contax.logic.commands.ImportCsvCommand;
import seedu.contax.model.ImportCsv;
import seedu.contax.testutil.ImportCsvObjectBuilder;

public class ImportCsvParserTest {
    private ImportCsvParser parser = new ImportCsvParser();

    @Test
    public void parse_onlyFilePathPresent_success() {
        //default positions for columns
        ImportCsv expectedImportCsvObject = new ImportCsvObjectBuilder().build();

        assertParseSuccess(parser, COMMAND_VALID_CSV_FILEPATH, new ImportCsvCommand(expectedImportCsvObject));
    }

    @Test
    public void parse_customParams_success() {
        //TO-DO once the custom thing is done
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedError = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCsvCommand.MESSAGE_USAGE);

        //missing filepath prefix
        assertParseFailure(parser, " ", expectedError);

        //got other prefixes but no have filepath
        assertParseFailure(parser, COMMAND_CSV_NAMEPOSITION + COMMAND_CSV_PHONEPOSITION
                + COMMAND_CSV_EMAILPOSITION + COMMAND_CSV_ADDRESSPOSITION + COMMAND_CSV_TAGPOSITION, expectedError);
    }

    @Test
    public void parse_invalidFields_failure() {
        //invalid filename
        //bad extension
        assertParseFailure(parser, COMMAND_INVALID_BAD_EXTENSION_FILEPATH, ImportCsv.FILE_PATH_CONSTRAINTS);
        //no extension
        assertParseFailure(parser, COMMAND_INVALID_NO_EXTENSION_FILEPATH, ImportCsv.FILE_PATH_CONSTRAINTS);

        //invalid position numbers
        //letters as position numbers
        assertParseFailure(parser, COMMAND_VALID_CSV_FILEPATH + COMMAND_CSV_INVALID_NAMEPOSITION,
                ImportCsv.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, COMMAND_VALID_CSV_FILEPATH + COMMAND_CSV_INVALID_PHONEPOSITION,
                ImportCsv.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, COMMAND_VALID_CSV_FILEPATH + COMMAND_CSV_INVALID_EMAILPOSITION,
                ImportCsv.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, COMMAND_VALID_CSV_FILEPATH + COMMAND_CSV_INVALID_ADDRESSPOSITION,
                ImportCsv.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, COMMAND_VALID_CSV_FILEPATH + COMMAND_CSV_INVALID_TAGPOSITION,
                ImportCsv.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, COMMAND_VALID_CSV_FILEPATH + COMMAND_CSV_INVALID_NAMEPOSITION
                        + COMMAND_CSV_INVALID_PHONEPOSITION + COMMAND_CSV_INVALID_EMAILPOSITION
                        + COMMAND_CSV_INVALID_ADDRESSPOSITION + COMMAND_CSV_INVALID_TAGPOSITION,
                ImportCsv.MESSAGE_CONSTRAINTS);

        //negative position numbers
        assertParseFailure(parser, COMMAND_VALID_CSV_FILEPATH + COMMAND_CSV_NEGATIVE_NAMEPOSITION,
                ImportCsv.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, COMMAND_VALID_CSV_FILEPATH + COMMAND_CSV_NEGATIVE_PHONEPOSITION,
                ImportCsv.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, COMMAND_VALID_CSV_FILEPATH + COMMAND_CSV_NEGATIVE_EMAILPOSITION,
                ImportCsv.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, COMMAND_VALID_CSV_FILEPATH + COMMAND_CSV_NEGATIVE_ADDRESSPOSITION,
                ImportCsv.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, COMMAND_VALID_CSV_FILEPATH + COMMAND_CSV_NEGATIVE_TAGPOSITION,
                ImportCsv.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, COMMAND_VALID_CSV_FILEPATH + COMMAND_CSV_NEGATIVE_NAMEPOSITION
                        + COMMAND_CSV_NEGATIVE_PHONEPOSITION + COMMAND_CSV_NEGATIVE_EMAILPOSITION
                        + COMMAND_CSV_NEGATIVE_ADDRESSPOSITION + COMMAND_CSV_NEGATIVE_TAGPOSITION,
                ImportCsv.MESSAGE_CONSTRAINTS);

        //clashing position numbers
    }

}
