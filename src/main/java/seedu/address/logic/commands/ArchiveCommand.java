package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import seedu.address.commons.util.FileUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Archives the address book.
 */
public class ArchiveCommand extends Command {

    public static final String COMMAND_WORD = "archive";
    public static final String MESSAGE_SUCCESS = "Tracey has been archived!";
    public static final String MESSAGE_FAILURE = "Unable to archive the address book";
    public static final SimpleDateFormat ARCHIVE_DIRECTORY_NAME_FORMAT = new SimpleDateFormat("ddMMyy");
    public static final SimpleDateFormat ARCHIVE_FILE_NAME_FORMAT = new SimpleDateFormat("ddMMyy_HHmmssSSS");

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        archiveFile(model);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    /**
     * Initialises the path for the archived address book file. This method will first get the directory path of the
     * address book file path e.g. "[ROOT]/data" if the address book file path is "[ROOT]/data/addressbook.json".
     *  A path to the archived address book file is then returned using this directory path as the reference path.
     * @param addressBookFilePath path for address book file
     * @param archiveDirectoryName name for the archive directory
     * @param archiveFileName name for the archived file
     * @return path for the archived address book file
     */
    private Path initArchiveFilePath(Path addressBookFilePath, String archiveDirectoryName, String archiveFileName) {
        String splitRegex = Pattern.quote(System.getProperty("file.separator")); // "\" is the regex in this case
        StringBuilder addressBookFileDirectory = new StringBuilder();
        String[] splitAddressBookFilePath = addressBookFilePath.toString().split(splitRegex);

        // to get the directory path of the address book file path
        for (int i = 0; i < splitAddressBookFilePath.length - 1; i++) {
            addressBookFileDirectory.append(splitAddressBookFilePath[i]);
            if (i + 1 != splitAddressBookFilePath.length) {
                addressBookFileDirectory.append("\\");
            }
        }

        // path for the archived address book file
        Path fullArchiveFilePath = Paths.get(addressBookFileDirectory.toString(), COMMAND_WORD, archiveDirectoryName,
                archiveFileName);
        return fullArchiveFilePath;
    }

    /**
     * Initialises the name for the directory for the archived file using the current date.
     * @return Name for the directory for the archived file
     */
    private String initArchiveDirectoryName() {
        Date date = new Date();
        return ARCHIVE_DIRECTORY_NAME_FORMAT.format(date);
    }

    /**
     * Initialises the name for the archived file using the current date and time.
     * @return Name for the archived file
     */
    private String initArchiveFileName() {
        Date date = new Date();
        return ARCHIVE_FILE_NAME_FORMAT.format(date) + ".json";
    }

    /**
     * Creates the archived address book file in the specified path and copies the information in the address book
     * to the archived address book.
     * @param model Address book model.
     * @throws CommandException if the file or directory cannot be created, or if the file cannot be copied.
     */
    private void archiveFile(Model model) throws CommandException {
        Path addressBookFilePath = model.getAddressBookFilePath();
        String archiveDirectoryName = initArchiveDirectoryName();
        String archiveFileName = initArchiveFileName();
        Path archiveFilePath = initArchiveFilePath(addressBookFilePath, archiveDirectoryName, archiveFileName);
        try {
            FileUtil.createIfMissing(archiveFilePath);
            Files.copy(addressBookFilePath, archiveFilePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException error) {
            throw new CommandException(MESSAGE_FAILURE);
        }
    }
}
