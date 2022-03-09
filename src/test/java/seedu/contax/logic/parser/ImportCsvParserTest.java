package seedu.contax.logic.parser;

import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.contax.logic.commands.CommandTestUtil.COMMAND_CSV_ADDRESSPOSITION;
import static seedu.contax.logic.commands.CommandTestUtil.COMMAND_CSV_ADDRESSPOSITION_CLASH;
import static seedu.contax.logic.commands.CommandTestUtil.COMMAND_CSV_EMAILPOSITION;
import static seedu.contax.logic.commands.CommandTestUtil.COMMAND_CSV_EMAILPOSITION_CLASH;
import static seedu.contax.logic.commands.CommandTestUtil.COMMAND_CSV_INVALID_ADDRESSPOSITION;
import static seedu.contax.logic.commands.CommandTestUtil.COMMAND_CSV_INVALID_EMAILPOSITION;
import static seedu.contax.logic.commands.CommandTestUtil.COMMAND_CSV_INVALID_NAMEPOSITION;
import static seedu.contax.logic.commands.CommandTestUtil.COMMAND_CSV_INVALID_PHONEPOSITION;
import static seedu.contax.logic.commands.CommandTestUtil.COMMAND_CSV_INVALID_TAGPOSITION;
import static seedu.contax.logic.commands.CommandTestUtil.COMMAND_CSV_NAMEPOSITION;
import static seedu.contax.logic.commands.CommandTestUtil.COMMAND_CSV_NAMEPOSITION_CLASH;
import static seedu.contax.logic.commands.CommandTestUtil.COMMAND_CSV_NEGATIVE_ADDRESSPOSITION;
import static seedu.contax.logic.commands.CommandTestUtil.COMMAND_CSV_NEGATIVE_EMAILPOSITION;
import static seedu.contax.logic.commands.CommandTestUtil.COMMAND_CSV_NEGATIVE_NAMEPOSITION;
import static seedu.contax.logic.commands.CommandTestUtil.COMMAND_CSV_NEGATIVE_PHONEPOSITION;
import static seedu.contax.logic.commands.CommandTestUtil.COMMAND_CSV_NEGATIVE_TAGPOSITION;
import static seedu.contax.logic.commands.CommandTestUtil.COMMAND_CSV_PHONEPOSITION;
import static seedu.contax.logic.commands.CommandTestUtil.COMMAND_CSV_PHONEPOSITION_CLASH;
import static seedu.contax.logic.commands.CommandTestUtil.COMMAND_CSV_TAGPOSITION;
import static seedu.contax.logic.commands.CommandTestUtil.COMMAND_CSV_TAGPOSITION_CLASH;
import static seedu.contax.logic.commands.CommandTestUtil.COMMAND_INVALID_BAD_EXTENSION_FILEPATH;
import static seedu.contax.logic.commands.CommandTestUtil.COMMAND_INVALID_NO_EXTENSION_FILEPATH;
import static seedu.contax.logic.commands.CommandTestUtil.COMMAND_VALID_CSV_FILEPATH;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_FILE;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.contax.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.contax.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.contax.logic.commands.ImportCsvCommand;
import seedu.contax.model.IndexedCsvFile;
import seedu.contax.testutil.ImportCsvObjectBuilder;

public class ImportCsvParserTest {
    private ImportCsvParser parser = new ImportCsvParser();

    @Test
    public void parse_onlyFilePathPresent_success() {
        //Default positions for columns
        IndexedCsvFile expectedIndexedCsvFileObject = new ImportCsvObjectBuilder().build();

        assertParseSuccess(parser, COMMAND_VALID_CSV_FILEPATH, new ImportCsvCommand(expectedIndexedCsvFileObject));
    }

