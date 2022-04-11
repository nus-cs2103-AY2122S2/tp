package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.util.FileUtil.isValidPath;

import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ExportCommandParser implements Parser<ExportCommand> {
    public static final String INVALID_FILE_NAME = "File name should only contain alphanumeric characters";
    public static final String INVALID_FILE_PATH = "That is not a valid file path\n" + INVALID_FILE_NAME;
    public static final String WRONG_FILE_TYPE = "Please include a .csv file extension with your file name.";

    /**
     * Parses the user input of the path to target csv file to export applicant data into.
     *
     * @param args String path to the csv file.
     * @return ExportCommand with path to csv file.
     */
    public ExportCommand parse(String args) throws ParseException {

        //check whether valid path input given by the user
        String strCsvFilePath = args.strip();
        checkFilePath(strCsvFilePath);
        Path csvFilePath = Paths.get(strCsvFilePath);

        return new ExportCommand(csvFilePath);
    }

    /**
     * Checks if specified filepath to csv file is not empty string and a valid csv filepath.
     *
     * @param path filepath to target csv file.
     * @throws ParseException If filepath is "" or not a valid filepath or not a .csv file.
     */
    private void checkFilePath(String path) throws ParseException {
        if (path.equals("")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExportCommand.MESSAGE_USAGE));
        } else if (!isValidPath(path)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, INVALID_FILE_PATH));
        } else if (!isValidCsvFile(path)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, WRONG_FILE_TYPE));
        }
    }

    /**
     * Checks if the given file path leads to a file with .csv extension.
     *
     * @param path String filepath to target file.
     * @return boolean true or false.
     */
    private boolean isValidCsvFile(String path) {
        return path.endsWith(".csv");
    }
}