    @Test
    public void parse_customParams_success() {
        //All columns shifted by 1
        IndexedCsvFile expectedIndexedCsvFileObject = new ImportCsvObjectBuilder(
                ImportCsvObjectBuilder.CUSTOM_COLUMNS_CSV_FILEPATH,
                2, 3, 4, 5, 6).build();

        assertParseSuccess(parser, " " + PREFIX_FILE
                + ImportCsvObjectBuilder.CUSTOM_COLUMNS_CSV_FILEPATH + " "
                + PREFIX_NAME + "2 " + PREFIX_PHONE + "3 " + PREFIX_EMAIL + "4 "
                + PREFIX_ADDRESS + "5 " + PREFIX_TAG + "6", new ImportCsvCommand(expectedIndexedCsvFileObject));
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
        assertParseFailure(parser, COMMAND_INVALID_BAD_EXTENSION_FILEPATH, IndexedCsvFile.FILE_PATH_CONSTRAINTS);
        //no extension
        assertParseFailure(parser, COMMAND_INVALID_NO_EXTENSION_FILEPATH, IndexedCsvFile.FILE_PATH_CONSTRAINTS);

        //invalid position numbers
        //letters as position numbers
        assertParseFailure(parser, COMMAND_VALID_CSV_FILEPATH + COMMAND_CSV_INVALID_NAMEPOSITION,
                IndexedCsvFile.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, COMMAND_VALID_CSV_FILEPATH + COMMAND_CSV_INVALID_PHONEPOSITION,
                IndexedCsvFile.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, COMMAND_VALID_CSV_FILEPATH + COMMAND_CSV_INVALID_EMAILPOSITION,
                IndexedCsvFile.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, COMMAND_VALID_CSV_FILEPATH + COMMAND_CSV_INVALID_ADDRESSPOSITION,
                IndexedCsvFile.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, COMMAND_VALID_CSV_FILEPATH + COMMAND_CSV_INVALID_TAGPOSITION,
                IndexedCsvFile.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, COMMAND_VALID_CSV_FILEPATH + COMMAND_CSV_INVALID_NAMEPOSITION
                        + COMMAND_CSV_INVALID_PHONEPOSITION + COMMAND_CSV_INVALID_EMAILPOSITION
                        + COMMAND_CSV_INVALID_ADDRESSPOSITION + COMMAND_CSV_INVALID_TAGPOSITION,
                IndexedCsvFile.MESSAGE_CONSTRAINTS);

        //negative position numbers
        assertParseFailure(parser, COMMAND_VALID_CSV_FILEPATH + COMMAND_CSV_NEGATIVE_NAMEPOSITION,
                IndexedCsvFile.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, COMMAND_VALID_CSV_FILEPATH + COMMAND_CSV_NEGATIVE_PHONEPOSITION,
                IndexedCsvFile.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, COMMAND_VALID_CSV_FILEPATH + COMMAND_CSV_NEGATIVE_EMAILPOSITION,
                IndexedCsvFile.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, COMMAND_VALID_CSV_FILEPATH + COMMAND_CSV_NEGATIVE_ADDRESSPOSITION,
                IndexedCsvFile.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, COMMAND_VALID_CSV_FILEPATH + COMMAND_CSV_NEGATIVE_TAGPOSITION,
                IndexedCsvFile.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, COMMAND_VALID_CSV_FILEPATH + COMMAND_CSV_NEGATIVE_NAMEPOSITION
                        + COMMAND_CSV_NEGATIVE_PHONEPOSITION + COMMAND_CSV_NEGATIVE_EMAILPOSITION
                        + COMMAND_CSV_NEGATIVE_ADDRESSPOSITION + COMMAND_CSV_NEGATIVE_TAGPOSITION,
                IndexedCsvFile.MESSAGE_CONSTRAINTS);

        //clashing position numbers
        assertParseFailure(parser, COMMAND_VALID_CSV_FILEPATH + COMMAND_CSV_NAMEPOSITION_CLASH,
                IndexedCsvFile.MESSAGE_CLASHING_POSITIONS);
        assertParseFailure(parser, COMMAND_VALID_CSV_FILEPATH + COMMAND_CSV_PHONEPOSITION_CLASH,
                IndexedCsvFile.MESSAGE_CLASHING_POSITIONS);
        assertParseFailure(parser, COMMAND_VALID_CSV_FILEPATH + COMMAND_CSV_EMAILPOSITION_CLASH,
                IndexedCsvFile.MESSAGE_CLASHING_POSITIONS);
        assertParseFailure(parser, COMMAND_VALID_CSV_FILEPATH + COMMAND_CSV_ADDRESSPOSITION_CLASH,
                IndexedCsvFile.MESSAGE_CLASHING_POSITIONS);
        assertParseFailure(parser, COMMAND_VALID_CSV_FILEPATH + COMMAND_CSV_TAGPOSITION_CLASH,
                IndexedCsvFile.MESSAGE_CLASHING_POSITIONS);
    }
}
